package parser;

@SuppressWarnings("serial")
public class RegularExpressionParserIteratedSymbolExpectedException extends
		RegularExpressionParserException {

	private static final String Message = "Iteratorsymbol erwartet!";

	protected RegularExpressionParserIteratedSymbolExpectedException(final String message) {
		super(message);
	}

	public static RegularExpressionParserException create() {
		return new RegularExpressionParserIteratedSymbolExpectedException(Message);
	}

}
