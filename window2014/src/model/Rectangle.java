package model;

public class Rectangle extends RectangularArea {

	public Rectangle(Point position, int width, int height) throws NegativeLengthException {
		super(position, width, height);
	}

	@Override
	boolean isInTransitively(RectangularPart part) {
		return false;
	}

}
