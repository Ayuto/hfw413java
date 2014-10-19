package expressions;

import java.util.Map;

import model.BufferEntry;
import model.OptionalIntegerValue;

public class Variable extends BaseExpression {
	
	public static Variable create(String name) {
		return new Variable(name);
	}
	
	private String name;
	
	private Variable(String name) {
		this.name = name;
	}

	@Override
	public OptionalIntegerValue getValue(
			Map<String, OptionalIntegerValue> constantEnvironment, Map<String, Expression> variableEnvironment)
			throws UnknownVariableException, UnknownConstantException {
		if (!variableEnvironment.containsKey(this.name)) throw new UnknownVariableException(this.name);
		return variableEnvironment.get(this.name).getValue(constantEnvironment, variableEnvironment);
	}

	@Override
	protected BufferEntry getEntry() {
		return new model.Variable(this.name);
	}

}
