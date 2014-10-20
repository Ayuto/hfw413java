package expressions;

/**
 * A cycle exception is thrown, if a cycle is detected in a composite which
 * should be without cycles.
 */
public class CycleException extends Exception {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = -141694697704826146L;

	/**
	 * Constructor for a new Cycle-Exception.
	 */
	public CycleException() {
		super("Es sind keine Zyklen erlaubt!!!");
	}
}
