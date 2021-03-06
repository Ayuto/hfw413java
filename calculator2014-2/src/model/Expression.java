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
	 * Substitutes <variable> with <expression> recursively.
	 * 
	 * @throws Error if a cycle was detected.
	 */
	public abstract void substitute(final Variable variable, final Expression expression);
	
	/**
	 * Creates a deep copy of the expression. Variables are not copied!
	 */
	public abstract Expression copy();
}
