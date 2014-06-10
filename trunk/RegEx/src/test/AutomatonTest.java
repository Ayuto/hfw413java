package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import automaton.Automaton;
import automaton.State;


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
		
		this.m2 = Automaton.create('a');
		
		this.m3 = Automaton.create('a');
		this.m3.getStart().add('a', this.m3.getStart());
		
		this.m4 = Automaton.create('b');
		this.m4.getStart().add('a', this.m4.getStart());
		
		this.m5 = Automaton.create();
		final State z1 = State.create(this.m5);
		final State z2 = State.create(this.m5);
		final State z3 = State.create(this.m5);
		this.m5.getStart().add('0', z1);
		z1.add('1', z2);
		z1.add('0', z3);
		z2.add('1', this.m5.getEnd());
		z3.add('0', z2);
		z3.add('1', z2);
		
		this.m6 = Automaton.create();
		final State a1 = State.create(this.m6);
		final State a2 = State.create(this.m6);
		final State a3 = State.create(this.m6);
		this.m6.getStart().add('0', this.m6.getStart());
		this.m6.getStart().add('1', a1);
		a1.add('0', this.m6.getStart());
		a1.add('1', a2);
		a2.add('0', this.m6.getStart());
		a2.add('1', a3);
		a3.add('0', this.m6.getStart());
		a3.add('1', this.m6.getEnd());
		this.m6.getEnd().add('0', this.m6.getEnd());
		this.m6.getEnd().add('1', this.m6.getEnd());
		
		this.m7 = Automaton.create('0');
		final State q1 = State.create(this.m7);
		final State q2 = State.create(this.m7);
		final State q3 = State.create(this.m7);
		final State q4 = State.create(this.m7);
		final State q5 = State.create(this.m7);
		final State q7 = State.create(this.m7);
		@SuppressWarnings("unused")
		final State q8 = State.create(this.m7);
		this.m7.getStart().add('0', this.m7.getStart());
		this.m7.getStart().add('1', this.m7.getStart());
		this.m7.getStart().add('1', q1);
		this.m7.getStart().add('0', q5);
		q1.add('0', q2);
		q1.add('1', q3);
		q2.add('1', q3);
		q3.add('0', q7);
		q3.add('1', q3);
		q4.add('1', this.m7.getStart());
		q4.add('0', q3);
		q5.add('1', q2);
		q5.add('1', this.m7.getEnd());
		q5.add('0', q4);
		q5.add('1', q4);
		this.m7.getEnd().add('0', q7);
		this.m7.getEnd().add('1', q7);
		q7.add('0', q7);
		q7.add('1', q7);
	}
	
	@Test
	public void testM1() {
		Assert.assertFalse(this.m1.recognizes("irgendwas"));
		Assert.assertFalse(this.m1.recognizes("hugo"));
		Assert.assertFalse(this.m1.recognizes("hallo"));
		Assert.assertFalse(this.m1.recognizes(" "));
		Assert.assertFalse(this.m1.recognizes(""));
		Assert.assertFalse(this.m1.recognizes("96"));
		Assert.assertFalse(this.m1.recognizes("1900"));
		Assert.assertFalse(this.m1.recognizes("aaa"));
		Assert.assertFalse(this.m1.recognizes("bbb"));
		Assert.assertFalse(this.m1.recognizes("FHDW"));
		
		final int oldAmountOfStates = this.m1.getStates().size();
		final int oldAmountOfTransitions = this.m1.getDelta().size();
		
		this.m1.normalizeStates();
		
		Assert.assertEquals(oldAmountOfStates, this.m1.getStates().size());
		Assert.assertEquals(oldAmountOfTransitions, this.m1.getDelta().size());
		
		Assert.assertFalse(this.m1.recognizes("irgendwas"));
		Assert.assertFalse(this.m1.recognizes("hugo"));
		Assert.assertFalse(this.m1.recognizes("hallo"));
		Assert.assertFalse(this.m1.recognizes(" "));
		Assert.assertFalse(this.m1.recognizes(""));
		Assert.assertFalse(this.m1.recognizes("96"));
		Assert.assertFalse(this.m1.recognizes("1900"));
		Assert.assertFalse(this.m1.recognizes("aaa"));
		Assert.assertFalse(this.m1.recognizes("bbb"));
		Assert.assertFalse(this.m1.recognizes("FHDW"));
	}
	
	@Test
	public void testM2() {
		Assert.assertFalse(this.m2.recognizes("aa"));
		Assert.assertTrue(this.m2.recognizes("a"));
		Assert.assertFalse(this.m2.recognizes("aaa"));
		Assert.assertFalse(this.m2.recognizes("aaaa"));
		Assert.assertFalse(this.m2.recognizes(""));
		Assert.assertFalse(this.m2.recognizes("a "));
		Assert.assertFalse(this.m2.recognizes("1900"));
		Assert.assertFalse(this.m2.recognizes("hallo"));
		Assert.assertFalse(this.m2.recognizes("Lehrte"));
		Assert.assertFalse(this.m2.recognizes("Hugo"));
		Assert.assertFalse(this.m2.recognizes("ahugo"));
		
		final int oldAmountOfStates = this.m2.getStates().size();
		final int oldAmountOfTransitions = this.m2.getDelta().size();
		
		this.m2.normalizeStates();
		
		Assert.assertEquals(oldAmountOfStates, this.m2.getStates().size());
		Assert.assertEquals(oldAmountOfTransitions, this.m2.getDelta().size());
		
		Assert.assertFalse(this.m2.recognizes("aa"));
		Assert.assertTrue(this.m2.recognizes("a"));
		Assert.assertFalse(this.m2.recognizes("aaa"));
		Assert.assertFalse(this.m2.recognizes("aaaa"));
		Assert.assertFalse(this.m2.recognizes(""));
		Assert.assertFalse(this.m2.recognizes("a "));
		Assert.assertFalse(this.m2.recognizes("1900"));
		Assert.assertFalse(this.m2.recognizes("hallo"));
		Assert.assertFalse(this.m2.recognizes("Lehrte"));
		Assert.assertFalse(this.m2.recognizes("Hugo"));
		Assert.assertFalse(this.m2.recognizes("ahugo"));
	}
		
	@Test
	public void testM3() {
		String input = "a";
		for (int i = 0; i < 30; i++) {
			Assert.assertTrue(this.m3.recognizes(input));
			input = input + "a";
		}
		Assert.assertFalse(this.m3.recognizes(""));
		Assert.assertFalse(this.m3.recognizes(" "));
		Assert.assertFalse(this.m3.recognizes("1900"));
		Assert.assertFalse(this.m3.recognizes("Hugo"));
		Assert.assertFalse(this.m3.recognizes("ahugo"));
		Assert.assertFalse(this.m3.recognizes("Hallo"));
		Assert.assertFalse(this.m3.recognizes("FHDW"));
		Assert.assertFalse(this.m3.recognizes("Automaten sind toll!"));
		
		final int oldAmountOfStates = this.m3.getStates().size();
		final int oldAmountOfTransitions = this.m3.getDelta().size();
		
		this.m3.normalizeStates();
		
		Assert.assertEquals(oldAmountOfStates, this.m3.getStates().size());
		Assert.assertEquals(oldAmountOfTransitions, this.m3.getDelta().size());
		
		input = "a";
		for (int i = 0; i < 30; i++) {
			Assert.assertTrue(this.m3.recognizes(input));
			input = input + "a";
		}
		Assert.assertFalse(this.m3.recognizes(""));
		Assert.assertFalse(this.m3.recognizes(" "));
		Assert.assertFalse(this.m3.recognizes("1900"));
		Assert.assertFalse(this.m3.recognizes("Hugo"));
		Assert.assertFalse(this.m3.recognizes("ahugo"));
		Assert.assertFalse(this.m3.recognizes("Hallo"));
		Assert.assertFalse(this.m3.recognizes("FHDW"));
		Assert.assertFalse(this.m3.recognizes("Automaten sind toll!"));
	}
	
	@Test
	public void testM4() {
		Assert.assertFalse(this.m4.recognizes(""));
		Assert.assertTrue(this.m4.recognizes("aaab"));
		Assert.assertTrue(this.m4.recognizes("aab"));
		Assert.assertTrue(this.m4.recognizes("b"));
		Assert.assertTrue(this.m4.recognizes("aaaaaaaaaab"));
		Assert.assertFalse(this.m4.recognizes("ba"));
		Assert.assertFalse(this.m4.recognizes("aaaabaab"));
		Assert.assertFalse(this.m4.recognizes("aabaaaaa"));
		Assert.assertFalse(this.m4.recognizes("ababa"));
		Assert.assertFalse(this.m4.recognizes("aababbb"));
		Assert.assertFalse(this.m4.recognizes("aaaaaabb"));
		Assert.assertFalse(this.m4.recognizes("Hugo"));
		
		final int oldAmountOfStates = this.m4.getStates().size();
		final int oldAmountOfTransitions = this.m4.getDelta().size();
		
		this.m4.normalizeStates();
		
		Assert.assertEquals(oldAmountOfStates, this.m4.getStates().size());
		Assert.assertEquals(oldAmountOfTransitions, this.m4.getDelta().size());
		
		Assert.assertFalse(this.m4.recognizes(""));
		Assert.assertTrue(this.m4.recognizes("aaab"));
		Assert.assertTrue(this.m4.recognizes("aab"));
		Assert.assertTrue(this.m4.recognizes("b"));
		Assert.assertTrue(this.m4.recognizes("aaaaaaaaaab"));
		Assert.assertFalse(this.m4.recognizes("ba"));
		Assert.assertFalse(this.m4.recognizes("aaaabaab"));
		Assert.assertFalse(this.m4.recognizes("aabaaaaa"));
		Assert.assertFalse(this.m4.recognizes("ababa"));
		Assert.assertFalse(this.m4.recognizes("aababbb"));
		Assert.assertFalse(this.m4.recognizes("aaaaaabb"));
		Assert.assertFalse(this.m4.recognizes("Hugo"));
	}
		
	@Test
	public void testM5() {
		Assert.assertFalse(this.m5.recognizes(""));
		Assert.assertFalse(this.m5.recognizes("0"));
		Assert.assertFalse(this.m5.recognizes("01"));
		Assert.assertTrue(this.m5.recognizes("011"));
		Assert.assertTrue(this.m5.recognizes("0011"));
		Assert.assertFalse(this.m5.recognizes("001"));
		Assert.assertFalse(this.m5.recognizes("0110"));
		Assert.assertTrue(this.m5.recognizes("0001"));
		Assert.assertFalse(this.m5.recognizes("00011"));
		Assert.assertFalse(this.m5.recognizes("010101"));
		Assert.assertFalse(this.m5.recognizes("111000"));
		Assert.assertFalse(this.m5.recognizes("Hugo"));

		final int oldAmountOfStates = this.m5.getStates().size();
		final int oldAmountOfTransitions = this.m5.getDelta().size();
		
		this.m5.normalizeStates();
		
		Assert.assertEquals(oldAmountOfStates, this.m5.getStates().size());
		Assert.assertEquals(oldAmountOfTransitions, this.m5.getDelta().size());
		
		Assert.assertFalse(this.m5.recognizes(""));
		Assert.assertFalse(this.m5.recognizes("0"));
		Assert.assertFalse(this.m5.recognizes("01"));
		Assert.assertTrue(this.m5.recognizes("011"));
		Assert.assertTrue(this.m5.recognizes("0011"));
		Assert.assertFalse(this.m5.recognizes("001"));
		Assert.assertFalse(this.m5.recognizes("0110"));
		Assert.assertTrue(this.m5.recognizes("0001"));
		Assert.assertFalse(this.m5.recognizes("00011"));
		Assert.assertFalse(this.m5.recognizes("010101"));
		Assert.assertFalse(this.m5.recognizes("111000"));
		Assert.assertFalse(this.m5.recognizes("Hugo"));
	}
		
	@Test
	public void testM6() {
		Assert.assertFalse(this.m6.recognizes(""));
		Assert.assertFalse(this.m6.recognizes("0"));
		Assert.assertFalse(this.m6.recognizes("1"));
		Assert.assertFalse(this.m6.recognizes("01"));
		Assert.assertFalse(this.m6.recognizes("101"));
		Assert.assertFalse(this.m6.recognizes("10111"));
		Assert.assertFalse(this.m6.recognizes("011101"));
		Assert.assertFalse(this.m6.recognizes("0111"));
		Assert.assertTrue(this.m6.recognizes("01111"));
		Assert.assertFalse(this.m6.recognizes("11011"));
		Assert.assertFalse(this.m6.recognizes("11101110111"));
		Assert.assertTrue(this.m6.recognizes("1111"));
		Assert.assertTrue(this.m6.recognizes("1111100"));
		Assert.assertFalse(this.m6.recognizes("010101"));
		Assert.assertFalse(this.m6.recognizes("111000"));
		Assert.assertFalse(this.m6.recognizes("Hugo"));
		
		final int oldAmountOfStates = this.m6.getStates().size();
		final int oldAmountOfTransitions = this.m6.getDelta().size();
		
		this.m6.normalizeStates();
		
		Assert.assertEquals(oldAmountOfStates, this.m6.getStates().size());
		Assert.assertEquals(oldAmountOfTransitions, this.m6.getDelta().size());
		
		Assert.assertFalse(this.m6.recognizes(""));
		Assert.assertFalse(this.m6.recognizes("0"));
		Assert.assertFalse(this.m6.recognizes("1"));
		Assert.assertFalse(this.m6.recognizes("01"));
		Assert.assertFalse(this.m6.recognizes("101"));
		Assert.assertFalse(this.m6.recognizes("10111"));
		Assert.assertFalse(this.m6.recognizes("011101"));
		Assert.assertFalse(this.m6.recognizes("0111"));
		Assert.assertTrue(this.m6.recognizes("01111"));
		Assert.assertFalse(this.m6.recognizes("11011"));
		Assert.assertFalse(this.m6.recognizes("11101110111"));
		Assert.assertTrue(this.m6.recognizes("1111"));
		Assert.assertTrue(this.m6.recognizes("1111100"));
		Assert.assertFalse(this.m6.recognizes("010101"));
		Assert.assertFalse(this.m6.recognizes("111000"));
		Assert.assertFalse(this.m6.recognizes("Hugo"));
	}
	
	@Test
	public void testM7() {
		Assert.assertFalse(this.m7.recognizes(""));
		Assert.assertTrue(this.m7.recognizes("0"));
		Assert.assertFalse(this.m7.recognizes("1"));
		Assert.assertTrue(this.m7.recognizes("01"));
		Assert.assertTrue(this.m7.recognizes("101"));
		Assert.assertFalse(this.m7.recognizes("10111"));
		Assert.assertTrue(this.m7.recognizes("011101"));
		Assert.assertFalse(this.m7.recognizes("0111"));
		Assert.assertFalse(this.m7.recognizes("01111"));
		Assert.assertFalse(this.m7.recognizes("11011"));
		Assert.assertFalse(this.m7.recognizes("11101110111"));
		Assert.assertFalse(this.m7.recognizes("1111"));
		Assert.assertTrue(this.m7.recognizes("1111100"));
		Assert.assertTrue(this.m7.recognizes("010101"));
		Assert.assertTrue(this.m7.recognizes("111000"));
		Assert.assertFalse(this.m7.recognizes("Hugo"));
		
		this.m7.normalizeStates();
		
		Assert.assertEquals(8, this.m7.getDelta().size());
		Assert.assertEquals(4, this.m7.getStates().size());
		
		Assert.assertFalse(this.m7.recognizes(""));
		Assert.assertTrue(this.m7.recognizes("0"));
		Assert.assertFalse(this.m7.recognizes("1"));
		Assert.assertTrue(this.m7.recognizes("01"));
		Assert.assertTrue(this.m7.recognizes("101"));
		Assert.assertFalse(this.m7.recognizes("10111"));
		Assert.assertTrue(this.m7.recognizes("011101"));
		Assert.assertFalse(this.m7.recognizes("0111"));
		Assert.assertFalse(this.m7.recognizes("01111"));
		Assert.assertFalse(this.m7.recognizes("11011"));
		Assert.assertFalse(this.m7.recognizes("11101110111"));
		Assert.assertFalse(this.m7.recognizes("1111"));
		Assert.assertTrue(this.m7.recognizes("1111100"));
		Assert.assertTrue(this.m7.recognizes("010101"));
		Assert.assertTrue(this.m7.recognizes("111000"));
		Assert.assertFalse(this.m7.recognizes("Hugo"));
	}
}
