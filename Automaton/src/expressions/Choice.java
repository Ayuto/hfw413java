package expressions;

import java.util.Collection;
import java.util.Iterator;

import automaton.model.Automaton;

/**
 * A Choice is a RegularExpression for one expression or another.
 */
public class Choice extends CompositeExpression {

	public static Choice create(Collection<RegularExpression> parts) {
		return new Choice(parts);
	}

	private Choice(Collection<RegularExpression> parts) {
		super(parts);
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
