package automaton.model;

import java.util.Collection;
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

	private State start;
	private State end;
	private final StateCollection states;
	private final Collection<StateTransition> delta;
	private boolean optional;

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
		if (this.isOptional() && input.isEmpty()) {
			return true;
		}
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

	/**
	 * Changes the receiver to the choice of itself or {@code <argument>}.
	 */
	public void choice(Automaton argument) {
		this.getStart().createEmptyTransitionTo(argument.getStart());
		this.getEnd().createEmptyTransitionFrom(argument.getEnd());
		argument.getStates().changeAutomaton(this);
		this.getStates().addAll(argument.getStates());
		this.setOptional(this.isOptional() || argument.isOptional());
	}

	/**
	 * Changes the receiver to the sequence of the receiver and afterwards the
	 * automaton {@code <argument>}.
	 */
	public void sequence(Automaton argument) {
		this.getEnd().createEmptyTransitionTo(argument.getStart());
		if (this.isOptional()) {
			this.getStart().createEmptyTransitionTo(argument.getStart());
		}
		if (argument.isOptional()) {
			argument.getEnd().createEmptyTransitionFrom(this.getEnd());
		}
		argument.getStates().changeAutomaton(this);
		this.getStates().addAll(argument.getStates());
		this.setEnd(argument.getEnd());
		this.setOptional(this.isOptional() && argument.isOptional());
	}

	/**
	 * Changes the receiver that it represents its own iteration.
	 */
	public void iterated() {
		this.getStart().createEmptyTransitionFrom(this.getEnd());
	}

	/**
	 * Deletes states which are not necessary for the automaton to work.
	 */
	public void normalize() {
		StateCollection check1 = StateCollection.create(this.getStart())
				.checkBeginning();
		check1.add(this.getStart());
		check1.add(this.getEnd());
		StateCollection check2 = StateCollection.create(this.getEnd())
				.checkEnd();
		check2.add(this.getStart());
		check2.add(this.getEnd());
		this.getStates().check(check1, check2);
	}

	public void setStart(State start) {
		this.start = start;
	}

	public void setEnd(State end) {
		this.end = end;
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

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}
}
