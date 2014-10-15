package visitor;

import model.ExpressionProcess;
import model.OptionalIntegerValue;

public class VisitorImpl implements Visitor {

	private final ExpressionProcess owner;
	
	public VisitorImpl(final ExpressionProcess owner) {
		this.owner = owner;
	}

	@Override
	public void handleStopCommand() {
		this.owner.stop();
	}

	@Override
	public void handleOptionalIntegerValue(final OptionalIntegerValue value) {
		this.owner.addDetectedValue(value);
	}
}
