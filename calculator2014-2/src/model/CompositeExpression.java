package model;

/**
 * A compositeExpression is an expression with two arguments.
 */
public abstract class CompositeExpression extends Expression implements
		Observer {

	public static final String CYCLE_DETECTED_MSG = "Cycle detected!!!";
	private static final String EXPRESSION_OPEN_BRACKET = "(";
	private static final String EXPRESSION_CLOSE_BRACKET = ")";
	private static final String EQUALITY_SIGN = "=";
	

	private Expression first;
	private Expression second;
	private IntValue value;

	/**
	 * Constructor of CompositeExpression. Calculates the value and registers <this> to both arguments.
	 * Throws an Error if one of the arguments contains <this>.
	 */
	public CompositeExpression(final Expression first, final Expression second) {
		if (first.contains(this) || second.contains(this)) {
			throw new Error(CYCLE_DETECTED_MSG);
		}
		this.first = first;
		this.second = second;
		this.getFirst().register(this);
		this.getSecond().register(this);
		this.value = this.calculate();
		}

	public void setFirst(Expression first) {
		this.first = first;
	}

	public void setSecond(Expression second) {
		this.second = second;
	}

	@Override
	public void update() {
		this.value = this.calculate();
		this.notifyObservers();
	}

	/**
	 * Calculates the value of <this>.
	 */
	public abstract IntValue calculate();

	/**
	 * Returns the Operator of <this>.
	 */
	public abstract String getOperator();

	@Override
	public IntValue getValue() {
		return this.value;
	}

	protected Expression getFirst() {
		return this.first;
	}

	protected Expression getSecond() {
		return this.second;
	}

	@Override
	public boolean contains(final Expression argument) {
		if (this.equals(argument)) {
			return true;
		}
		return (this.getFirst().contains(argument) || this.getSecond().contains(
				argument));
	}

	@Override
	public String toString() {
		return EXPRESSION_OPEN_BRACKET + this.getFirst().toString()
				+ this.getOperator() + this.getSecond().toString()
				+ EQUALITY_SIGN + this.getValue().toString() + EXPRESSION_CLOSE_BRACKET;
	}
	
	public void substitute(final Variable variable, final Expression expression)
	{
		if (this.getFirst().equals(variable)) {
			this.setFirst(expression);
			expression.register(this);
		}
		else {
			this.getFirst().substitute(variable, expression);
		}
		
		if (this.getSecond().equals(variable))
		{
			this.setSecond(expression);
			expression.register(this);
		}
		else {
			this.getSecond().substitute(variable, expression);
		}
		this.update();
	}
}
