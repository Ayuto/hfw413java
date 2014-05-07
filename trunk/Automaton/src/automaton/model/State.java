package automaton.model;

import java.util.Collection;
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
	public static State create(Automaton out) {
		return new State(out);
	}

	private Automaton out;

	private State(Automaton out) {
		this.setOut(out);
	}

	/**
	 * Adds a new transition to the automaton of {@code <this>} from the state
	 * {@code <this>}.
	 * 
	 * @param c
	 *            Input of the new transition.
	 * @param s
	 *            New state after the input of {@code <c>}.
	 */
	public void add(char c, State s) {
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
	public StateCollection get(char c) {
		StateCollection result = StateCollection.create();
		Iterator<StateTransition> iterator = this.getOut().getDelta()
				.iterator();
		while (iterator.hasNext()) {
			StateTransition current = iterator.next();
			if (current.getFrom().equals(this) && current.getBy() == c)
				result.add(current.getTo());
		}
		return result;
	}

	/**
	 * Calculates a collection of all states reachable from {@code <this>}.
	 * 
	 * @return The StateCollection with all reachable states.
	 */
	public Collection<StateTransition> fetchSuccessors() {
		Collection<StateTransition> result = new LinkedList<StateTransition>();
		Iterator<StateTransition> iterator = this.getOut().getDelta()
				.iterator();
		while (iterator.hasNext()) {
			StateTransition current = iterator.next();
			if (current.getFrom().equals(this))
				result.add(current);
		}
		return result;
	}

	/**
	 * Calculates a collection of all predecessor states from {@code <this>}.
	 * 
	 * @return The StateCollection with all predecessor states.
	 */
	public Collection<StateTransition> fetchPredecessors() {
		Collection<StateTransition> result = new LinkedList<StateTransition>();
		Iterator<StateTransition> iterator = this.getOut().getDelta()
				.iterator();
		while (iterator.hasNext()) {
			StateTransition current = iterator.next();
			if (current.getTo().equals(this))
				result.add(current);
		}
		return result;
	}

	/**
	 * Connects the state {@code <this>} with all successors of the state
	 * {@code <state>}.
	 * 
	 * @param state
	 *            {@code <this>} should be connected with {@code <state>}.
	 */
	public void createEmptyTransitionTo(State state) {
		Iterator<StateTransition> successors = state.fetchSuccessors()
				.iterator();
		while (successors.hasNext()) {
			StateTransition current = successors.next();
			this.add(current.getBy(), current.getTo());
		}
	}

	/**
	 * Connects all predecessors of {@code <state>} with {@code <this>}.
	 * 
	 * @param state
	 *            state which should be connected with {@code <this>}.
	 */
	public void createEmptyTransitionFrom(State state) {
		Iterator<StateTransition> predecessors = state.fetchPredecessors()
				.iterator();
		while (predecessors.hasNext()) {
			StateTransition current = predecessors.next();
			current.getFrom().add(current.getBy(), this);
		}
	}

	/**
	 * Removes all Transitions with the receiver as start or end state.
	 */
	public void removeAllTransitions() {
		Iterator<StateTransition> iterator = this.getOut().getDelta()
				.iterator();
		while (iterator.hasNext()) {
			StateTransition current = iterator.next();
			if (current.getFrom().equals(this) || current.getTo().equals(this)) {
				iterator.remove();
			}
		}
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
		return out;
	}

	public void setOut(Automaton out) {
		this.out = out;
	}
}
