package test;

import integerType.IntegerStopCommand;
import integerType.IntegerType;
import integerType.IntegerWrapper;

import java.util.LinkedList;
import java.util.List;

import model.MergeSortManager;

import org.junit.Assert;
import org.junit.Test;

public class TestMergeSort {

	@Test
	public void testNoChangesNecessary() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper(1));
		l1.add(new IntegerWrapper(2));
		l1.add(new IntegerWrapper(3));
		l1.add(new IntegerWrapper(4));
		l1.add(IntegerStopCommand.getInstance());
		
		final MergeSortManager<IntegerType> manager = new MergeSortManager<IntegerType>(l1);
		final List<IntegerType> result = manager.sort();
		l1.remove(l1.size() - 1);
		Assert.assertEquals(l1, result);
	}
	
	@Test
	public void testAllChangesNecessary() {
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
		
		final MergeSortManager<IntegerType> manager = new MergeSortManager<IntegerType>(l1);
		
		Assert.assertEquals(lresult, manager.sort());
	}

	@Test
	public void testRandom() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper(2));
		l1.add(new IntegerWrapper(4));
		l1.add(new IntegerWrapper(3));
		l1.add(new IntegerWrapper(1));
		l1.add(IntegerStopCommand.getInstance());
		
		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper(1));
		result.add(new IntegerWrapper(2));
		result.add(new IntegerWrapper(3));
		result.add(new IntegerWrapper(4));
		
		final MergeSortManager<IntegerType> manager = new MergeSortManager<IntegerType>(l1);
		Assert.assertEquals(result, manager.sort());
	}
	
	@Test
	public void testSingleEntry() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper(2));
		l1.add(IntegerStopCommand.getInstance());
		
		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper(2));
		
		final MergeSortManager<IntegerType> manager = new MergeSortManager<IntegerType>(l1);
		Assert.assertEquals(result, manager.sort());
	}
	
	@Test
	public void testTwoEntriesOrdered() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper(2));
		l1.add(new IntegerWrapper(4));
		l1.add(IntegerStopCommand.getInstance());
		
		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper(2));
		result.add(new IntegerWrapper(4));
		
		final MergeSortManager<IntegerType> manager = new MergeSortManager<IntegerType>(l1);
		Assert.assertEquals(result, manager.sort());
	}
	
	@Test
	public void testTwoEntriesUnordered() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper(4));
		l1.add(new IntegerWrapper(2));
		l1.add(IntegerStopCommand.getInstance());
		
		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper(2));
		result.add(new IntegerWrapper(4));
		
		final MergeSortManager<IntegerType> manager = new MergeSortManager<IntegerType>(l1);
		Assert.assertEquals(result, manager.sort());
	}
}
