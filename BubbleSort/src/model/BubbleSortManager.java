package model;

import java.util.List;
import java.util.Vector;

import lock.AbstractLock;
import lock.Lock;
import buffer.AbstractBuffer;
import buffer.BufferSolution;

public class BubbleSortManager<T extends BufferEntry<T>> {

	private final List<T> input;
	private AbstractBuffer<T> result;
	private int runningThreads;
	private AbstractLock mutex;
	private AbstractLock lockFinished;

	public BubbleSortManager(final List<T> input) {
		this.input = input;
		this.result = new BufferSolution<T>();
		this.runningThreads = 0;
		this.mutex = new Lock(false);
		this.lockFinished = new Lock(true);
	}

	public List<T> sort() {
		for (final T element : this.input) {
			this.result.put(element);
		}
		this.createNewThread();
		this.lockFinished.lock();
		final List<T> result = new Vector<T>();
		T current = this.result.get();
		while (!current.isStopCommand()) {
			result.add(current);
			current = this.result.get();
		}
		return result;
	}

	void createNewThread() {
		this.mutex.lock();
		final AbstractBuffer<T> input = this.result;
		this.result = new BufferSolution<T>();
		new BubbleSort<T>(input, this.result, this).start();
		this.runningThreads++;
		this.mutex.unlock();
	}

	void threadEnds() {
		this.mutex.lock();
		this.runningThreads--;
		if (this.runningThreads == 0) {
			this.lockFinished.unlock();
		}
		this.mutex.unlock();
	}
}
