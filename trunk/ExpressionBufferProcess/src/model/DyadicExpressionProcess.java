package model;

import java.util.LinkedList;
import java.util.List;

import visitor.Visitor;
import visitor.VisitorImpl;
import buffer.AbstractBuffer;

public abstract class DyadicExpressionProcess implements ExpressionProcess {

	private final AbstractBuffer<Tupel> inputBuffer;
	private final AbstractBuffer<BufferEntry> outputBuffer;
	private final List<OptionalIntegerValue> possibleInputs;
	private boolean running;
	private final Visitor visitor;
	
	public DyadicExpressionProcess(final AbstractBuffer<Tupel> inputBuffer,
			final AbstractBuffer<BufferEntry> outputBuffer) {
		this.inputBuffer = inputBuffer;
		this.outputBuffer = outputBuffer;
		this.possibleInputs = new LinkedList<OptionalIntegerValue>();
		this.running = false;
		this.visitor = new VisitorImpl(this);
	}
	
	protected abstract BufferEntry calculate(OptionalIntegerValue arg1, OptionalIntegerValue arg2);
	
	@Override
	public void run() {
		while (this.running) {
			final Tupel input = this.inputBuffer.get();
			input.getFirst().accept(this.visitor);
			input.getSecond().accept(this.visitor);
			if (this.possibleInputs.size() == 2) {
				final BufferEntry result = this.calculate(this.possibleInputs.remove(0), this.possibleInputs.remove(0));
				System.out.println("Calculated:" + result);
				this.outputBuffer.put(result);
			}
		}
	}
	
	@Override
	public void addDetectedValue(final OptionalIntegerValue value) {
		this.possibleInputs.add(value);
	}
	
	@Override
	public void start() {
		this.running = true;
		this.possibleInputs.clear();
		new Thread(this).start();
	}
	
	@Override
	public void stop() {
		this.running = false;
		this.possibleInputs.clear();
		this.outputBuffer.put(StopCommand.getInstance());
	}
}
