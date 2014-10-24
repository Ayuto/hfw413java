package model;

import java.util.Collection;
import java.util.Map;

import buffer.AbstractBuffer;

/**
 * A Process is a abstract type for wrapper of all expression processes for a
 * better use of them.
 */
public interface Process {

	/**
	 * Reads the current inputs and calculates the result once.
	 * 
	 * @return the calculated result as a buffer entry from the output buffer.
	 */
	BufferEntry run();

	/**
	 * Adds a new constant to the constant environment of this process. If there
	 * is already a constant with the given name this constant will be
	 * overwritten.
	 * 
	 * @param name
	 *            the name of the new constant or the constant which should be
	 *            changed
	 * @param value
	 *            new value for the constant
	 */
	void addConstant(String name, OptionalIntegerValue value);

	/**
	 * Adds a new variable to the variable environment of this process. If there
	 * is already a variable with the given name this variable will be
	 * overwritten.
	 * 
	 * @param name
	 *            the name of the new variable or the variable which should be
	 *            changed
	 * @param process
	 *            new process which should be evaluated for the value of the
	 *            variable
	 */
	void addVariable(String name, Process process);

	/**
	 * Sets the input of a specified buffer for the next run.
	 * 
	 * @param source
	 *            the input of this buffer should be set
	 * @param newInput
	 *            the new input for the specified buffer
	 */
	void setInput(AbstractBuffer<BufferEntry> source, BufferEntry newInput);

	/**
	 * Getter for the Collection of processes which are wrapped by the receiver.
	 * 
	 * @return Collection with all subprocesses of the receiver.
	 */
	Collection<ExpressionProcess> getSubprocesses();

	/**
	 * Getter for the Map where all input entries are mapped to their buffers.
	 * 
	 * @return Map where all input entries are mapped to their buffers.
	 */
	Map<AbstractBuffer<BufferEntry>, BufferEntry> getInputs();

	/**
	 * Getter for the output buffer.
	 * 
	 * @return the output buffer for all results of the receiver.
	 */
	AbstractBuffer<BufferEntry> getOutput();

	/**
	 * Starts the process.
	 */
	void start();

	/**
	 * Stops the process.
	 */
	void stop();

	/**
	 * Getter for the constant environment.
	 * @return the constant environment
	 */
	Map<String, OptionalIntegerValue> getConstantEnvironment();

	/**
	 * Getter for the variable environment.
	 * @return the variable environment
	 */
	Map<String, Process> getVariableEnvironment();
}
