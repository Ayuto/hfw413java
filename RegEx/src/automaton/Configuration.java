package automaton;

import java.util.Iterator;

/**
 * A current configuration of an automaton with a collection of all current
 * states and an input string. All current states should always belong to
 * {@code <out>}!
 */
public class Configuration {

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
	public static Configuration create(final Automaton out,
			final StateCollection states, final String input) {
		return new Configuration(out, states, input);
	}

	private final Automaton out;
	private final StateCollection states;
	private final String input;

	private Configuration(final Automaton out, final StateCollection states,
			final String input) {
		this.out = out;
		this.states = states;
		this.input = input;
	}

	/**
	 * Performs a step of the configuration. If the input of the configuration
	 * is empty: {@code this.step().equals(this)}!
	 * 
	 * @return The new configuration after the performed step.
	 */
	public Configuration step() {
		if (this.getInput().length() == 0) {
			return this;
		}
		final StateCollection result = StateCollection.create();
		final char c = this.getInput().charAt(0);
		final Iterator<State> iterator = this.getStates().iterator();
		while (iterator.hasNext()) {
			final State current = iterator.next();
			result.addAll(current.get(c));
		}
		return Configuration.create(this.getOut(), result,
				this.input.substring(1));
	}

	/**
	 * Runs the configuration until the input is empty. If the input is already
	 * empty: {@code this.run().equals(this)}.
	 * 
	 * @return The final configuration after performing all steps for the whole
	 *         input.
	 */
	public Configuration run() {
		if (this.getInput().length() == 0) {
			return this;
		}
		return this.step().run();
	}

	/**
	 * Checks whether the current configuration is an endconfiguration or not. A
	 * endconfiguration is a configuration with the endstate of the automaton in
	 * it's states.
	 * 
	 * @return true only if the current configuration is an endconfiguration.
	 */
	public boolean isEndConfiguration() {
		final Iterator<State> iterator = this.getStates().iterator();
		while (iterator.hasNext()) {
			final State current = iterator.next();
			if (current.isEndState()) {
				return true;
			}
		}
		return false;
	}

	public Automaton getOut() {
		return this.out;
	}

	public StateCollection getStates() {
		return this.states;
	}

	public String getInput() {
		return this.input;
	}
}
