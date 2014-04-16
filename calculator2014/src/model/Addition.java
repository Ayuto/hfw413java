package model;

public class Addition extends CompositeExpression {

	private static final String OPERATOR_SYMBOL = "+";

	public static Addition create(Expression first, Expression second) {
		return new Addition(first, second);
	}

	private Addition(Expression first, Expression second) {
		super(first, second);
	}

	public String getOperator() {
		return Addition.OPERATOR_SYMBOL;
	}

	@Override
	public int calculate() {
		return this.getFirst().getValue() + this.getSecond().getValue();
	}
}
