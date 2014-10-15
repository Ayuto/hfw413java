package model;

import visitor.Visitor;
import visitor.VisitorImpl;
import buffer.AbstractBuffer;

public class CloneProcess implements ExpressionProcess {

	private final AbstractBuffer<BufferEntry> input;
	private final AbstractBuffer<BufferEntry> output1;
	private final AbstractBuffer<BufferEntry> output2;
	private boolean running;
	private final Visitor visitor;
	
	public CloneProcess(final AbstractBuffer<BufferEntry> input,
			final AbstractBuffer<BufferEntry> output1,
			final AbstractBuffer<BufferEntry> output2) {
		this.input = input;
		this.output1 = output1;
		this.output2 = output2;
		this.running = false;
		this.visitor = new VisitorImpl(this);
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
}
