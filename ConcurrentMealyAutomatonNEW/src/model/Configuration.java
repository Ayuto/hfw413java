package model;

import java.util.Collection;
import java.util.Iterator;

import model.tree.OutputTree;
import model.tree.TreeRoot;

/**
 * A current configuration of an automaton with a current state, an input string
 * and the current output.
 */
public class Configuration implements Runnable {

	/**
	 * Creates a configuration of the given automaton with the given state, the
	 * given input and the given output.
	 * 
	 * @param manager
	 *            Thread manager of the configuration
	 * @param out
	 *            The automaton of the configuration.
	 * @param states
	 *            The current states of the configuration.
	 * @param input
	 *            The input of the configuration.
	 * @param output
	 *            The current output of the configuration.
	 * @return The created configuration.
	 */
	public static Configuration create(AutomatonThreadManager manager,
			final Automaton out, final State state, final String input,
			final OutputTree output) {
		return new Configuration(out, state, input, output, manager);
	}

	/**
	 * Creates the starting configuration of the given automaton with the given
	 * input String.
	 * 
	 * @param manager
	 *            Thread manager of the configuration
	 * @param out
	 *            The automaton of the configuration.
	 * @param input
	 *            The input of the configuration.
	 * @return The created configuration.
	 */
	public static Configuration create(AutomatonThreadManager manager,
			final Automaton out, final String input) {
		return Configuration.create(manager, out, out.getStart(), input,
				TreeRoot.getInstance());
	}

	private final Automaton out;
	private State state;
	private String input;
	private OutputTree output;
	private boolean running;
	private AutomatonThreadManager manager;

	private Configuration(final Automaton out, final State state,
			final String input, final OutputTree output,
			AutomatonThreadManager manager) {
		this.out = out;
		this.state = state;
		this.input = input;
		this.output = output;
		this.running = false;
		this.manager = manager;
	}

	/**
	 * Let the configuration run. Performs steps of the automaton until the
	 * thread is stopped or there are no successors or the input is empty.
	 */
	public void run() {
		while (this.running) {
			if (this.getInput().isEmpty()) {
				this.running = false;
			} else {
				final char c = this.getInput().charAt(0);
				final Collection<StateTransition> possibleStateTransitions = this
						.getState().get(c);
				if (possibleStateTransitions.isEmpty()) {
					this.running = false;
					this.manager.threadEnds(this, false, this.output);
					return;
				} else {
					final String newInput = this.getInput().substring(1);
					Iterator<StateTransition> stateTransitions = possibleStateTransitions
							.iterator();
					StateTransition first = stateTransitions.next();
					while (stateTransitions.hasNext()) {
						StateTransition current = stateTransitions.next();
						this.manager.configurationHasSeveralSuccessorsDetected(
								current.getTo(), newInput,
								this.output.addElement(current.getOutput()));
					}
					this.state = first.getTo();
					this.input = newInput;
					this.output = this.output.addElement(first.getOutput());
				}
			}
		}
		this.manager.threadEnds(this, this.isEndConfiguration(), this.output);
	}

	/**
	 * Starts the receiver as a new thread.
	 */
	public void start() {
		this.running = true;
		new Thread(this).start();
	}

	/**
	 * Ends the receiver after the current step.
	 */
	public void stop() {
		this.running = false;
	}

	/**
	 * Checks whether the current configuration is an endconfiguration. A
	 * endconfiguration is a configuration with the endstate of the automaton as
	 * the current state.
	 * 
	 * @return true only if the current configuration is an endconfiguration.
	 */
	public boolean isEndConfiguration() {
		return this.getOut().getEnd().equals(this.getState());
	}

	public Automaton getOut() {
		return this.out;
	}

	public State getState() {
		return this.state;
	}

	public String getInput() {
		return this.input;
	}
}
