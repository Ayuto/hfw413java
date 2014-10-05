package model;

import java.util.Collection;
import java.util.LinkedList;

public abstract class Observee {
	
	private final Collection<Observer> myObserver;
	
	public Observee() {
		this.myObserver = new LinkedList<Observer>();
	}

	private Collection<Observer> getMyObserver() {
		return this.myObserver;
	}
	
	public void notiyObserver() {
		for (final Observer o : this.getMyObserver()){
			o.update();
		}
	}
	
	public void register(final Observer o) {
		this.getMyObserver().add(o);
	}
	
	public void deregister(final Observer o) {
		this.getMyObserver().remove(o);
	}
}
