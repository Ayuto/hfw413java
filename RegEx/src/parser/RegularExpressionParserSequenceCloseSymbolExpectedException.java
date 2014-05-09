package parser;
@SuppressWarnings("serial")
public class RegularExpressionParserSequenceCloseSymbolExpectedException extends
		RegularExpressionParserException {

	private static final String Message = "Schlie�ende Klammer f�r Sequence erwartet!";

	protected RegularExpressionParserSequenceCloseSymbolExpectedException(final String message) {
		super(message);
	}

	public static RegularExpressionParserException create() {
		return new RegularExpressionParserSequenceCloseSymbolExpectedException(Message);
	}

}
