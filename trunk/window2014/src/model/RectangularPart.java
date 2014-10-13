package model;

public class RectangularPart extends RectangularArea {

	RectangularArea parent;

	public RectangularPart(Point position, int width, int height) {
		super(position, width, height);
	}

	public RectangularPart(Point position, int width, int height,
			RectangularArea parent) throws HierarchyException {
		super(position, width, height);
		this.setParent(parent);
	}

	public String toString() {
		return super.toString()
				+ " { PARENT: "
				+ (this.getParent() != null ? this.getParent().toString()
						: "null") + " }";
	}

	private RectangularArea getParent() {
		return this.parent;
	}

	public void setParent(RectangularArea parent) throws HierarchyException {
		if (!parent.contains(this)) {
			throw new Error("<parent> does not contain <this>.");
		} else if (parent.isIn(this)) {
			throw new HierarchyException();
		} else {
			this.parent = parent;
		}
	}

	boolean isInTransitively(RectangularPart part) {
		if (this.getParent() == null)
			return false;
		return this.getParent().isInTransitively(part);
	}
}

@SuppressWarnings("serial")
class HierarchyException extends Exception {
	public HierarchyException() {
		super("No cycles!!!");
	}

}
