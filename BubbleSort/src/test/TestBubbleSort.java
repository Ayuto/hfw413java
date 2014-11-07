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

	@Test
	public void testEmpty() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(IntegerStopCommand.getInstance());

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		Assert.assertEquals(new LinkedList<IntegerType>(), manager.sort());
		Assert.assertEquals(1, manager.getThreadAmount());
	}

	@Test
	public void testNoSwitch() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper(1));
		l1.add(new IntegerWrapper(2));
		l1.add(new IntegerWrapper(3));
		l1.add(new IntegerWrapper(4));
		l1.add(IntegerStopCommand.getInstance());

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		final List<IntegerType> result = manager.sort();
		l1.remove(l1.size() - 1);
		Assert.assertEquals(l1, result);
		Assert.assertEquals(1, manager.getThreadAmount());
	}

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

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);

		Assert.assertEquals(lresult, manager.sort());
		Assert.assertEquals(4, manager.getThreadAmount());
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

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
	}

	@Test
	public void testSingleEntry() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper(2));
		l1.add(IntegerStopCommand.getInstance());

		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper(2));

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(1, manager.getThreadAmount());
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

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(1, manager.getThreadAmount());
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

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(2, manager.getThreadAmount());
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

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(1, manager.getThreadAmount());
	}

	@Test
	public void testAsciiIntegerOrdered() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper('a'));
		l1.add(new IntegerWrapper('b'));
		l1.add(new IntegerWrapper('c'));
		l1.add(IntegerStopCommand.getInstance());

		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper('a'));
		result.add(new IntegerWrapper('b'));
		result.add(new IntegerWrapper('c'));

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(1, manager.getThreadAmount());
	}

	@Test
	public void testAsciiIntegerUnordered() {
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

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(5, manager.getThreadAmount());
	}

	@Test
	public void testAsciiIntegerOrderedUppercase() {
		final List<IntegerType> l1 = new LinkedList<IntegerType>();
		l1.add(new IntegerWrapper('A'));
		l1.add(new IntegerWrapper('B'));
		l1.add(new IntegerWrapper('C'));
		l1.add(IntegerStopCommand.getInstance());

		final List<IntegerType> result = new LinkedList<IntegerType>();
		result.add(new IntegerWrapper('A'));
		result.add(new IntegerWrapper('B'));
		result.add(new IntegerWrapper('C'));

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(1, manager.getThreadAmount());
	}

	@Test
	public void testAsciiIntegerUnorderedUppercase() {
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

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(5, manager.getThreadAmount());
	}

	@Test
	public void testAsciiIntegerUnorderedLowerAndUpperCase() {
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

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(3, manager.getThreadAmount());
	}

	@Test
	public void testAsciiIntegerAndIntegerOrdered() {
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

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(1, manager.getThreadAmount());
	}

	@Test
	public void testAsciiIntegerAndIntegerUnordered() {
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

		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				l1);
		Assert.assertEquals(result, manager.sort());
		Assert.assertEquals(5, manager.getThreadAmount());
	}

	@Test
	public void testRandom1() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(66));
		listToBeSorted.add(new IntegerWrapper(44));
		listToBeSorted.add(new IntegerWrapper(63));
		listToBeSorted.add(new IntegerWrapper(66));
		listToBeSorted.add(new IntegerWrapper(86));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(44));
		sortedList.add(new IntegerWrapper(63));
		sortedList.add(new IntegerWrapper(66));
		sortedList.add(new IntegerWrapper(66));
		sortedList.add(new IntegerWrapper(86));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom2() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(72));
		listToBeSorted.add(new IntegerWrapper(69));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(69));
		sortedList.add(new IntegerWrapper(72));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom3() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(30));
		listToBeSorted.add(new IntegerWrapper(40));
		listToBeSorted.add(new IntegerWrapper(67));
		listToBeSorted.add(new IntegerWrapper(74));
		listToBeSorted.add(new IntegerWrapper(62));
		listToBeSorted.add(new IntegerWrapper(20));
		listToBeSorted.add(new IntegerWrapper(95));
		listToBeSorted.add(new IntegerWrapper(48));
		listToBeSorted.add(new IntegerWrapper(1));
		listToBeSorted.add(new IntegerWrapper(59));
		listToBeSorted.add(new IntegerWrapper(7));
		listToBeSorted.add(new IntegerWrapper(30));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(1));
		sortedList.add(new IntegerWrapper(7));
		sortedList.add(new IntegerWrapper(20));
		sortedList.add(new IntegerWrapper(30));
		sortedList.add(new IntegerWrapper(30));
		sortedList.add(new IntegerWrapper(40));
		sortedList.add(new IntegerWrapper(48));
		sortedList.add(new IntegerWrapper(59));
		sortedList.add(new IntegerWrapper(62));
		sortedList.add(new IntegerWrapper(67));
		sortedList.add(new IntegerWrapper(74));
		sortedList.add(new IntegerWrapper(95));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom4() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(34));
		listToBeSorted.add(new IntegerWrapper(19));
		listToBeSorted.add(new IntegerWrapper(2));
		listToBeSorted.add(new IntegerWrapper(53));
		listToBeSorted.add(new IntegerWrapper(87));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(2));
		sortedList.add(new IntegerWrapper(19));
		sortedList.add(new IntegerWrapper(34));
		sortedList.add(new IntegerWrapper(53));
		sortedList.add(new IntegerWrapper(87));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom5() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(74));
		listToBeSorted.add(new IntegerWrapper(51));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(51));
		sortedList.add(new IntegerWrapper(74));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom6() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(71));
		listToBeSorted.add(new IntegerWrapper(23));
		listToBeSorted.add(new IntegerWrapper(76));
		listToBeSorted.add(new IntegerWrapper(92));
		listToBeSorted.add(new IntegerWrapper(31));
		listToBeSorted.add(new IntegerWrapper(60));
		listToBeSorted.add(new IntegerWrapper(48));
		listToBeSorted.add(new IntegerWrapper(51));
		listToBeSorted.add(new IntegerWrapper(32));
		listToBeSorted.add(new IntegerWrapper(92));
		listToBeSorted.add(new IntegerWrapper(87));
		listToBeSorted.add(new IntegerWrapper(82));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(23));
		sortedList.add(new IntegerWrapper(31));
		sortedList.add(new IntegerWrapper(32));
		sortedList.add(new IntegerWrapper(48));
		sortedList.add(new IntegerWrapper(51));
		sortedList.add(new IntegerWrapper(60));
		sortedList.add(new IntegerWrapper(71));
		sortedList.add(new IntegerWrapper(76));
		sortedList.add(new IntegerWrapper(82));
		sortedList.add(new IntegerWrapper(87));
		sortedList.add(new IntegerWrapper(92));
		sortedList.add(new IntegerWrapper(92));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom7() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(58));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(58));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom8() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(49));
		listToBeSorted.add(new IntegerWrapper(12));
		listToBeSorted.add(new IntegerWrapper(18));
		listToBeSorted.add(new IntegerWrapper(89));
		listToBeSorted.add(new IntegerWrapper(39));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(12));
		sortedList.add(new IntegerWrapper(18));
		sortedList.add(new IntegerWrapper(39));
		sortedList.add(new IntegerWrapper(49));
		sortedList.add(new IntegerWrapper(89));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom9() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(6));
		listToBeSorted.add(new IntegerWrapper(28));
		listToBeSorted.add(new IntegerWrapper(58));
		listToBeSorted.add(new IntegerWrapper(81));
		listToBeSorted.add(new IntegerWrapper(40));
		listToBeSorted.add(new IntegerWrapper(46));
		listToBeSorted.add(new IntegerWrapper(92));
		listToBeSorted.add(new IntegerWrapper(74));
		listToBeSorted.add(new IntegerWrapper(65));
		listToBeSorted.add(new IntegerWrapper(86));
		listToBeSorted.add(new IntegerWrapper(45));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(6));
		sortedList.add(new IntegerWrapper(28));
		sortedList.add(new IntegerWrapper(40));
		sortedList.add(new IntegerWrapper(45));
		sortedList.add(new IntegerWrapper(46));
		sortedList.add(new IntegerWrapper(58));
		sortedList.add(new IntegerWrapper(65));
		sortedList.add(new IntegerWrapper(74));
		sortedList.add(new IntegerWrapper(81));
		sortedList.add(new IntegerWrapper(86));
		sortedList.add(new IntegerWrapper(92));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom10() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(34));
		listToBeSorted.add(new IntegerWrapper(64));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(34));
		sortedList.add(new IntegerWrapper(64));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom11() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(79));
		listToBeSorted.add(new IntegerWrapper(61));
		listToBeSorted.add(new IntegerWrapper(40));
		listToBeSorted.add(new IntegerWrapper(15));
		listToBeSorted.add(new IntegerWrapper(3));
		listToBeSorted.add(new IntegerWrapper(26));
		listToBeSorted.add(new IntegerWrapper(14));
		listToBeSorted.add(new IntegerWrapper(80));
		listToBeSorted.add(new IntegerWrapper(48));
		listToBeSorted.add(new IntegerWrapper(99));
		listToBeSorted.add(new IntegerWrapper(16));
		listToBeSorted.add(new IntegerWrapper(61));
		listToBeSorted.add(new IntegerWrapper(85));
		listToBeSorted.add(new IntegerWrapper(36));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(3));
		sortedList.add(new IntegerWrapper(14));
		sortedList.add(new IntegerWrapper(15));
		sortedList.add(new IntegerWrapper(16));
		sortedList.add(new IntegerWrapper(26));
		sortedList.add(new IntegerWrapper(36));
		sortedList.add(new IntegerWrapper(40));
		sortedList.add(new IntegerWrapper(48));
		sortedList.add(new IntegerWrapper(61));
		sortedList.add(new IntegerWrapper(61));
		sortedList.add(new IntegerWrapper(79));
		sortedList.add(new IntegerWrapper(80));
		sortedList.add(new IntegerWrapper(85));
		sortedList.add(new IntegerWrapper(99));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom12() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(99));
		listToBeSorted.add(new IntegerWrapper(30));
		listToBeSorted.add(new IntegerWrapper(4));
		listToBeSorted.add(new IntegerWrapper(66));
		listToBeSorted.add(new IntegerWrapper(16));
		listToBeSorted.add(new IntegerWrapper(12));
		listToBeSorted.add(new IntegerWrapper(59));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(4));
		sortedList.add(new IntegerWrapper(12));
		sortedList.add(new IntegerWrapper(16));
		sortedList.add(new IntegerWrapper(30));
		sortedList.add(new IntegerWrapper(59));
		sortedList.add(new IntegerWrapper(66));
		sortedList.add(new IntegerWrapper(99));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom13() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(36));
		listToBeSorted.add(new IntegerWrapper(26));
		listToBeSorted.add(new IntegerWrapper(6));
		listToBeSorted.add(new IntegerWrapper(37));
		listToBeSorted.add(new IntegerWrapper(83));
		listToBeSorted.add(new IntegerWrapper(15));
		listToBeSorted.add(new IntegerWrapper(80));
		listToBeSorted.add(new IntegerWrapper(55));
		listToBeSorted.add(new IntegerWrapper(18));
		listToBeSorted.add(new IntegerWrapper(93));
		listToBeSorted.add(new IntegerWrapper(65));
		listToBeSorted.add(new IntegerWrapper(62));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(6));
		sortedList.add(new IntegerWrapper(15));
		sortedList.add(new IntegerWrapper(18));
		sortedList.add(new IntegerWrapper(26));
		sortedList.add(new IntegerWrapper(36));
		sortedList.add(new IntegerWrapper(37));
		sortedList.add(new IntegerWrapper(55));
		sortedList.add(new IntegerWrapper(62));
		sortedList.add(new IntegerWrapper(65));
		sortedList.add(new IntegerWrapper(80));
		sortedList.add(new IntegerWrapper(83));
		sortedList.add(new IntegerWrapper(93));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom14() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(68));
		listToBeSorted.add(new IntegerWrapper(90));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(68));
		sortedList.add(new IntegerWrapper(90));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom15() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(31));
		listToBeSorted.add(new IntegerWrapper(46));
		listToBeSorted.add(new IntegerWrapper(36));
		listToBeSorted.add(new IntegerWrapper(39));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(31));
		sortedList.add(new IntegerWrapper(36));
		sortedList.add(new IntegerWrapper(39));
		sortedList.add(new IntegerWrapper(46));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom16() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(73));
		listToBeSorted.add(new IntegerWrapper(18));
		listToBeSorted.add(new IntegerWrapper(46));
		listToBeSorted.add(new IntegerWrapper(78));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(18));
		sortedList.add(new IntegerWrapper(46));
		sortedList.add(new IntegerWrapper(73));
		sortedList.add(new IntegerWrapper(78));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom17() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(85));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(85));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom18() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(70));
		listToBeSorted.add(new IntegerWrapper(78));
		listToBeSorted.add(new IntegerWrapper(32));
		listToBeSorted.add(new IntegerWrapper(72));
		listToBeSorted.add(new IntegerWrapper(35));
		listToBeSorted.add(new IntegerWrapper(77));
		listToBeSorted.add(new IntegerWrapper(23));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(23));
		sortedList.add(new IntegerWrapper(32));
		sortedList.add(new IntegerWrapper(35));
		sortedList.add(new IntegerWrapper(70));
		sortedList.add(new IntegerWrapper(72));
		sortedList.add(new IntegerWrapper(77));
		sortedList.add(new IntegerWrapper(78));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom19() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(55));
		listToBeSorted.add(new IntegerWrapper(93));
		listToBeSorted.add(new IntegerWrapper(43));
		listToBeSorted.add(new IntegerWrapper(25));
		listToBeSorted.add(new IntegerWrapper(58));
		listToBeSorted.add(new IntegerWrapper(91));
		listToBeSorted.add(new IntegerWrapper(26));
		listToBeSorted.add(new IntegerWrapper(26));
		listToBeSorted.add(new IntegerWrapper(72));
		listToBeSorted.add(new IntegerWrapper(53));
		listToBeSorted.add(new IntegerWrapper(38));
		listToBeSorted.add(new IntegerWrapper(81));
		listToBeSorted.add(new IntegerWrapper(50));
		listToBeSorted.add(new IntegerWrapper(98));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(25));
		sortedList.add(new IntegerWrapper(26));
		sortedList.add(new IntegerWrapper(26));
		sortedList.add(new IntegerWrapper(38));
		sortedList.add(new IntegerWrapper(43));
		sortedList.add(new IntegerWrapper(50));
		sortedList.add(new IntegerWrapper(53));
		sortedList.add(new IntegerWrapper(55));
		sortedList.add(new IntegerWrapper(58));
		sortedList.add(new IntegerWrapper(72));
		sortedList.add(new IntegerWrapper(81));
		sortedList.add(new IntegerWrapper(91));
		sortedList.add(new IntegerWrapper(93));
		sortedList.add(new IntegerWrapper(98));
		Assert.assertEquals(sortedList, result);
	}

	@Test
	public void testRandom20() {
		final List<IntegerType> listToBeSorted = new LinkedList<IntegerType>();
		listToBeSorted.add(new IntegerWrapper(35));
		listToBeSorted.add(new IntegerWrapper(24));
		listToBeSorted.add(new IntegerWrapper(42));
		listToBeSorted.add(new IntegerWrapper(15));
		listToBeSorted.add(new IntegerWrapper(50));
		listToBeSorted.add(new IntegerWrapper(95));
		listToBeSorted.add(IntegerStopCommand.getInstance());
		final BubbleSortManager<IntegerType> manager = new BubbleSortManager<IntegerType>(
				listToBeSorted);
		final List<IntegerType> result = manager.sort();
		final List<IntegerType> sortedList = new LinkedList<IntegerType>();
		sortedList.add(new IntegerWrapper(15));
		sortedList.add(new IntegerWrapper(24));
		sortedList.add(new IntegerWrapper(35));
		sortedList.add(new IntegerWrapper(42));
		sortedList.add(new IntegerWrapper(50));
		sortedList.add(new IntegerWrapper(95));
		Assert.assertEquals(sortedList, result);
	}
}
