package expressions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import buffer.AbstractBuffer;
import buffer.BufferSolution;
import model.BufferEntry;
import model.ExpressionProcess;
import model.OptionalIntegerValue;
import model.Process;
import model.ProcessImpl;

/**
 * Abstract type for all expressions which are no composites.
 */
public abstract class BaseExpression implements Expression {

	@Override
	public boolean contains(Expression arg) {
		return this.equals(arg);
	}

	/**
	 * Creates a buffer entry out of the base expression.
	 * 
	 * @return the created buffer entry
	 */
	protected abstract BufferEntry getEntry();

	@Override
	public Process toProcess() {
		AbstractBuffer<BufferEntry> buffer = new BufferSolution<BufferEntry>();
		Map<AbstractBuffer<BufferEntry>, BufferEntry> inputs = new HashMap<AbstractBuffer<BufferEntry>, BufferEntry>();
		inputs.put(buffer, this.getEntry());
		return new ProcessImpl(buffer, new LinkedList<ExpressionProcess>(),
				inputs, new HashMap<String, OptionalIntegerValue>(),
				new HashMap<String, Process>());
	}
}
