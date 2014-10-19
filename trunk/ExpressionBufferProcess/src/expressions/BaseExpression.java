package expressions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import buffer.AbstractBuffer;
import buffer.BufferSolution;
import model.BufferEntry;
import model.ExpressionProcess;
import model.Process;
import model.ProcessImpl;

public abstract class BaseExpression implements Expression {

	@Override
	public boolean contains(Expression arg) {
		return this.equals(arg);
	}
	
	protected abstract BufferEntry getEntry();
	
	@Override
	public Process toProcess() {
		AbstractBuffer<BufferEntry> buffer = new BufferSolution<BufferEntry>();
		Map<AbstractBuffer<BufferEntry>, BufferEntry> inputs = new HashMap<AbstractBuffer<BufferEntry>, BufferEntry>();
		inputs.put(buffer, this.getEntry());
		return new ProcessImpl(buffer, new LinkedList<ExpressionProcess>(), inputs);
	}
}
