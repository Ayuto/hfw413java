package test;

import java.util.Collection;
import java.util.LinkedList;

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

	
	@Before
	public void setUp() throws Exception {
		this.m1 = Automaton.create();
		
		this.m2 = Automaton.create('a', "hallo");
		
		this.m3 = Automaton.create('a', "End");
		this.m3.getStart().add('a', this.m3.getStart(), "notEnd");
		
		this.m4 = Automaton.create('b', "b");
		this.m4.getStart().add('a', this.m4.getStart(), "a");
//		
//		this.m5 = Automaton.create();
//		final State z1 = State.create(this.m5);
//		final State z2 = State.create(this.m5);
//		final State z3 = State.create(this.m5);
//		this.m5.getStart().add('0', z1);
//		z1.add('1', z2);
//		z1.add('0', z3);
//		z2.add('1', this.m5.getEnd());
//		z3.add('0', z2);
//		z3.add('1', z2);
//		
//		this.m6 = Automaton.create();
//		final State a1 = State.create(this.m6);
//		final State a2 = State.create(this.m6);
//		final State a3 = State.create(this.m6);
//		this.m6.getStart().add('0', this.m6.getStart());
//		this.m6.getStart().add('1', a1);
//		a1.add('0', this.m6.getStart());
//		a1.add('1', a2);
//		a2.add('0', this.m6.getStart());
//		a2.add('1', a3);
//		a3.add('0', this.m6.getStart());
//		a3.add('1', this.m6.getEnd());
//		this.m6.getEnd().add('0', this.m6.getEnd());
//		this.m6.getEnd().add('1', this.m6.getEnd());
//		
//		this.m7 = Automaton.create('0');
//		final State q1 = State.create(this.m7);
//		final State q2 = State.create(this.m7);
//		final State q3 = State.create(this.m7);
//		final State q4 = State.create(this.m7);
//		final State q5 = State.create(this.m7);
//		final State q7 = State.create(this.m7);
//		State.create(this.m7);
//		this.m7.getStart().add('0', this.m7.getStart());
//		this.m7.getStart().add('1', this.m7.getStart());
//		this.m7.getStart().add('1', q1);
//		this.m7.getStart().add('0', q5);
//		q1.add('0', q2);
//		q1.add('1', q3);
//		q2.add('1', q3);
//		q3.add('0', q7);
//		q3.add('1', q3);
//		q4.add('1', this.m7.getStart());
//		q4.add('0', q3);
//		q5.add('1', q2);
//		q5.add('1', this.m7.getEnd());
//		q5.add('0', q4);
//		q5.add('1', q4);
//		this.m7.getEnd().add('0', q7);
//		this.m7.getEnd().add('1', q7);
//		q7.add('0', q7);
//		q7.add('1', q7);
	}
	
	@Test
	public void testEmpty() {
		boolean failed = false;
		try {
			this.m1.getPossibleOutput("abc");
		} catch (NoOutputException e) {
			failed = true;
		}
		Assert.assertTrue(failed);
	}
	
	@Test
	public void testOneTransition() throws NoOutputException {
		Assert.assertEquals("hallo", this.m2.getPossibleOutput("a"));
	}
	
	@Test
	public void testAtLeastOneA() throws NoOutputException {
		String result = this.m3.getPossibleOutput("aaa");
		Collection<String> possibleResults = new LinkedList<String>();
		possibleResults.add("End");
		possibleResults.add("notEndEnd");
		possibleResults.add("notEndnotEndEnd");
		Assert.assertTrue(possibleResults.contains(result));
		result = this.m3.getPossibleOutput("aaa");
		Assert.assertTrue(possibleResults.contains(result));
	}
	
	@Test
	public void testAsAndOneB() throws NoOutputException {
		Assert.assertEquals("aaaaab", this.m4.getPossibleOutput("aaaaab"));
		for (int i = 0; i < 20; i++) {
			System.out.println(i + " " + this.m4.getPossibleOutput("aaab"));
		}
//		Assert.assertEquals("aaab", this.m4.getPossibleOutput("aaab"));
//		Assert.assertEquals(2, this.m4.getCreatedThreads());
	}
}
