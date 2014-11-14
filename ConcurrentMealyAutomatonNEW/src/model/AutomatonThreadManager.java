package model;

import java.util.Collection;
import java.util.LinkedList;

import model.lock.Lock;
import model.tree.OutputTree;
import model.tree.TreeRoot;

/**
 * Objects of this class are managing all running threads for the calculation of
 * a possible output from a mealy automaton.
 */
public class AutomatonThreadManager {

	private final Automaton automaton;
	private final Collection<Configuration> runningThreads;
	private final Lock finished;
	private final Lock mutex;
	private String result;
	private Collection<String> results;

	public AutomatonThreadManager(Automaton automaton) {
		this.automaton = automaton;
		this.runningThreads = new LinkedList<Configuration>();
		this.finished = new Lock(true);
		this.mutex = new Lock(false);
	}

	/**
	 * Calculates one possible output of the automaton from the receiver.
	 * Therefore it will create several threads which let the automaton run. If
	 * there is more than one possible output, it is random which one of them
	 * will be returned.
	 * 
	 * @param input
	 *            given input for the mealy automaton
	 * @return one possible output of the receiver.
	 * @throws NoOutputException
	 *             if there is no possible output
	 */
	public String getPossibleOutput(String input) throws NoOutputException {
		this.result = null;
		this.results = null;
		this.startNewThread(this.automaton.getStart(), input,
				TreeRoot.getInstance());
		this.finished.lock();
		if (this.result == null) {
			throw new NoOutputException("There is no possible output!");
		}
		return this.result;
	}
	
	/**
	 * Calculates all possible output of the automaton from the receiver.
	 * Therefore it will create several threads which let the automaton run.
	 * 
	 * @param input
	 *            given input for the mealy automaton
	 * @return one possible output of the receiver.
	 * @throws NoOutputException
	 *             if there is no possible output
	 */
	public Collection<String> getAllPossibleOutputs(String input) throws NoOutputException {
		this.results = new LinkedList<String>();
		this.startNewThread(this.automaton.getStart(), input, TreeRoot.getInstance());
		this.finished.lock();
		if (this.results.isEmpty()) {
			throw new NoOutputException("There Is no possible output!");
		}
		return this.results;
	}

	private void startNewThread(State state, String input, OutputTree output) {
		this.mutex.lock();
		Configuration config = Configuration.create(this, this.automaton, state,
				input, output);
		this.runningThreads.add(config);
		config.start();
		this.mutex.unlock();
	}

	/**
	 * A running configuration should call this method of its manager if there
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
	 * A running configuration should call this method of its manager if it
	 * ends. A configuration can terminate if there is no possible successor for
	 * the given input. This would be an unsuccessful ending. A configuration
	 * can terminate if the input is empty, too. Than the configuration has to
	 * check, if the current state is the endstate of the automaton.
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
		if (success && this.results != null) {
			try {
				this.results.add(output.getValue());
			} catch (RootHasNoValueException e) {
				e.printStackTrace();
				throw new Error("Internal error! Should never happen here...");
			}
		}
		if (success && this.result == null && this.results == null) {
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
}
