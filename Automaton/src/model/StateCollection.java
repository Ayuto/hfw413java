package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A collection of states with no duplicate states.
 */
public class StateCollection {
	
	/**
	 * Creates a collection with no states in it.
	 * @return the created collection.
	 */
	public static StateCollection create() {
		return new StateCollection(new HashSet<State>());
	}
	
	/**
	 * Creates a collection with one state in it.
	 * @param s The State in the collection.
	 * @return the created collection.
	 */
	public static StateCollection create(State s) {
		StateCollection states = StateCollection.create();
		states.add(s);
		return states;
	}

	private Collection<State> data;
	
	private StateCollection (Collection<State> data) {
		this.data = data;
	}
	
	/**
	 * Adds the state {@code <s>} to the collection.
	 * @param s State to be added to the collection.
	 */
	public void add(State s) {
		this.getData().add(s);
	}

	/**
	 * Adds the collection {@code <states>} to the collection.
	 * @param states Collection to be added to the collection.
	 */
	public void add(StateCollection states) {
		Iterator<State> iterator = states.iterator();
		while(iterator.hasNext()) {
			State current = iterator.next();
			this.add(current);
		}
	}
	
	/**
	 * Creates an iterator of the collection.
	 * @return The iterator of this collection.
	 */
	public Iterator<State> iterator() {
		return this.getData().iterator();
	}

	private Collection<State> getData() {
		return this.data;
	}
}
