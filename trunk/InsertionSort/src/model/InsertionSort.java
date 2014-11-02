package model;

import buffer.AbstractBuffer;
import buffer.BufferSolution;

/**
 * A Runnable which does one run of an insertion sort.
 * 
 * @param <T>
 *            Type of the elements of the input.
 */
public class InsertionSort<T extends BufferEntry<T>> implements Runnable {

	private final AbstractBuffer<T> inputSorted;
	private final AbstractBuffer<T> inputUnsorted;
	private final AbstractBuffer<T> outputSorted;
	private final AbstractBuffer<T> outputUnsorted;
	private final InsertionSortManager<T> manager;

	/**
	 * Initializes the object.
	 * 
	 * @param inputSorted
	 *            A buffer that contains already sorted elements.
	 * @param inputUnsorted
	 *            A buffer that contains unsorted elements.
	 * @param outputSorted
	 *            An output buffer for sorted elements.
	 * @param manager
	 *            The manager which will take care of the sorting process.
	 */
	public InsertionSort(final AbstractBuffer<T> inputSorted,
			final AbstractBuffer<T> inputUnsorted,
			final AbstractBuffer<T> outputSorted,
			final InsertionSortManager<T> manager) {
		this.inputSorted = inputSorted;
		this.inputUnsorted = inputUnsorted;
		this.outputSorted = outputSorted;
		this.outputUnsorted = new BufferSolution<T>();
		this.manager = manager;
	}

	@Override
	public void run() {
		final T element = this.inputUnsorted.get();
		if (element.isStopCommand()) {
			this.end();
			return;
		}
		this.manager.startNewThread(this.outputSorted, this.outputUnsorted);
		T current = this.inputUnsorted.get();
		while (!current.isStopCommand()) {
			this.outputUnsorted.put(current);
			current = this.inputUnsorted.get();
		}
		this.outputUnsorted.put(current);

		current = this.inputSorted.get();
		while (!current.isStopCommand() && current.compareTo(element) <= 0) {
			this.outputSorted.put(current);
			current = this.inputSorted.get();
		}
		this.outputSorted.put(element);

		while (!current.isStopCommand()) {
			this.outputSorted.put(current);
			current = this.inputSorted.get();
		}
		this.outputSorted.put(current);
		this.manager.threadEnds();
	}

	/**
	 * Moves all entries from <inputSorted> to <outputSorted>.
	 */
	private void end() {
		T current = this.inputSorted.get();
		while (!current.isStopCommand()) {
			this.outputSorted.put(current);
			current = this.inputSorted.get();
		}

		this.outputSorted.put(current); // stop command
		this.outputUnsorted.put(current); // stop command

		this.manager.threadEnds();
	}

	/**
	 * Starts the sorting process.
	 */
	public void start() {
		new Thread(this).start();
	}

}
