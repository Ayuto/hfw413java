package model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * A nondeterministic finite automaton for not empty input Strings, with ONE startstate and ONE endstate.
 * Startstate and endstate are not the same!
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
	public static Automaton create (char c) {
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
	 * @param input the given word
	 * @return Returns true only if the automaton {@code<this>} recognizes the word {@code<input>}
	 */
	public boolean recognizes(String input){
		return Configuration.create(this, StateCollection.create(start), input).run().isEndConfiguration();
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
	 * Returns the collection of all possible statetransitions of the automaton {@code<this>}.
	 */
	public Collection<StateTransition> getDelta() {
		return delta;
	}
}
