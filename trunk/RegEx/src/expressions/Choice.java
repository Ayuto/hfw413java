package expressions;

import java.util.Collection;
import java.util.Iterator;

import automaton.Automaton;

/**
 * A Choice is a CompositeExpression which accepts all parts.
 */
public class Choice extends CompositeExpression{
	
	public static Choice create(final Collection<RegularExpression> parts){
		return new Choice(parts);
	}

	private Choice(final Collection<RegularExpression> parts) {
		super(parts);
	}

	@Override
	public Automaton toBaseAutomaton() {
		final Automaton result = Automaton.create();
		final Iterator<RegularExpression> iterator = this.getParts().iterator();
		while (iterator.hasNext()){
			final RegularExpression current = iterator.next();
			if (current.isOptional()){
				result.setOptional(true);
			}
			result.choice(current.toAutomaton());
		}
		return result;
	}

	@Override
	public boolean equals(final Object argument) {
		if (argument instanceof Choice){
			final Choice argumentAsChoice = (Choice) argument;
			return this.isIterated() == argumentAsChoice.isIterated() && this.isOptional() == argumentAsChoice.isOptional() && this.getParts().equals(argumentAsChoice.getParts());
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
