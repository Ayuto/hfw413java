package expressions;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import model.BufferEntry;
import model.ExpressionProcess;
import model.OptionalIntegerValue;
import model.Process;
import model.ProcessImpl;
import model.Tupel;
import model.TupelGeneratorProcess;
import buffer.AbstractBuffer;
import buffer.BufferSolution;

public abstract class CompositeExpression implements Expression {
	
	private final Expression first;
	private final Expression second;
	
	protected CompositeExpression(Expression first, Expression second) throws CycleException {
		if (first.contains(this) || second.contains(this)) throw new CycleException();
		this.first = first;
		this.second = second;
	}
	
	@Override
	public boolean contains(Expression arg) {
		if (this.equals(arg)) 
			return true;
		return this.first.contains(arg) || this.second.contains(arg);
	}
	
	protected abstract OptionalIntegerValue calculate(OptionalIntegerValue value1, OptionalIntegerValue value2);

	@Override
	public OptionalIntegerValue getValue(
			Map<String, OptionalIntegerValue> constantEnvironment,
			Map<String, Expression> variableEnvironment)
			throws UnknownConstantException, UnknownVariableException {
		return this.calculate(this.first.getValue(constantEnvironment, variableEnvironment), this.second.getValue(constantEnvironment, variableEnvironment));
	}
	
	@Override
	public Process toProcess() {
		Process firstArg = this.first.toProcess();
		Process secondArg = this.second.toProcess();
		Map<AbstractBuffer<BufferEntry>, BufferEntry> inputs = this.createInputSum(firstArg.getInputs(), secondArg.getInputs());
		Collection<ExpressionProcess> subprocesses = this.createProcessSum(firstArg.getSubprocesses(), secondArg.getSubprocesses());
		AbstractBuffer<Tupel> input = new BufferSolution<Tupel>();
		AbstractBuffer<BufferEntry> output = new BufferSolution<BufferEntry>();
		// TODO null entfernen
		ExpressionProcess tupelGen = new TupelGeneratorProcess(firstArg.getOutput(), secondArg.getOutput(), input, null, null);
		ExpressionProcess process = this.createProcess(input, output);
		subprocesses.add(tupelGen);
		subprocesses.add(process);
		return new ProcessImpl(output, subprocesses, inputs);
	}
	
	protected abstract ExpressionProcess createProcess(AbstractBuffer<Tupel> input,
			AbstractBuffer<BufferEntry> output);

	private Collection<ExpressionProcess> createProcessSum(
			Collection<ExpressionProcess> subprocesses,
			Collection<ExpressionProcess> subprocesses2) {
		LinkedList<ExpressionProcess> result = new LinkedList<ExpressionProcess>();
		result.addAll(subprocesses);
		result.addAll(subprocesses2);
		return result;
	}

	private Map<AbstractBuffer<BufferEntry>, BufferEntry> createInputSum(Map<AbstractBuffer<BufferEntry>, BufferEntry> map1, Map<AbstractBuffer<BufferEntry>, BufferEntry> map2) {
		Map<AbstractBuffer<BufferEntry>, BufferEntry> result = new HashMap<AbstractBuffer<BufferEntry>, BufferEntry>();
		for (AbstractBuffer<BufferEntry> buffer : map1.keySet()) {
			result.put(buffer, map1.get(buffer));
		}
		for (AbstractBuffer<BufferEntry> buffer : map2.keySet()) {
			result.put(buffer, map2.get(buffer));
		}
		return result;
	}

}
