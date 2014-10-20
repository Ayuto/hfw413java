package model;

import visitor.Visitor;

/**
 * A constant which can be written into a buffer.
 */
public class Constant implements BufferEntry {

	private final String name;

	/**
	 * Constructor for a new constant.
	 * 
	 * @param name
	 *            the name of the new constant
	 */
	public Constant(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.handleConstant(this);
	}

}
