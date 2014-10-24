package test;

import java.util.LinkedList;
import java.util.List;

import model.BubbleSortManager;
import model.BufferEntry;
import model.IntegerWrapper;
import model.StopCommand;

import org.junit.Assert;
import org.junit.Test;

public class TestBubbleSort {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testNoSwitch() {
		final List<BufferEntry<IntegerWrapper>> l1 = new LinkedList<BufferEntry<IntegerWrapper>>();
		l1.add(new IntegerWrapper(1));
		l1.add(new IntegerWrapper(2));
		l1.add(new IntegerWrapper(3));
		l1.add(new IntegerWrapper(4));
		l1.add(new StopCommand<IntegerWrapper>());
		
		final BubbleSortManager manager = new BubbleSortManager(l1);
		final List<BufferEntry<IntegerWrapper>> result = manager.sort();
		l1.remove(l1.size() - 1);
		Assert.assertEquals(l1, result);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testAllSwitch() {
		final List<BufferEntry<IntegerWrapper>> l1 = new LinkedList<BufferEntry<IntegerWrapper>>();
		l1.add(new IntegerWrapper(4));
		l1.add(new IntegerWrapper(3));
		l1.add(new IntegerWrapper(2));
		l1.add(new IntegerWrapper(1));
		l1.add(new StopCommand<IntegerWrapper>());
		
		final List<BufferEntry<IntegerWrapper>> lresult = new LinkedList<BufferEntry<IntegerWrapper>>();
		lresult.add(new IntegerWrapper(1));
		lresult.add(new IntegerWrapper(2));
		lresult.add(new IntegerWrapper(3));
		lresult.add(new IntegerWrapper(4));
		
		final BubbleSortManager manager = new BubbleSortManager(l1);
		
		Assert.assertEquals(lresult, manager.sort());
	}

}
