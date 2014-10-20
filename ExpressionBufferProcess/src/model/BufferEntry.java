package model;

import visitor.Visitor;

/**
 * A buffer entry can be written into a buffer.
 */
public interface BufferEntry {

	/**
	 * A buffer entry accepts a visitor to evaluate the type of the entry.
	 * 
	 * @param visitor
	 *            accepted visitor.
	 */
	void accept(Visitor visitor);
}