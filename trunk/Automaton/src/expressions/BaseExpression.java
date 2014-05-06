package expressions;

import automaton.model.Automaton;

/**
 * A BaseExpression is the expression for just one character.
 */
public class BaseExpression extends RegularExpression {

	public static BaseExpression create(char c) {
		return new BaseExpression(c);
	}

	private final char character;

	private BaseExpression(char c) {
		this.character = c;
	}

	@Override
	protected Automaton toBaseAutomaton() {
		return Automaton.create(this.getCharacter());
	}

	public char getCharacter() {
		return character;
	}

	@Override
	public boolean contains(RegularExpression ex) {
		return this.equals(ex);
	}

	@Override
	public void add(RegularExpression ex) {
		throw new UnsupportedOperationException();
	}

}
