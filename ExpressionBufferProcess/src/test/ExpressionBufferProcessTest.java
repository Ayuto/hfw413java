package test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.AdditionProcess;
import model.BufferEntry;
import model.CloneProcess;
import model.DivisionProcess;
import model.ExpressionProcess;
import model.MultiplicationProcess;
import model.OptionalIntegerValue;
import model.Process;
import model.SubtractionProcess;
import model.Tupel;
import model.TupelGeneratorProcess;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import buffer.AbstractBuffer;
import buffer.BufferSolution;

public class ExpressionBufferProcessTest {

	private List<ExpressionProcess> processes;
	private AbstractBuffer<BufferEntry> input1;
	private AbstractBuffer<BufferEntry> input2;
	private AbstractBuffer<BufferEntry> input3;
	private AbstractBuffer<BufferEntry> output;
	
	@Before
	public void setUp() throws Exception {
		// Liste mit allen Prozessen
				this.processes = new LinkedList<ExpressionProcess>();
				
				// Erstellung der Eingangspuffer
				this.input1 = new BufferSolution<BufferEntry>();
				this.input2 = new BufferSolution<BufferEntry>();
				this.input3 = new BufferSolution<BufferEntry>();
				
				// Erzeugung des Ausgangspuffer
				this.output = new BufferSolution<BufferEntry>();
				
				// Erstellung der Zwischenpuffer
				final AbstractBuffer<BufferEntry> input2clone1 = new BufferSolution<BufferEntry>();
				final AbstractBuffer<BufferEntry> input2clone2 = new BufferSolution<BufferEntry>();
				final AbstractBuffer<Tupel> inputAdd1 = new BufferSolution<Tupel>();
				final AbstractBuffer<Tupel> inputSub1 = new BufferSolution<Tupel>();
				final AbstractBuffer<Tupel> inputMul1 = new BufferSolution<Tupel>();
				final AbstractBuffer<Tupel> inputDiv1 = new BufferSolution<Tupel>();
				final AbstractBuffer<Tupel> inputAdd2 = new BufferSolution<Tupel>();
				final AbstractBuffer<Tupel> inputSub2 = new BufferSolution<Tupel>();
				final AbstractBuffer<BufferEntry> outputAdd1 = new BufferSolution<BufferEntry>();
				final AbstractBuffer<BufferEntry> outputSub1 = new BufferSolution<BufferEntry>();
				final AbstractBuffer<BufferEntry> outputMul1 = new BufferSolution<BufferEntry>();
				final AbstractBuffer<BufferEntry> outputDiv1 = new BufferSolution<BufferEntry>();
				final AbstractBuffer<BufferEntry> outputAdd2 = new BufferSolution<BufferEntry>();
				final AbstractBuffer<BufferEntry> input3clone1 = new BufferSolution<BufferEntry>();
				final AbstractBuffer<BufferEntry> input3clone2 = new BufferSolution<BufferEntry>();
				final AbstractBuffer<BufferEntry> input3clone3 = new BufferSolution<BufferEntry>();
				final AbstractBuffer<BufferEntry> input3cloneTemp = new BufferSolution<BufferEntry>();
				final AbstractBuffer<BufferEntry> outputSub1clone1 = new BufferSolution<BufferEntry>();
				final AbstractBuffer<BufferEntry> outputSub1clone2 = new BufferSolution<BufferEntry>();
				
				// Erstellung der Prozesse
				final ExpressionProcess cloneInput2 = new CloneProcess(this.input2, input2clone1, input2clone2, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess clone1Input3 = new CloneProcess(this.input3, input3clone1, input3cloneTemp, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess clone2Input3 = new CloneProcess(input3cloneTemp, input3clone2, input3clone3, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess tupel4add1 = new TupelGeneratorProcess(this.input1, input2clone1, inputAdd1, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess add1 = new AdditionProcess(inputAdd1, outputAdd1, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess tupel4sub1 = new TupelGeneratorProcess(input2clone2, input3clone1, inputSub1, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess sub1 = new SubtractionProcess(inputSub1, outputSub1, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess tupel4mul1 = new TupelGeneratorProcess(input3clone2, input3clone3, inputMul1, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess mul1 = new MultiplicationProcess(inputMul1, outputMul1, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess cloneOutputSub1 = new CloneProcess(outputSub1, outputSub1clone1, outputSub1clone2, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess tupel4div1 = new TupelGeneratorProcess(outputAdd1, outputSub1clone1, inputDiv1, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess div1 = new DivisionProcess(inputDiv1, outputDiv1, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess tupel4add2 = new TupelGeneratorProcess(outputSub1clone2, outputMul1, inputAdd2, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess add2 = new AdditionProcess(inputAdd2, outputAdd2, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess tupel4sub2 = new TupelGeneratorProcess(outputDiv1, outputAdd2, inputSub2, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				final ExpressionProcess sub2 = new SubtractionProcess(inputSub2, this.output, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
				
				// Hinzuf�gen der Prozesse zur Liste
				this.processes.add(cloneInput2);
				this.processes.add(clone1Input3);
				this.processes.add(clone2Input3);
				this.processes.add(tupel4add1);
				this.processes.add(add1);
				this.processes.add(tupel4sub1);
				this.processes.add(sub1);
				this.processes.add(tupel4mul1);
				this.processes.add(mul1);
				this.processes.add(cloneOutputSub1);
				this.processes.add(tupel4div1);
				this.processes.add(div1);
				this.processes.add(tupel4add2);
				this.processes.add(add2);
				this.processes.add(tupel4sub2);
				this.processes.add(sub2);
	}

	@Test
	public void testCalculations() {
		this.input1.put(new OptionalIntegerValue(6));
		this.input2.put(new OptionalIntegerValue(3));
		this.input3.put(new OptionalIntegerValue(2));
		for (final ExpressionProcess process : this.processes) {
			process.start();
		}
		Assert.assertEquals(new OptionalIntegerValue(4), this.output.get());
	}
	
	@Test
	public void testSub() {
		final AbstractBuffer<BufferEntry> input1 = new BufferSolution<BufferEntry>();
		final AbstractBuffer<BufferEntry> input2 = new BufferSolution<BufferEntry>();
		final AbstractBuffer<BufferEntry> output = new BufferSolution<BufferEntry>();
		final AbstractBuffer<Tupel> input = new BufferSolution<Tupel>();
		final ExpressionProcess tupelgen = new TupelGeneratorProcess(input1, input2, input, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
		final ExpressionProcess sub = new SubtractionProcess(input, output, new HashMap<String, OptionalIntegerValue>(), new HashMap<String, Process>());
		
		tupelgen.start();
		sub.start();
		
		input1.put(new OptionalIntegerValue(100));
		input2.put(new OptionalIntegerValue(4));
		
		Assert.assertEquals(new OptionalIntegerValue(96), output.get());
	}

}
