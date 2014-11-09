package model;

public class Output extends OutputTree {
	
	private final OutputTree parent;
	private final String currentElement;
	
	public Output(OutputTree parent, String element) {
		this.parent = parent;
		this.currentElement = element;
	}

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
