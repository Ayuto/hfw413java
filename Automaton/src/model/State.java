package model;

import java.util.Iterator;

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
	 */
	public void add(char c, State s) {
		this.getOut().getDelta().add(StateTransition.create(this, s, c));
	}

	/**
	 * Calculates a collection of all states reachable with the input {@code <c>}
	 * from {@code <this>}.
	 * 
	 * @param c
	 *            The given input.
	 * @return The StateCollection with all the reachable states.
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
	 * Checks whether {@code <this>} is an endstate.
	 * @return true only if {@code <this>} is an endstate.
	 */
	public boolean isEndState() {
		return this.equals(this.getOut().getEnd());
	}

	public Automaton getOut() {
		return out;
	}
}
