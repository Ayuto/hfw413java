package model;

@SuppressWarnings("serial")
public class NotMatchingAutomatonsException extends Exception {

	private static final String NOT_MATCHING_AUTOMATONS_EXCEPTION_MSG = "The Automatons of the current States are not matching.";
	
	@Override
	public String getMessage() {
		return NotMatchingAutomatonsException.NOT_MATCHING_AUTOMATONS_EXCEPTION_MSG;
	}
}
