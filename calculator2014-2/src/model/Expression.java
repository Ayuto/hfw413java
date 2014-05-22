package model;

/**
 * A abstract Expression which can contains other expressions and has a value.
 */
public abstract class Expression extends Observee {
	
	/**
	 * Returns the current value of the expression.
	 */
	public abstract IntValue getValue();

	/**
	 * Returns true only when <this> contains the expression <argument>.
	 */
	public abstract boolean contains(Expression argument);

	/**
	 * Subsitutes <variable> with <expression>.
	 * 
	 * @throws Error if a cycle was detected.
	 */
	public abstract void substitute(final Variable variable, final Expression expression);
	
	public abstract Expression copy();
}
