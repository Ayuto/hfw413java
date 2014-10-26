package test;

import integerType.IntegerStopCommand;
import integerType.IntegerType;
import integerType.IntegerWrapper;

import java.util.LinkedList;
import java.util.List;

import model.BubbleSortManager;

import org.junit.Assert;
import org.junit.Test;

public class TestBubbleSort {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testNoSwitch() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper(1));
		l1.add(new IntegerWrapper(2));
		l1.add(new IntegerWrapper(3));
		l1.add(new IntegerWrapper(4));
		l1.add(IntegerStopCommand.getInstance());
		
		final BubbleSortManager manager = new BubbleSortManager(l1);
		final List<IntegerType> result = manager.sort();
		l1.remove(l1.size() - 1);
		Assert.assertEquals(l1, result);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testAllSwitch() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper(4));
		l1.add(new IntegerWrapper(3));
		l1.add(new IntegerWrapper(2));
		l1.add(new IntegerWrapper(1));
		l1.add(IntegerStopCommand.getInstance());
		
		final List<IntegerType> lresult = new LinkedList<IntegerType>();
		lresult.add(new IntegerWrapper(1));
		lresult.add(new IntegerWrapper(2));
		lresult.add(new IntegerWrapper(3));
		lresult.add(new IntegerWrapper(4));
		
		final BubbleSortManager manager = new BubbleSortManager(l1);
		
		Assert.assertEquals(lresult, manager.sort());
	}

}
