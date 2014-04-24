package model;

public class Division extends CompositeExpression {

	private static final String OPERATOR_SYMBOL = "/";

	public static Division create(final Expression first, final Expression second) {
		return new Division(first, second);
	}

	protected Division(final Expression first, final Expression second) {
		super(first, second);
	}

	@Override
	public String getOperator() {
		return Division.OPERATOR_SYMBOL;
	}

	@Override
	public int calculate() {
		if (this.getSecond().getValue() == 0) {
			throw DivisionByZeroException.create(DIVISION_BY_ZERO_MSG);
		}
		return this.getFirst().getValue() / this.getSecond().getValue();
	}

}
