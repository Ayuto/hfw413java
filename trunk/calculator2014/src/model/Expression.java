package model;

public abstract class Expression extends Observee {
	public abstract int getValue();

	public abstract boolean contains(Expression argument);
}
