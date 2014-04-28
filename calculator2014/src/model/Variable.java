package model;

/**
 * A unary Expression with a value.
 */
public class Variable extends Expression {

	private static final int InitialVariableValue = 0;
	private static final String ValueOpenBracket = "(";
	private static final String ValueCloseBracket = ")";
	private static final int IncrementValue = 1;

	/**
	 * Creates a Variable with the name <name>, the value <InitialVariableValue> and returns it.
	 */
	public static Variable createVariable(final String name) {
		return new Variable(name, InitialVariableValue);
	}

	private final String name;
	private int value;

	private Variable(final String name, final int initialValue) {
		this.name = name;
		this.setValue(initialValue);
	}

	private String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.getName() + ValueOpenBracket + this.getValue()
				+ ValueCloseBracket;
	}

	/**
	 * Increases the value of <this> by <IncrementValue>.
	 */
	public void up() {
		this.setValue(this.getValue() + IncrementValue);
		this.notifyObservers();
	}

	/**
	 * Decreases the value of <this> by <IncrementValue>.
	 */
	public void down() {
		this.setValue(this.getValue() - IncrementValue);
		this.notifyObservers();
	}

	@Override
	public int getValue() {
		return this.value;
	}

	private void setValue(final int newValue) {
		this.value = newValue;
	}

	@Override
	public boolean contains(final Expression argument) {
		return this.equals(argument);
	}
}
