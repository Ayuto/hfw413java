package model;

import java.util.Collection;
import java.util.Map;

import buffer.AbstractBuffer;

/**
 * Implementation for the process wrapper which wraps a process system.
 */
public class ProcessImpl implements Process {

	private AbstractBuffer<BufferEntry> output;
	private Collection<ExpressionProcess> subprocesses;
	private Map<AbstractBuffer<BufferEntry>, BufferEntry> inputs;
	private Map<String, OptionalIntegerValue> constantEnvironment;
	private Map<String, Process> variableEnvironment;

	/**
	 * Constructor for a new process with the given parameters.
	 * 
	 * @param output
	 *            buffer for all results
	 * @param subprocesses
	 *            collection with all subprocesses.
	 * @param inputs
	 *            map which maps all input buffer entries to their buffers.
	 * @param constantEnvironment
	 *            environment which specifies the values for all constants.
	 * @param varibaleEnvironment
	 *            environment which specifies processes which has to be
	 *            evaluated for the variables.
	 */
	public ProcessImpl(AbstractBuffer<BufferEntry> output,
			Collection<ExpressionProcess> subprocesses,
			Map<AbstractBuffer<BufferEntry>, BufferEntry> inputs,
			Map<String, OptionalIntegerValue> constantEnvironment,
			Map<String, Process> varibaleEnvironment) {
		this.output = output;
		this.subprocesses = subprocesses;
		this.inputs = inputs;
		this.constantEnvironment = constantEnvironment;
		this.variableEnvironment = varibaleEnvironment;
		for (ExpressionProcess process : this.subprocesses) {
			process.updateConstantEnvironment(constantEnvironment);
			process.updateVariableEnvironment(varibaleEnvironment);
		}
	}

	@Override
	public Collection<ExpressionProcess> getSubprocesses() {
		return subprocesses;
	}
	
	@Override
	public Map<String, OptionalIntegerValue> getConstantEnvironment() {
		return this.constantEnvironment;
	}
	
	@Override
	public Map<String, Process> getVariableEnvironment() {
		return this.variableEnvironment;
	}

	@Override
	public Map<AbstractBuffer<BufferEntry>, BufferEntry> getInputs() {
		return inputs;
	}

	@Override
	public AbstractBuffer<BufferEntry> getOutput() {
		return output;
	}
	
	@Override
	public void start() {
		for (ExpressionProcess process : this.subprocesses) {
			process.start();
		}
	}
	
	@Override
	public void stop() {
		for (AbstractBuffer<BufferEntry> inputBuffer : this.inputs.keySet()) {
			inputBuffer.put(StopCommand.getInstance());
		}
	}

	@Override
	public BufferEntry run() {
		for (AbstractBuffer<BufferEntry> inputBuffer : this.inputs.keySet()) {
			inputBuffer.put(this.inputs.get(inputBuffer));
		}
		return this.output.get();
	}

	@Override
	public void addConstant(String name, OptionalIntegerValue value) {
		this.constantEnvironment.put(name, value);
	}

	@Override
	public void addVariable(String name, Process arg) {
		this.variableEnvironment.put(name, arg);
	}

	@Override
	public void setInput(AbstractBuffer<BufferEntry> source, BufferEntry input) {
		this.inputs.put(source, input);
	}
}
