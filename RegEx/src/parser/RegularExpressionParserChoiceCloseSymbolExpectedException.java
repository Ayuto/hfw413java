package parser;

@SuppressWarnings("serial")
public class RegularExpressionParserChoiceCloseSymbolExpectedException extends
		RegularExpressionParserException {

	private static final String Message = "Schlie�ende Klammer f�r Auswahl erwartet!";

	protected RegularExpressionParserChoiceCloseSymbolExpectedException(final String message) {
		super(message);
	}

	public static RegularExpressionParserException create() {
		return new RegularExpressionParserChoiceCloseSymbolExpectedException(Message);
	}

}
