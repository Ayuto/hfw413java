package model.tree;

import model.RootHasNoValueException;

/**
 * Abstract output tree which has a string value and where you can add
 * additional elements.
 */
public abstract class OutputTree {

	/**
	 * Returns true if and only if the receiver is the root element of the tree.
	 */
	public abstract boolean isRoot();

	/**
	 * Calculates the string value of the whole tree with the current element as
	 * last part.
	 * 
	 * @return the string value of the tree
	 * @throws RootHasNoValueException
	 *             if the receiver is the root of the tree.
	 */
	public abstract String getValue() throws RootHasNoValueException;

	/**
	 * Adds the given element to the receiver and returns the result as a new
	 * tree.
	 * 
	 * @param element
	 *            given element which should be added to the tree
	 * @return a new tree with the receiver as parent and the given element as
	 *         new current element.
	 */
	public OutputTree addElement(String element) {
		return new Output(this, element);
	}
}
