package model;

import buffer.AbstractBuffer;
import buffer.BufferSolution;

public class MergeSort<T extends BufferEntry<T>> implements Runnable {

	private final AbstractBuffer<T> start;
	private final AbstractBuffer<T> output1;
	private final AbstractBuffer<T> output2;
	private final AbstractBuffer<T> input1;
	private final AbstractBuffer<T> input2;
	private final AbstractBuffer<T> result;
	private final MergeSortManager<T> manager;
	private boolean running;
	
	/**
	 * Initializes the object.
	 * @param start A buffer that contains elements which should be sorted.
	 * @param result The output buffer for sorted elements.
	 * @param manager The manager which will take care of the sorting process.
	 */
	public MergeSort(AbstractBuffer<T> start, AbstractBuffer<T> result, MergeSortManager<T> manager) {
		this.start = start;
		this.output1 = new BufferSolution<T>();
		this.output2 = new BufferSolution<T>();
		this.input1 = new BufferSolution<T>();
		this.input2 = new BufferSolution<T>();
		this.result = result;
		this.manager = manager;
		this.running = false;
	}

	@Override
	public void run() {
		this.split();
		if (this.running) {
			this.manager.startNewThread(output1, input1);
			this.manager.startNewThread(output2, input2);
		} else {
			this.endRecursion();
		}
		this.merge();
	}

	/**
	 * Splits the input buffer into two smaller buffers.
	 */
	private void split() {
		T current = start.get();
		int counter = 0;
		boolean toggle = true;
		while (!current.isStopCommand()) {
			if (toggle) {
				this.output1.put(current);
			} else {
				this.output2.put(current);
			}
			toggle = !toggle;
			current = start.get();
			counter++;
		}
		if (counter <= 2) {
			this.running = false;
		}
		this.output1.put(current);
		this.output2.put(current);
	}

	/**
	 * Merges and sorts both input buffers into one result buffer.
	 */
	private void merge() {
		T first = this.input1.get();
		T second = this.input2.get();
		boolean finished = false;
		while (!finished) {
			if (first.isStopCommand()) {
				while (!second.isStopCommand()) {
					this.result.put(second);
					second = this.input2.get();
				}
				finished = true;
			} else if (second.isStopCommand()) {
				while (!first.isStopCommand()) {
					this.result.put(first);
					first = this.input1.get();
				}
				finished = true;
			} else {
				if (first.compareTo(second) <= 0) {
					this.result.put(first);
					first = this.input1.get();
				} else {
					this.result.put(second);
					second = this.input2.get();
				}
			}
		}
		this.result.put(first);
	}

	/**
	 * Starts the sorting process.
	 */
	public void start() {
		this.running = true;
		new Thread(this).start();
	}

	/**
	 * Finalizes the splitting phase.
	 */
	private void endRecursion() {
		this.putAllEntriesToOtherBuffer(output1, input1);
		this.putAllEntriesToOtherBuffer(output2, input2);
	}
	
	/**
	 * Moves all entries from <source> to <destination>.
	 */
	private void putAllEntriesToOtherBuffer(AbstractBuffer<T> source, AbstractBuffer<T> destination) {
		T current = source.get();
		while (!current.isStopCommand()) {
			destination.put(current);
			current = source.get();
		}
		destination.put(current);
	}
}
