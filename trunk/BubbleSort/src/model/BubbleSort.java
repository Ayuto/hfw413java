package model;

import buffer.AbstractBuffer;

public class BubbleSort<T extends BufferEntry<T>> implements Runnable {

	private final BubbleSortManager<T> manager;
	private final AbstractBuffer<T> input;
	private final AbstractBuffer<T> result;
	private boolean running;
	private boolean switched;

	public BubbleSort(final AbstractBuffer<T> input,
			final AbstractBuffer<T> result,
			final BubbleSortManager<T> manager) {
		this.manager = manager;
		this.input = input;
		this.result = result;
		this.running = false;
	}

	@Override
	public void run() {
		T first = this.input.get();
		T second = this.input.get();
		this.switched = false;
		while (this.running) {
			if (first.compareTo(second) <= 0) {
				this.result.put(first);
				first = second;
				second = this.input.get();
			} else {
				if (!this.switched) {
					this.switched = true;
					this.manager.createNewThread();
				}
				this.result.put(second);
				second = this.input.get();
			}
			if (second.isStopCommand()){
				this.stop();
			}
		}
		synchronized (this.manager) {
			this.manager.threadEnds();
			this.notify();
		}
	}

	public void start() {
		this.running = true;
		new Thread(this).start();
	}

	public void stop() {
		this.running = false;
	}

}
