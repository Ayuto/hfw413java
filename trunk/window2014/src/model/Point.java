package model;

public class Point {
	
	private int x;
	private int y;
	
	public Point(int x, int y){
		this.setX(x);
		this.setY(y);
	}
	public void move(int deltaX, int deltaY){
		this.setX(this.getX() + deltaX);
		this.setY(this.getY() + deltaY);
	}
	public int getX() {
		return x;
	}
	private void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	private void setY(int y) {
		this.y = y;
	}
	public String toString(){
		return "[X: " + this.getX() + ", Y: " + this.getY() + "]";
	}
	
	/**
	 * @param point to compare with this point.
	 * @return true, if this point equals <point>. Returns false else.
	 */
	public boolean equals(Point point) {
		return this.getX()==point.getX()&&this.getY()==point.getY();
	}
}
