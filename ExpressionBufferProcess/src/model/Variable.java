package model;

import visitor.Visitor;

/**
 * A variable which can be written into a buffer.
 */
public class Variable implements BufferEntry {

	private String name;

	/**
	 * Constructor for a variable with the given name.
	 * 
	 * @param name
	 *            given name for the variable
	 */
	public Variable(String name) {
		this.name = name;
	}

	/**
	 * Getter for the name of the variable.
	 * 
	 * @return the name of the receiver.
	 */
	public String getName() {
		return this.name;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.handleVariable(this);
	}

}
