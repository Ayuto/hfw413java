package expressions;

import java.util.Map;

import buffer.AbstractBuffer;
import model.BufferEntry;
import model.ExpressionProcess;
import model.OptionalIntegerValue;
import model.Process;
import model.SubtractionProcess;
import model.Tupel;

/**
 * This composite expression represents the operation subtraction of two
 * OptionalIntegerValues.
 */
public class Subtraction extends CompositeExpression {

	/**
	 * Creates a new subtraction with the given parameters and returns it. Checks
	 * by constructing the expressions and throws a cycle exception if one is
	 * detected.
	 * 
	 * @param first
	 *            first parameter of the new subtraction.
	 * @param second
	 *            second parameter of the new subtraction.
	 * @return the created subtraction
	 * @throws CycleException
	 *             if a cycle is detected.
	 */
	public static Subtraction create(Expression first, Expression second) throws CycleException {
		return new Subtraction(first, second);
	}
	
	private Subtraction(Expression first, Expression second)
			throws CycleException {
		super(first, second);
	}

	@Override
	protected OptionalIntegerValue calculate(OptionalIntegerValue value1,
			OptionalIntegerValue value2) {
		return value1.sub(value2);
	}

	@Override
	protected ExpressionProcess createProcess(AbstractBuffer<Tupel> input,
			AbstractBuffer<BufferEntry> output,
			Map<String, OptionalIntegerValue> constantEnvironment,
			Map<String, Process> variableEnvironment) {
		return new SubtractionProcess(input, output, constantEnvironment, variableEnvironment);
	}

}
