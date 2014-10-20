package expressions;

/**
 * A unknown variable exception is thrown, if a variable could not be found in
 * the current environment.
 */
public class UnknownVariableException extends Exception {

	/**
	 * Generated serial version id.
	 */
	private static final long serialVersionUID = -5479719413044592458L;

	/**
	 * Constructor for a unknown variable exception.
	 * 
	 * @param name
	 *            the name of the unknown variable.
	 */
	public UnknownVariableException(String name) {
		super("Die gesuche Variable " + name + " wurde in der Variablenumgebung nicht gefunden.");
	}
}
