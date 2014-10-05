package model;

public class CalculatedState extends VisibleSizeState {
	
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
