package parser;

@SuppressWarnings("serial")
public class RegularExpressionParserOptionalAndIteratedSymbolExpectedException extends RegularExpressionParserException{

	private static final String Message = "Optional-und-Iterator-Symbol erwartet!";

	protected RegularExpressionParserOptionalAndIteratedSymbolExpectedException(final String message) {
		super(message);
	}

	public static RegularExpressionParserOptionalAndIteratedSymbolExpectedException create() {
		return new RegularExpressionParserOptionalAndIteratedSymbolExpectedException(Message);
	}

}
