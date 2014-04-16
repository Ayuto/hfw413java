package model;

public class Devision extends CompositeExpression {

	private static final String OPERATOR_SYMBOL = "/";

	public static Devision create(Expression first, Expression second) {
		return new Devision(first, second);
	}

	private Devision(Expression first, Expression second) {
		super(first, second);
	}

	public String getOperator() {
		return Devision.OPERATOR_SYMBOL;
	}

	@Override
	public int calculate() {
		return this.getFirst().getValue() / this.getSecond().getValue();
	}

}
