package model;

import java.util.Map;

import buffer.AbstractBuffer;

public class SubtractionProcess extends DyadicExpressionProcess {

	public SubtractionProcess(final AbstractBuffer<Tupel> inputBuffer,
			final AbstractBuffer<BufferEntry> outputBuffer,
			final Map<String, OptionalIntegerValue> constantEnvironment,
			final Map<String, Process> variableEnvironment) {
		super(inputBuffer, outputBuffer, constantEnvironment, variableEnvironment);
	}

	@Override
	protected BufferEntry calculate(final OptionalIntegerValue arg1,
			final OptionalIntegerValue arg2) {
		return arg1.sub(arg2);
	}
}
