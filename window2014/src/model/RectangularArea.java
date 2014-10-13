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

	/**
	 * Returns the area that is overlapped by <area>.
	 * 
	 * If there is no overlapping, a Rectangle object with a width and a height
	 * of 0 will be returned.
	 */
	public Rectangle getOverlappedArea(final RectangularArea area) {
		final Point p1 = this.getLeftUpperCorner();
		final Point p2 = area.getLeftUpperCorner();

		final int xStart = Math.max(p1.getX(), p2.getX());
		final int xEnd = Math.min(p1.getX() + this.getWidth(),
				p2.getX() + area.getWidth());

		final int yStart = Math.max(p1.getY(), p2.getY());
		final int yEnd = Math.min(p1.getY() + this.getHeight(), p2.getY()
				+ area.getHeight());

		if (xStart >= xEnd || yStart >= yEnd) {
			try {
				return new Rectangle(new Point(0, 0), 0, 0);
			} catch (final NegativeLengthException e) {
				throw new Error("Can't happen!!!");
			}
		}

		try {
			return new Rectangle(new Point(xStart, yStart), xEnd - xStart, yEnd
					- yStart);
		} catch (final NegativeLengthException e) {
			throw new Error("Can't happen!!!");
		}
	}

	/**
	 * Returns true if the height and width is 0.
	 */
	public boolean isEmpty() {
		return this.getHeight() == 0 && this.getWidth() == 0;
	}

	/**
	 * Returns True if <this> and <area> do not overlap.
	 */
	public boolean doesNotOverlap(final RectangularArea area) {
		return this.getOverlappedArea(area).isEmpty();
	}

	/**
	 * Returns the edges of this area.
	 */
	public Point[] getEdges() {
		final Point[] result = { this.getLeftUpperCorner(),
				this.getRightUpperCorner(), this.getRightLowerCorner(),
				this.getLeftLowerCorner() };
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof RectangularArea) {
			final RectangularArea objAsRect = (RectangularArea) obj;
			return objAsRect.getLeftUpperCorner().equals(
					this.getLeftUpperCorner())
					&& objAsRect.getWidth() == this.getWidth()
					&& objAsRect.getHeight() == this.getHeight();
		}
		return false;
	}

	/**
	 * Splits the area into smaller areas. Overlapped areas are excluded.
	 * 
	 * @throws HierarchyException
	 */
	public RectangularPartCollection split(
			final RectangularArea overlappedArea, final Window parent)
			throws HierarchyException {
		final Point p1 = this.getLeftUpperCorner();
		final Point p5 = overlappedArea.getLeftUpperCorner();
		final Point p6 = overlappedArea.getRightUpperCorner();
		final Point p3 = new Point(p6.getX(), p1.getY());
		final Point p4 = new Point(p1.getX(), p5.getY());
		final Point p2 = new Point(p5.getX(), p1.getY());
		final Point p8 = overlappedArea.getLeftLowerCorner();
		final Point p7 = new Point(p1.getX(), p8.getY());
		final Point p9 = overlappedArea.getRightLowerCorner();
		final Point p10 = this.getRightUpperCorner();
		final Point p11 = new Point(p10.getX(), p4.getY());
		final Point p13 = this.getLeftLowerCorner();
		final Point p12 = new Point(p10.getX(), p7.getY());

		final RectangularPartCollection result = new RectangularPartCollection();
		result.createAndAddPart(p1, p2.getX() - p1.getX(),
				p4.getY() - p1.getY(), parent);
		result.createAndAddPart(p2, p3.getX() - p2.getX(),
				p5.getY() - p2.getY(), parent);
		result.createAndAddPart(p3, p10.getX() - p3.getX(),
				p6.getY() - p3.getY(), parent);
		result.createAndAddPart(p4, p5.getX() - p4.getX(),
				p7.getY() - p4.getY(), parent);
		result.createAndAddPart(p6, p11.getX() - p6.getX(),
				p9.getY() - p6.getY(), parent);
		result.createAndAddPart(p7, p8.getX() - p7.getX(),
				p13.getY() - p7.getY(), parent);
		result.createAndAddPart(p8, p9.getX() - p8.getX(),
				p13.getY() - p8.getY(), parent);
		result.createAndAddPart(p9, p12.getX() - p9.getX(),
				p13.getY() - p9.getY(), parent);
		return result;
	}

	abstract boolean isInTransitively(RectangularPart part);

	public boolean contains(RectangularArea area) {
		int areaX = area.getLeftUpperCorner().getX();
		int thisX = this.getLeftUpperCorner().getX();

		int areaY = area.getLeftUpperCorner().getY();
		int thisY = this.getLeftUpperCorner().getY();

		return areaX >= thisX
				&& areaX + area.getWidth() <= thisX + this.getWidth()
				&& areaY >= thisY
				&& areaY + area.getHeight() <= thisY + this.getHeight();
	}
}
