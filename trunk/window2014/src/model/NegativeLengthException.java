package model;

@SuppressWarnings("serial")
public class NegativeLengthException extends Exception {
	
	private static final String NegativeLengthExceptionMessage = "Length specification cannot be negative";

	public NegativeLengthException() {
		super(NegativeLengthExceptionMessage);
	}

}
