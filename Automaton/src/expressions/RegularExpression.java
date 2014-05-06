package expressions;
import java.util.Random;

import automaton.model.Automaton;

public abstract class RegularExpression {
	
	private boolean optional;
	private boolean iterated;
	
	public RegularExpression(boolean optional, boolean iterated) {
		this.optional = optional;
		this.iterated = iterated;
	}
	
	protected abstract Automaton toBaseAutomaton();
	
	public Automaton toAutomaton() {
		Automaton result = this.toBaseAutomaton();
		if (this.isIterated()) {
			// TODO handle iterated
		}
		if (this.isOptional()) {
			// TODO handle optional
		}
		return result;
	}
	
	public abstract boolean contains(RegularExpression ex);

	/**
	 * @param text
	 * @return true if and only if the text 
	 * belongs to the language of the receiver.
	 */
	public boolean check(final String text) {
		// TODO 
		return new Random().nextBoolean();
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

//TODO Just for testing; remove in final implementation
class DummyExpression extends RegularExpression {

	public DummyExpression() {
		super(false, false);
	}

	public static RegularExpression create() {
		return new DummyExpression();
	}

	@Override
	protected Automaton toBaseAutomaton() {
		// TODO Just for testing!
		return null;
	}

	@Override
	public boolean contains(RegularExpression ex) {
		// TODO Just for testing!
		return false;
	}
	
}