package expressions;

import buffer.AbstractBuffer;
import model.BufferEntry;
import model.ExpressionProcess;
import model.OptionalIntegerValue;
import model.SubtractionProcess;
import model.Tupel;

public class Subtraction extends CompositeExpression {

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
			AbstractBuffer<BufferEntry> output) {
		// TODO null!!!
		return new SubtractionProcess(input, output, null, null);
	}

}
