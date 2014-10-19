package visitor;

import model.Constant;
import model.OptionalIntegerValue;
import model.Variable;

public interface Visitor {
	void handleStopCommand();
	void handleOptionalIntegerValue(OptionalIntegerValue value);
	void handleConstant(Constant constant);
	void handleVariable(Variable variable);
}
