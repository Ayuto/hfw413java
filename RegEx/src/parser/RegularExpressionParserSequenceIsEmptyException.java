package parser;

@SuppressWarnings("serial")
public class RegularExpressionParserSequenceIsEmptyException extends
		RegularExpressionParserException {

	private static final String Message = "Sequence ist leer!";

	private RegularExpressionParserSequenceIsEmptyException(final String message) {
		super(message);
	}

	public static RegularExpressionParserException create() {
		return new RegularExpressionParserSequenceIsEmptyException(Message);
	}

}
