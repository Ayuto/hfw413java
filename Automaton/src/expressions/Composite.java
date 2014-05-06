package expressions;

import java.util.Collection;
import java.util.Iterator;

public abstract class Composite extends RegularExpression {

	private final Collection<RegularExpression> parts;
	
	public Composite (Collection<RegularExpression> parts, boolean optional, boolean iterated) {
		super(optional, iterated);
		this.parts = parts;
	}

	public Collection<RegularExpression> getParts() {
		return parts;
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
