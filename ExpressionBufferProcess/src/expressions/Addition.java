package expressions;

import buffer.AbstractBuffer;
import model.AdditionProcess;
import model.BufferEntry;
import model.ExpressionProcess;
import model.OptionalIntegerValue;
import model.Tupel;

public class Addition extends CompositeExpression {
	
	public static Addition create(Expression first, Expression second) throws CycleException {
		return new Addition(first, second);
	}

	private Addition(Expression first, Expression second)
			throws CycleException {
		super(first, second);
	}

	@Override
	protected OptionalIntegerValue calculate(OptionalIntegerValue value1,
			OptionalIntegerValue value2) {
		return value1.add(value2);
	}

	@Override
	protected ExpressionProcess createProcess(AbstractBuffer<Tupel> input,
			AbstractBuffer<BufferEntry> output) {
		// TODO null!!!
		return new AdditionProcess(input, output, null, null);
	}

}
