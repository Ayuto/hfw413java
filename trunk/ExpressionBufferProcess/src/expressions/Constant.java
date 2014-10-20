package expressions;

import java.util.Map;

import model.BufferEntry;
import model.OptionalIntegerValue;

/**
 * A constant is a elementary expression with a name and a corresponding value
 * in a map.
 */
public class Constant extends BaseExpression {

	/**
	 * Creates a new constant with the given name.
	 * 
	 * @param name
	 *            the name of the new constant.
	 * @return the created constant with the given name.
	 */
	public static Constant create(String name) {
		return new Constant(name);
	}

	private String name;

	private Constant(String name) {
		this.name = name;
	}

	@Override
	public OptionalIntegerValue getValue(
			Map<String, OptionalIntegerValue> constantEnvironment,
			Map<String, Expression> variableEnvironment)
			throws UnknownConstantException {
		if (!constantEnvironment.containsKey(this.name))
			throw new UnknownConstantException(this.name);
		return constantEnvironment.get(this.name);
	}

	@Override
	protected BufferEntry getEntry() {
		return new model.Constant(this.name);
	}

}
