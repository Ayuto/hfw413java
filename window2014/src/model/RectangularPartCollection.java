package model;

import java.util.Collection;
import java.util.Vector;

public class RectangularPartCollection {

	private Collection<RectangularPart> parts;

	public RectangularPartCollection() {
		this.setParts(new Vector<RectangularPart>());
	}

	public Collection<RectangularPart> getParts() {
		return parts;
	}

	public void setParts(Collection<RectangularPart> parts) {
		this.parts = parts;
	}

	public void add(RectangularPartCollection partCollection) {
		/*
		if (this.getParts().isEmpty()) {
			this.getParts().addAll(partCollection.getParts());
			return;
		}
		
		for (final RectangularPart currentPart : partCollection.getParts()) {
			for (final RectangularPart current : this.getParts()) {
				if (current.doesNotOverlap(currentPart))
					this.add(currentPart);
			}
		}*/
		this.getParts().addAll(partCollection.getParts());
	}

	public Vector<RectangularPart> toVector() {
		return (Vector<RectangularPart>) this.parts;
	}

	public void add(RectangularPart part) {
		this.getParts().add(part);
	}

	public boolean equals(Object o) {
		if (o instanceof RectangularPartCollection) {
			RectangularPartCollection partsCollection = (RectangularPartCollection) o;
			return this.getParts().equals(partsCollection.getParts());
		}
		return false;
	}

	@Override
	public String toString() {
		return this.parts.toString();
	}
	
	public void createAndAddPart(Point p, int w, int h) {
		if(w != 0 && h != 0) {
			this.getParts().add(new RectangularPart(p, w, h));
		}
	}
}
