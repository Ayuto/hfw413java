package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A abstract class for all Objects which can be observed.
 */
public abstract class Observee {

	private final Collection<Observer> observers;

	/**
	 * Constructor which initializes the list of observers with an empty list.
	 */
	public Observee() {
		this.observers = new LinkedList<Observer>();
	}

	/**
	 * Registers the observer <o> on <this>
	 */
	public void register(final Observer o) {
		this.getObservers().add(o);
	}

	/**
	 * Deregisters the observer <o> from <this>
	 */
	public void deregister(final Observer o) {
		this.getObservers().remove(o);
	}

	/**
	 * Notifies all observers registered on <this>.
	 */
	public void notifyObservers() {
		final Iterator<Observer> currentlyObservingObjects = this.getObservers()
				.iterator();
		while (currentlyObservingObjects.hasNext()) {
			final Observer current = currentlyObservingObjects.next();
			current.update();
		}
	}

	private Collection<Observer> getObservers() {
		return this.observers;
	}
}
