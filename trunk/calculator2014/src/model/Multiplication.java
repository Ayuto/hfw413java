package model;

public class Multiplication extends CompositeExpression {

	private static final String OPERATOR_SYMBOL = "*";

	public static Multiplication create(Expression first, Expression second) {
		return new Multiplication(first, second);
	}

	private Multiplication(Expression first, Expression second) {
		super(first, second);
	}

	public String getOperator() {
		return Multiplication.OPERATOR_SYMBOL;
	}

	@Override
	public int calculate() {
		return this.getFirst().getValue() * this.getSecond().getValue();
	}

}
