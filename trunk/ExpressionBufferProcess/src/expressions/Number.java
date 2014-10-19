package expressions;

import java.util.Map;

import model.BufferEntry;
import model.OptionalIntegerValue;

public class Number extends BaseExpression {
	
	public static Number create(OptionalIntegerValue value) {
		return new Number(value);
	}
	
	private OptionalIntegerValue value;
	
	private Number(OptionalIntegerValue value) {
		this.value = value;
	}

	@Override
	public OptionalIntegerValue getValue(
			Map<String, OptionalIntegerValue> constantEnvironment,
			Map<String, Expression> variableEnvironment) {
		return this.value;
	}

	@Override
	protected BufferEntry getEntry() {
		return this.value;
	}

}
