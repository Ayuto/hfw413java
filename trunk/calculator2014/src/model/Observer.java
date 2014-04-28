package model;

/**
 * A interface for all classes who wants to observer an object.
 */
public interface Observer {

	/**
	 * Method which is called from the observee, if he notifies his observers.
	 */
	void update();
}
