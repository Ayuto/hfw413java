package test;

import java.util.LinkedList;
import java.util.List;

import model.BubbleSortManager;
import model.IntegerWrapper;
import model.IntegerWrapperStopCommand;

import org.junit.Assert;
import org.junit.Test;

public class TestBubbleSort {

	@Test
	public void test() {
		final List<IntegerWrapper> l1 = new LinkedList<IntegerWrapper>();
		l1.add(new IntegerWrapper(1));
		l1.add(new IntegerWrapper(2));
		l1.add(new IntegerWrapper(3));
		l1.add(new IntegerWrapper(4));
		l1.add(IntegerWrapperStopCommand.getInstance());
		
		final BubbleSortManager<IntegerWrapper> manager = new BubbleSortManager<IntegerWrapper>(l1);
		final List<IntegerWrapper> result = manager.sort();
		Assert.assertEquals(l1, result);
	}

}