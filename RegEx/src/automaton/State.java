package automaton;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A state belongs to an automaton and administrates all possible transitions.
 */
public class State {

	/**
	 * Creates a state from the automaton {@code <out>}.
	 * 
	 * @param out
	 *            The new state belongs to this automaton.
	 * @return The created state.
	 */
	public static State create(final Automaton out) {
		return new State(out);
	}

	private Automaton out;

	private State(final Automaton out) {
		this.setOut(out);
	}

	/**
	 * Adds a new transition to the automaton of {@code <this>} from the state
	 * {@code <this>}.
	 * 
	 * @param c
	 *            Input of the new transition.
	 * @param s
	 *            New state after the input of {@code <c>}.
	 */
	public void add(final char c, final State s) {
		this.getOut().getDelta().add(StateTransition.create(this, s, c));
	}

	/**
	 * Calculates a collection of all states reachable with the input
	 * {@code <c>} from {@code <this>}.
	 * 
	 * @param c
	 *            The given input.
	 * @return The StateCollection with all reachable states.
	 */
	public StateCollection get(final char c) {
		final StateCollection result = StateCollection.create();
		final Iterator<StateTransition> iterator = this.getOut().getDelta()
				.iterator();
		while (iterator.hasNext()) {
			final StateTransition current = iterator.next();
			if (current.getFrom().equals(this) && current.getBy() == c) {
				result.add(current.getTo());
			}
		}
		return result;
	}

	/**
	 * Calculates a collection of all states reachable from {@code <this>}.
	 // TODO
	 * @return The StateCollection with all reachable states.
	 */
	public Collection<StateTransition> fetchSuccessors() {
		final Collection<StateTransition> result = new LinkedList<StateTransition>();
		final Iterator<StateTransition> iterator = this.getOut().getDelta()
				.iterator();
		while (iterator.hasNext()) {
			final StateTransition current = iterator.next();
			if (current.getFrom().equals(this)) {
				result.add(current);
			}
		}
		return result;
	}

	/**
	 * Calculates a collection of all predecessor states from {@code <this>}.
	 * 
	 * @return The StateCollection with all predecessor states.
	 */
	public Collection<StateTransition> fetchPredecessors() {
		final Collection<StateTransition> result = new LinkedList<StateTransition>();
		final Iterator<StateTransition> iterator = this.getOut().getDelta()
				.iterator();
		while (iterator.hasNext()) {
			final StateTransition current = iterator.next();
			if (current.getTo().equals(this)) {
				result.add(current);
			}
		}
		return result;
	}
	
	/**
	 * Creates an empty Transition from the receiver to the argument in the Automaton of the receiver.
	 */
	public void createEmptyTransitionTo(final State to) {
		final Iterator<StateTransition> iterator = this.fetchPredecessors().iterator();
		while (iterator.hasNext()) {
			final StateTransition current = iterator.next();
			this.getOut().getDelta().add(StateTransition.create(current.getFrom(), to, current.getBy()));
		}
	}
	
	/**
	 * Creates an empty Transition from the argument to the receiver in the Automaton of the receiver.
	 */
	public void createEmptyTransitionFrom(final State from) {
		final Iterator<StateTransition> iterator = this.fetchSuccessors().iterator();
		while (iterator.hasNext()){
			final StateTransition current = iterator.next();
			this.getOut().getDelta().add(StateTransition.create(from, current.getTo(), current.getBy()));
		}
	}

	/**
	 * Checks whether {@code <this>} is an endstate.
	 * 
	 * @return true only if {@code <this>} is an endstate.
	 */
	public boolean isEndState() {
		return this.equals(this.getOut().getEnd());
	}

	public Automaton getOut() {
		return this.out;
	}

	public void setOut(final Automaton out) {
		this.out = out;
	}
}
