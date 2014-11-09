package model;

public abstract class OutputTree {

	public abstract boolean isRoot();
	
	public abstract String getValue() throws RootHasNoValueException;
	
	public OutputTree addElement(String element) {
		return new Output(this, element);
	}
}
