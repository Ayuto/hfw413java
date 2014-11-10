package model;

/**
 * A exception if there is no possible output of a mealy automaton.
 */
public class NoOutputException extends Exception {

	/**
	 * Generated serial version id.
	 */
	private static final long serialVersionUID = 809760815691956130L;

	public NoOutputException() {
		super();
	}

	public NoOutputException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoOutputException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoOutputException(String message) {
		super(message);
	}

	public NoOutputException(Throwable cause) {
		super(cause);
	}
}
