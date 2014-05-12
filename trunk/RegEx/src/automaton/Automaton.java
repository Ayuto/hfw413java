package automaton;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A nondeterministic finite automaton for not empty input Strings, with ONE
 * startstate and ONE endstate. Startstate and endstate are not the same!
 * Startstate and endstate are out of the state collection states.
 */
public class Automaton {

	/**
	 * Creates an automaton which recognizes no word.
	 */
	public static Automaton create() {
		return new Automaton();
	}

	/**
	 * Creates an automaton which recognizes the word {@code<c>}
	 */
	public static Automaton create(final char c) {
		final Automaton automaton = new Automaton();
		automaton.getStart().add(c, automaton.getEnd());
		return automaton;
	}

	private State start;
	private State end;
	private final StateCollection states;
	private final Collection<StateTransition> delta;
	private boolean optional;

	private Automaton() {
		this.start = State.create(this);
		this.end = State.create(this);
		final StateCollection states = StateCollection.create();
		states.add(this.start);
		states.add(this.end);
		this.states = states;
		this.delta = new LinkedList<StateTransition>();
	}

	/**
	 * Checks the word {@code<input>} with the automaton {@code<this>}.
	 * 
	 * @param input
	 *            the given word
	 * @return Returns true only if the automaton {@code<this>} recognizes the
	 *         word {@code<input>}
	 */
	public boolean recognizes(final String input) {
		if (this.isOptional() && input.isEmpty()) {
			return true;
		}
		return Configuration
				.create(this, StateCollection.create(this.start), input).run()
				.isEndConfiguration();
	}

	/**
	 * Calculates all reachable states from state {@code <from>}.
	 * 
	 * @param from
	 *            Starting state
	 * @return Collection of all successor statetransitions.
	 */
	public Collection<StateTransition> fetchSuccessors(final State from) {
		return from.fetchSuccessorTransitions();
	}

	/**
	 * Calculates all predecessor states from state {@code <to>}.
	 * 
	 * @param from
	 *            Ending state
	 * @return Collection of all predecessor statetransitions.
	 */
	public Collection<StateTransition> fetchPredecessors(final State to) {
		return to.fetchPredecessorTransitions();
	}

	/**
	 * Iterates the receiver.
	 */
	public void iterate() {
		this.getEnd().createEmptyTransitionTo(this.getStart());
	}

	/**
	 * Changes the receiver to the sequence of the receiver and the argument.
	 * There might be sideeffects on the argument.
	 */
	public void sequence(final Automaton argument) {
		this.addStatesAndTransitionsOf(argument);
		this.getEnd().createEmptyTransitionTo(argument.getStart());
		if (this.isOptional()) {
			argument.getStart().createEmptyTransitionFrom(this.getStart());
		}
		if (argument.isOptional()) {
			this.getEnd().createEmptyTransitionTo(argument.getEnd());
		}
		this.setEnd(argument.getEnd());
	}

	/**
	 * Changes the receiver to the choice of the receiver and the argument.
	 * There might be sideeffects on the argument.
	 */
	public void choice(final Automaton argument) {
		this.addStatesAndTransitionsOf(argument);
		argument.getStart().createEmptyTransitionFrom(this.getStart());
		argument.getEnd().createEmptyTransitionTo(this.getEnd());
	}

	/**
	 * Deletes all not necessary states of the receiver including all
	 * transitions of these states.
	 */
	public void normalizeStates() {
		final StateCollection reachableFromBeginning = StateCollection.create(
				this.getStart()).checkBeginning();
		final StateCollection reachableFromEnd = StateCollection.create(
				this.getEnd()).checkEnding();
		this.getStates().checkAllStatesAndDeleteIfNessesary(reachableFromEnd, reachableFromBeginning);
	}

	private void addStatesAndTransitionsOf(final Automaton automaton) {
		final Iterator<State> iterator = automaton.getStates().iterator();
		while (iterator.hasNext()) {
			final State current = iterator.next();
			current.setOut(this);
			this.getStates().add(current);
		}
		final Iterator<StateTransition> transitions = automaton.getDelta()
				.iterator();
		while (transitions.hasNext()) {
			final StateTransition current = transitions.next();
			this.getDelta().add(current);
		}
	}

	public void setStart(final State start) {
		this.start = start;
	}

	public void setEnd(final State end) {
		this.end = end;
	}

	/**
	 * Returns the startstate.
	 */
	public State getStart() {
		return this.start;
	}

	/**
	 * Returns the endstate.
	 */
	public State getEnd() {
		return this.end;
	}

	/**
	 * Returns the collection of all states of the automaton {@code<this>}.
	 */
	public StateCollection getStates() {
		return this.states;
	}

	/**
	 * Returns the collection of all possible statetransitions of the automaton
	 * {@code<this>}.
	 */
	public Collection<StateTransition> getDelta() {
		return this.delta;
	}

	public boolean isOptional() {
		return this.optional;
	}

	public void setOptional(final boolean optional) {
		this.optional = optional;
	}
}
