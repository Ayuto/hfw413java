package model;

/**
 * A Tupel are two buffer entries combined in one object.
 */
public class Tupel {

	private final BufferEntry first;
	private final BufferEntry second;

	/**
	 * Constructor for a tupel with the given two buffer entries.
	 * 
	 * @param first
	 *            the first buffer entry
	 * @param second
	 *            the second buffer entry
	 */
	public Tupel(final BufferEntry first, final BufferEntry second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Getter for the second buffer entry of the receiver.
	 * 
	 * @return the second buffer entry
	 */
	public BufferEntry getSecond() {
		return this.second;
	}

	/**
	 * Getter for the first buffer entry of the receiver.
	 * 
	 * @return the first buffer entry
	 */
	public BufferEntry getFirst() {
		return this.first;
	}
}