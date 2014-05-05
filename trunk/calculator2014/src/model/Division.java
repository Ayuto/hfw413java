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
	public int calculate() {
		if (this.getSecond().getValue() == 0) {
			throw DivisionByZeroException.create();
		}
		return this.getFirst().getValue() / this.getSecond().getValue();
	}

}
