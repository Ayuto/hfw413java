package model;

import java.util.List;
import java.util.Vector;

import buffer.AbstractBuffer;
import buffer.BufferSolution;

/**
 * A class to sort a list by using the merge sort algorithm.
 */
public class MergeSortManager<T extends BufferEntry<T>> {

	private final List<T> input;

	/**
	 * Sorts a list from the smallest to the highest value.
	 */
	public MergeSortManager(List<T> input) {
		this.input = input;
	}

	/**
	 * Starts the sorting process.
	 * 
	 * @return The sorted list.
	 */
	public List<T> sort() {
		AbstractBuffer<T> inputBuffer = new BufferSolution<T>();
		AbstractBuffer<T> outputBuffer = new BufferSolution<T>();
		List<T> result = new Vector<T>();
		for (T current : this.input) {
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
	public void startNewThread(AbstractBuffer<T> bufferInput, AbstractBuffer<T> bufferOutput) {
		new MergeSort<T>(bufferInput, bufferOutput, this).start();
	}
}
