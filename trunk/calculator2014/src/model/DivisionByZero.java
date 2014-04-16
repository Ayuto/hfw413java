package model;

public class DivisionByZero extends Expression {

	private static final String DIVISION_BY_ZERO_MSG = "Devision by zero detected! You can not devide by zero";
	private static DivisionByZero instance = null;

	public static DivisionByZero getInstance() {
		if (instance == null)
			instance = new DivisionByZero();
		return instance;
	}

	@Override
	public int getValue() {
		return 0;
	}

	@Override
	public String toString() {
		return DIVISION_BY_ZERO_MSG;
	}

	@Override
	public boolean contains(Expression argument) {
		return false;
	}
}
