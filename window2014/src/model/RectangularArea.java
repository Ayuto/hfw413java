package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

abstract public class RectangularArea {
	
	private Point leftUpperCorner;
	private int width;
	private int height;
	
	protected RectangularArea(Point position, int width, int height) {
		this.setLeftUpperCorner(position);
		this.setWidth(width < 0 ? 0 :width);
		this.setHeight(height < 0 ? 0 : height);
	}
	public int getHeight() {
		return height;
	}
	private void setHeight(int height) {
		this.height = height;
	}
	public Point getLeftUpperCorner() {
		return leftUpperCorner;
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
	private void setLeftUpperCorner(Point leftUpperCorner) {
		this.leftUpperCorner = leftUpperCorner;
	}
	public int getWidth() {
		return width;
	}
	private void setWidth(int width) {
		this.width = width;
	}
	public void move(int deltaX, int deltaY){
		this.getLeftUpperCorner().move(deltaX, deltaY);
	}
	public void resize(int width, int height) throws NegativeLengthException{
		if (width <= 0) throw new NegativeLengthException();
		if (height <= 0) throw new NegativeLengthException();
		this.setHeight(height);
		this.setWidth(width);
	}
	public String toString(){
		return  "(WIDTH: " + this.getWidth() + ", HEIGHT: " + this.getHeight() + ") at " + this.getLeftUpperCorner();
	}
	public boolean isIn(RectangularPart part) {
		if (this.equals(part)) return true;
		return this.isInTransitively(part);
	}
	
	public RectangularPartCollection splitAt(Point point)
	{
		int x = point.getX();
		int y = point.getY();
		
		Point middlePoint = new Point(this.getLeftUpperCorner().getX(), y);
		Point upperPoint = new Point(x, this.getLeftUpperCorner().getY());
		
		int rightWidth = upperPoint.getX() - this.getLeftUpperCorner().getX();
		int leftWidth = point.getX() - middlePoint.getX();
		int upperHeigth = this.getLeftUpperCorner().getY() - middlePoint.getY();
		int lowerHeight  = middlePoint.getY() - this.getLeftLowerCorner().getY();
		
		RectangularPartCollection myCollection = new RectangularPartCollection();
		
		if (leftWidth > 0 && upperHeigth > 0)
			myCollection.add(new RectangularPart(this.getLeftUpperCorner(), leftWidth, upperHeigth));

		if (rightWidth > 0 && upperHeigth > 0)
			myCollection.add(new RectangularPart(upperPoint, rightWidth, upperHeigth));

		if (leftWidth > 0 && lowerHeight > 0)
			myCollection.add(new RectangularPart(middlePoint, leftWidth, lowerHeight));

		if (rightWidth > 0 && lowerHeight > 0)
			myCollection.add(new RectangularPart(point, rightWidth, lowerHeight));
		
		return myCollection;
	}
	
	public boolean containsPoint(Point point)
	{
		Point point2 = this.getLeftUpperCorner();
		int x1 = point2.getX();
		int y1 = point2.getY();
		int x2 = x1 + this.getWidth();
		int y2 = y1 + this.getHeight();
		
		return x1 <= point.getX() && point.getX() <= x2 && y1 <= point.getY() && point.getY() <= y2;
	}
	
	public Vector<Point> getContainedPoints(Collection<Point> points)
	{
		Vector<Point> temp = new Vector<Point>();
		Iterator<Point> i = points.iterator();
		while(i.hasNext()) {
			Point point = i.next();
			if (this.containsPoint(point))
				temp.add(point);
		}
		return temp;
	}
	
	abstract boolean isInTransitively(RectangularPart part);
}
