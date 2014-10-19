package model;

import visitor.Visitor;

public class Constant implements BufferEntry {

	private final String name;

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
