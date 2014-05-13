package expressions;

import java.util.Collection;
import java.util.Iterator;

/**
 * A CompositeExpression is a container for RegularExpression objects.
 */
public abstract class CompositeExpression extends RegularExpression{
	
	final private Collection<RegularExpression> parts;

	public CompositeExpression(final Collection<RegularExpression> parts){
		this.parts = parts;
	}

	@Override
	public boolean contains(final RegularExpression argument) {
		if (this.equals(argument)) {
			return true;
		}
		final Iterator<RegularExpression> iterator = this.getParts().iterator();
		while (iterator.hasNext()) {
			final RegularExpression current = iterator.next();
			if (current.contains(argument)) {
				return true;
			}
		}
		return false;
	}

	public Collection<RegularExpression> getParts() {
		return this.parts;
	}
}
