package model;

public class Subtraction extends CompositeExpression {

	private static final String OPERATOR_SYMBOL = "-";

	public static Subtraction create(Expression first, Expression second) {
		return new Subtraction(first, second);
	}

	private Subtraction(Expression first, Expression second) {
		super(first, second);
	}

	public String getOperator() {
		return Subtraction.OPERATOR_SYMBOL;
	}

	@Override
	public int calculate() {
		return this.getFirst().getValue() - this.getSecond().getValue();
	}
}
