package model;

import java.util.Map;

import buffer.AbstractBuffer;

/**
 * A process for the dyadic expression addition.
 */
public class AdditionProcess extends DyadicExpressionProcess {

	/**
	 * Constructor for a new addition process with a tupel buffer and a result
	 * buffer.
	 * 
	 * @param inputBuffer
	 *            tupel buffer for all inputs.
	 * @param outputBuffer
	 *            result buffer for all results.
	 * @param variableEnvironment 
	 * @param constantEnvironment 
	 */
	public AdditionProcess(final AbstractBuffer<Tupel> inputBuffer,
			final AbstractBuffer<BufferEntry> outputBuffer, Map<String, OptionalIntegerValue> constantEnvironment, Map<String, Process> variableEnvironment) {
		super(inputBuffer, outputBuffer, constantEnvironment, variableEnvironment);
	}

	@Override
	protected BufferEntry calculate(final OptionalIntegerValue arg1,
			final OptionalIntegerValue arg2) {
		return arg1.add(arg2);
	}
}