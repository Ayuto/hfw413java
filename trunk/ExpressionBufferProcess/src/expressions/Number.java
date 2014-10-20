package expressions;

import java.util.Map;

import model.BufferEntry;
import model.OptionalIntegerValue;

/**
 * A number is a elementary expression with a value.
 */
public class Number extends BaseExpression {
	
	/**
	 * Creates a new number with the given value.
	 * 
	 * @param value
	 *            the value of the new number.
	 * @return the created number with the given value.
	 */
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
