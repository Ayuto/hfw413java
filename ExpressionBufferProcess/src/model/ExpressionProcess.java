package model;

import java.util.Map;

/**
 * Expression process which can be started as a thread.
 */
public interface ExpressionProcess extends Runnable {

	/**
	 * Stops the receiver.
	 */
	void stop();

	/**
	 * Starts the receiver.
	 */
	void start();

	/**
	 * Adds a value to the receivers calculation which was detected by a
	 * visitor.
	 * 
	 * @param value
	 *            detected value.
	 */
	void addDetectedValue(OptionalIntegerValue value);

	/**
	 * Updates the visitor of the receiver with the new constant environment.
	 * @param constantEnvironment new constant environment
	 */
	void updateConstantEnvironment(
			Map<String, OptionalIntegerValue> constantEnvironment);

	/**
	 * Updates the visitor of the receiver with the new variable environment.
	 * @param variableEnvironment new constant environments
	 */
	void updateVariableEnvironment(Map<String, Process> variableEnvironment);
}
