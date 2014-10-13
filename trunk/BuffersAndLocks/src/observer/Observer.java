package observer;


public abstract class Observer {
	
	/**Operation that each observer needs to implement in 
	 * order to receive asynchronous notifications
	 */
	protected abstract void update(int value);
}
