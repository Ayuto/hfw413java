package model;

import buffer.AbstractBuffer;
import buffer.BufferSolution;

public class QuickSort<T extends BufferEntry<T>> implements Runnable {
	
	private final AbstractBuffer<T> start;
	private final AbstractBuffer<T> result;
	
	private final AbstractBuffer<T> input1;
	private final AbstractBuffer<T> input2;
	
	private final AbstractBuffer<T> output1;
	private final AbstractBuffer<T> output2;
	private final QuickSortManager<T> manager;

	public QuickSort(final AbstractBuffer<T> start, final AbstractBuffer<T> result, final QuickSortManager<T> manager) {
		this.start = start;
		this.result = result;
		this.manager = manager;

		this.input1 = new BufferSolution<T>();
		this.input2 = new BufferSolution<T>();
		
		this.output1 = new BufferSolution<T>();
		this.output2 = new BufferSolution<T>();
	}
	
	@Override
	public void run() {
		final T pivot = this.start.get();
		if (pivot.isStopCommand()) {
			this.result.put(pivot);
			return;
		}
		
		boolean startNewThread = false;
		T current = this.start.get();
		while(!current.isStopCommand()) {
			if (current.compareTo(pivot) <= 0) {
				this.output1.put(current);
			} else {
				this.output2.put(current);
			}
			startNewThread = true;
			
			current = this.start.get();
		}
		
		this.output1.put(pivot);
		this.output1.put(current); // Stop command
		this.output2.put(current); // Stop command
		
		if (startNewThread) {
			this.manager.createNewThread(this.output1, this.input1);
			this.manager.createNewThread(this.output2, this.input2);
		}
		else {
			this.input1.put(this.output1.get()); // Pivot element
			this.input1.put(this.output1.get()); // Stop command
			this.input2.put(this.output2.get()); // Stop command
		}
		
		this.merge();
	}

	private void merge() {
		this.moveInputToResult(this.input1);
		final T stop = this.moveInputToResult(this.input2);
		this.result.put(stop);
	}
	
	public T moveInputToResult(final AbstractBuffer<T> input) {
		T current = input.get();
		while (!current.isStopCommand()) {
			this.result.put(current);
			current = input.get();
		}
		return current;
	}

	public void start() {
		new Thread(this).start();
	}

}
