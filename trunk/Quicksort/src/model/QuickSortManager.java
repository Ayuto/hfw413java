package model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import buffer.AbstractBuffer;
import buffer.BufferSolution;

/**
 * A class to sort a list by using the quick sort algorithm.
 */
public class QuickSortManager<T extends BufferEntry<T>> {
	private final Collection<T> input;
	private int threadAmount;

	public int getThreadAmount() {
		return threadAmount;
	}

	/**
	 * Sorts a list from the smallest to the highest value.
	 */
	public QuickSortManager(final Collection<T> input) {
		this.input = input;
	}

	/**
	 * Starts the sorting process.
	 * 
	 * @return The sorted list.
	 */
	public List<T> sort() {
		final AbstractBuffer<T> input = new BufferSolution<T>();
		final AbstractBuffer<T> output = new BufferSolution<T>();

		for (final T current : this.input) {
			input.put(current);
		}

		this.createNewThread(input, output);

		final List<T> result = new LinkedList<T>();

		T current = output.get();
		while (!current.isStopCommand()) {
			result.add(current);
			current = output.get();
		}
		return result;
	}

	/**
	 * Starts a new QuickSort thread.
	 */
	public void createNewThread(final AbstractBuffer<T> input,
			final AbstractBuffer<T> output) {
		new QuickSort<T>(input, output, this).start();
		this.threadAmount++;
	}
}
