package model;

import java.util.Collection;
import java.util.Iterator;

/**
 * A current configuration of an automaton with a collection of all current
 * states and an input string. All current states should always belong to
 * {@code <out>}!
 */
public class Configuration implements Runnable {

	/**
	 * Creates a configuration of the given automaton with the given states and
	 * the given input.
	 * 
	 * @param out
	 *            The automaton of the configuration.
	 * @param states
	 *            The current states of the configuration.
	 * @param input
	 *            The input of the configuration.
	 * @return The created configuration.
	 */
	public static Configuration create(final Automaton out, final State state,
			final String input, final OutputTree output) {
		return new Configuration(out, state, input, output);
	}

	public static Configuration create(final Automaton out, final String input) {
		return Configuration.create(out, out.getStart(), input,
				TreeRoot.getInstance());
	}

	private final Automaton out;
	private State state;
	private String input;
	private OutputTree output;
	private boolean running;

	private Configuration(final Automaton out, final State state,
			final String input, final OutputTree output) {
		this.out = out;
		this.state = state;
		this.input = input;
		this.output = output;
		this.running = false;
	}

	/**
	 * Performs a step of the configuration. If the input of the configuration
	 * is empty: {@code this.step().equals(this)}!
	 * 
	 * @return The new configuration after the performed step.
	 */
	public void run() {
		while (this.running) {
			if (this.getInput().isEmpty()) {
				this.getOut().threadEnds(this, this.isEndConfiguration(),
						this.output);
				this.running = false;
				return;
			}
			final String newInput = this.getInput().substring(1);
			final char c = this.getInput().charAt(0);
			final Collection<StateTransition> possibleStateTransitions = this
					.getState().get(c);
			if (possibleStateTransitions.isEmpty()) {
				this.getOut().threadEnds(this, false, this.output);
				this.running = false;
				return;
			}
			Iterator<StateTransition> stateTransitions = possibleStateTransitions
					.iterator();
			StateTransition first = stateTransitions.next();
			while (stateTransitions.hasNext()) {
				StateTransition current = stateTransitions.next();
				this.getOut().configurationHasSeveralStatesDetected(
						current.getTo(), newInput,
						this.output.addElement(current.getOutput()));
			}
			this.state = first.getTo();
			this.input = newInput;
			this.output = this.output.addElement(first.getOutput());
		}
		this.getOut().threadEnds(this, false, this.output);
	}

	public void start() {
		this.running = true;
		new Thread(this).start();
	}

	public void stop() {
		this.running = false;
	}

	/**
	 * Checks whether the current configuration is an endconfiguration or not. A
	 * endconfiguration is a configuration with the endstate of the automaton in
	 * it's states.
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
