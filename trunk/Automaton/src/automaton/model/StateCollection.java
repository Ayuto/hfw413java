package automaton.model;

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
	public static StateCollection create(State s) {
		StateCollection states = StateCollection.create();
		states.add(s);
		return states;
	}

	private Collection<State> data;

	private StateCollection(Collection<State> data) {
		this.data = data;
	}

	/**
	 * Adds the state {@code <s>} to the collection.
	 * 
	 * @param s
	 *            State to be added to the collection.
	 */
	public void add(State s) {
		this.getData().add(s);
	}

	/**
	 * Adds the collection {@code <states>} to the collection.
	 * 
	 * @param states
	 *            Collection to be added to the collection.
	 */
	public void addAll(StateCollection states) {
		this.getData().addAll(states.getData());
	}

	/**
	 * Returns all reachable states from all states in {@code <this>}.
	 */
	public StateCollection checkBeginning() {
		int sizeBegin = this.getData().size();
		Iterator<State> states = this.iterator();
		while (states.hasNext()) {
			State s = states.next();
			Iterator<StateTransition> iterator = s.fetchSuccessors().iterator();
			while (iterator.hasNext()) {
				StateTransition current = iterator.next();
				this.add(current.getTo());
			}
		}
		if (this.getData().size() == sizeBegin) {
			return this;
		} else {
			return this.checkBeginning();
		}
	}

	/**
	 * Returns all states out of whom at least one state of {@code <this>} is
	 * reachable.
	 */
	public StateCollection checkEnd() {
		int sizeBegin = this.getData().size();
		Iterator<State> states = this.iterator();
		while (states.hasNext()) {
			State s = states.next();
			Iterator<StateTransition> iterator = s.fetchPredecessors()
					.iterator();
			while (iterator.hasNext()) {
				StateTransition current = iterator.next();
				this.add(current.getFrom());
			}
		}
		if (this.getData().size() == sizeBegin) {
			return this;
		} else {
			return this.checkEnd();
		}
	}

	/**
	 * Deletes all elements of {@code <this>} which are not in {@code <list1>}
	 * and {@code <list2>}.
	 */
	public void check(StateCollection list1, StateCollection list2) {
		Iterator<State> iterator = this.iterator();
		while (iterator.hasNext()) {
			State current = iterator.next();
			if (!(list1.contains(current) && list2.contains(current))) {
				current.removeAllTransitions();
				iterator.remove();
			}
		}
	}

	/**
	 * Changes the automaton of all states of {@code <this>} to
	 * {@code <automaton>}.
	 */
	public void changeAutomaton(Automaton automaton) {
		Iterator<State> states = this.iterator();
		while (states.hasNext()) {
			State current = states.next();
			current.setOut(automaton);
		}
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
	public boolean contains(State s) {
		return this.getData().contains(s);
	}

	private Collection<State> getData() {
		return this.data;
	}
}
