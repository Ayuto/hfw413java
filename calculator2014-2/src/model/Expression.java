package model;

/**
 * A abstract Expression which can contains other expressions and has a value.
 */
public abstract class Expression extends Observee {
	
	/**
	 * Returns the current value of the expression.
	 */
	public abstract int getValue();

	/**
	 * Returns true only when <this> contains the expression <argument>.
	 */
	public abstract boolean contains(Expression argument);
}
