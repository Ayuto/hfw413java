package expressions;

import automaton.model.Automaton;

public class Element extends RegularExpression {

	public static Element create (char c, boolean optional, boolean iterated) {
		return new Element(c, optional, iterated);
	}
	
	private final char character;
	
	private Element(char c, boolean optional, boolean iterated) {
		super(optional, iterated);
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

}
