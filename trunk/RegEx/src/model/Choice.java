package model;

import java.util.Collection;
import java.util.Iterator;

import automaton.Automaton;

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
			result.choice(current.toAutomaton());
		}
		return result;
	}

	@Override
	public boolean equals(final Object argument) {
		if (argument instanceof Choice){
			final Choice argumentAsChoice = (Choice) argument;
			return this.isIterated()==argumentAsChoice.isIterated()&&this.getParts().equals(argumentAsChoice.getParts());
		}
		return false;
	}

}
