package model;

abstract public class RectangularArea extends Observee {

	private Point leftUpperCorner;
	private int width;
	private int height;

	protected RectangularArea(final Point position, final int width,
			final int height) {
		this.setLeftUpperCorner(position);
		this.setWidth(width < 0 ? 0 : width);
		this.setHeight(height < 0 ? 0 : height);
	}

	public int getHeight() {
		return this.height;
	}

	private void setHeight(final int height) {
		this.height = height;
	}

	public Point getLeftUpperCorner() {
		return this.leftUpperCorner;
	}

	public Point getRightUpperCorner() {
		return new Point(this.getLeftUpperCorner().getX() + this.getWidth(),
				this.getLeftUpperCorner().getY());
	}

	public Point getLeftLowerCorner() {
		return new Point(this.getLeftUpperCorner().getX(), this
				.getLeftUpperCorner().getY() + this.getHeight());
	}

	public Point getRightLowerCorner() {
		return new Point(this.getLeftLowerCorner().getX() + this.getWidth(),
				this.getLeftLowerCorner().getY());
	}

	private void setLeftUpperCorner(final Point leftUpperCorner) {
		this.leftUpperCorner = leftUpperCorner;
	}

	public int getWidth() {
		return this.width;
	}

	private void setWidth(final int width) {
		this.width = width;
	}

	public void move(final int deltaX, final int deltaY) {
		this.getLeftUpperCorner().move(deltaX, deltaY);
	}

	public void resize(final int width, final int height)
			throws NegativeLengthException {
		if (width <= 0) {
			throw new NegativeLengthException();
		}
		if (height <= 0) {
			throw new NegativeLengthException();
		}
		this.setHeight(height);
		this.setWidth(width);
	}

	@Override
	public String toString() {
		return "(WIDTH: " + this.getWidth() + ", HEIGHT: " + this.getHeight()
				+ ") at " + this.getLeftUpperCorner().toString();
	}

	public boolean isIn(final RectangularPart part) {
		if (this.equals(part)) {
			return true;
		}
		return this.isInTransitively(part);
	}

	public Rectangle getOverlappedArea(RectangularArea area) {
		Point p1 = this.getLeftUpperCorner();
		Point p2 = area.getLeftUpperCorner();

		int xStart = Math.max(p1.getX(), p2.getX());
		int xEnd = Math.min(p1.getX() + this.getWidth(),
				p2.getX() + area.getWidth());

		int yStart = Math.max(p1.getY(), p2.getY());
		int yEnd = Math.min(p1.getY() + this.getHeight(),
				p2.getY() + area.getHeight());

		if (xStart >= xEnd || yStart >= yEnd) {
			try {
				return new Rectangle(new Point(0, 0), 0, 0);
			} catch (NegativeLengthException e) {
				throw new Error("Can't happen!!!");
			}
		}

		try {
			return new Rectangle(new Point(xStart, yStart), xEnd - xStart, yEnd
					- yStart);
		} catch (NegativeLengthException e) {
			throw new Error("Can't happen!!!");
		}
	}

	public boolean isEmpty() {
		return this.getHeight() == 0 && this.getWidth() == 0;
	}

	public boolean doesNotOverlap(RectangularArea area) {
		return this.getOverlappedArea(area).isEmpty();
	}

	public Point[] getEdges() {
		Point[] result = { this.getLeftUpperCorner(),
				this.getRightUpperCorner(), this.getRightLowerCorner(),
				this.getLeftLowerCorner() };
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RectangularArea) {
			RectangularArea objAsRect = (RectangularArea) obj;
			return objAsRect.getLeftUpperCorner().equals(
					this.getLeftUpperCorner())
					&& objAsRect.getWidth() == this.getWidth()
					&& objAsRect.getHeight() == this.getHeight();
		}
		return false;
	}
	
	public RectangularPartCollection split(RectangularArea overlappedArea) {
		Point p1 = this.getLeftUpperCorner();
		Point p5 = overlappedArea.getLeftUpperCorner();
		Point p6 = overlappedArea.getRightUpperCorner();
		Point p3 = new Point(p6.getX(), p1.getY());
		Point p4 = new Point(p1.getX(), p5.getY());
		Point p2 = new Point(p5.getX(), p1.getY());
		Point p8 = overlappedArea.getLeftLowerCorner();
		Point p7 = new Point(p1.getX(), p8.getY());
		Point p9 = overlappedArea.getRightLowerCorner();
		Point p10 = this.getRightUpperCorner();
		Point p11 =  new Point(p10.getX(), p4.getY());
		Point p13 = this.getLeftLowerCorner();
		Point p12 = new Point(p10.getX(), p7.getY());
		
		RectangularPartCollection result = new RectangularPartCollection();
		result.createAndAddPart(p1, p2.getX()-p1.getX(), p4.getY()-p1.getY());
		result.createAndAddPart(p2, p3.getX()-p2.getX(), p5.getY()-p2.getY());
		result.createAndAddPart(p3, p10.getX()-p3.getX(), p6.getY()-p3.getY());
		result.createAndAddPart(p4, p5.getX()-p4.getX(), p7.getY()-p4.getY());
		result.createAndAddPart(p6, p11.getX()-p6.getX(), p9.getY()-p6.getY());
		result.createAndAddPart(p7, p8.getX()-p7.getX(), p13.getY()-p7.getY());
		result.createAndAddPart(p8, p9.getX()-p8.getX(), p13.getY()-p8.getY());
		result.createAndAddPart(p9, p12.getX()-p9.getX(), p13.getY()-p9.getY());
		return result;
	}

	abstract boolean isInTransitively(RectangularPart part);
}
