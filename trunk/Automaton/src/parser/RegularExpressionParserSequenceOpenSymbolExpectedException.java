package parser;
@SuppressWarnings("serial")
public class RegularExpressionParserSequenceOpenSymbolExpectedException extends
		RegularExpressionParserException {

	private static final String Message = "Öffnende Klammer für Sequenz erwartet!";

	protected RegularExpressionParserSequenceOpenSymbolExpectedException(final String message) {
		super(message);
	}

	public static RegularExpressionParserException create() {
		return new RegularExpressionParserSequenceOpenSymbolExpectedException(Message);
	}

}
