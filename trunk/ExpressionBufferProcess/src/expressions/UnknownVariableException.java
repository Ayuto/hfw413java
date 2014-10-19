package expressions;

public class UnknownVariableException extends Exception {

	/**
	 * Generated serial version id.
	 */
	private static final long serialVersionUID = -5479719413044592458L;

	public UnknownVariableException(String name) {
		super("Die gesuche Variable " + name + " wurde in der Variablenumgebung nicht gefunden.");
	}
}
