package model;
/**
 * State for a window with cached visible parts. This is saved in <visibleContext>.
 */
/*
 * This class is used if the visible context was already calculated.
 */
public class CalculatedState extends VisibleSizeState {
	
	/*
	 * Creates a new object of this class.
	 * 
	 * @param <owner>:
	 * The Window object which is the owner of this state.
	 * 
	 * @param <visibleContext>:
	 * The calculated visible context.
	 */
	public static VisibleSizeState create(final Window owner,
			final RectangularPartCollection visibleContext) {
		return new CalculatedState(owner, visibleContext);
	}

	private final RectangularPartCollection visibleContext;
	
	private CalculatedState(final Window owner, final RectangularPartCollection visibleContext) {
		super(owner);
		this.visibleContext = visibleContext;
	}

	@Override
	public RectangularPartCollection getVisibleContext() {
		return this.visibleContext;
	}

}
