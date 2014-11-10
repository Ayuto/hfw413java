package test;

import java.util.LinkedList;
import java.util.List;

import model.Automaton;
import model.NoOutputException;
import model.State;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AutomatonTest {

	private Automaton m1;
	
	private Automaton m2;
	
	private Automaton m3;
	
	private Automaton m4;
	
	private Automaton m5;
	
	private Automaton m6;
	
	private Automaton m7;
	
	private Automaton m8;

	
	@Before
	public void setUp() throws Exception {
		this.m1 = Automaton.create();
		
		this.m2 = Automaton.create('a', "hallo");
		
		this.m3 = Automaton.create('a', "End");
		this.m3.getStart().add('a', this.m3.getStart(), "NotEnd");

		this.m4 = Automaton.create('b', "b");
		this.m4.getStart().add('a', this.m4.getStart(), "a");
		
		this.m5 = Automaton.create();
		final State z1 = State.create(this.m5);
		final State z2 = State.create(this.m5);
		final State z3 = State.create(this.m5);
		this.m5.getStart().add('0', z1, "0");
		z1.add('1', z2, "1");
		z1.add('0', z3, "0");
		z2.add('1', this.m5.getEnd(), "1");
		z3.add('0', z2, "0");
		z3.add('1', z2, "1");
		
		this.m6 = Automaton.create();
		final State a1 = State.create(this.m6);
		final State a2 = State.create(this.m6);
		final State a3 = State.create(this.m6);
		this.m6.getStart().add('0', this.m6.getStart(), "0");
		this.m6.getStart().add('1', a1, "1");
		a1.add('0', this.m6.getStart(), "0");
		a1.add('1', a2, "1");
		a2.add('0', this.m6.getStart(), "0");
		a2.add('1', a3, "1");
		a3.add('0', this.m6.getStart(), "0");
		a3.add('1', this.m6.getEnd(), "1");
		this.m6.getEnd().add('0', this.m6.getEnd(), "0");
		this.m6.getEnd().add('1', this.m6.getEnd(), "1");
		
		this.m7 = Automaton.create('0', "0");
		final State q1 = State.create(this.m7);
		final State q2 = State.create(this.m7);
		final State q3 = State.create(this.m7);
		final State q4 = State.create(this.m7);
		final State q5 = State.create(this.m7);
		final State q7 = State.create(this.m7);
		State.create(this.m7);
		this.m7.getStart().add('0', this.m7.getStart(), "0");
		this.m7.getStart().add('1', this.m7.getStart(), "1");
		this.m7.getStart().add('1', q1, "1");
		this.m7.getStart().add('0', q5, "0");
		q1.add('0', q2, "0");
		q1.add('1', q3, "1");
		q2.add('1', q3, "1");
		q3.add('0', q7, "0");
		q3.add('1', q3, "1");
		q4.add('1', this.m7.getStart(), "1");
		q4.add('0', q3, "0");
		q5.add('1', q2, "1");
		q5.add('1', this.m7.getEnd(), "1");
		q5.add('0', q4, "0");
		q5.add('1', q4, "1");
		this.m7.getEnd().add('0', q7, "0");
		this.m7.getEnd().add('1', q7, "1");
		q7.add('0', q7, "0");
		q7.add('1', q7, "1");
		
		this.m8 = Automaton.create();
		final State s1 = State.create(this.m8);
		final State s2 = State.create(this.m8);
		this.m8.getStart().add('0', s1, "test1");
		this.m8.getStart().add('0', s2, "test2");
		s1.add('0', this.m8.getEnd(), "ende");
		s2.add('0', this.m8.getEnd(), "ende");
	}
	
	@Test
	public void testEmpty() {
		try {
			this.m1.getPossibleOutput("ab");
			Assert.fail();
		} catch (NoOutputException e) {
		}
	}
	
	@Test
	public void testSingleEntry() {
		String result = null;
		try {
			result = this.m2.getPossibleOutput("a");
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("hallo", result);
	}
	
	@Test
	public void testAtLeastOneA() {
		String result = null;
		try {
			result = this.m3.getPossibleOutput("aaa");
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("NotEndNotEndEnd", result);
		
		try {
			result = this.m3.getPossibleOutput("aaa");
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("NotEndNotEndEnd", result);
	}
	
	@Test
	public void testPossibleAsAndAtLeastOneB() {
		String result = null;
		try {
			result = this.m4.getPossibleOutput("aab");
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("aab", result);
		
		try {
			result = this.m4.getPossibleOutput("aaaab");
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("aaaab", result);
	}
	
	@Test
	public void testM5() {
		String result = null;
		try {
			result = this.m5.getPossibleOutput("0011");
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("0011", result);
		
		try {
			result = this.m5.getPossibleOutput("0001");
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("0001", result);
		
		try {
			result = this.m5.getPossibleOutput("011");
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("011", result);
	}
	
	@Test
	public void testM6() {
		String result = null;
		String input = "1111";
		try {
			result = this.m6.getPossibleOutput(input);
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals(input, result);
		
		result = null;
		input = "11111";
		try {
			result = this.m6.getPossibleOutput(input);
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertEquals(input, result);
		result = null;
		input = "11110";
		try {
			result = this.m6.getPossibleOutput(input);
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals(input, result);
		

		Assert.assertEquals(input, result);
		result = null;
		input = "11110101011";
		try {
			result = this.m6.getPossibleOutput(input);
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals(input, result);


		Assert.assertEquals(input, result);
		result = null;
		input = "110111101";
		try {
			result = this.m6.getPossibleOutput(input);
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals(input, result);
	}
	
	@Test
	public void testM7() {
		String input = "01110";
		String result = null;
		try {
			result = this.m7.getPossibleOutput(input);
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals(input, result);
	}
	
	@Test
	public void testM8() {
		String input = "00";
		List<String> possibleResults = new LinkedList<String>();
		possibleResults.add("test1ende");
		possibleResults.add("test2ende");
		
		String result = null;
		try {
			result = this.m8.getPossibleOutput(input);
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertTrue(possibleResults.contains(result));
		
		try {
			result = this.m8.getPossibleOutput(input);
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertTrue(possibleResults.contains(result));
		
		try {
			result = this.m8.getPossibleOutput(input);
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertTrue(possibleResults.contains(result));
		
		try {
			result = this.m8.getPossibleOutput(input);
		} catch (NoOutputException e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertTrue(possibleResults.contains(result));
	}
}
