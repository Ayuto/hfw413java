package expressions;

import java.util.Collection;

import automaton.model.Automaton;

public class Sequence extends Composite {

	public static Sequence create (Collection<RegularExpression> parts, boolean optional, boolean iterated) {
		if (parts.isEmpty()) {
			throw new EmptySequenceException();
		}
		return new Sequence(parts, optional, iterated);
	}
	
	private Sequence(Collection<RegularExpression> parts, boolean optional,
			boolean iterated) {
		super(parts, optional, iterated);
	}

	@Override
	protected Automaton toBaseAutomaton() {
		// TODO Auto-generated method stub
		return null;
	}

}
