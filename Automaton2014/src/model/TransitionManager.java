package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A generic manager for all state transitions.
 * 
 * @param <Element>
 * @param <ElementType>
 */
public class TransitionManager<Element, ElementType extends Type<Element>> {

	/**
	 * Creates a new manager for state transitions without any transitions.
	 * 
	 * @return The created transition manager.
	 */
	public static <Element, ElementType extends Type<Element>> TransitionManager<Element, ElementType> create() {
		return new TransitionManager<Element, ElementType>();
	}

	private java.util.HashSet<Triple<ElementType>> transitions;

	private TransitionManager() {
		this.transitions = new java.util.HashSet<Triple<ElementType>>();
	}

	/**
	 * Adds the transition from {@code<fromState>} by {@code<elementType>} to
	 * {@code<toState>} to the receiver.
	 * 
	 * @param fromState
	 * @param elementType
	 * @param toState
	 */
	public void addTransition(final State fromState,
			final ElementType elementType, final State toState) {
		this.getTransitions().add(
				Triple.create(fromState, elementType, toState));
	}

	private HashSet<Triple<ElementType>> getTransitions() {
		return this.transitions;
	}

	/**
	 * Calculates all states which are needed for at least one of the receivers
	 * transitions.
	 * 
	 * @return A set with all these states.
	 */
	public StateSet getStates() {
		final StateSet result = StateSet.create();
		final java.util.Iterator<Triple<ElementType>> transitions = this
				.getTransitions().iterator();
		while (transitions.hasNext()) {
			Triple<ElementType> current = transitions.next();
			result.addState(current.getFromState());
			result.addState(current.getToState());
		}
		return result;
	}

	/**
	 * Calculates all states which are reachable with the given input from the
	 * given {@code<fromState>} with the transitions of the receiver.
	 * 
	 * @param fromState
	 * @param element
	 * @return A set of all these states.
	 */
	public StateSet getSuccessorStates(final State fromState,
			final Element element) {
		final StateSet result = StateSet.create();
		final java.util.Iterator<Triple<ElementType>> triples = this
				.getTransitions().iterator();
		while (triples.hasNext()) {
			Triple<ElementType> current = triples.next();
			ElementType currentType = current.getElementType();
			State currentFromState = current.getFromState();
			if (currentFromState.equals(fromState)
					&& currentType.isTypeFor(element))
				result.addState(current.getToState());
		}
		return result;
	}

	/**
	 * Calculates all transitions of the receiver which are possible from the
	 * given {@code<fromState>}.
	 * 
	 * @param fromState
	 * @return A set with all these transitions.
	 */
	public Set<Triple<ElementType>> getSuccessorTransitions(
			final State fromState) {
		final Set<Triple<ElementType>> result = new HashSet<Triple<ElementType>>();
		final Iterator<Triple<ElementType>> triples = this.getTransitions()
				.iterator();
		while (triples.hasNext()) {
			Triple<ElementType> current = triples.next();
			State currentFromState = current.getFromState();
			if (currentFromState.equals(fromState))
				result.add(current);
		}
		return result;
	}

	/**
	 * Calculates all transitions of the receiver which can lead to the given
	 * {@code<toState>}.
	 * 
	 * @param toState
	 * @return A set with all these transitions.
	 */
	public Set<Triple<ElementType>> getPredecessorTransitions(
			final State toState) {
		final Set<Triple<ElementType>> result = new HashSet<Triple<ElementType>>();
		final Iterator<Triple<ElementType>> triples = this.getTransitions()
				.iterator();
		while (triples.hasNext()) {
			Triple<ElementType> current = triples.next();
			State currentToState = current.getToState();
			if (currentToState.equals(toState))
				result.add(current);
		}
		return result;
	}

	/**
	 * Adds all transitions from the given {@code<transitionsManager>} to the
	 * receivers transitions.
	 * 
	 * @param transitionManager
	 */
	public void addTransitions(
			TransitionManager<Element, ElementType> transitionManager) {
		this.transitions.addAll(transitionManager.transitions);
	}
}

/**
 * A generic triple for 2 states and an elementType representing a state
 * transition.
 * 
 * @param <ElementType>
 */
class Triple<ElementType> {

	/**
	 * Creates a new triple/transition with the given values.
	 * 
	 * @param fromState
	 * @param elementType
	 * @param toState
	 * @return The created triple/transition.
	 */
	public static <ElementType> Triple<ElementType> create(State fromState,
			ElementType elementType, State toState) {
		return new Triple<ElementType>(fromState, elementType, toState);
	}

	final private State fromState;
	final private State toState;
	final ElementType elementType;

	private Triple(final State fromState, final ElementType elementType,
			final State toState) {
		this.fromState = fromState;
		this.elementType = elementType;
		this.toState = toState;
	}

	@Override
	public boolean equals(final Object argument) {
		if (argument instanceof Triple) {
			@SuppressWarnings("rawtypes")
			Triple argumentAsTriple = (Triple) argument;
			return this.getFromState().equals(argumentAsTriple.getFromState())
					&& this.getToState().equals(argumentAsTriple.getToState())
					&& this.getElementType().equals(
							argumentAsTriple.getElementType());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.getFromState().hashCode();
	}

	State getFromState() {
		return this.fromState;
	}

	State getToState() {
		return this.toState;
	}

	ElementType getElementType() {
		return this.elementType;
	}
}