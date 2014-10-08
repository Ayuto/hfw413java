package model;

public class RectangularPart extends RectangularArea {

	RectangularArea parent;

	public RectangularPart(Point position, int width, int height) {
		super(position, width, height);
	}

	public String toString() {
		return super.toString() + " { PARENT: " + (this.getParent() != null ? this.getParent().toString() : "null")
				+ " }";
	}

	private RectangularArea getParent() {
		return this.parent;
	}

	public void setParent(RectangularArea parent) throws HierarchyException {
		Point pointPart = this.getLeftUpperCorner();
		Point pointParent = parent.getLeftUpperCorner();
		if (!(pointParent.getX() <= pointPart.getX()
				&& pointParent.getX() + parent.getWidth() >= pointPart.getX()
						+ this.getWidth()
				&& pointParent.getY() >= pointPart.getY() && pointParent.getY()
				+ parent.getHeight() <= pointPart.getY() + this.getHeight())) {
			throw new Error();
		} else {
			if (parent.isIn(this))
				throw new HierarchyException();
			this.parent = parent;
		}
	}

	boolean isInTransitively(RectangularPart part) {
		if (this.getParent() == null)
			return false;
		return this.getParent().isInTransitively(part);
	}
	
	public boolean equals(Object o) {
		boolean equals = false;
		if(o instanceof RectangularPart) {
			RectangularPart part = (RectangularPart) o;
			equals = this.getLeftLowerCorner().equals(part.getLeftLowerCorner())&&this.getHeight()==part.getHeight()&&this.getWidth()==part.getWidth();
		}
		return equals;
	}
}

@SuppressWarnings("serial")
class HierarchyException extends Exception {
	public HierarchyException() {
		super("No cycles!!!");
	}

}
