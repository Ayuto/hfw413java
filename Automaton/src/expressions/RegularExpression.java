package expressions;

import automaton.model.Automaton;

/**
 * An abstract class for all RegularExpressions. A RegularExpression can be
 * optional or/and iterated.
 */
public abstract class RegularExpression {

	private boolean optional;
	private boolean iterated;

	protected abstract Automaton toBaseAutomaton();

	public Automaton toAutomaton() {
		Automaton result = this.toBaseAutomaton();
		if (this.isIterated()) {
			result.iterated();
		}
		return result;
	}
	
	/**
	 * Adds the RegularExpression {@code <ex>} to the container of the receiver.
	 */
	public abstract void add (RegularExpression ex);

	/**
	 * Returns true only if {@code <this>} contains the regularExpression
	 * {@code <ex>}.
	 */
	public abstract boolean contains(RegularExpression ex);

	/**
	 * @param text
	 * @return true if and only if the text belongs to the language of the
	 *         receiver.
	 */
	public boolean check(final String text) {
		return this.toAutomaton().recognizes(text);
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public boolean isIterated() {
		return iterated;
	}

	public void setIterated(boolean iterated) {
		this.iterated = iterated;
	}
}