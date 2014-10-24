package model;

import java.util.List;
import java.util.Vector;

import buffer.AbstractBuffer;
import buffer.BufferSolution;

public class BubbleSortManager<T extends BufferEntry<T>> {

	private final List<T> input;
	private AbstractBuffer<T> result;
	private int runningThreads;

	public BubbleSortManager(final List<T> input) {
		this.input = input;
		this.result = new BufferSolution<T>();
		this.runningThreads = 0;
	}

	public List<T> sort() {
		for (final T element : this.input) {
			this.result.put(element);
		}
		this.createNewThread();
		synchronized (this) {
			while (this.runningThreads > 0) {
				try {
					this.wait();
				} catch (final InterruptedException e) {
					throw new Error("interruption");
				}
			}
		}
		final List<T> result = new Vector<T>();
		T current = this.result.get();
		while (!current.isStopCommand()) {
			result.add(current);
			current = this.result.get();
		}
		return result;
	}

	synchronized void createNewThread() {
		final AbstractBuffer<T> input = this.result;
		this.result = new BufferSolution<T>();
		new BubbleSort<T>(input, this.result, this).start();
		this.runningThreads++;
	}

	public void threadEnds() {
		this.runningThreads--;
	}
}
