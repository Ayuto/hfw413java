package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A state belongs to an automaton and administrates all possible transitions.
 */
public class State {

	/**
	 * Creates a state from the automaton {@code <out>}.
	 * 
	 * @param out
	 *            The new state belongs to this automaton.
	 * @return The created state.
	 */
	public static State create(final Automaton out) {
		return new State(out);
	}

	private final Automaton out;

	private State(final Automaton out) {
		this.out = out;
	}

	/**
	 * Adds a new transition to the automaton of {@code <this>} from the state
	 * {@code <this>}.
	 * 
	 * @param c
	 *            Input of the new transition.
	 * @param s
	 *            New state after the input of {@code <c>}.
	 * @throws NotMatchingAutomatonsException If the state s is not from the same automaton as the receiver.
	 */
	public void add(final char c, final State s) throws NotMatchingAutomatonsException {
		this.getOut().getDelta().add(StateTransition.create(this, s, c));
	}

	/**
	 * Calculates a collection of all states reachable with the input
	 * {@code <c>} from {@code <this>}.
	 * 
	 * @param c
	 *            The given input.
	 * @return The StateCollection with all reachable states.
	 */
	public Collection<State> get(final char c) {
		final Collection<State> result = new HashSet<State>();
		final Iterator<StateTransition> iterator = this.getOut().getDelta()
				.iterator();
		while (iterator.hasNext()) {
			final StateTransition current = iterator.next();
			if (current.getFrom().equals(this) && current.getBy() == c) {
				result.add(current.getTo());
			}
		}
		return result;
	}

	/**
	 * Calculates all transitions starting at the receiver.
	 * @return A collection with all calculated transitions.
	 */
	public Collection<StateTransition> fetchSuccessors() {
		final Collection<StateTransition> result = new LinkedList<StateTransition>();
		final Iterator<StateTransition> iterator = this.getOut().getDelta()
				.iterator();
		while (iterator.hasNext()) {
			final StateTransition current = iterator.next();
			if (current.getFrom().equals(this)) {
				result.add(current);
			}
		}
		return result;
	}

	/**
	 * Calculates all transitions leading to the receiver.
	 * 
	 * @return The collection of all calculated transitions.
	 */
	public Collection<StateTransition> fetchPredecessors() {
		final Collection<StateTransition> result = new LinkedList<StateTransition>();
		final Iterator<StateTransition> iterator = this.getOut().getDelta()
				.iterator();
		while (iterator.hasNext()) {
			final StateTransition current = iterator.next();
			if (current.getTo().equals(this)) {
				result.add(current);
			}
		}
		return result;
	}
	
	@Override
	public boolean equals(final Object obj) {
		return super.equals(obj);
	}

	/**
	 * Checks whether {@code <this>} is an endstate.
	 * 
	 * @return true only if {@code <this>} is an endstate.
	 */
	public boolean isEndState() {
		return this.equals(this.getOut().getEnd());
	}

	public Automaton getOut() {
		return this.out;
	}
}
