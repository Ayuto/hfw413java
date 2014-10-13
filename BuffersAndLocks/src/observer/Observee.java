
package observer;

import java.util.Iterator;
import java.util.Vector;

public class Observee {
	 
	private Vector<Observer> observers;
		
	protected Observee(){
		this.observers = new Vector<Observer>();
	}
	/**Attaches an observer to this observee. 
	 * The same observer can only be registered once.
	 * @param observer receives update notifications 
	 * until deregister is called for this observer.
	 */
	public void register(Observer observer){
		if (!this.observers.contains(observer)) this.observers.add(observer);
	}
	/**Detaches an observer from this observee. 
	 * @param observer does no longer get update notifications
	 * until deregister is called for this observer.
	 */
	public void deregister(Observer observer){
		this.observers.remove(observer);
	}
	/**Sends update notifications to all registered observers
	 */
	protected void notifyObservers(int value){
		Iterator<Observer> observerIterator = this.observers.iterator();
		while (observerIterator.hasNext()){
			Observer current = observerIterator.next();
			current.update(value);
		}
	}
}
