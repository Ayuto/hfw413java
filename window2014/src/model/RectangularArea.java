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

	public RectangularPartCollection splitAt(final Point point) {
		final int x = point.getX();
		final int y = point.getY();

		final Point middlePoint = new Point(this.getLeftUpperCorner().getX(), y);
		final Point upperPoint = new Point(x, this.getLeftUpperCorner().getY());

		final int rightWidth = this.getRightUpperCorner().getX()
				- upperPoint.getX();
		final int leftWidth = upperPoint.getX() - this.getLeftUpperCorner().getX();
		final int upperHeigth = middlePoint.getY() - this.getLeftUpperCorner().getY();
		final int lowerHeight = this.getLeftLowerCorner().getY() - middlePoint.getY();

		final RectangularPartCollection myCollection = new RectangularPartCollection();

		if (leftWidth > 0 && upperHeigth > 0) {
			myCollection.add(new RectangularPart(this.getLeftUpperCorner(),
					leftWidth, upperHeigth));
		}

		if (rightWidth > 0 && upperHeigth > 0) {
			myCollection.add(new RectangularPart(upperPoint, rightWidth,
					upperHeigth));
		}

		if (leftWidth > 0 && lowerHeight > 0) {
			myCollection.add(new RectangularPart(middlePoint, leftWidth,
					lowerHeight));
		}

		if (rightWidth > 0 && lowerHeight > 0) {
			myCollection
					.add(new RectangularPart(point, rightWidth, lowerHeight));
		}

		return myCollection;
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

	abstract boolean isInTransitively(RectangularPart part);
}
