package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class Window extends RectangularArea implements Observer {
	
	private static int nextWindowNumber = 0;

	private static final int InitialWidth = 200;
	private static final int InitialHeight = 100;
	private static final int Origin = 0;
	private static final Point getInitialPosition(){
		return new Point(Origin,Origin);
	}

	final int number;
	private boolean open;
	private VisibleSizeState state;
	Collection<Window> aboveMe;
	
	Window(){
		super(getInitialPosition(), InitialWidth, InitialHeight);
		this.number = nextWindowNumber++;
		this.setOpen(true);
		this.setAboveMe(new Vector<Window>());
		this.setState(NotCalculatedState.create(this));
	}
	private boolean isOpen() {
		return this.open;
	}
	public void setOpen(final boolean open) {
		this.open = open;
		this.notiyObserver();
		System.out.println("SetOpen(" + open + "): " + this);
	}
	@Override
	public void move(final int deltaX, final int deltaY){
		super.move(deltaX, deltaY);
		this.notiyObserver();		
		System.out.println("Move: " + this);
	}
	@Override
	public void resize(final int width, final int height) throws NegativeLengthException{
		super.resize(width, height);
		this.notiyObserver();
		System.out.println("Resize: " + this);
	}
	
	public RectangularPartCollection getVisibleContext() throws NegativeLengthException{
		return this.getState().getVisibleContext();
	}

	public RectangularPartCollection calculateVisibleContext() throws NegativeLengthException{
		//TODO (1) implement calculation of visible parts
		RectangularPartCollection result = new RectangularPartCollection();
		
		// There are no visible parts if the window is closed
		if (!this.isOpen()){
			return result;
		}
		
		final RectangularPart meAsPart = new RectangularPart(this.getLeftUpperCorner(),this.getWidth(),this.getHeight());
		
		// Check hierarchy
		try {
			meAsPart.setParent(this);
		} catch (final HierarchyException e) {
			throw new Error("Hierarchy shall be guaranteed!");
		}

		result.add(meAsPart);
		
		// Iterate over all windows above this window
		final Iterator<Window> aboveWindows = this.getAboveMe().iterator();
		while (aboveWindows.hasNext()) {
			final Window currentWindow = aboveWindows.next();
			
			// Create a temporary RectangularPartCollection object
			final RectangularPartCollection temp = new RectangularPartCollection();
			
			// Iterate over all current results
			final Iterator<RectangularPart> currentResults = result.getParts().iterator();
			while(currentResults.hasNext()) {
				final RectangularPart current = currentResults.next();
				
				// If the current window does not overlap the current part, we
				// can safely add the current part to the temporary collection.
				if (current.doNotOverlap(currentWindow)) {
					temp.add(current);
				} else {
					final Vector<Point> points = new Vector<Point>();
					points.add(currentWindow.getLeftLowerCorner());
					points.add(currentWindow.getLeftUpperCorner());
					points.add(currentWindow.getRightLowerCorner());
					points.add(currentWindow.getRightUpperCorner());
					
					final Vector<Point> containedPoints = current.getContainedPoints(points);
					
					// Do the actual calculation
					RectangularPartCollection splittedParts = null;
					switch (containedPoints.size())
					{
					case 0:
						// TODO
						splittedParts = new RectangularPartCollection();
						break;
					case 1:
						splittedParts = current.splitAt(containedPoints.firstElement());
						break;
					case 2:
						splittedParts = current.splitAt(containedPoints.firstElement());
						final Iterator<RectangularPart> iterator = splittedParts.getParts().iterator();
						while(iterator.hasNext()){
							final RectangularPart currentPart = iterator.next();
							splittedParts.add(currentPart.splitAt(containedPoints.get(1)));
						}
						break;
					case 4:
						splittedParts = current.splitAt(containedPoints.firstElement());
						for(int i=1; i<containedPoints.size(); i++) {
							final Iterator<RectangularPart> iterator2 = splittedParts.getParts().iterator();
							while(iterator2.hasNext()){
								final RectangularPart currentPart = iterator2.next();
								splittedParts.add(currentPart.splitAt(containedPoints.get(i)));
							}
						}
						break;
					default:
						throw new Error("This should never happen.");
					}
					
					// Add all parts to the temporary collection except those
					// which overlap with the current window.
					final Iterator<RectangularPart> i = splittedParts.getParts().iterator();
					while(i.hasNext()) {
						final RectangularPart iCurrent = i.next();
						if (iCurrent.doNotOverlap(currentWindow)) {
							temp.add(iCurrent);
						}
					}
				}
			}
			result = temp;
		}
		return result;
	}
	
	@Override
	public String toString(){
		return "WINDOW " + this.getNumber() + " : " + super.toString() + " " + (this.isOpen() ? "OPEN" : "CLOSED");
	}
	private int getNumber() {
		return this.number;
	}
	@Override
	boolean isInTransitively(final RectangularPart part) {
		return false;
	}
	private Collection<Window> getAboveMe() {
		return this.aboveMe;
	}
	private void setAboveMe(final Collection<Window> aboveMe) {
		this.aboveMe = aboveMe;
	}
	public void setAsTop(final Window window) {
		if (this.equals(window)) {
			for (final Window current : this.getAboveMe()) {
				current.deregister(window);
			}
			this.getAboveMe().clear();
		} else {
			if (!this.getAboveMe().contains(window)) {
				this.getAboveMe().add(window);
				window.register(this);
			}
		}
		this.getState().visibleSizePossiblyChanged();
	}
	public void dispose() {
		for (final Window current : this.getAboveMe()) {
			current.deregister(this);
		}
		this.aboveMe.clear();
		this.setOpen(false);
		System.out.println("Dispose: " + this);

	}
	public void newTop(final Window window) {
		this.getAboveMe().add(window);
		window.register(this);
	}
	@Override
	public void update() {
		this.getState().visibleSizePossiblyChanged();
	}
	
	public VisibleSizeState getState() {
		return this.state;
	}
	
	public void setState(final VisibleSizeState state) {
		this.state = state;
	}
}
