package expressions;

public class UnknownConstantException extends Exception {

	/**
	 * Generated serial version id.
	 */
	private static final long serialVersionUID = 8463315458993065196L;

	public UnknownConstantException(String name) {
		super("Die gesuchte Konstante " + name + " wurde in der gewählten Umgebung nicht gefunden.");
	}
}
