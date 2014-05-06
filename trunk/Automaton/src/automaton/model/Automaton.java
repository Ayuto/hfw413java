package automaton.model;

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
	public static Automaton create(char c) {
		Automaton automaton = new Automaton();
		automaton.getStart().add(c, automaton.getEnd());
		return automaton;
	}

	private final State start;
	private final State end;
	private final StateCollection states;
	private final Collection<StateTransition> delta;

	private Automaton() {
		this.start = State.create(this);
		this.end = State.create(this);
		StateCollection states = StateCollection.create();
		states.add(start);
		states.add(end);
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
	public boolean recognizes(String input) {
		return Configuration.create(this, StateCollection.create(start), input)
				.run().isEndConfiguration();
	}

	/**
	 * Calculates all reachable states from state {@code <from>}.
	 * 
	 * @param from
	 *            Starting state
	 * @return Collection of all successor statetransitions.
	 */
	public Collection<StateTransition> fetchSuccessors(State from) {
		return from.fetchSuccessors();
	}

	/**
	 * Calculates all predecessor states from state {@code <to>}.
	 * 
	 * @param from
	 *            Ending state
	 * @return Collection of all predecessor statetransitions.
	 */
	public Collection<StateTransition> fetchPredecessors(State to) {
		return to.fetchPredecessors();
	}

	public Automaton choice(Automaton argument) {
		Automaton result = Automaton.create();
		result.getStates().addAll(this.getStates());
		result.getStates().addAll(argument.getStates());
		createEmptySuccessorTransition(result.getStart(), this.getStart());
		createEmptySuccessorTransition(result.getStart(), argument.getStart());
		createEmptyPredecessorTransition(this.getEnd(), result.getEnd());
		createEmptyPredecessorTransition(argument.getEnd(), result.getEnd());
		return result;
	}

	private void createEmptyPredecessorTransition(State state1, State state2) {
		Iterator<StateTransition> state1Predecessors = state1
				.fetchPredecessors().iterator();
		while (state1Predecessors.hasNext()) {
			StateTransition current = state1Predecessors.next();
			state1.getOut()
					.getDelta()
					.add(StateTransition.create(current.getFrom(), state2,
							current.getBy()));
		}
	}

	private void createEmptySuccessorTransition(State state1, State state2) {
		Iterator<StateTransition> state2Transitions = state2.fetchSuccessors()
				.iterator();
		while (state2Transitions.hasNext()) {
			StateTransition current = state2Transitions.next();
			state1.getOut()
					.getDelta()
					.add(StateTransition.create(state1, current.getTo(),
							current.getBy()));
		}
	}

	/**
	 * Returns the startstate.
	 */
	public State getStart() {
		return start;
	}

	/**
	 * Returns the endstate.
	 */
	public State getEnd() {
		return end;
	}

	/**
	 * Returns the collection of all states of the automaton {@code<this>}.
	 */
	public StateCollection getStates() {
		return states;
	}

	/**
	 * Returns the collection of all possible statetransitions of the automaton
	 * {@code<this>}.
	 */
	public Collection<StateTransition> getDelta() {
		return delta;
	}
}
