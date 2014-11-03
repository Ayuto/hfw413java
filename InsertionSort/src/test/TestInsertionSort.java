package test;

import integerType.IntegerStopCommand;
import integerType.IntegerType;
import integerType.IntegerWrapper;

import java.util.LinkedList;
import java.util.List;

import model.InsertionSortManager;

import org.junit.Assert;
import org.junit.Test;

public class TestInsertionSort {

	@Test
	public void testEmpty() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(IntegerStopCommand.getInstance());

		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(
				l1);
		Assert.assertEquals(new LinkedList<IntegerType>(), manager.sort());
		Assert.assertEquals(1, manager.getThreadAmount());
	}

	@Test
	public void testNoChangesNecessary() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper(1));
		l1.add(new IntegerWrapper(2));
		l1.add(new IntegerWrapper(3));
		l1.add(new IntegerWrapper(4));
		l1.add(IntegerStopCommand.getInstance());

		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(
				l1);
		final List<IntegerType> result = manager.sort();
		l1.remove(l1.size() - 1);
		Assert.assertEquals(l1, result);
		Assert.assertEquals(5, manager.getThreadAmount());
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

		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(
				l1);

		Assert.assertEquals(lresult, manager.sort());
		Assert.assertEquals(5, manager.getThreadAmount());
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

		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(5, manager.getThreadAmount());
	}

	@Test
	public void testSingleEntry() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper(2));
		l1.add(IntegerStopCommand.getInstance());

		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper(2));

		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(2, manager.getThreadAmount());
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

		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(3, manager.getThreadAmount());
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

		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(3, manager.getThreadAmount());
	}
	

	@Test
	public void testAllTheSame() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper(2));
		l1.add(new IntegerWrapper(2));
		l1.add(new IntegerWrapper(2));
		l1.add(new IntegerWrapper(2));
		l1.add(IntegerStopCommand.getInstance());
		
		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper(2));
		result.add(new IntegerWrapper(2));
		result.add(new IntegerWrapper(2));
		result.add(new IntegerWrapper(2));
		
		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(5, manager.getThreadAmount());
	}
	
	@Test
	public void testCharacterOrdered(){
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper('a'));
		l1.add(new IntegerWrapper('b'));
		l1.add(new IntegerWrapper('c'));
		l1.add(IntegerStopCommand.getInstance());
		
		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper('a'));
		result.add(new IntegerWrapper('b'));
		result.add(new IntegerWrapper('c'));
		
		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(4, manager.getThreadAmount());
	}
	
	@Test
	public void testCharacterUnordered(){
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper('e'));
		l1.add(new IntegerWrapper('d'));
		l1.add(new IntegerWrapper('c'));
		l1.add(new IntegerWrapper('b'));
		l1.add(new IntegerWrapper('a'));
		l1.add(IntegerStopCommand.getInstance());
		
		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper('a'));
		result.add(new IntegerWrapper('b'));
		result.add(new IntegerWrapper('c'));
		result.add(new IntegerWrapper('d'));
		result.add(new IntegerWrapper('e'));
		
		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(6, manager.getThreadAmount());
	}
	
	@Test
	public void testCharacterOrderedUppercase(){
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper('A'));
		l1.add(new IntegerWrapper('B'));
		l1.add(new IntegerWrapper('C'));
		l1.add(IntegerStopCommand.getInstance());
		
		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper('A'));
		result.add(new IntegerWrapper('B'));
		result.add(new IntegerWrapper('C'));
		
		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(4, manager.getThreadAmount());
	}
	
	@Test
	public void testCharacterUnorderedUppercase(){
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper('E'));
		l1.add(new IntegerWrapper('D'));
		l1.add(new IntegerWrapper('C'));
		l1.add(new IntegerWrapper('B'));
		l1.add(new IntegerWrapper('A'));
		l1.add(IntegerStopCommand.getInstance());
		
		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper('A'));
		result.add(new IntegerWrapper('B'));
		result.add(new IntegerWrapper('C'));
		result.add(new IntegerWrapper('D'));
		result.add(new IntegerWrapper('E'));
		
		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(6, manager.getThreadAmount());
	}
	
	@Test
	public void testCharacterUnorderedLowerAndUpperCase(){
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper('E'));
		l1.add(new IntegerWrapper('D'));
		l1.add(new IntegerWrapper('C'));
		l1.add(new IntegerWrapper('b'));
		l1.add(new IntegerWrapper('a'));
		l1.add(IntegerStopCommand.getInstance());
		
		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper('C'));
		result.add(new IntegerWrapper('D'));
		result.add(new IntegerWrapper('E'));
		result.add(new IntegerWrapper('a'));
		result.add(new IntegerWrapper('b'));
		
		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(6, manager.getThreadAmount());
	}
	
	@Test
	public void testCharacterAndIntegerOrdered(){
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper('1'));
		l1.add(new IntegerWrapper('2'));
		l1.add(new IntegerWrapper('3'));
		l1.add(new IntegerWrapper('A'));
		l1.add(new IntegerWrapper('B'));
		l1.add(IntegerStopCommand.getInstance());
		
		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper('1'));
		result.add(new IntegerWrapper('2'));
		result.add(new IntegerWrapper('3'));
		result.add(new IntegerWrapper('A'));
		result.add(new IntegerWrapper('B'));
		
		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(6, manager.getThreadAmount());
	}
	
	@Test
	public void testCharacterAndIntegerUnordered(){
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper('A'));
		l1.add(new IntegerWrapper('b'));
		l1.add(new IntegerWrapper('3'));
		l1.add(new IntegerWrapper('2'));
		l1.add(new IntegerWrapper('1'));
		l1.add(IntegerStopCommand.getInstance());
		
		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper('1'));
		result.add(new IntegerWrapper('2'));
		result.add(new IntegerWrapper('3'));
		result.add(new IntegerWrapper('A'));
		result.add(new IntegerWrapper('b'));
		
		final InsertionSortManager<IntegerType> manager = new InsertionSortManager<IntegerType>(l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(6, manager.getThreadAmount());
	}
}
