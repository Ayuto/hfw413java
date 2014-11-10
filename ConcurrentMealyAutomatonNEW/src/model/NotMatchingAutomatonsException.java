package model;

/**
 * A exception if the user tries to add States to a not matching automaton.
 */
public class NotMatchingAutomatonsException extends Exception {

	/**
	 * Generated serial version id.
	 */
	private static final long serialVersionUID = 2626332335174267186L;
	
	private static final String NOT_MATCHING_AUTOMATONS_EXCEPTION_MSG = "The Automatons of the current States are not matching.";
	
	@Override
	public String getMessage() {
		return NotMatchingAutomatonsException.NOT_MATCHING_AUTOMATONS_EXCEPTION_MSG;
	}
}
