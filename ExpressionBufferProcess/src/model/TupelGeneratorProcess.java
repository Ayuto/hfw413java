package model;

import java.util.LinkedList;
import java.util.List;

import visitor.Visitor;
import visitor.VisitorImpl;
import buffer.AbstractBuffer;

public class TupelGeneratorProcess implements ExpressionProcess {

	private final AbstractBuffer<BufferEntry> input1;
	private final AbstractBuffer<BufferEntry> input2;
	private final AbstractBuffer<Tupel> output;
	private boolean running;
	private final List<BufferEntry> result;
	private final Visitor visitor;
	private final Thread myThread;

	public TupelGeneratorProcess(final AbstractBuffer<BufferEntry> input1,
			final AbstractBuffer<BufferEntry> input2, final AbstractBuffer<Tupel> output) {
		this.input1 = input1;
		this.input2 = input2;
		this.output = output;
		this.running = false;
		this.result = new LinkedList<BufferEntry>();
		this.visitor = new VisitorImpl(this);
		this.myThread = new Thread(this);
	}
	
	
	@Override
	public void run() {
		while(this.running) {
			this.input1.get().accept(this.visitor);
			this.input2.get().accept(this.visitor);
			System.out.println("TupelGen: Got " + this.result.get(0) + " und " + this.result.get(1));
			if (this.result.size() < 2) {
				this.output.put(new Tupel(StopCommand.getInstance(), StopCommand.getInstance()));
			} else {
				this.output.put(new Tupel(this.result.remove(0), this.result.remove(0)));
			}
		}
	}

	@Override
	public void stop() {
		this.running = false;
	}

	@Override
	public void start() {
		this.running = true;
		this.result.clear();
		this.myThread.start();
	}

	@Override
	public void addDetectedValue(final OptionalIntegerValue value) {
		this.result.add(value);
	}

}
