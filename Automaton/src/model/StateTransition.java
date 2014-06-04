package model;

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
	 * @throws NotMatchingAutomatonsException if the automaton of from
	 *         is not the same as the automaton of to.
	 */
	public static StateTransition create(final State from, final State to,
			final char by) throws NotMatchingAutomatonsException {
		if (!from.getOut().equals(to.getOut())) {
			throw new NotMatchingAutomatonsException();
		}
		return new StateTransition(from, to, by);
	}

	private final State from;
	private final State to;
	private final char by;

	private StateTransition(final State from, final State to, final char by) {
		this.from = from;
		this.to = to;
		this.by = by;
	}

	public State getFrom() {
		return this.from;
	}

	public State getTo() {
		return this.to;
	}

	public char getBy() {
		return this.by;
	}
}
