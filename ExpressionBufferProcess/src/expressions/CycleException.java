package expressions;

public class CycleException extends Exception {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = -141694697704826146L;

	public CycleException() {
		super("Es sind keine Zyklen erlaubt!!!");
	}
}
