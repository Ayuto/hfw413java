package model;

import java.util.List;
import java.util.Stack;

/**
 * A generic configuration for a current state and and an input for a automaton.
 * 
 * @param <Element>
 */
public class Configuration<Element> {

	/**
	 * Creates a new configuration with the given values.
	 * 
	 * @param automaton
	 * @param startState
	 * @param elementList
	 * @return the created configuration.
	 */
	public static <Element> Configuration<Element> create(
			final Automaton<Element, ? extends Type<Element>> automaton,
			final State startState, final List<Element> elementList) {
		return new Configuration<Element>(automaton, startState, elementList);
	}

	private final Automaton<Element, ? extends Type<Element>> automaton;
	private final List<Element> elementList;
	private final Stack<StateSet> stateHistory;

	private Configuration(
			Automaton<Element, ? extends Type<Element>> automaton,
			State startState, List<Element> elementList) {
		this.automaton = automaton;
		this.elementList = elementList;
		this.stateHistory = new Stack<StateSet>();
		this.getStateHistory().push(StateSet.create(startState));
	}

	/**
	 * Lets the automaton run.
	 * 
	 * @return the last configuration with the empty input.
	 */
	public Configuration<Element> run() {
		java.util.Iterator<Element> inputStream = this.getElementList()
				.iterator();
		while (inputStream.hasNext())
			this.setStates(this.step(inputStream.next()));
		return this;
	}

	private Stack<StateSet> getStateHistory() {
		return this.stateHistory;
	}

	private StateSet step(Element current) {
		StateSet result = StateSet.create();
		java.util.Iterator<State> states = this.getStates().iterator();
		while (states.hasNext())
			result.addStateSet(this.step(current, states.next()));
		return result;
	}

	private StateSet step(Element element, State fromState) {
		return this.getAutomaton().getSuccessorStates(fromState, element);
	}

	private Automaton<Element, ? extends Type<Element>> getAutomaton() {
		return this.automaton;
	}

	private List<Element> getElementList() {
		return this.elementList;
	}

	/**
	 * Checks whether the current configuration is an end configuration.
	 * 
	 * @return {@code True} only if the receiver represents a end configuration
	 *         of its automaton, otherwise {@code False}.
	 */
	public boolean isEndConfiguration() {
		return this.getStates().contains(automaton.getStopState());
	}

	private StateSet getStates() {
		return this.getStateHistory().peek();
	}

	private void setStates(StateSet states) {
		this.getStateHistory().push(states);
	}
}
