package model;

import java.util.Map;

import buffer.AbstractBuffer;

public class AdditionProcess extends DyadicExpressionProcess {

	public AdditionProcess(final AbstractBuffer<Tupel> inputBuffer,
			final AbstractBuffer<BufferEntry> outputBuffer,
			final Map<String, OptionalIntegerValue> constantEnvironment,
			final Map<String, Process> variableEnvironment) {
		super(inputBuffer, outputBuffer, constantEnvironment, variableEnvironment);
	}

	@Override
	protected BufferEntry calculate(final OptionalIntegerValue arg1,
			final OptionalIntegerValue arg2) {
		return arg1.add(arg2);
	}
}