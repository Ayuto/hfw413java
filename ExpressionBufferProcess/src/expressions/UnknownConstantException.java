package expressions;

/**
 * A unknown constant exception is thrown, if a constant could not be found in
 * the current environment.
 */
public class UnknownConstantException extends Exception {

	/**
	 * Generated serial version id.
	 */
	private static final long serialVersionUID = 8463315458993065196L;

	/**
	 * Constructor for a unknown constant exception.
	 * 
	 * @param name
	 *            the name of the unknown constant.
	 */
	public UnknownConstantException(String name) {
		super("Die gesuchte Konstante " + name
				+ " wurde in der gewählten Umgebung nicht gefunden.");
	}
}
