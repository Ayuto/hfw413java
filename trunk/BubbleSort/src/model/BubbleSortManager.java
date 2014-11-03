package model;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import lock.AbstractLock;
import lock.Lock;
import buffer.AbstractBuffer;
import buffer.BufferSolution;

/**
 * A class to sort a list by using the bubble sort algorithm.
 */
public class BubbleSortManager<T extends BufferEntry<T>> {

	private final Collection<T> input;
	private AbstractBuffer<T> result;
	private int runningThreads;
	private int threadAmount;
	private final AbstractLock mutex;
	private final AbstractLock lockFinished;

	/**
	 * Sorts a list from the smallest to the highest value.
	 */
	public BubbleSortManager(final Collection<T> input) {
		this.input = input;
		this.result = new BufferSolution<T>();
		this.runningThreads = 0;
		this.mutex = new Lock(false);
		this.lockFinished = new Lock(true);
	}

	/**
	 * Starts the sorting process.
	 * 
	 * @return The sorted list.
	 */
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

	/**
	 * Starts a new BubbleSort thread.
	 */
	void createNewThread() {
		this.mutex.lock();
		final AbstractBuffer<T> input = this.result;
		this.result = new BufferSolution<T>();
		new BubbleSort<T>(input, this.result, this).start();
		this.threadAmount++;
		this.runningThreads++;
		this.mutex.unlock();
	}

	/**
	 * This operation should be called by a terminating BubbleSort thread to
	 * finalize it.
	 */
	void threadEnds() {
		this.mutex.lock();
		this.runningThreads--;
		if (this.runningThreads == 0) {
			this.lockFinished.unlock();
		}
		this.mutex.unlock();
	}
	
	public int getThreadAmount() {
		return this.threadAmount;
	}
}
