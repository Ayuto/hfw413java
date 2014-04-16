package model;

public abstract class CompositeExpression extends Expression implements
		Observer {

	private static final String CYCLE_DETECTED_MSG = "Cycle detected!!!";
	private static final String EXPRESSION_OPEN_BRACKET = "(";
	private static final String EXPRESSION_CLOSE_BRACKET = ")";
	private static final String EQUALITY_SIGN = "=";
	public static final String DIVISION_BY_ZERO_MSG = "Devision by zero detected! You can not devide by zero";

	private final Expression first;
	private final Expression second;
	private int value;

	public CompositeExpression(Expression first, Expression second) {
		if (first.contains(this) || second.contains(this))
			throw new Error(CYCLE_DETECTED_MSG);
		if (first.equals(DivisionByZero.getInstance())
				|| second.equals(DivisionByZero.getInstance()))
			throw new Error(DIVISION_BY_ZERO_MSG);
		this.first = first;
		this.second = second;
		this.getFirst().register(this);
		this.getSecond().register(this);
		this.value = this.calculate();
	}

	@Override
	public void update() {
		this.value = this.calculate();
		this.notifyObservers();
	}

	public abstract int calculate();

	public abstract String getOperator();

	public int getValue() {
		return value;
	}

	protected Expression getFirst() {
		return first;
	}

	protected Expression getSecond() {
		return second;
	}

	@Override
	public boolean contains(Expression argument) {
		if (this.equals(argument))
			return true;
		return (this.getFirst().contains(this) || this.getSecond().contains(
				this));
	}

	@Override
	public String toString() {
		return EXPRESSION_OPEN_BRACKET + this.getFirst().toString()
				+ this.getOperator() + this.getSecond().toString()
				+ EQUALITY_SIGN + this.getValue() + EXPRESSION_CLOSE_BRACKET;
	}
}
