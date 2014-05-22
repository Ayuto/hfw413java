package model;

/**
 * A binary subtraction with two Expressions as arguments.
 */
public class Subtraction extends CompositeExpression {

	private static final String OPERATOR_SYMBOL = "-";

	/**
	 * Creates and returns a Division with two arguments and the value <first> minus <second>.
	 */
	public static Subtraction create(final Expression first, final Expression second) {
		return new Subtraction(first, second);
	}

	private Subtraction(final Expression first, final Expression second) {
		super(first, second);
	}

	@Override
	public String getOperator() {
		return Subtraction.OPERATOR_SYMBOL;
	}

	@Override
	public IntValue calculate() {
		return this.getFirst().getValue().subtract(this.getSecond().getValue());
	}
	
	@Override
	public Expression copy() {
		Subtraction newExpression = Subtraction.create(this.getFirst().copy(), this.getSecond().copy());
		newExpression.getFirst().register(newExpression);
		newExpression.getSecond().register(newExpression);
		return newExpression;
	}
}
