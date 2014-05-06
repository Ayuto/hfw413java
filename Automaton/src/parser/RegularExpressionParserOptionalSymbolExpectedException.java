package parser;

@SuppressWarnings("serial")
public class RegularExpressionParserOptionalSymbolExpectedException extends RegularExpressionParserException {

	private static final String Message = "Optionalsymbol erwartet!";

	protected RegularExpressionParserOptionalSymbolExpectedException(final String message) {
		super(message);
	}

	public static RegularExpressionParserOptionalSymbolExpectedException create() {
		return new RegularExpressionParserOptionalSymbolExpectedException(Message);
	}

}
