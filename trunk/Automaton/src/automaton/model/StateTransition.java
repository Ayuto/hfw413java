package automaton.model;

/**
 * A state transition from a state by a character to a state.
 */
public class StateTransition {

	/**
	 * Creates a state transition. State {@code <from>} and state {@code <to>}
	 * should belong to the same automaton.
	 * 
	 * @param from
	 *            The starting state.
	 * @param to
	 *            The ending state.
	 * @param by
	 *            The needed input.
	 * @return The created state transition.
	 */
	public static StateTransition create(State from, State to, char by)
			throws IllegalStateTransitionException {
		return new StateTransition(from, to, by);
	}

	private final State from;
	private final State to;
	private final char by;

	private StateTransition(State from, State to, char by) {
		this.from = from;
		this.to = to;
		this.by = by;
	}

	public State getFrom() {
		return from;
	}

	public State getTo() {
		return to;
	}

	public char getBy() {
		return by;
	}
}
