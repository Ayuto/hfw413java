package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

abstract public class RectangularArea extends Observee {
	
	private Point leftUpperCorner;
	private int width;
	private int height;
	
	protected RectangularArea(final Point position, final int width, final int height) {
		this.setLeftUpperCorner(position);
		this.setWidth(width < 0 ? 0 :width);
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
	Point getRightUpperCorner() {
		return new Point(this.getLeftUpperCorner().getX()+this.getWidth(), this.getLeftUpperCorner().getY());
	}
	Point getLeftLowerCorner() {
		return new Point(this.getLeftUpperCorner().getX(), this.getLeftUpperCorner().getY()-this.getHeight());
	}
	Point getRightLowerCorner() {
		return new Point(this.getLeftLowerCorner().getX()+this.getWidth(), this.getLeftLowerCorner().getY());
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
	public void move(final int deltaX, final int deltaY){
		this.getLeftUpperCorner().move(deltaX, deltaY);
	}
	public void resize(final int width, final int height) throws NegativeLengthException{
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
	public String toString(){
		return  "(WIDTH: " + this.getWidth() + ", HEIGHT: " + this.getHeight() + ") at " + this.getLeftUpperCorner().toString();
	}
	public boolean isIn(final RectangularPart part) {
		if (this.equals(part)) {
			return true;
		}
		return this.isInTransitively(part);
	}
	
	public RectangularPartCollection splitAt(final Point point)
	{
		final int x = point.getX();
		final int y = point.getY();
		
		final Point middlePoint = new Point(this.getLeftUpperCorner().getX(), y);
		final Point upperPoint = new Point(x, this.getLeftUpperCorner().getY());
		
		final int rightWidth = upperPoint.getX() - this.getLeftUpperCorner().getX();
		final int leftWidth = point.getX() - middlePoint.getX();
		final int upperHeigth = this.getLeftUpperCorner().getY() - middlePoint.getY();
		final int lowerHeight  = middlePoint.getY() - this.getLeftLowerCorner().getY();
		
		final RectangularPartCollection myCollection = new RectangularPartCollection();
		
		if (leftWidth > 0 && upperHeigth > 0) {
			myCollection.add(new RectangularPart(this.getLeftUpperCorner(), leftWidth, upperHeigth));
		}

		if (rightWidth > 0 && upperHeigth > 0) {
			myCollection.add(new RectangularPart(upperPoint, rightWidth, upperHeigth));
		}

		if (leftWidth > 0 && lowerHeight > 0) {
			myCollection.add(new RectangularPart(middlePoint, leftWidth, lowerHeight));
		}

		if (rightWidth > 0 && lowerHeight > 0) {
			myCollection.add(new RectangularPart(point, rightWidth, lowerHeight));
		}
		
		return myCollection;
	}
	
	public boolean containsPoint(final Point point)
	{
		final Point point2 = this.getLeftUpperCorner();
		final int x1 = point2.getX();
		final int y1 = point2.getY();
		final int x2 = x1 + this.getWidth();
		final int y2 = y1 + this.getHeight();
		
		return x1 <= point.getX() && point.getX() <= x2 && y1 <= point.getY() && point.getY() <= y2;
	}
	
	public Vector<Point> getContainedPoints(final Collection<Point> points)
	{
		final Vector<Point> temp = new Vector<Point>();
		final Iterator<Point> i = points.iterator();
		while(i.hasNext()) {
			final Point point = i.next();
			if (this.containsPoint(point)) {
				temp.add(point);
			}
		}
		return temp;
	}

	public boolean doNotOverlap(RectangularArea rectangularArea) {
		Point point1 = rectangularArea.getLeftUpperCorner();
		Point point2 = this.getLeftUpperCorner();
		if (point1.getX() > point2.getX() + this.getWidth()
				|| point1.getX() + rectangularArea.getWidth() < point2.getX()) {
			return true;
		}
		return point1.getY() < point2.getY() - this.getHeight()
				|| point1.getY() - rectangularArea.getHeight() > point2.getY();
	}
	
	abstract boolean isInTransitively(RectangularPart part);
}
