package parser;

@SuppressWarnings("serial")
public class RegularExpressionParserChoiceOpenSymbolExpectedException extends
		RegularExpressionParserException {

	private static final String Message = "�ffnende Klammer f�r Auswahl erwartet!";

	protected RegularExpressionParserChoiceOpenSymbolExpectedException(final String message) {
		super(message);
	}

	public static RegularExpressionParserException create() {
		return new RegularExpressionParserChoiceOpenSymbolExpectedException(Message);
	}

}
