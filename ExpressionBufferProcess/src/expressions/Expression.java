package expressions;

import java.util.Map;

import model.OptionalIntegerValue;
import model.Process;

public interface Expression {
	boolean contains(Expression arg);
	OptionalIntegerValue getValue(Map<String, OptionalIntegerValue> constantEnvironment, Map<String, Expression> variableEnvironment) throws UnknownConstantException, UnknownVariableException;
	Process toProcess();
}
