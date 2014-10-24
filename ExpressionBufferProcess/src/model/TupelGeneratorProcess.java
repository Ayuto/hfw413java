package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import visitor.VisitorImpl;
import buffer.AbstractBuffer;

/**
 * A tupel generator process reads two input buffer and combines both entries to
 * a tupel which is written into an output buffer.
 */
public class TupelGeneratorProcess implements ExpressionProcess {

	private final AbstractBuffer<BufferEntry> input1;
	private final AbstractBuffer<BufferEntry> input2;
	private final AbstractBuffer<Tupel> output;
	private boolean running;
	private final List<BufferEntry> result;
	private final VisitorImpl visitor;

	/**
	 * Constructor for a tupel generator process with the given parameters.
	 * 
	 * @param input1
	 *            first input buffer
	 * @param input2
	 *            second input buffer
	 * @param output
	 *            tupel output buffer
	 */
	public TupelGeneratorProcess(final AbstractBuffer<BufferEntry> input1,
			final AbstractBuffer<BufferEntry> input2,
			final AbstractBuffer<Tupel> output,
			Map<String, OptionalIntegerValue> constantEnvironment,
			Map<String, Process> variableEnvironment) {
		this.input1 = input1;
		this.input2 = input2;
		this.output = output;
		this.running = false;
		this.result = new LinkedList<BufferEntry>();
		this.visitor = new VisitorImpl(this, constantEnvironment, variableEnvironment);
	}

	@Override
	public void run() {
		while (this.running) {
			this.input1.get().accept(this.visitor);
			this.input2.get().accept(this.visitor);
			if (this.result.size() < 2) {
				this.output.put(new Tupel(StopCommand.getInstance(),
						StopCommand.getInstance()));
			} else {
				this.output.put(new Tupel(this.result.remove(0), this.result
						.remove(0)));
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
	public void stop() {
		this.running = false;
	}

	@Override
	public void start() {
		this.running = true;
		this.result.clear();
		new Thread(this).start();
	}

	@Override
	public void addDetectedValue(final OptionalIntegerValue value) {
		this.result.add(value);
	}

}
