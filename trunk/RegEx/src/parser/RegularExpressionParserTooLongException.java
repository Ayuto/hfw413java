package parser;

@SuppressWarnings("serial")
public class RegularExpressionParserTooLongException extends RegularExpressionParserException {

	private static final String Message = "Ausdruck hat überflüssige Zeichen am Ende!";

	private RegularExpressionParserTooLongException(final String message) {
		super(message);
	}

	public static RegularExpressionParserTooLongException create() {
		return new RegularExpressionParserTooLongException(Message);
	}

}
