package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class Observee {

	private final Collection<Observer> observers;

	public Observee() {
		this.observers = new LinkedList<Observer>();
	}

	public void register(Observer o) {
		this.getObservers().add(o);
	}

	public void deregister(Observer o) {
		this.getObservers().remove(o);
	}

	public void notifyObservers() {
		Iterator<Observer> currentlyObservingObjects = this.getObservers()
				.iterator();
		while (currentlyObservingObjects.hasNext()) {
			Observer current = currentlyObservingObjects.next();
			current.update();
		}
	}

	private Collection<Observer> getObservers() {
		return this.observers;
	}
}
