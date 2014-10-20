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

/**
 * A abstract type for all expressions which are composites out of two
 * subexpressions.
 */
public abstract class CompositeExpression implements Expression {

	private final Expression first;
	private final Expression second;

	protected CompositeExpression(Expression first, Expression second)
			throws CycleException {
		if (first.contains(this) || second.contains(this))
			throw new CycleException();
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean contains(Expression arg) {
		if (this.equals(arg))
			return true;
		return this.first.contains(arg) || this.second.contains(arg);
	}

	/**
	 * Calculates the value of the receivers expression.
	 * 
	 * @param value1
	 *            first value for the calculation.
	 * @param value2
	 *            second value for the calculation.
	 * @return result of the calculation.
	 */
	protected abstract OptionalIntegerValue calculate(
			OptionalIntegerValue value1, OptionalIntegerValue value2);

	@Override
	public OptionalIntegerValue getValue(
			Map<String, OptionalIntegerValue> constantEnvironment,
			Map<String, Expression> variableEnvironment)
			throws UnknownConstantException, UnknownVariableException {
		return this.calculate(
				this.first.getValue(constantEnvironment, variableEnvironment),
				this.second.getValue(constantEnvironment, variableEnvironment));
	}

	@Override
	public Process toProcess() {
		Process firstArg = this.first.toProcess();
		Process secondArg = this.second.toProcess();
		
		Map<AbstractBuffer<BufferEntry>, BufferEntry> inputs = this
				.createInputSum(firstArg.getInputs(), secondArg.getInputs());
		
		Map<String, OptionalIntegerValue> constantEnvironment = this
				.sumConstantEnvironment(firstArg.getConstanEnvironment(),
						secondArg.getConstanEnvironment());
		Map<String, Process> variableEnvironment = this.sumVariableEnvironment(
				firstArg.getVariableEnvironment(),
				secondArg.getVariableEnvironment());
		
		Collection<ExpressionProcess> subprocesses = this.createProcessSum(
				firstArg.getSubprocesses(), secondArg.getSubprocesses());
		
		AbstractBuffer<Tupel> input = new BufferSolution<Tupel>();
		AbstractBuffer<BufferEntry> output = new BufferSolution<BufferEntry>();
		
		
		ExpressionProcess tupelGen = new TupelGeneratorProcess(
				firstArg.getOutput(), secondArg.getOutput(), input, constantEnvironment, variableEnvironment);
		ExpressionProcess process = this.createProcess(input, output,
				constantEnvironment, variableEnvironment);
		
		subprocesses.add(tupelGen);
		subprocesses.add(process);
		return new ProcessImpl(output, subprocesses, inputs,
				constantEnvironment, variableEnvironment);
	}

	private Map<String, Process> sumVariableEnvironment(
			Map<String, Process> map1, Map<String, Process> map2) {
		Map<String, Process> result = new HashMap<String, Process>();
		for (String name : map1.keySet()) {
			result.put(name, map1.get(name));
		}
		for (String name : map2.keySet()) {
			result.put(name, map2.get(name));
		}
		return result;
	}

	/**
	 * Creates a new map consisting out of all constants of both argument maps.
	 * If one name is used in both maps it will mapped to the value of the
	 * second map.
	 * 
	 * @param map1
	 *            first map
	 * @param map2
	 *            second map
	 * @return new map with all constants of both maps.
	 */
	private Map<String, OptionalIntegerValue> sumConstantEnvironment(
			Map<String, OptionalIntegerValue> map1,
			Map<String, OptionalIntegerValue> map2) {
		Map<String, OptionalIntegerValue> result = new HashMap<String, OptionalIntegerValue>();
		for (String name : map1.keySet()) {
			result.put(name, map1.get(name));
		}
		for (String name : map2.keySet()) {
			result.put(name, map2.get(name));
		}
		return result;
	}

	/**
	 * Creates the corresponding process for the special type of the composite.
	 * 
	 * @param input
	 *            input tupel buffer for the new process.
	 * @param output
	 *            output result buffer for the new process.
	 * @param map2
	 * @param map
	 * @return the new created process.
	 */
	protected abstract ExpressionProcess createProcess(
			AbstractBuffer<Tupel> input, AbstractBuffer<BufferEntry> output,
			Map<String, OptionalIntegerValue> constantEnvironment,
			Map<String, Process> variableEnvironment);

	/**
	 * Creates a new collection consisting out of the sum of processes of both
	 * arguments.
	 * 
	 * @param subprocesses
	 *            first collection argument.
	 * @param subprocesses2
	 *            second collection argument.
	 * @return the new created collection with all processes which are at least
	 *         in one of the arguments.
	 */
	private Collection<ExpressionProcess> createProcessSum(
			Collection<ExpressionProcess> subprocesses,
			Collection<ExpressionProcess> subprocesses2) {
		LinkedList<ExpressionProcess> result = new LinkedList<ExpressionProcess>();
		result.addAll(subprocesses);
		result.addAll(subprocesses2);
		return result;
	}

	/**
	 * Creates a new map consisting out of all entries of both arguments. If one
	 * key is used in both maps, there will be the value of the second argument
	 * in the result.
	 * 
	 * @param map1
	 *            first argument.
	 * @param map2
	 *            second argument.
	 * @return the new created map with all entries which are at least in one
	 *         argument.
	 */
	private Map<AbstractBuffer<BufferEntry>, BufferEntry> createInputSum(
			Map<AbstractBuffer<BufferEntry>, BufferEntry> map1,
			Map<AbstractBuffer<BufferEntry>, BufferEntry> map2) {
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
