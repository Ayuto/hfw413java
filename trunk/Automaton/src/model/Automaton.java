package model;

import java.util.Collection;
import java.util.HashSet;

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
		try {
			automaton.getStart().add(c, automaton.getEnd());
		} catch (final NotMatchingAutomatonsException e) {
			System.out.println("Shouldn't happen here...");
			e.printStackTrace();
		}
		return automaton;
	}

	private final State start;
	private final State end;
	private final Collection<State> states;
	private final Collection<StateTransition> delta;
	private boolean optional;

	private Automaton() {
		this.start = State.create(this);
		this.end = State.create(this);
		this.states = new HashSet<State>();
		this.states.add(this.start);
		this.states.add(this.end);
		this.delta = new HashSet<StateTransition>();
		this.setOptional(false);
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
		return Configuration.create(this, input).run().isEndConfiguration();
	}

	/**
	 * Calculates all reachable states from state {@code <from>}.
	 * 
	 * @param from
	 *            Starting state
	 * @return Collection of all successor statetransitions.
	 */
	public Collection<StateTransition> fetchSuccessors(final State from) {
		return from.fetchSuccessors();
	}

	/**
	 * Calculates all predecessor states from state {@code <to>}.
	 * 
	 * @param from
	 *            Ending state
	 * @return Collection of all predecessor statetransitions.
	 */
	public Collection<StateTransition> fetchPredecessors(final State to) {
		return to.fetchPredecessors();
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
	public Collection<State> getStates() {
		return this.states;
	}

	/**
	 * Returns the collection of all possible statetransitions of the automaton
	 * {@code<this>}.
	 */
	public Collection<StateTransition> getDelta() {
		return this.delta;
	}

	/**
	 * Getter for the optional state of the receiver.
	 * 
	 * @return True only if the receiver is a optional automaton.
	 */
	public boolean isOptional() {
		return this.optional;
	}

	/**
	 * Setter for the optional state of the receiver.
	 * 
	 * @param optional
	 *            new optional state of the receiver.
	 */
	public void setOptional(final boolean optional) {
		this.optional = optional;
	}
}
