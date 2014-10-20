package model;

import java.util.HashMap;
import java.util.Map;

import visitor.VisitorImpl;
import buffer.AbstractBuffer;

/**
 * A clone process clones all entries of a buffer into two other buffers.
 */
public class CloneProcess implements ExpressionProcess {

	private final AbstractBuffer<BufferEntry> input;
	private final AbstractBuffer<BufferEntry> output1;
	private final AbstractBuffer<BufferEntry> output2;
	private boolean running;
	private final VisitorImpl visitor;

	/**
	 * Constructor for a new clone process with the given parameters.
	 * 
	 * @param input
	 *            a buffer of buffer enties which should be cloned.
	 * @param output1
	 *            first output buffer with all cloned entries.
	 * @param output2
	 *            second output buffer with all cloned entries.
	 */
	public CloneProcess(final AbstractBuffer<BufferEntry> input,
			final AbstractBuffer<BufferEntry> output1,
			final AbstractBuffer<BufferEntry> output2) {
		this.input = input;
		this.output1 = output1;
		this.output2 = output2;
		this.running = false;
		this.visitor = new VisitorImpl(this,
				new HashMap<String, OptionalIntegerValue>(),
				new HashMap<String, Process>());
	}

	@Override
	public void run() {
		while (this.running) {
			final BufferEntry entry = this.input.get();
			entry.accept(this.visitor);
			this.output1.put(entry);
			this.output2.put(entry);
		}
	}

	@Override
	public void stop() {
		this.running = false;
	}

	@Override
	public void start() {
		this.running = true;
		new Thread(this).start();
	}

	/**
	 * No special handling of OptionalIntegerValues because the CloneProcess
	 * just clones the input.
	 */
	@Override
	public void addDetectedValue(final OptionalIntegerValue value) {
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
}
