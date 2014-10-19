package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import buffer.AbstractBuffer;

public class ProcessImpl implements Process {

	private AbstractBuffer<BufferEntry> output;
	private Collection<ExpressionProcess> subprocesses;
	private Map<AbstractBuffer<BufferEntry>, BufferEntry> inputs;
	private Map<String, OptionalIntegerValue> constantEnvironment;
	private Map<String, Process> variableEnvironment;
	
	public ProcessImpl(AbstractBuffer<BufferEntry> output,
			Collection<ExpressionProcess> subprocesses,
			Map<AbstractBuffer<BufferEntry>, BufferEntry> inputs) {
		this.output = output;
		this.subprocesses = subprocesses;
		this.inputs = inputs;
		this.constantEnvironment = new HashMap<String, OptionalIntegerValue>();
		this.variableEnvironment = new HashMap<String, Process>();
	}
	
	public Collection<ExpressionProcess> getSubprocesses() {
		return subprocesses;
	}
	
	public Map<AbstractBuffer<BufferEntry>, BufferEntry> getInputs() {
		return inputs;
	}
	
	public AbstractBuffer<BufferEntry> getOutput() {
		return output;
	}

	@Override
	public BufferEntry run() {
		for (ExpressionProcess process : this.subprocesses) {
			process.start();
		}
		for (AbstractBuffer<BufferEntry> inputBuffer : this.inputs.keySet()) {
			inputBuffer.put(this.inputs.get(inputBuffer));
			inputBuffer.put(StopCommand.getInstance());
		}
		return this.output.get();
	}
	
	@Override
	public synchronized void addConstant(String name, OptionalIntegerValue value) {
		this.constantEnvironment.put(name, value);
	}
	
	@Override
	public synchronized void addVariable(String name, Process process) {
		this.variableEnvironment.put(name, process);
	}
	
	@Override
	public void setInput(AbstractBuffer<BufferEntry> source, BufferEntry input) {
		this.inputs.put(source, input);
	}
}
