package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class Window extends RectangularArea {
	
	private static int nextWindowNumber = 0;

	private static final int InitialWidth = 200;
	private static final int InitialHeight = 100;
	private static final int Origin = 0;
	private static final Point getInitialPosition(){
		return new Point(Origin,Origin);
	}

	final int number;
	private boolean open;
	Collection<Window> aboveMe;
	
	Window(){
		super(getInitialPosition(), InitialWidth, InitialHeight);
		this.number = nextWindowNumber++;
		this.setOpen(true);
		this.setAboveMe(new Vector<Window>());
	}
	private boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
		// TODO (2) possibly send event to all windows below
		System.out.println("SetOpen(" + open + "): " + this);
	}
	public void move(int deltaX, int deltaY){
		super.move(deltaX, deltaY);
		// TODO (2) possibly send event to all windows below		
		System.out.println("Move: " + this);
	}
	public void resize(int width, int height) throws NegativeLengthException{
		super.resize(width, height);
		// TODO (2) possibly send event to all windows below
		System.out.println("Resize: " + this);
	}

	public RectangularPartCollection getVisibleContext() throws NegativeLengthException{
		//TODO (1) implement calculation of visible parts
		RectangularPartCollection result = new RectangularPartCollection();
		if (this.isOpen()){
			RectangularPart meAsPart = new RectangularPart(this.getLeftUpperCorner(),this.getWidth(),this.getHeight());
			try {
				meAsPart.setParent(this);
			} catch (HierarchyException e) {
				throw new Error("Hierarchy shall be guaranteed!");
			}
			result.add(meAsPart);
			
			Iterator<Window> aboveWindows = this.getAboveMe().iterator();
			while (aboveWindows.hasNext()) {
				Window currentWindow = aboveWindows.next();
				RectangularPart currentWindowAsPart = new RectangularPart(currentWindow.getLeftUpperCorner(), currentWindow.getWidth(), currentWindow.getHeight());
				RectangularPartCollection temp = new RectangularPartCollection();
				Iterator<RectangularPart> currentResults = result.getParts().iterator();
				while(currentResults.hasNext()) {
					RectangularPart current = currentResults.next();
					if (current.doNotOverlap(currentWindowAsPart)) {
						temp.add(current);
					} else {
						Vector<Point> points = new Vector<Point>();
						points.add(currentWindow.getLeftLowerCorner());
						points.add(currentWindow.getLeftUpperCorner());
						points.add(currentWindow.getRightLowerCorner());
						points.add(currentWindow.getRightUpperCorner());
						
						Vector<Point> containedPoints = current.getContainedPoints(points);
						
						RectangularPartCollection splittedParts = null;
						switch (containedPoints.size())
						{
						case 0:
							break;
						case 1:
							splittedParts = current.splitAt(containedPoints.firstElement());
							break;
						case 2:
							splittedParts = current.splitAt(containedPoints.firstElement());
							Iterator<RectangularPart> iterator = splittedParts.getParts().iterator();
							while(iterator.hasNext()){
								RectangularPart currentPart = iterator.next();
								splittedParts.add(currentPart.splitAt(containedPoints.get(1)));
							}
							break;
						case 4:
							splittedParts = current.splitAt(containedPoints.firstElement());
							for(int i=1; i<containedPoints.size(); i++) {
								Iterator<RectangularPart> iterator2 = splittedParts.getParts().iterator();
								while(iterator2.hasNext()){
									RectangularPart currentPart = iterator2.next();
									splittedParts.add(currentPart.splitAt(containedPoints.get(i)));
								}
							}
							break;
						default:
							throw new Error();
						}
						
						Iterator<RectangularPart> i = splittedParts.getParts().iterator();
						while(i.hasNext()) {
							RectangularPart iCurrent = i.next();
							if (iCurrent.doNotOverlap(currentWindowAsPart)) {
								temp.add(iCurrent);
							}
						}
					}
				}
				result = temp;
			}
		}
		return result;
	}
	
	public String toString(){
		return "WINDOW " + this.getNumber() + " : " + super.toString() + " " + (this.isOpen() ? "OPEN" : "CLOSED");
	}
	private int getNumber() {
		return this.number;
	}
	@Override
	boolean isInTransitively(RectangularPart part) {
		return false;
	}
	private Collection<Window> getAboveMe() {
		return aboveMe;
	}
	private void setAboveMe(Collection<Window> aboveMe) {
		this.aboveMe = aboveMe;
	}
	public void setAsTop(Window window) {
		if (this.equals(window)) {
			this.getAboveMe().clear();
		} else {
			if (!this.getAboveMe().contains(window)) this.getAboveMe().add(window);
		}
	}
	public void dispose() {
		// TODO (2) implement dispose: possibly send event to all windows below
		System.out.println("Dispose: " + this);

	}
	public void newTop(Window window) {
		this.getAboveMe().add(window);
	}
}
