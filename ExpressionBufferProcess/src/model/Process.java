package model;

import java.util.Collection;
import java.util.Map;

import buffer.AbstractBuffer;

public interface Process {
	BufferEntry run();
	void addConstant(String name, OptionalIntegerValue value);
	void addVariable(String name, Process process);
	void setInput(AbstractBuffer<BufferEntry> source, BufferEntry newInput);
	Collection<ExpressionProcess> getSubprocesses();
	Map<AbstractBuffer<BufferEntry>, BufferEntry> getInputs();
	AbstractBuffer<BufferEntry> getOutput();
}
