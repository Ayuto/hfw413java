package model;

import java.util.Vector;

public class ExpressionFacade {

	public static ExpressionFacade createExpressionFacade() {
		return new ExpressionFacade(new Vector<Expression>(),
				new Vector<Variable>());
	}

	private final Vector<Expression> expressions;
	private final Vector<Variable> variables;

	private ExpressionFacade(final Vector<Expression> expressions,
			final Vector<Variable> variables) {
		this.expressions = expressions;
		this.variables = variables;
	}

	public void createVariable(final String name) {
		final Variable newVariable = Variable.createVariable(name);
		this.getExpressions().add(newVariable);
		this.getVariables().add(newVariable);
	}

	public Vector<Expression> getExpressions() {
		return this.expressions;
	}

	public Vector<Variable> getVariables() {
		return this.variables;
	}

	public void up(final Variable variable) {
		variable.up();
	}

	public void down(final Variable variable) {
		variable.down();
	}

	public void createAdd(final Expression firstArgument,
			final Expression secondArgument) {
		final Expression newAddition = Addition.create(firstArgument, secondArgument);
		this.getExpressions().add(newAddition);
	}

	public void createSubtract(final Expression firstArgument,
			final Expression secondArgument) {
		final Expression newSubtraction = Subtraction.create(firstArgument, secondArgument);
		this.getExpressions().add(newSubtraction);
	}

	public void createMultiply(final Expression firstArgument,
			final Expression secondArgument) {
		final Expression newMultiplication = Multiplication.create(firstArgument,
					secondArgument);
		this.getExpressions().add(newMultiplication);
	}

	public void createDivide(final Expression firstArgument,
			final Expression secondArgument) {
		final Expression newDivision = Division.create(firstArgument, secondArgument);
		this.getExpressions().add(newDivision);
	}
}
