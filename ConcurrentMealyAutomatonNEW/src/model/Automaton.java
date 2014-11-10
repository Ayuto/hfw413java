package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import model.lock.Lock;
import model.tree.OutputTree;
import model.tree.TreeRoot;

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
	private final Collection<Configuration> runningThreads;
	private final Lock finished;
	private final Lock mutex;
	private String result;

	private Automaton() {
		this.start = State.create(this);
		this.end = State.create(this);
		this.states = new HashSet<State>();
		this.states.add(this.start);
		this.states.add(this.end);
		this.delta = new HashSet<StateTransition>();
		this.runningThreads = new LinkedList<Configuration>();
		this.finished = new Lock(true);
		this.mutex = new Lock(false);
	}

	/**
	 * Calculates one possible output of the receiver. Therefore it will create
	 * several threads which let the automaton run. If there is more than one
	 * possible output, it is random which one of them will be returned.
	 * 
	 * @param input
	 *            given input for the mealy automaton
	 * @return one possible output of the receiver.
	 * @throws NoOutputException
	 *             if there is no possible output
	 */
	public String getPossibleOutput(String input) throws NoOutputException {
		this.startNewThread(this.getStart(), input, TreeRoot.getInstance());
		this.result = null;
		this.finished.lock();
		if (this.result == null) {
			throw new NoOutputException("There is no possible output!");
		}
		return this.result;
	}

	private void startNewThread(State state, String input, OutputTree output) {
		this.mutex.lock();
		Configuration config = Configuration.create(this, state, input, output);
		this.runningThreads.add(config);
		config.start();
		this.mutex.unlock();
	}

	/**
	 * A running configuration should call this method of its automaton if there
	 * is more than one possible successor.
	 * 
	 * @param state
	 *            possible successor
	 * @param input
	 *            the input after the transition to the given successor.
	 * @param output
	 *            the current output after the transition to the given
	 *            successor.
	 */
	void configurationHasSeveralSuccessorsDetected(State state, String input,
			OutputTree output) {
		this.startNewThread(state, input, output);
	}

	/**
	 * A running configuration should call this method of its automaton if it
	 * ends. A configuration can terminate if there is no possible successor for
	 * the given input. This would be an unsuccessful ending. 
	 * A configuration can terminate if the input is empty, too. Than the configuration 
	 * has to check, if the current state is the endstate of the automaton.
	 * 
	 * @param thread
	 *            configuration which terminates
	 * @param success
	 *            this parameter should only be true, if the configuration
	 *            terminates successfully with a possible result
	 * @param output
	 *            the calculated output after the last transition.
	 */
	void threadEnds(final Configuration thread, final boolean success,
			final OutputTree output) {
		this.mutex.lock();
		this.runningThreads.remove(thread);
		if (success && this.result == null) {
			try {
				this.result = output.getValue();
			} catch (RootHasNoValueException e) {
				throw new Error("Internal error! Should never happen here...");
			}
			for (Configuration current : this.runningThreads) {
				current.stop();
			}
		}
		if (this.runningThreads.isEmpty()) {
			this.finished.unlock();
		}
		this.mutex.unlock();
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
}
