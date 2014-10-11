package model;

/*
 * This class is used if the visible context was not calculated yet.
 */
public class NotCalculatedState extends VisibleSizeState {

	/*
	 * Creates a new object of this class.
	 * 
	 * @param <owner>:
	 * The Window object which is the owner of this state.
	 * */
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
