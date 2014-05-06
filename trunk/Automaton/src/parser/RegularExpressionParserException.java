package parser;

@SuppressWarnings("serial")
public abstract class RegularExpressionParserException extends Exception {
	
	protected RegularExpressionParserException(final String message){
		super(message);
	}

}
