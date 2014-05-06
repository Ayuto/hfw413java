package expressions;

import java.util.Collection;
import java.util.Iterator;

/**
 * A CompositeExpression is an abstract class for all expressions out of others.
 */
public abstract class CompositeExpression extends RegularExpression {

	private final Collection<RegularExpression> parts;

	public CompositeExpression(Collection<RegularExpression> parts) {
		super();
		this.parts = parts;
	}

	public Collection<RegularExpression> getParts() {
		return parts;
	}
	
	public void add(RegularExpression ex) {
		if (ex.contains(this)) {
			throw new CycleException();
		}
		this.getParts().add(ex);
	}

	@Override
	public boolean contains(RegularExpression ex) {
		if (this.equals(ex))
			return true;
		Iterator<RegularExpression> iterator = this.getParts().iterator();
		while (iterator.hasNext()) {
			RegularExpression current = iterator.next();
			if (current.contains(ex))
				return true;
		}
		return false;
	}
}
