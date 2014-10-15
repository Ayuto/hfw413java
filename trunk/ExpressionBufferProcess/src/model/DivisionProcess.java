package model;

import buffer.AbstractBuffer;

public class DivisionProcess extends DyadicExpressionProcess {

	public DivisionProcess(final AbstractBuffer<Tupel> inputBuffer,
			final AbstractBuffer<BufferEntry> outputBuffer) {
		super(inputBuffer, outputBuffer);
	}

	@Override
	protected BufferEntry calculate(final OptionalIntegerValue arg1,
			final OptionalIntegerValue arg2) {
		return arg1.div(arg2);
	}
}
