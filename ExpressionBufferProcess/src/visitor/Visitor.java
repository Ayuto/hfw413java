package visitor;

import model.OptionalIntegerValue;

public interface Visitor {
	void handleStopCommand();
	void handleOptionalIntegerValue(OptionalIntegerValue value);
}
