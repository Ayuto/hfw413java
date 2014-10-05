package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class Window extends RectangularArea implements Observer {

	private static int nextWindowNumber = 0;

	private static final int InitialWidth = 200;
	private static final int InitialHeight = 100;
	private static final int Origin = 0;

	private static final Point getInitialPosition() {
		return new Point(Origin, Origin);
	}

	final int number;
	private boolean open;
	private VisibleSizeState state;
	Collection<Window> aboveMe;

	Window() {
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
	public void move(final int deltaX, final int deltaY) {
		super.move(deltaX, deltaY);
		this.notiyObserver();
		System.out.println("Move: " + this);
	}

	@Override
	public void resize(final int width, final int height)
			throws NegativeLengthException {
		super.resize(width, height);
		this.notiyObserver();
		System.out.println("Resize: " + this);
	}

	public RectangularPartCollection getVisibleContext()
			throws NegativeLengthException {
		return this.getState().getVisibleContext();
	}

	public RectangularPartCollection calculateVisibleContext()
			throws NegativeLengthException {
		// TODO (1) implement calculation of visible parts
		RectangularPartCollection result = new RectangularPartCollection();

		if (this.isOpen()) {
			RectangularPart meAsPart = new RectangularPart(
					this.getLeftUpperCorner(), this.getWidth(),
					this.getHeight());
			try {
				meAsPart.setParent(this);
			} catch (HierarchyException e) {
				throw new Error("Hierarchy shall be guaranteed!");
			}
			result.add(meAsPart);

			// Iterate over all open windows above this window
			final Iterator<Window> openAboveWindows = getOpenAboveWindows()
					.iterator();
			while (openAboveWindows.hasNext()) {
				final Window currentWindow = openAboveWindows.next();

				// Create a temporary RectangularPartCollection object
				final RectangularPartCollection temp = new RectangularPartCollection();

				// Iterate over all current results
				final Iterator<RectangularPart> currentResults = result
						.getParts().iterator();
				while (currentResults.hasNext()) {
					final RectangularPart current = currentResults.next();

					// If the current window does not overlap the current part,
					// we
					// can safely add the current part to the temporary
					// collection.
					if (current.doNotOverlap(currentWindow)) {
						temp.add(current);
					} else {
						final Vector<Point> points = new Vector<Point>();
						points.add(currentWindow.getLeftLowerCorner());
						points.add(currentWindow.getLeftUpperCorner());
						points.add(currentWindow.getRightLowerCorner());
						points.add(currentWindow.getRightUpperCorner());

						final Vector<Point> containedPoints = current
								.getContainedPoints(points);

						// Do the actual calculation
						RectangularPartCollection splittedParts = null;
						switch (containedPoints.size()) {
						case 0:
							// TODO
							splittedParts = new RectangularPartCollection();
							break;
						case 1:
							splittedParts = current.splitAt(containedPoints
									.firstElement());
							break;
						case 2:
							splittedParts = current.splitAt(containedPoints
									.firstElement());
							final Iterator<RectangularPart> iterator = splittedParts
									.getParts().iterator();
							while (iterator.hasNext()) {
								final RectangularPart currentPart = iterator
										.next();
								splittedParts.add(currentPart
										.splitAt(containedPoints.get(1)));
							}
							break;
						case 4:
							splittedParts = current.splitAt(containedPoints
									.firstElement());
							for (int i = 1; i < containedPoints.size(); i++) {
								final Iterator<RectangularPart> iterator2 = splittedParts
										.getParts().iterator();
								while (iterator2.hasNext()) {
									final RectangularPart currentPart = iterator2
											.next();
									splittedParts.add(currentPart
											.splitAt(containedPoints.get(i)));
								}
							}
							break;
						default:
							throw new Error("This should never happen.");
						}

						// Add all parts to the temporary collection except
						// those
						// which overlap with the current window.
						final Iterator<RectangularPart> i = splittedParts
								.getParts().iterator();
						while (i.hasNext()) {
							final RectangularPart iCurrent = i.next();
							if (iCurrent.doNotOverlap(currentWindow)) {
								temp.add(iCurrent);
							}
						}
					}
				}
				result.getParts().addAll(temp.getParts());
			}
		}
		return result;
	}

	private Collection<Window> getOpenAboveWindows() {
		Collection<Window> aboveWindows = this.getAboveMe();
		Iterator<Window> aboveWindowsI = aboveWindows.iterator();
		while (aboveWindowsI.hasNext()) {
			Window currentWindow = aboveWindowsI.next();
			if (!currentWindow.isOpen()) {
				aboveWindows.remove(currentWindow);
			}
		}
		return aboveWindows;
	}

	@Override
	public String toString() {
		return "WINDOW " + this.getNumber() + " : " + super.toString() + " "
				+ (this.isOpen() ? "OPEN" : "CLOSED");
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

//	public RectangularPartCollection calculateVisibleContext() {
//		RectangularPartCollection result = new RectangularPartCollection();
//		Collection<Window> aboveMes = this.getAboveMe();
//		Iterator<Window> i = aboveMes.iterator();
//		while (i.hasNext()) {
//			Window current = i.next();
//			if (!this.doNotOverlap(current)) {
//				if (!this.fullyGetsOverlapped(current)) {
//					final Vector<Point> points = new Vector<Point>();
//					points.add(current.getLeftLowerCorner());
//					points.add(current.getLeftUpperCorner());
//					points.add(current.getRightLowerCorner());
//					points.add(current.getRightUpperCorner());
//
//					final Vector<Point> containedPoints = this
//							.getContainedPoints(points);
//
//					RectangularPartCollection splitParts = split(this, containedPoints);
//					Iterator<RectangularPart> i2 = splitParts.getParts().iterator();
//					while (i2.hasNext()){
//						RectangularPart current2 = i2.next();
//						Window currentWindow = new Window();
//						currentWindow.move(current2.getLeftUpperCorner().getX(), current2.getLeftUpperCorner().getY());
//						try {
//							currentWindow.resize(current2.getWidth(), current2.getHeight());
//						} catch (NegativeLengthException e) {
//							e.printStackTrace();
//						}
//						result.add(currentWindow.calculateVisibleContext());
//					}
//				} else {
//					;
//				}
//
//			} else {
//				result.add(new RectangularPart(this.getLeftUpperCorner(), this
//						.getWidth(), this.getHeight()));
//			}
//		}
//		return result;
//	}
//
//	private RectangularPartCollection split(Window current,
//			final Vector<Point> containedPoints) {
//		RectangularPartCollection splittedParts = current
//				.splitAt(containedPoints.firstElement());
//		for (int i = 1; i < containedPoints.size(); i++) {
//			final Iterator<RectangularPart> iterator2 = splittedParts
//					.getParts().iterator();
//			while (iterator2.hasNext()) {
//				final RectangularPart currentPart = iterator2.next();
//				splittedParts.add(currentPart.splitAt(containedPoints.get(i)));
//			}
//		}
//		return splittedParts;
//	}

}
