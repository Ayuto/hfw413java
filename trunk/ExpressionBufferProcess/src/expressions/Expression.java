package expressions;

import java.util.Map;

import model.OptionalIntegerValue;
import model.Process;

/**
 * A arithmetic expression can be evaluated and transformed into a process.
 * Furthermore it can be checked whether a expression contains another one.
 */
public interface Expression {

	/**
	 * Checks whether the receiver contains the given expression as a direct or
	 * indirect subexpression.
	 * 
	 * @param arg
	 *            expression to be checked.
	 * @return {@code True} if and only if the receiver contains the given
	 *         argument as a direct or indirect subexpression.
	 */
	boolean contains(Expression arg);

	/**
	 * Evaluates the receiver and returns its value.
	 * 
	 * @param constantEnvironment
	 *            environment for all saved constants
	 * @param variableEnvironment
	 *            environment for all saved variables
	 * @return the value of the receiver
	 * @throws UnknownConstantException
	 *             if the name of a used constant is not found in the current
	 *             constant environment.
	 * @throws UnknownVariableException
	 *             if the name of a used variable is not found in the current
	 *             variable environment.
	 */
	OptionalIntegerValue getValue(
			Map<String, OptionalIntegerValue> constantEnvironment,
			Map<String, Expression> variableEnvironment)
			throws UnknownConstantException, UnknownVariableException;

	/**
	 * Transforms the receiver into a process.
	 * 
	 * @return the created process
	 */
	Process toProcess();
}
