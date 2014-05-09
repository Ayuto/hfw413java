package parser;

@SuppressWarnings("serial")
public class RegularExpressionParserTooShortException extends RegularExpressionParserException {

	private static final String Message = "Regulärer Ausdruck ist zu kurz!";
	public static RegularExpressionParserTooShortException create(){
		return new RegularExpressionParserTooShortException(Message);
	}
	private RegularExpressionParserTooShortException(final String message) {
		super(message);
	}

}
