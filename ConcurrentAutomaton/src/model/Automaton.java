package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import lock.Lock;
import model.tree.Node;

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
	public static Automaton create(final char c, String output) {
		final Automaton automaton = new Automaton();
		try {
			automaton.getStart().add(c, automaton.getEnd(), output);
		} catch (final NotMatchingAutomatonsException e) {
			System.out.println("Shouldn't happen here...");
			e.printStackTrace();
		}
		return automaton;
	}

	private int createdThreads;
	private final State start;
	private final State end;
	private final Collection<State> states;
	private final Collection<StateTransition> delta;
	private Collection<Configuration> runningThreads;
	private Lock finished;
	private Lock mutex;
	private String result;
	private boolean errorOccured;

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
	
	public String getPossibleOutput(String input) throws NoOutputException {
		this.result = "";
		this.startNewThread(new Node<String>(null), this.getStart(), input);
		
		this.finished.lock();
		if (this.errorOccured)
			throw new NoOutputException("No possible output found.");

		this.mutex.lock();
		for(Configuration current : this.runningThreads) {
			current.stop();
		}
		this.runningThreads.clear();
		this.errorOccured = false;
		this.mutex.unlock();
		return result;
	}
	
	private void startNewThread(Node<String> newParent, State to, String newInput) {
		Configuration config = Configuration.create(this, newParent, to, newInput);
		this.mutex.lock();
		this.createdThreads++;
		this.runningThreads.add(config);
		this.mutex.unlock();
		config.start();
	}

	public void threadFinished(Configuration thread, boolean success, Node<String> node) {
		this.mutex.lock();
		this.runningThreads.remove(thread);
		
		if (success) {
			this.errorOccured = false;
			this.result = this.generateString(node);
			this.finished.unlock();
		} else if (this.runningThreads.isEmpty()) {
			this.errorOccured = true;
			this.finished.unlock();
		}
		
		this.mutex.unlock();
	}

	private String generateString(Node<String> node) {
		String result = "";
		while (!node.isRoot()) {
			result = node.getValue() + result;
			node = node.getParent();
		}
		return result;
	}

	public void hasSeveralSuccessors(Node<String> newParent, State to,
			String newInput) {
		this.startNewThread(newParent, to, newInput);
	}

	public int getCreatedThreads() {
		return createdThreads;
	}
}