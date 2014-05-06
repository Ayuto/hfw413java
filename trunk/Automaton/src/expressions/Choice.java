package expressions;

import java.util.Collection;
import java.util.Iterator;

import automaton.model.Automaton;

public class Choice extends Composite {

	public static Choice create(Collection<RegularExpression> parts,
			boolean optional, boolean iterated) {
		return new Choice(parts, optional, iterated);
	}

	private Choice(Collection<RegularExpression> parts, boolean optional,
			boolean iterated) {
		super(parts, optional, iterated);
	}

	@Override
	protected Automaton toBaseAutomaton() {
		Automaton result = Automaton.create();
		Iterator<RegularExpression> iterator = this.getParts().iterator();
		while (iterator.hasNext()) {
			RegularExpression current = iterator.next();
			result.choice(current.toAutomaton());
		}
		return result;
	}
}
