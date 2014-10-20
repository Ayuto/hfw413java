package model;

import java.util.Map;

import buffer.AbstractBuffer;

/**
 * A process for the dyadic expression subtraction.
 */
public class SubtractionProcess extends DyadicExpressionProcess {

	/**
	 * Constructor for the subtraction process with the given parameters.
	 * 
	 * @param inputBuffer
	 *            tupel buffer for all inputs.
	 * @param outputBuffer
	 *            result buffer for all outputs.
	 * @param variableEnvironment 
	 * @param constantEnvironment 
	 */
	public SubtractionProcess(final AbstractBuffer<Tupel> inputBuffer,
			final AbstractBuffer<BufferEntry> outputBuffer, Map<String, OptionalIntegerValue> constantEnvironment, Map<String, Process> variableEnvironment) {
		super(inputBuffer, outputBuffer, constantEnvironment, variableEnvironment);
	}

	@Override
	protected BufferEntry calculate(final OptionalIntegerValue arg1,
			final OptionalIntegerValue arg2) {
		return arg1.sub(arg2);
	}
}
