package model;

import java.util.Collection;
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

	/**
	 * Returns the visible context of this window.
	 */
	public RectangularPartCollection getVisibleContext()
			throws NegativeLengthException {
		return this.getState().getVisibleContext();
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
	public boolean equals(final Object obj) {
		return this == obj;
	}

	@Override
	boolean isInTransitively(final RectangularPart part) {
		return false;
	}

	public Collection<Window> getAboveMe() {
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
			this.getState().visibleSizePossiblyChanged();
		} else {
			if (!this.getAboveMe().contains(window)) {
				this.getAboveMe().add(window);
				window.register(this);
				this.getState().visibleSizePossiblyChanged();
			}
		}
	}

	public void dispose() {
		this.notiyObserver();
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

	/**
	 * Calculates the visible context of this Window.
	 */
	public RectangularPartCollection calculateVisibleContext() {
		RectangularPartCollection result = new RectangularPartCollection();

		if (this.isOpen()) {
			final RectangularPart meAsPart = new RectangularPart(
					this.getLeftUpperCorner(), this.getWidth(),
					this.getHeight());

			try {
				meAsPart.setParent(this);
			} catch (final HierarchyException e) {
				throw new Error("Hierarchy shall be guaranteed!");
			}

			result.add(meAsPart);

			for (final Window window : this.getAboveMe()) {
				final RectangularPartCollection tmpResult = new RectangularPartCollection();

				for (final RectangularPart part : result.getParts()) {
					final Rectangle overlappedArea = part.getOverlappedArea(window);
					
					if (overlappedArea.isEmpty()) {
						tmpResult.add(part);
					} else {
						tmpResult.add(part.split(overlappedArea));
					}
				}

				result = tmpResult;
			}
		}
		return result;
	}
}
