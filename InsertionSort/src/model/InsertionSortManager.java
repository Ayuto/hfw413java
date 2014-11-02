package model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import lock.AbstractLock;
import lock.Lock;
import buffer.AbstractBuffer;
import buffer.BufferSolution;

/**
 * A class to sort a list by using the insertion sort algorithm.
 */
public class InsertionSortManager<T extends BufferEntry<T>> {
	private final Collection<T> input;
	private AbstractBuffer<T> result;

	private final AbstractLock mutex;
	private final AbstractLock finished;

	private int runningThreads;

	/**
	 * Sorts a list from the smallest to the highest value.
	 */
	public InsertionSortManager(final Collection<T> input) {
		this.input = input;
		this.result = new BufferSolution<T>();

		this.mutex = new Lock(false);
		this.finished = new Lock(true);

		this.runningThreads = 0;
	}

	/**
	 * Starts the sorting process.
	 * 
	 * @return The sorted list.
	 */
	public List<T> sort() {
		this.result = new BufferSolution<T>();
		T stop = null;

		for (final T current : this.input) {
			this.result.put(current);
			if (current.isStopCommand()) {
				stop = current;
			}
		}

		this.startNewThread(this.getNewBufferWithStop(stop), this.result);

		this.finished.lock();

		final List<T> result = new LinkedList<T>();
		T current = this.result.get();
		while (!current.isStopCommand()) {
			result.add(current);
			current = this.result.get();
		}
		return result;
	}

	/**
	 * Returns a new buffer which contains only the given stop command.
	 */
	private AbstractBuffer<T> getNewBufferWithStop(final T stop) {
		final AbstractBuffer<T> result = new BufferSolution<T>();
		result.put(stop);
		return result;
	}

	/**
	 * Starts a new InsertionSort thread by using the two given buffers.
	 */
	public void startNewThread(final AbstractBuffer<T> inputSorted,
			final AbstractBuffer<T> inputUnsorted) {
		this.mutex.lock();
		this.result = new BufferSolution<T>();
		this.runningThreads++;
		new InsertionSort<T>(inputSorted, inputUnsorted, this.result, this)
				.start();
		this.mutex.unlock();
	}

	/**
	 * This operation should be called by a terminating InsertionSort thread to
	 * finalize it.
	 */
	public void threadEnds() {
		this.mutex.lock();
		this.runningThreads--;
		if (this.runningThreads == 0) {
			this.finished.unlock();
		}
		this.mutex.unlock();
	}
}
