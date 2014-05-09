package model;

import automaton.Automaton;

public abstract class RegularExpression {
	
	private boolean iterated;
	
	public abstract Automaton toBaseAutomaton();
	
	public Automaton toAutomaton(){
		final Automaton result = this.toBaseAutomaton();
		if (this.isIterated()){
			result.iterate();
		}
		return result;
	}
	
	public void setIterated(final boolean iterated) {
		this.iterated = iterated;
	}

	public boolean isIterated() {
		return this.iterated;
	}
	
	/**
	 * Returns true only if the receiver contains the argument.
	 */
	public abstract boolean contains(RegularExpression argument);
	
	@Override
	public abstract boolean equals(final Object argument);
}
