package model;

public abstract class VisibleSizeState {
	
	private Window owner;
	
	public VisibleSizeState(final Window owner) {
		this.owner = owner;
	}
	
	/*
	 * Overrides the current state of the owner with a new NotCalculatedState object.
	 */
	public void visibleSizePossiblyChanged() {
		this.getOwner().setState(NotCalculatedState.create(this.getOwner()));
	}
	
	/*
	 * Returns the visible context of a Window.
	 */
	public abstract RectangularPartCollection getVisibleContext() throws NegativeLengthException;

	protected Window getOwner() {
		return this.owner;
	}

	protected void setOwner(final Window owner) {
		this.owner = owner;
	}
}
