package automaton;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A collection of states with no duplicate states.
 */
public class StateCollection {

	/**
	 * Creates a collection with no states in it.
	 * 
	 * @return the created collection.
	 */
	public static StateCollection create() {
		return new StateCollection(new HashSet<State>());
	}

	/**
	 * Creates a collection with one state in it.
	 * 
	 * @param s
	 *            The State in the collection.
	 * @return the created collection.
	 */
	public static StateCollection create(final State s) {
		final StateCollection states = StateCollection.create();
		states.add(s);
		return states;
	}

	private final Collection<State> data;

	private StateCollection(final Collection<State> data) {
		this.data = data;
	}

	/**
	 * Adds the state {@code <s>} to the collection.
	 * 
	 * @param s
	 *            State to be added to the collection.
	 */
	public void add(final State s) {
		this.getData().add(s);
	}

	/**
	 * Adds the collection {@code <states>} to the collection.
	 * 
	 * @param states
	 *            Collection to be added to the collection.
	 */
	public void addAll(final StateCollection states) {
		this.getData().addAll(states.getData());
	}

	/**
	 * Creates an iterator of the collection.
	 * 
	 * @return The iterator of this collection.
	 */
	public Iterator<State> iterator() {
		return this.getData().iterator();
	}

	/**
	 * Returns true only if the collection contains the given state.
	 */
	public boolean contains(final State s) {
		return this.getData().contains(s);
	}

	private Collection<State> getData() {
		return this.data;
	}
}
