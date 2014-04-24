package model;

public class Addition extends CompositeExpression {

	private static final String OPERATOR_SYMBOL = "+";

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
	public int calculate() {
		return this.getFirst().getValue() + this.getSecond().getValue();
	}
}
