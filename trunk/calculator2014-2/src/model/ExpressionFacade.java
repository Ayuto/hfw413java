package model;
import java.util.Iterator;
import java.util.Vector;



public class ExpressionFacade {
	
	public static ExpressionFacade createExpressionFacade(){
		return new ExpressionFacade();
	}

	private final Vector<Expression> expressions;
	private final Vector<Variable> variables;
	
	private ExpressionFacade(){
		this.expressions = new Vector<Expression>();
		this.variables = new Vector<Variable>();
	}
	public void createVariable(final String name) {
		final Variable newVariable = Variable.createVariable(name);
		this.expressions.add(newVariable);
		this.variables.add(newVariable);
	}
	public Vector<Expression> getExpressions(){
		return this.expressions;
	}
	public Vector<Variable> getVariables(){
		return this.variables;
	}
	public void up(final Variable variable) {
		variable.up();
	}
	public void down(final Variable variable) {
		variable.down();
	}
	public void createAdd(final Expression firstArgument, final Expression secondArgument) {
		final Expression newExpression = Addition.create(firstArgument, secondArgument);
		this.getExpressions().add(newExpression);
	}
	public void createSubtract(final Expression firstArgument, final Expression secondArgument) {
		final Expression newExpression = Subtraction.create(firstArgument, secondArgument);
		this.getExpressions().add(newExpression);
	}
	public void createMultiply(final Expression firstArgument, final Expression secondArgument) {
		final Expression newExpression = Multiplication.create(firstArgument, secondArgument);
		this.getExpressions().add(newExpression);
	}
	public void createDivide(final Expression firstArgument, final Expression secondArgument) {
		final Expression newExpression = Division.create(firstArgument, secondArgument);
		this.getExpressions().add(newExpression);
	}
	public void substitute(final Variable variable, final Expression expression) {
		Iterator<Expression> iter = this.getExpressions().iterator();
		while (iter.hasNext())
		{
			Expression current = iter.next();
			current.substitute(variable, expression);
		}
	}

}
