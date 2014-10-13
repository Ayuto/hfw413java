package model;

import java.util.Collection;
import java.util.Vector;

public class RectangularPartCollection {

	private Collection<RectangularPart> parts;

	public RectangularPartCollection() {
		this.setParts(new Vector<RectangularPart>());
	}

	public Collection<RectangularPart> getParts() {
		return this.parts;
	}

	public void setParts(final Collection<RectangularPart> parts) {
		this.parts = parts;
	}

	public void add(final RectangularPartCollection partCollection) {
		this.getParts().addAll(partCollection.getParts());
	}

	public void add(final RectangularPart part) {
		this.getParts().add(part);
	}

	public Vector<RectangularPart> toVector() {
		return (Vector<RectangularPart>) this.parts;
	}

	@Override
	public boolean equals(final Object o) {
		if (o instanceof RectangularPartCollection) {
			final RectangularPartCollection partsCollection = (RectangularPartCollection) o;
			return this.getParts().equals(partsCollection.getParts());
		}
		return false;
	}

	@Override
	public String toString() {
		return this.parts.toString();
	}

	/**
	 * Creates and adds a new RectangularPart object if <w> and <h> are not 0.
	 * @param p given point of a rectangle.
	 * @param w given width of a rectangle.
	 * @param h given height of a rectangle.
	 * @throws HierarchyException 
	 */
	public void createAndAddPart(final Point p, final int w, final int h, final Window parent) throws HierarchyException {
		if (w != 0 && h != 0) {
			this.getParts().add(new RectangularPart(p, w, h, parent));
		}
	}
}
