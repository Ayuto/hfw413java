package model;

@SuppressWarnings("serial")
public class DivisionByZeroException extends Error {

	public static DivisionByZeroException create (final String text){
		return new DivisionByZeroException(text);
	}

	private final String text;
	
	private DivisionByZeroException(final String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
}
