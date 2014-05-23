package model;

/**
 * A binary division with two expressions as arguments.
 */
public class Division extends CompositeExpression {

	private static final String OPERATOR_SYMBOL = "/";

	/**
	 * Creates and returns a Division with two arguments and the value <first> divided by <second>.
	 */
	public static Division create(final Expression first, final Expression second) {
		return new Division(first, second);
	}

	private Division(final Expression first, final Expression second) {
		super(first, second);
	}

	@Override
	public String getOperator() {
		return Division.OPERATOR_SYMBOL;
	}

	@Override
	public IntValue calculate() {
		if (this.getSecond().getValue().equals(0)) {
			return new IntValue("Division by zero!");
		}
		
		return this.getFirst().getValue().divide(this.getSecond().getValue());
	}
	
	@Override
	public Expression copy() {
		return Division.create(this.getFirst().copy(), this.getSecond().copy());
	}
}
