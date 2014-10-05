package model;

public abstract class VisibleSizeState {
	
	private Window owner;
	
	public VisibleSizeState(final Window owner) {
		this.owner = owner;
	}
	
	public void visibleSizePossiblyChanged() {
		this.getOwner().setState(NotCalculatedState.create(this.getOwner()));
	}
	
	public abstract RectangularPartCollection getVisibleContext() throws NegativeLengthException;

	protected Window getOwner() {
		return this.owner;
	}

	protected void setOwner(final Window owner) {
		this.owner = owner;
	}
}
