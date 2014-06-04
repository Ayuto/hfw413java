package expressions;

public class CycleException extends Exception {

	private static final String CYCLE_MESSAGE = "A cycle has been detected!";
	
	/**
	 * Generated serialID.
	 */
	private static final long serialVersionUID = -1050687715272327950L;

	@Override
	public String getMessage() {
		return CycleException.CYCLE_MESSAGE;
	}
}
