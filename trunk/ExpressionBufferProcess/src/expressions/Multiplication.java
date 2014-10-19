package expressions;

import buffer.AbstractBuffer;
import model.BufferEntry;
import model.ExpressionProcess;
import model.MultiplicationProcess;
import model.OptionalIntegerValue;
import model.Tupel;

public class Multiplication extends CompositeExpression {

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
			AbstractBuffer<BufferEntry> output) {
		// TODO null!!!
		return new MultiplicationProcess(input, output, null, null);
	}

}
