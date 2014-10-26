package model;

import buffer.AbstractBuffer;

/**
 * A Runnable which does one run of a bubble sort.
 * @param <T> Type of the elements of the input.
 */
public class BubbleSort<T extends BufferEntry<T>> implements Runnable {

	private final BubbleSortManager<T> manager;
	private final AbstractBuffer<T> input;
	private final AbstractBuffer<T> result;
	private boolean running;
	private boolean switched;

	/**
	 * Initializes the object.
	 * @param input A buffer that contains elements which should be sorted.
	 * @param result The output buffer for sorted elements.
	 * @param manager The manager which will take care of the sorting process.
	 */
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
		if (first.isStopCommand()) {
			this.manager.threadEnds();
			return;
		}
		T second = this.input.get();
		this.switched = false;
		while (this.running) {
			if (first.isStopCommand()) {
				this.result.put(second);
				this.result.put(first);
				this.stop();
			} else if (second.isStopCommand()) {
				this.result.put(first);
				this.result.put(second);
				this.stop();
			} else if (first.compareTo(second) <= 0) {
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
		}
		this.manager.threadEnds();
	}

	/**
	 * Starts the sorting process.
	 */
	public void start() {
		this.running = true;
		new Thread(this).start();
	}

	/**
	 * Stops the sorting process.
	 */
	public void stop() {
		this.running = false;
	}

}
