package model.tree;

import model.RootHasNoValueException;

/**
 * A node of the output tree with a current element and a parent.
 */
public class Output extends OutputTree {

	private final OutputTree parent;
	private final String currentElement;

	/**
	 * Constructor for an output tree with the given element and the given
	 * parent.
	 * 
	 * @param parent
	 *            given parent for the new output tree.
	 * @param element
	 *            given element for the new output tree.
	 */
	public Output(OutputTree parent, String element) {
		this.parent = parent;
		this.currentElement = element;
	}

	/**
	 * Constructor for an output tree which has the root element as parent and
	 * the given element as value.
	 * 
	 * @param element
	 *            given element for the new output tree.
	 */
	public Output(String element) {
		this(TreeRoot.getInstance(), element);
	}

	@Override
	public boolean isRoot() {
		return false;
	}

	@Override
	public String getValue() {
		if (this.parent.isRoot()) {
			return this.currentElement;
		}
		try {
			return this.parent.getValue() + this.currentElement;
		} catch (RootHasNoValueException e) {
			throw new Error("Internal error! Should never happen here!");
		}
	}

}
