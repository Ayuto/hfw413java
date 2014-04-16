package model;

public class Division extends CompositeExpression {

	private static final String OPERATOR_SYMBOL = "/";

	public static Division create(Expression first, Expression second) {
		if (second.getValue() == 0)
			throw new Error(CompositeExpression.DIVISION_BY_ZERO_MSG);
		return new Division(first, second);
	}

	protected Division(Expression first, Expression second) {
		super(first, second);
	}

	public String getOperator() {
		return Division.OPERATOR_SYMBOL;
	}

	@Override
	public int calculate() {
		return this.getFirst().getValue() / this.getSecond().getValue();
	}

}
