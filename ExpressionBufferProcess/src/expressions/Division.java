package expressions;

import buffer.AbstractBuffer;
import model.BufferEntry;
import model.DivisionProcess;
import model.ExpressionProcess;
import model.OptionalIntegerValue;
import model.Tupel;

public class Division extends CompositeExpression {
	
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
			AbstractBuffer<BufferEntry> output) {
		// TODO null!
		return new DivisionProcess(input, output, null, null);
	}

}
