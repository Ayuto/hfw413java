package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class RectangularPartCollection {
	
	private Collection<RectangularPart> parts;

	public RectangularPartCollection(){
		this.setParts(new Vector<RectangularPart>());
	}

	public Collection<RectangularPart> getParts() {
		return parts;
	}

	public void setParts(Collection<RectangularPart> parts) {
		this.parts = parts;
	}

	public void add(RectangularPartCollection partCollection) {
		Iterator<RectangularPart> i1 = partCollection.getParts().iterator();
		while (i1.hasNext()) {
			RectangularPart currentPart = i1.next();
			if(!this.getParts().isEmpty()) {
				Iterator<RectangularPart> i2 = this.getParts().iterator();
				while (i2.hasNext()) {
					RectangularPart current = i2.next();
					if (!currentPart.doNotOverlap(current)){
						throw new Error();
					}
				}
			}
		}
		this.getParts().addAll(partCollection.parts);
	}

	public Vector<RectangularPart> toVector() {
		return (Vector<RectangularPart>) this.parts;
	}

	public void add(RectangularPart part) {
		this.getParts().add(part);
	}
}
