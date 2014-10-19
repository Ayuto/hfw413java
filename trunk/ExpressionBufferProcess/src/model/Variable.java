package model;

import visitor.Visitor;

public class Variable implements BufferEntry {
	
	private String name;
	
	public Variable(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.handleVariable(this);
	}

}
