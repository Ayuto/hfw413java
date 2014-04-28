package model;

/**
 * Exception to handle the division by zero.
 */
@SuppressWarnings("serial")
public class DivisionByZeroException extends Error {
	
	private static final String DIVISION_BY_ZERO_MSG = "Devision by zero detected! You can not devide by zero";

	public static DivisionByZeroException create (){
		return new DivisionByZeroException(DIVISION_BY_ZERO_MSG);
	}

	private final String text;
	
	private DivisionByZeroException(final String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
}
