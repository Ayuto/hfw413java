package model;

import java.util.Map;

import buffer.AbstractBuffer;

/**
 * A process for the dyadic expression multiplication.
 */
public class MultiplicationProcess extends DyadicExpressionProcess {

	/**
	 * Constructor for a multiplication process with the given parameters.
	 * 
	 * @param inputBuffer
	 *            the tupel buffer for all inputs
	 * @param outputBuffer
	 *            a buffer for all results
	 * @param variableEnvironment 
	 * @param constantEnvironment 
	 */
	public MultiplicationProcess(final AbstractBuffer<Tupel> inputBuffer,
			final AbstractBuffer<BufferEntry> outputBuffer, Map<String, OptionalIntegerValue> constantEnvironment, Map<String, Process> variableEnvironment) {
		super(inputBuffer, outputBuffer, constantEnvironment, variableEnvironment);
	}

	@Override
	protected BufferEntry calculate(final OptionalIntegerValue arg1,
			final OptionalIntegerValue arg2) {
		return arg1.mul(arg2);
	}
}
