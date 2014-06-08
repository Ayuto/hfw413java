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
	 * Calculates the amount of states which are currently in the receivers collection.
	 * @return The amount of states which are currently in the receivers collection.
	 */
	public int size() {
		return this.getData().size();
	}

	/**
	 * Returns true only if the collection contains the given state.
	 */
	public boolean contains(final State s) {
		return this.getData().contains(s);
	}
	
	@Override
	public boolean equals(final Object argument) {
		if (argument instanceof StateCollection) {
			final StateCollection argumentAsStateCollection = (StateCollection) argument;
			return this.getData().equals(argumentAsStateCollection.getData());
		}
		return false;
	}

	private Collection<State> getData() {
		return this.data;
	}

	/**
	 * Calculates all states which are in the receiver and all states which are
	 * reachable out of the states in the receiver
	 * 
	 * @return The StateCollection with all the calculated states.
	 */
	public StateCollection checkBeginning() {
		StateCollection result = StateCollection.create();
		result.addAll(this);
		final Iterator<State> iterator = this.iterator();
		while (iterator.hasNext()) {
			final State current = iterator.next();
			result.addAll(current.fetchSuccessors());
		}
		if (this.size() == result.size()) {
			return result;
		}
		return result.checkBeginning();
	}
	
	/**
	 * Checks all states in the receiver. If the state is also in both arguments
	 * it will stay in the StateCollection, otherwise it will be deleted and all
	 * transitions of these states will be deleted, too.
	 * @param reachableFromBeginning 
	 */
	public void checkAllStatesAndDeleteIfNessesary(
			final StateCollection argument1, final StateCollection argument2) {
		final Iterator<State> iterator = this.iterator();
		while (iterator.hasNext()) {
			final State current = iterator.next();
			if (!(argument1.contains(current) && argument2.contains(current))) {
				current.deleteAllTransitions();
				iterator.remove();
			}
		}
	}

	/**
	 * Calculates all states which are in the receiver and all states out of
	 * whom the states of the receiver are reachable.
	 * 
	 * @return The StateCollection with all the calculated states.
	 */
	public StateCollection checkEnding() {
		StateCollection result = StateCollection.create();
		result.addAll(this);
		final Iterator<State> iterator = this.iterator();
		while (iterator.hasNext()) {
			final State current = iterator.next();
			result.addAll(current.fetchPredecessors());
		}
		if (this.size() == result.size()) {
			return result;
		}
		return result.checkEnding();
	}
}
