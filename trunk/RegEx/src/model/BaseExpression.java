package model;

import automaton.Automaton;

/**
 * A BaseExpression is an expression for a character.
 */
public class BaseExpression extends RegularExpression{

	final private char c;

	public BaseExpression(final char c) {
		this.c = c;	
	}

	@Override
	public Automaton toBaseAutomaton() {
		return Automaton.create(this.getC());
	}

	public char getC() {
		return this.c;
	}

	@Override
	public boolean contains(final RegularExpression argument) {
		return this.equals(argument);
	}

	@Override
	public boolean equals(final Object argument) {
		if (argument instanceof BaseExpression){
			final BaseExpression argumentAsBaseExpression = (BaseExpression) argument;
			return this.isIterated() == argumentAsBaseExpression.isIterated()&&this.getC()==argumentAsBaseExpression.getC();
		}
		return false;
	}
}
