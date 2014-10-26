package model;

import java.util.List;
import java.util.Vector;

import buffer.AbstractBuffer;
import buffer.BufferSolution;

public class MergeSortManager<T extends BufferEntry<T>> {

	private final List<T> input;
	
	public MergeSortManager(List<T> input) {
		this.input = input;
	}
	
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
	
	public void startNewThread(AbstractBuffer<T> bufferInput, AbstractBuffer<T> bufferOutput) {
		new MergeSort<T>(bufferInput, bufferOutput, this).start();
	}
}
