package expressions;

import java.util.Map;

import buffer.AbstractBuffer;
import model.AdditionProcess;
import model.BufferEntry;
import model.ExpressionProcess;
import model.OptionalIntegerValue;
import model.Process;
import model.Tupel;

/**
 * This composite expression represents the operation addition of two
 * OptionalIntegerValues.
 */
public class Addition extends CompositeExpression {

	/**
	 * Creates a new addition with the given parameters and returns it. Checks
	 * by constructing the expressions and throws a cycle exception if one is
	 * detected.
	 * 
	 * @param first
	 *            first parameter of the new addition.
	 * @param second
	 *            second parameter of the new addition.
	 * @return the created addition
	 * @throws CycleException
	 *             if a cycle is detected.
	 */
	public static Addition create(Expression first, Expression second)
			throws CycleException {
		return new Addition(first, second);
	}

	private Addition(Expression first, Expression second) throws CycleException {
		super(first, second);
	}

	@Override
	protected OptionalIntegerValue calculate(OptionalIntegerValue value1,
			OptionalIntegerValue value2) {
		return value1.add(value2);
	}

	@Override
	protected ExpressionProcess createProcess(AbstractBuffer<Tupel> input,
			AbstractBuffer<BufferEntry> output,
			Map<String, OptionalIntegerValue> constantEnvironment, Map<String, Process> variableEnvironment) {
		return new AdditionProcess(input, output, constantEnvironment, variableEnvironment);
	}

}
