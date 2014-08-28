package model;

/**
 * A generic abstract regular expression which can be transformed to an
 * automaton.
 * 
 * @param <Element>
 * @param <ElementType>
 */
public abstract class RegularExpression<Element, ElementType extends Type<Element>> {

	private boolean iterated = false;
	private boolean optional = false;

	/**
	 * Transforms the receiver to an automaton.
	 * 
	 * @return returns the automaton for the receiver.
	 */
	public Automaton<Element, ElementType> toAutomaton() {
		Automaton<Element, ElementType> result = this.toBaseAutomaton();
		if (iterated) {
			result.iterate();
		}
		result.setOptional(optional);
		return result;
	}

	protected abstract Automaton<Element, ElementType> toBaseAutomaton();

	public boolean isIterated() {
		return iterated;
	}

	public void setIterated(boolean iterated) {
		this.iterated = iterated;
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}
}