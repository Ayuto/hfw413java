package model;

public class TreeRoot extends OutputTree {

	private static TreeRoot instance = null;
	
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
