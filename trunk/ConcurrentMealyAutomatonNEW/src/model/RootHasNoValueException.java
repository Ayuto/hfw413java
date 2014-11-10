package model;

/**
 * Exception if the users tries to calculate the value of a root of an output tree.
 */
public class RootHasNoValueException extends Exception {

	/**
	 * Generated serial version id.
	 */
	private static final long serialVersionUID = -3501139542469188010L;

	public RootHasNoValueException() {
		super();
	}

	public RootHasNoValueException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RootHasNoValueException(String message, Throwable cause) {
		super(message, cause);
	}

	public RootHasNoValueException(String message) {
		super(message);
	}

	public RootHasNoValueException(Throwable cause) {
		super(cause);
	}
}
