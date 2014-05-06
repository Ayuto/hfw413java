package parser;
@SuppressWarnings("serial")
public class RegularExpressionParserSequenceOpenSymbolExpectedException extends
		RegularExpressionParserException {

	private static final String Message = "�ffnende Klammer f�r Sequenz erwartet!";

	protected RegularExpressionParserSequenceOpenSymbolExpectedException(final String message) {
		super(message);
	}

	public static RegularExpressionParserException create() {
		return new RegularExpressionParserSequenceOpenSymbolExpectedException(Message);
	}

}
