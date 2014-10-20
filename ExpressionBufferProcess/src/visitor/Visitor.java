package visitor;

import model.Constant;
import model.OptionalIntegerValue;
import model.Variable;

/**
 * Visitor for a buffer entry.
 */
public interface Visitor {

	/**
	 * Handles the buffer entry if it is a stop command.
	 */
	void handleStopCommand();

	/**
	 * Handles the buffer entry if it is a optional integer value.
	 * 
	 * @param value
	 *            the optional integer value which was detected
	 */
	void handleOptionalIntegerValue(OptionalIntegerValue value);

	/**
	 * Handles the buffer entry if it is a constant.
	 * 
	 * @param constant
	 *            the constant which was detected
	 */
	void handleConstant(Constant constant);

	/**
	 * Handles the buffer entry if it is a variable.
	 * 
	 * @param variable
	 *            the variable which was detected
	 */
	void handleVariable(Variable variable);
}
