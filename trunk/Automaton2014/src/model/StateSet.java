package model;

import java.util.HashSet;
import java.util.Iterator;

/**
 * A set of states which can contain every state just once.
 */
public class StateSet {

	/**
	 * Creates a new empty set for states.
	 * 
	 * @return the created set.
	 */
	public static StateSet create() {
		return new StateSet();
	}

	/**
	 * Creates a new set for states which contains the given state.
	 * 
	 * @param state
	 * @return the created set.
	 */
	public static StateSet create(final State state) {
		StateSet result = StateSet.create();
		result.addState(state);
		return result;
	}

	private HashSet<State> states;

	private StateSet() {
		this.states = new java.util.HashSet<State>();
	}

	/**
	 * Adds the given state to the set if the set doesn't contain it already.
	 * 
	 * @param state
	 */
	public void addState(final State state) {
		this.getStates().add(state);
	}

	private HashSet<State> getStates() {
		return this.states;
	}

	/**
	 * Checks whether the set contains the given state.
	 * 
	 * @param state
	 * @return {@code True} only if the set contains the given state, otherwise
	 *         {@code false}.
	 */
	public boolean contains(final State state) {
		return this.states.contains(state);
	}

	/**
	 * Creates an iterator for the receiver.
	 * 
	 * @return the iterator for the set.
	 */
	public Iterator<State> iterator() {
		return this.states.iterator();
	}

	/**
	 * Adds all states out of the given set.
	 * 
	 * @param stateSet
	 */
	public void addStateSet(final StateSet stateSet) {
		this.states.addAll(stateSet.getStates());
	}

}
