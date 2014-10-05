package model;

public class NotCalculatedState extends VisibleSizeState {

	public static VisibleSizeState create(final Window owner) {
		return new NotCalculatedState(owner);
	}
	
	private NotCalculatedState(final Window owner) {
		super(owner);
	}

	@Override
	public RectangularPartCollection getVisibleContext() throws NegativeLengthException {
		final RectangularPartCollection result = this.getOwner().calculateVisibleContext();
		this.getOwner().setState(CalculatedState.create(this.getOwner(), result));
		return result;
	}

	

}
