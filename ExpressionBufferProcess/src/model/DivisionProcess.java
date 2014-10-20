package model;

import java.util.Map;

import buffer.AbstractBuffer;

/**
 * A process for the dyadic expression division.
 */
public class DivisionProcess extends DyadicExpressionProcess {

	/**
	 * Constructor for a new division process with the given parameters.
	 * 
	 * @param inputBuffer
	 *            tupel buffer for all inputs.
	 * @param outputBuffer
	 *            result buffer for all results.
	 * @param variableEnvironment 
	 * @param constantEnvironment 
	 */
	public DivisionProcess(final AbstractBuffer<Tupel> inputBuffer,
			final AbstractBuffer<BufferEntry> outputBuffer, Map<String, OptionalIntegerValue> constantEnvironment, Map<String, Process> variableEnvironment) {
		super(inputBuffer, outputBuffer, constantEnvironment, variableEnvironment);
	}

	@Override
	protected BufferEntry calculate(final OptionalIntegerValue arg1,
			final OptionalIntegerValue arg2) {
		return arg1.div(arg2);
	}
}
