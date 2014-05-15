package model;

/**
 * A binary addition with two expressions as arguments.
 */
public class Addition extends CompositeExpression {

	private static final String OPERATOR_SYMBOL = "+";

	/**
	 * Creates an Addition with the two arguments <first> and <second> and returns it.
	 */
	public static Addition create(final Expression first, final Expression second) {
		return new Addition(first, second);
	}

	private Addition(final Expression first, final Expression second) {
		super(first, second);
	}

	@Override
	public String getOperator() {
		return Addition.OPERATOR_SYMBOL;
	}

	@Override
	public IntValue calculate() {
		return this.getFirst().getValue().add(this.getSecond().getValue());
	}
}
