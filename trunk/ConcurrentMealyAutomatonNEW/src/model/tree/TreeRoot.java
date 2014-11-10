package model.tree;

import model.RootHasNoValueException;

/**
 * A tree root represents the root element of an output tree.
 * The root element of the tree has no value!
 */
public class TreeRoot extends OutputTree {

	private static TreeRoot instance = null;
	
	/**
	 * Getter for the root element of an output tree.
	 */
	public static TreeRoot getInstance() {
		if (TreeRoot.instance == null) {
			TreeRoot.instance = new TreeRoot();
		}
		return TreeRoot.instance;
	}
	
	private TreeRoot() {
	}
	
	@Override
	public boolean isRoot() {
		return true;
	}

	@Override
	public String getValue() throws RootHasNoValueException {
		throw new RootHasNoValueException("The Root has no value!");
	}

}
