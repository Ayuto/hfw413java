package parser;
import expressions.RegularExpression;
import expressions.RegularExpressionFacade;


public class RegularExpressionParser {

	public static RegularExpressionParser create(final String text) {
		return new RegularExpressionParser(text);
	}

	private StringBuffer text;
	private final RegularExpressionFacade facade;

	public RegularExpressionParser(final String text) {
		this.start(text);
		this.facade = RegularExpressionFacade.create();
	}

	private void start(final String text) {
		this.text = new StringBuffer(text);
		this.skipWhiteSpace();
	}
	private StringBuffer getText() {
		return this.text;
	}
	private RegularExpression parseExpression() throws RegularExpressionParserException {
		final RegularExpression result = this.parseUndecoratedExpression();
		if (this.startsWithIteratorSymbol()) {
			this.facade.setIterated(result);
			this.skipIteratedSymbol();
			return result ;
		}
		if (this.startsWithOptionalSymbol()) {
			this.facade.setOptional(result);
			this.skipOptionalSymbol();
			return result ;
		}
		if (this.startsWithOptionalAndIteratedSymbol()) {
			this.facade.setIterated(result);
			this.facade.setOptional(result);			
			this.skipOptionalAndIteratedSymbol();
			return result ;
		}
		return result ;
	}

	private RegularExpression parseUndecoratedExpression() throws RegularExpressionParserException {
		if (this.isAtEnd()) throw RegularExpressionParserTooShortException.create();
		if (this.startsWithSequenceOpenBracket()) return parseSequence();
		if (this.startsWithChoiceOpenBracket()) return parseChoice();
		return parseBaseExpression();
	}
	private boolean startsWithIteratorSymbol() throws RegularExpressionParserTooShortException {
		return !this.isAtEnd() && this.getCurrent(false) == Constants.IteratedSymbol;
	}
	private boolean startsWithOptionalSymbol() throws RegularExpressionParserTooShortException {
		return !this.isAtEnd() && this.getCurrent(false) == Constants.OptionalSymbol;
	}
	private boolean startsWithOptionalAndIteratedSymbol() throws RegularExpressionParserTooShortException {
		return !this.isAtEnd() && this.getCurrent(false) == Constants.OptionalAndIteratedSymbol;
	}
	private boolean startsWithChoiceOpenBracket() throws RegularExpressionParserTooShortException {
		return !this.isAtEnd() && this.getCurrent(false) == Constants.ChoiceOpenBracket;
	}
	private boolean startsWithChoiceCloseBracket() throws RegularExpressionParserTooShortException {
		return !this.isAtEnd() && this.getCurrent(false) == Constants.ChoiceCloseBracket;
	}
	private boolean startsWithSequenceOpenBracket() throws RegularExpressionParserTooShortException {
		return !this.isAtEnd() && this.getCurrent(false) == Constants.SequenceOpenBracket;
	}
	private boolean startsWithSequenceCloseBracket() throws RegularExpressionParserTooShortException {
		return !this.isAtEnd() && this.getCurrent(false) == Constants.SequenceCloseBracket;
	}
	private RegularExpression parseChoice() throws RegularExpressionParserException {
		this.skipChoiceOpenBracket();
		final RegularExpression result = this.getFacade().createChoiceExpression();
		while (!this.startsWithChoiceCloseBracket()){
			final RegularExpression current = this.parseExpression();
			this.getFacade().add(result,current);
		}
		this.skipChoiceCloseBracket();
		return result;
	}

	private void skipIteratedSymbol() throws RegularExpressionParserException {
		if (this.startsWithIteratorSymbol())this.skip();
		else throw RegularExpressionParserIteratedSymbolExpectedException.create();
	}
	private void skipOptionalSymbol() throws RegularExpressionParserException {
		if (this.startsWithOptionalSymbol())this.skip();
		else throw RegularExpressionParserOptionalSymbolExpectedException.create();
	}
	private void skipOptionalAndIteratedSymbol() throws RegularExpressionParserException {
		if (this.startsWithOptionalAndIteratedSymbol())this.skip();
		else throw RegularExpressionParserOptionalAndIteratedSymbolExpectedException.create();
	}
	private void skipChoiceOpenBracket() throws RegularExpressionParserException {
		if (this.startsWithChoiceOpenBracket())this.skip();
		else throw RegularExpressionParserChoiceOpenSymbolExpectedException.create();
	}
	private void skipChoiceCloseBracket() throws RegularExpressionParserException {
		if (this.startsWithChoiceCloseBracket())this.skip();
		else throw RegularExpressionParserChoiceCloseSymbolExpectedException.create();
	}
	private void skipSequenceOpenBracket() throws RegularExpressionParserException {
		if (this.startsWithSequenceOpenBracket())this.skip();
		else throw RegularExpressionParserSequenceOpenSymbolExpectedException.create();
	}
	private void skipSequenceCloseBracket() throws RegularExpressionParserException {
		if (this.startsWithSequenceCloseBracket())this.skip();
		else throw RegularExpressionParserSequenceCloseSymbolExpectedException.create();
	}

	private RegularExpression parseSequence() throws RegularExpressionParserException {
		this.skipSequenceOpenBracket();
		if (this.startsWithSequenceCloseBracket()) throw RegularExpressionParserSequenceIsEmptyException.create();
		final RegularExpression result = this.getFacade().createSequenceExpression();
		while (!this.startsWithSequenceCloseBracket()){
			final RegularExpression current = this.parseExpression();
			this.getFacade().add(result,current);
		}
		this.skipSequenceCloseBracket();
		return result;
	}
	private RegularExpression parseBaseExpression() throws RegularExpressionParserException {
		final RegularExpression result = this.getFacade().createBaseExpression(this.getCurrent(true)); 
		this.skip();
		return result;
	}
	private void skip() throws RegularExpressionParserException {
		if (this.getText().charAt(0) == Constants.DevaluatorSymbol){
			this.getText().deleteCharAt(0);
		}
		if (this.isAtEnd()) throw RegularExpressionParserTooShortException.create();		
		this.getText().deleteCharAt(0);
		this.skipWhiteSpace();
	}

	private void skipWhiteSpace() {
		while (!this.isAtEnd() && Character.isWhitespace(this.getText().charAt(0))){
			this.getText().deleteCharAt(0);
		}
	}

	private char getCurrent(final boolean devalue) throws RegularExpressionParserTooShortException {
		if(this.isAtEnd())throw RegularExpressionParserTooShortException.create();
		if (devalue && this.getText().charAt(0) == Constants.DevaluatorSymbol){
			if (this.getText().length() < 1) throw RegularExpressionParserTooShortException.create();
			return this.getText().charAt(1);			
		}
		return this.getText().charAt(0);
	}
	private RegularExpressionFacade getFacade() {
		return this.facade;
	}
	private boolean isAtEnd() {
		return this.getText().length() == 0;
	}
	public RegularExpression parse() throws RegularExpressionParserException {
		final RegularExpression result = this.parseExpression();
		if (!this.isAtEnd()) throw RegularExpressionParserTooLongException.create();
		return result;
	}
}
