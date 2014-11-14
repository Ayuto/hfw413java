package model;

import java.util.Collection;
import java.util.HashSet;

/**
 * A mealy automaton for not empty input Strings, with ONE startstate and ONE
 * endstate. Startstate and endstate are not the same! Startstate and endstate
 * are out of the state collection states.
 */
public class Automaton {

	/**
	 * Creates an automaton which recognizes no word. This automaton can not
	 * create any output, too.
	 */
	public static Automaton create() {
		return new Automaton();
	}

	/**
	 * Creates an automaton which recognizes the word {@code<c>} with the output
	 * {@code<output>}.
	 */
	public static Automaton create(final char c, final String output) {
		final Automaton automaton = new Automaton();
		try {
			automaton.getStart().add(c, automaton.getEnd(), output);
		} catch (final NotMatchingAutomatonsException e) {
			e.printStackTrace();
			throw new Error("Internal Error! Shouldn't happen here...");
		}
		return automaton;
	}

	private final State start;
	private final State end;
	private final Collection<State> states;
	private final Collection<StateTransition> delta;

	private Automaton() {
		this.start = State.create(this);
		this.end = State.create(this);
		this.states = new HashSet<State>();
		this.states.add(this.start);
		this.states.add(this.end);
		this.delta = new HashSet<StateTransition>();
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
	 * Calculates one possible output of the receiver. Therefore it will create
	 * several threads which let the automaton run. If there is more than one
	 * possible output, it is random which one of them will be returned.
	 * 
	 * @param input
	 *            given input for the receiver
	 * @return one possible output of the receiver.
	 * @throws NoOutputException
	 *             if there is no possible output
	 */
	public String getPossibleOutput(String input) throws NoOutputException {
		return new AutomatonThreadManager(this).getPossibleOutput(input);
	}
	
	/**
	 * Calculates all possible output of the receiver.
	 * Therefore it will create several threads which let the automaton run.
	 * 
	 * @param input
	 *            given input for the receiver
	 * @return one possible output of the receiver.
	 * @throws NoOutputException
	 *             if there is no possible output
	 */
	public Collection<String> getAllPossibleOutputs(String input) throws NoOutputException {
		return new AutomatonThreadManager(this).getAllPossibleOutputs(input);
	}
}
