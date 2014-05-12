package expressions;

import java.util.Collection;
import java.util.Iterator;

import automaton.Automaton;

/**
 * A Sequence is a CompositeExpression which represents the sequence of all parts.
 */
public class Sequence extends CompositeExpression{

	public static Sequence create(final Collection<RegularExpression> parts){
		return new Sequence(parts);
	}
	
	private Sequence(final Collection<RegularExpression> parts) {
		super(parts);
	}

	@Override
	public Automaton toBaseAutomaton() {
		final Automaton result = Automaton.create();
		result.setOptional(true);
		final Iterator<RegularExpression> iterator = this.getParts().iterator();
		while (iterator.hasNext()){
			final RegularExpression current = iterator.next();
			result.sequence(current.toAutomaton());
			result.setOptional(result.isOptional() && current.isOptional());
		}
		return result;
	}

	@Override
	public boolean equals(final Object argument) {
		if (argument instanceof Sequence){
			final Sequence argumentAsSequence = (Sequence) argument;
			return this.compositeEquals(argumentAsSequence);
		}
		return false;
	}

	@Override
	public void add(final RegularExpression argument) {
		if (argument.contains(this)) {
			throw new CycleException();
		}
		this.getParts().add(argument);
	}
	
	

}
