package expressions;

import java.util.Map;

import buffer.AbstractBuffer;
import model.BufferEntry;
import model.ExpressionProcess;
import model.MultiplicationProcess;
import model.OptionalIntegerValue;
import model.Process;
import model.Tupel;

/**
 * This composite expression represents the operation multiplication of two
 * OptionalIntegerValues.
 */
public class Multiplication extends CompositeExpression {

	/**
	 * Creates a new multiplication with the given parameters and returns it. Checks
	 * by constructing the expressions and throws a cycle exception if one is
	 * detected.
	 * 
	 * @param first
	 *            first parameter of the new multiplication.
	 * @param second
	 *            second parameter of the new multiplication.
	 * @return the created multiplication
	 * @throws CycleException
	 *             if a cycle is detected.
	 */
	public static Multiplication create(Expression first, Expression second) throws CycleException {
		return new Multiplication(first, second);
	}
	
	private Multiplication(Expression first, Expression second)
			throws CycleException {
		super(first, second);
	}

	@Override
	protected OptionalIntegerValue calculate(OptionalIntegerValue value1,
			OptionalIntegerValue value2) {
		return value1.mul(value2);
	}

	@Override
	protected ExpressionProcess createProcess(AbstractBuffer<Tupel> input,
			AbstractBuffer<BufferEntry> output,
			Map<String, OptionalIntegerValue> constantEnvironment,
			Map<String, Process> variableEnvironment) {
		return new MultiplicationProcess(input, output, constantEnvironment, variableEnvironment);
	}

}
