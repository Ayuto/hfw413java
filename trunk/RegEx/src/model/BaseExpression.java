package model;

import automaton.Automaton;

/**
 * A BaseExpression is an expression for a character.
 */
public class BaseExpression extends RegularExpression{

	public static BaseExpression create (final char c) {
		return new BaseExpression(c);
	}
	
	final private char c;

	private BaseExpression(final char c) {
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
			return this.isIterated() == argumentAsBaseExpression.isIterated() && this.isOptional() == argumentAsBaseExpression.isOptional() && this.getC()==argumentAsBaseExpression.getC();
		}
		return false;
	}

	@Override
	public void add(final RegularExpression argument) {
		throw new UnsupportedOperationException("You can not add a expression to a elementary Expression.");
	}
}
