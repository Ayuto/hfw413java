package model;


public class Tupel {

	private final BufferEntry first;
	private final BufferEntry second;

	public Tupel(final BufferEntry first, final BufferEntry second) {
		this.first = first;
		this.second = second;
	}

	public BufferEntry getSecond() {
		return this.second;
	}

	public BufferEntry getFirst() {
		return this.first;
	}
}