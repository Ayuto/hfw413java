package expressions;

import java.util.Map;

import buffer.AbstractBuffer;
import model.BufferEntry;
import model.DivisionProcess;
import model.ExpressionProcess;
import model.OptionalIntegerValue;
import model.Process;
import model.Tupel;

/**
 * This composite expression represents the operation division of two
 * OptionalIntegerValues.
 */
public class Division extends CompositeExpression {
	
	/**
	 * Creates a new division with the given parameters and returns it. Checks
	 * by constructing the expressions and throws a cycle exception if one is
	 * detected.
	 * 
	 * @param first
	 *            first parameter of the new division.
	 * @param second
	 *            second parameter of the new division.
	 * @return the created division
	 * @throws CycleException
	 *             if a cycle is detected.
	 */
	public static Division create(Expression first, Expression second) throws CycleException {
		return new Division(first, second);
	}

	private Division(Expression first, Expression second)
			throws CycleException {
		super(first, second);
	}

	@Override
	protected OptionalIntegerValue calculate(OptionalIntegerValue value1,
			OptionalIntegerValue value2) {
		return value1.div(value2);
	}

	@Override
	protected ExpressionProcess createProcess(AbstractBuffer<Tupel> input,
			AbstractBuffer<BufferEntry> output,
			Map<String, OptionalIntegerValue> constantEnvironment,
			Map<String, Process> variableEnvironment) {
		return new DivisionProcess(input, output, constantEnvironment, variableEnvironment);
	}

}
