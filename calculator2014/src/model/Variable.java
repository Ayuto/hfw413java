package model;


public class Variable implements Expression {
	
	private static final int InitialVariableValue = 0;
	private static final String ValueOpenBracket = "(";
	private static final String ValueCloseBracket = ")";
	private static final int IncrementValue = 1;

	public static Variable createVariable(final String name){
		return new Variable(name, InitialVariableValue);
	}
	
	private final String name;
	private int value;
	
	private Variable(final String name, final int initialValue){
		this.name = name;
		this.setValue(initialValue);
	}
	private String getName() {
		return this.name;
	}

	@Override
	public String toString(){
		return this.getName() + ValueOpenBracket + this.getValue() + ValueCloseBracket;
	}
	public void up(){
		this.setValue(this.getValue() + IncrementValue);
	}
	public void down(){
		this.setValue(this.getValue() - IncrementValue);
	}
	public int getValue() {
		return this.value;
	}
	private void setValue(final int newValue) {
		this.value = newValue;
	}
}
