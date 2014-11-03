package model;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import buffer.AbstractBuffer;
import buffer.BufferSolution;

/**
 * A class to sort a list by using the merge sort algorithm.
 */
public class MergeSortManager<T extends BufferEntry<T>> {

	private final Collection<T> input;
	private int threadAmount;

	public int getThreadAmount() {
		return threadAmount;
	}

	/**
	 * Sorts a list from the smallest to the highest value.
	 */
	public MergeSortManager(final Collection<T> input) {
		this.input = input;
	}

	/**
	 * Starts the sorting process.
	 * 
	 * @return The sorted list.
	 */
	public List<T> sort() {
		final AbstractBuffer<T> inputBuffer = new BufferSolution<T>();
		final AbstractBuffer<T> outputBuffer = new BufferSolution<T>();
		final List<T> result = new Vector<T>();
		for (final T current : this.input) {
			inputBuffer.put(current);
		}
		this.startNewThread(inputBuffer, outputBuffer);
		T current = outputBuffer.get();
		while (!current.isStopCommand()) {
			result.add(current);
			current = outputBuffer.get();
		}
		return result;
	}
	
	/**
	 * Starts a new MergeSort thread.
	 * @param bufferInput The input buffer for the new thread.
	 * @param bufferOutput The output buffer for the new thread.
	 */
	public void startNewThread(final AbstractBuffer<T> bufferInput, final AbstractBuffer<T> bufferOutput) {
		new MergeSort<T>(bufferInput, bufferOutput, this).start();
		this.threadAmount++;
	}
}
