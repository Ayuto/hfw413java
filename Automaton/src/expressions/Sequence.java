package expressions;

import java.util.Collection;
import java.util.Iterator;

import automaton.model.Automaton;

/**
 * A Sequence is a expression for one RegularExpression after another.
 */
public class Sequence extends CompositeExpression {

	public static Sequence create(Collection<RegularExpression> parts) {
		return new Sequence(parts);
	}

	private Sequence(Collection<RegularExpression> parts) {
		super(parts);
	}

	@Override
	protected Automaton toBaseAutomaton() {
		Automaton result = Automaton.create();
		result.setOptional(true);
		Iterator<RegularExpression> iterator = this.getParts().iterator();
		while (iterator.hasNext()) {
			RegularExpression current = iterator.next();
			result.sequence(current.toAutomaton());
		}
		return result;
	}

}
