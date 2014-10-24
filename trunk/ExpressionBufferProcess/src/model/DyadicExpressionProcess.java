package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import visitor.VisitorImpl;
import buffer.AbstractBuffer;

/**
 * A dyadic expression process which evaluates the results for all inputs out of
 * buffers and writes results into a output buffer.
 */
public abstract class DyadicExpressionProcess implements ExpressionProcess {

	private final AbstractBuffer<Tupel> inputBuffer;
	private final AbstractBuffer<BufferEntry> outputBuffer;
	private final List<OptionalIntegerValue> possibleInputs;
	private boolean running;
	private final VisitorImpl visitor;

	/**
	 * Constructor for a dyadic expression process with the given parameters.
	 * 
	 * @param inputBuffer
	 *            tupel buffer for all input values.
	 * @param outputBuffer
	 *            result buffer for all calculated results.
	 * @param variableEnvironment2 
	 * @param constantEnvironment2 
	 */
	public DyadicExpressionProcess(final AbstractBuffer<Tupel> inputBuffer,
			final AbstractBuffer<BufferEntry> outputBuffer, Map<String, OptionalIntegerValue> constantEnvironment, Map<String, Process> variableEnvironment) {
		this.inputBuffer = inputBuffer;
		this.outputBuffer = outputBuffer;
		this.possibleInputs = new LinkedList<OptionalIntegerValue>();
		this.running = false;
		this.visitor = new VisitorImpl(this, constantEnvironment, variableEnvironment);
	}

	/**
	 * Calculates the receiver with the given input values.
	 * 
	 * @param arg1
	 *            first input value.
	 * @param arg2
	 *            second input value.
	 * @return result value of the calculation.
	 */
	protected abstract BufferEntry calculate(OptionalIntegerValue arg1,
			OptionalIntegerValue arg2);

	@Override
	public void run() {
		while (this.running) {
			final Tupel input = this.inputBuffer.get();
			input.getFirst().accept(this.visitor);
			input.getSecond().accept(this.visitor);
			if (this.possibleInputs.size() == 2) {
				final BufferEntry result = this.calculate(
						this.possibleInputs.remove(0),
						this.possibleInputs.remove(0));
				this.outputBuffer.put(result);
			}
		}
	}
	
	@Override
	public void updateConstantEnvironment(
			Map<String, OptionalIntegerValue> constantEnvironment) {
		this.visitor.setConstantEnvironment(constantEnvironment);
	}
	
	@Override
	public void updateVariableEnvironment(
			Map<String, Process> variableEnvironment) {
		this.visitor.setVariableEnvironment(variableEnvironment);
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
