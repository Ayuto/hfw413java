package expressions;

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
	protected Automaton toBaseAutomaton() {
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
	protected boolean baseEquals(final RegularExpression argument) {
		if (argument instanceof BaseExpression) {
			final BaseExpression argumentAsBaseExpression = (BaseExpression) argument;
			return this.getC() == argumentAsBaseExpression.getC();
		}
		return false;
	}

	@Override
	public void add(final RegularExpression argument) {
		throw new UnsupportedOperationException("You can not add a expression to a elementary Expression.");
	}
}
