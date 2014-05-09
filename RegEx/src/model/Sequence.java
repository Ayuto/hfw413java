package model;

import java.util.Collection;
import java.util.Iterator;

import automaton.Automaton;

public class Sequence extends CompositeExpression{

	public static Sequence create(final Collection<RegularExpression> parts){
		return new Sequence(parts);
	}
	
	private Sequence(final Collection<RegularExpression> parts) {
		super(parts);
	}

	@Override
	public Automaton toBaseAutomaton() {
		final Iterator<RegularExpression> iterator = this.getParts().iterator();
		final Automaton result = iterator.next().toAutomaton();
		while (iterator.hasNext()){
			final RegularExpression current = iterator.next();
			result.sequence(current.toAutomaton());
		}
		return result;
	}

	@Override
	public boolean equals(final Object argument) {
		if (argument instanceof Sequence){
			final Sequence argumentAsSequence = (Sequence) argument;
			return this.isIterated()==argumentAsSequence.isIterated()&&this.getParts().equals(argumentAsSequence);
		}
		return false;
	}
	
	

}
