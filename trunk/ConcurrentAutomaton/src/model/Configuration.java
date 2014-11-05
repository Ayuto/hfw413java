package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import model.tree.Node;

/**
 * A current configuration of an automaton with the current state and an input
 * string. The current state should always belong to {@code <out>}!
 */
public class Configuration implements Runnable {

	/**
	 * Creates a configuration of the given automaton with the given state and
	 * the given input.
	 * 
	 * @param out
	 *            The automaton of the configuration.
	 * @param state
	 *            The current state of the configuration.
	 * @param input
	 *            The input of the configuration.
	 * @return The created configuration.
	 */
	public static Configuration create(final Automaton out, final Node<String> parent, State state,
			final String input) {
		return new Configuration(out, parent, state, input);
	}

	private final Automaton out;
	private Node<String> parent;
	private String input;
	private boolean isRunning;
	private State state;

	private Configuration(final Automaton out, final Node<String> parent, State state,
			final String input) {
		this.out = out;
		this.parent = parent;
		this.input = input;
		this.isRunning = false;
		this.state = state;
	}

	/**
	 * Performs a step of the configuration. If the input of the configuration
	 * is empty: {@code this.step().equals(this)}!
	 * 
	 * @return The new configuration after the performed step.
	 */
	public void step() {
		if (this.getInput().length() == 0) {
			this.endThread();
			return;
		}
		final Collection<StateTransition> result = new HashSet<StateTransition>();
		final char c = this.getInput().charAt(0);
		result.addAll(this.getState().get(c));
		if (result.isEmpty()) {
			this.endThread();
			return;
		}
		
		Iterator<StateTransition> newStateTransitions = result.iterator();
		StateTransition first = newStateTransitions.next();
		Node<String> p = this.parent.addElement(first.getOutput());
		this.state = first.getTo();
		
		String newInput = this.getInput().substring(1);
		this.input = newInput;
		while (newStateTransitions.hasNext()) {
			StateTransition current = newStateTransitions.next();
			Node<String> newParent = this.parent.addElement(current.getOutput());
			this.getOut().hasSeveralSuccessors(newParent, current.getTo(), newInput);
		}
		this.parent = p;
	}

	private void endThread() {
		if (this.isEndConfiguration()) {
			this.getOut().threadFinished(this, true, this.parent);
		} else {
			this.getOut().threadFinished(this, false, parent);
		}
	}

	/**
	 * Runs the configuration until the input is empty. If the input is already
	 * empty: {@code this.run().equals(this)}.
	 * 
	 * @return The final configuration after performing all steps for the whole
	 *         input.
	 */
	public void run() {
		while (this.isRunning) {
			this.step();
		}
	}

	/**
	 * Checks whether the current configuration is an endconfiguration or not. A
	 * endconfiguration is a configuration with the endstate of the automaton in
	 * it's states.
	 * 
	 * @return true only if the current configuration is an endconfiguration.
	 */
	public boolean isEndConfiguration() {
		return this.getState().isEndState();
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

	public void start() {
		this.isRunning = true;
		new Thread(this).start();
	}

	public void stop() {
		this.isRunning = false;
	}
}
