package model;

/**
 * A binary multiplication with two expressions as arguments.
 */
public class Multiplication extends CompositeExpression {

	private static final String OPERATOR_SYMBOL = "*";

	/**
	 * Creates a Multiplication with the two arguments <first> and <second> and returns it.
	 */
	public static Multiplication create(final Expression first, final Expression second) {
		return new Multiplication(first, second);
	}

	private Multiplication(final Expression first, final Expression second) {
		super(first, second);
	}

	@Override
	public String getOperator() {
		return Multiplication.OPERATOR_SYMBOL;
	}

	@Override
	public IntValue calculate() {
		return this.getFirst().getValue().multiply(this.getSecond().getValue());
	}

}
