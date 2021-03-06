package expressions;

import automaton.Automaton;

/**
 * A abstract class for all regular expressions.
 */
public abstract class RegularExpression {
	
	private boolean iterated;
	private boolean optional;
	
	/**
	 * Returns the base automaton of the receiver which ignores the iterated state.
	 */
	protected abstract Automaton toBaseAutomaton();
	
	/**
	 * Returns the automaton of the receiver.
	 */
	public Automaton toAutomaton(){
		final Automaton result = this.toBaseAutomaton();
		if (this.isIterated()){
			result.iterate();
		}
		result.setOptional(this.isOptional());
		return result;
	}
	
	/**
	 * Returns true only if the receiver contains the argument.
	 */
	public abstract boolean contains(RegularExpression argument);
	
	/**
	 * Adds the argument to the container of the receiver.
	 * @throws UnsupportedOperationException if the receiver is a BaseExpression
	 * @throws CycleException if the argument contains already the receiver.
	 */
	public abstract void add(RegularExpression argument) throws CycleException;
	
	protected abstract boolean baseEquals(final RegularExpression argument);
	
	@Override
	public boolean equals(final Object argument) {
		if (argument instanceof RegularExpression) {
			final RegularExpression argumentAsRegularExpression = (RegularExpression) argument;
			return this.isIterated() == argumentAsRegularExpression.isIterated() && this.isOptional() == argumentAsRegularExpression.isOptional() && this.baseEquals(argumentAsRegularExpression);
		}
		return false;
	}
	
	/**
	 * @param text
	 * @return true if and only if the text 
	 * belongs to the language of the receiver.
	 */
	public boolean check(final String text) {
		return this.toAutomaton().recognizes(text);
	}
	
	public void setIterated(final boolean iterated) {
		this.iterated = iterated;
	}

	public boolean isIterated() {
		return this.iterated;
	}

	public boolean isOptional() {
		return this.optional;
	}

	public void setOptional(final boolean optional) {
		this.optional = optional;
	}
}