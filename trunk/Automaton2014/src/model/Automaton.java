package model;

import java.util.Iterator;
import java.util.List;

/**
 * A generic non deterministic finite automaton.
 * 
 * @param <Element>
 * @param <ElementType>
 */
public class Automaton<Element, ElementType extends Type<Element>> {

	/**
	 * Creates a non deterministic finite automaton with the given Type which
	 * recognizes nothing.
	 * 
	 * @return The created automaton.
	 */
	public static <Element, ElementType extends Type<Element>> Automaton<Element, ElementType> create() {
		return new Automaton<Element, ElementType>();
	}

	/**
	 * Creates a non deterministic finite automaton with the given Type which
	 * recognizes the given element.
	 * 
	 * @return The created automaton.
	 */
	public static <Element, ElementType extends Type<Element>> Automaton<Element, ElementType> create(
			ElementType elementType) {
		return new Automaton<Element, ElementType>(elementType);
	}

	private State startState;
	private State stopState;
	private final TransitionManager<Element, ElementType> transitionManager;
	private boolean optional = false;

	private Automaton() {
		this.transitionManager = TransitionManager.create();
		this.startState = State.create();
		this.stopState = State.create();
	}

	private Automaton(final ElementType elementType) {
		this();
		this.addTransition(this.getStartState(), elementType,
				this.getStopState());
	}

	/**
	 * Adds a Transition from {@code<fromState>} with the input
	 * {@code<elementType>} to {@code<toState>}.
	 * 
	 * @param fromState
	 * @param elementType
	 * @param toState
	 */
	public void addTransition(final State fromState,
			final ElementType elementType, final State toState) {
		this.getTransitionManager().addTransition(fromState, elementType,
				toState);
	}

	private TransitionManager<Element, ElementType> getTransitionManager() {
		return this.transitionManager;
	}

	public State getStartState() {
		return this.startState;
	}

	public State getStopState() {
		return this.stopState;
	}

	public StateSet getStates() {
		return this.getTransitionManager().getStates();
	}

	/**
	 * Checks if the receiver recognizes the given input {@code<elementList>}.
	 * 
	 * @param elementList
	 * @return {@code True} only if the receiver recognizes the input
	 *         {@code<elementList>}, otherwise {@code False}.
	 */
	public boolean recognizes(final List<Element> elementList) {
		if (optional && elementList.isEmpty()) {
			return true;
		}
		return this.createStartConfiguration(elementList).run()
				.isEndConfiguration();
	}

	private Configuration<Element> createStartConfiguration(
			final List<Element> elementList) {
		return Configuration.create(this, this.getStartState(), elementList);
	}

	/**
	 * Calculate all states which are reachable from the given
	 * {@code<fromState>} with the given input {@code<element>}
	 * 
	 * @param fromState
	 * @param element
	 * @return A set with all reachable states.
	 */
	public StateSet getSuccessorStates(final State fromState,
			final Element element) {
		return this.getTransitionManager().getSuccessorStates(fromState,
				element);
	}

	/**
	 * Changes the receiver to the iterated automaton of it.
	 */
	public void iterate() {
		Iterator<Triple<ElementType>> iterator = this.getTransitionManager()
				.getPredecessorTransitions(this.getStopState()).iterator();
		while (iterator.hasNext()) {
			Triple<ElementType> current = iterator.next();
			this.getTransitionManager().addTransition(current.getFromState(),
					current.getElementType(), this.getStartState());
		}
	}

	/**
	 * Changes the receiver to the choice of it and the given second automaton.
	 * 
	 * @param automaton
	 */
	public void choice(Automaton<Element, ElementType> automaton) {
		this.setOptional(this.isOptional() || automaton.isOptional());
		this.transitionManager.addTransitions(automaton.transitionManager);
		State newStartState = State.create();
		State newStopState = State.create();
		this.createEmptyTransitionTo(newStartState, this.getStartState());
		this.createEmptyTransitionTo(newStartState, automaton.getStartState());
		this.createEmptyTransitionFrom(this.getStopState(), newStopState);
		this.createEmptyTransitionFrom(automaton.getStopState(), newStopState);
		this.startState = newStartState;
		this.stopState = newStopState;
	}

	/**
	 * Changes the receiver to the sequence of it followed up by the given
	 * second automaton.
	 * 
	 * @param automaton
	 */
	public void sequence(Automaton<Element, ElementType> automaton) {
		this.transitionManager.addTransitions(automaton.transitionManager);
		this.createEmptyTransitionFrom(this.getStopState(),
				automaton.getStartState());

		if (this.isOptional()) {
			this.createEmptyTransitionTo(this.getStartState(),
					automaton.getStartState());
		}
		if (automaton.isOptional()) {
			this.createEmptyTransitionFrom(this.getStopState(),
					automaton.getStopState());
		}
		this.stopState = automaton.getStopState();
		this.setOptional(this.isOptional() && automaton.isOptional());
	}

	private void createEmptyTransitionFrom(State fromState, State toState) {
		Iterator<Triple<ElementType>> iterator = this.getTransitionManager()
				.getPredecessorTransitions(fromState).iterator();
		while (iterator.hasNext()) {
			Triple<ElementType> current = iterator.next();
			this.getTransitionManager().addTransition(current.getFromState(),
					current.getElementType(), toState);
		}
	}

	private void createEmptyTransitionTo(State fromState, State toState) {
		Iterator<Triple<ElementType>> iterator = this.getTransitionManager()
				.getSuccessorTransitions(toState).iterator();
		while (iterator.hasNext()) {
			Triple<ElementType> current = iterator.next();
			this.getTransitionManager().addTransition(fromState,
					current.getElementType(), current.getToState());
		}
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}
}
