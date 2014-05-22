package test;

import model.Automaton;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AutomatonTest {

	private Automaton m1;
	
	private Automaton m2;
	
	private Automaton m3;
	
	private Automaton m4;

	
	@Before
	public void setUp() throws Exception {
		this.m1 = Automaton.create();
		
		this.m2 = Automaton.create('a');
		
		this.m3 = Automaton.create('a');
		
		this.m3.getStart().add('a', this.m3.getStart());
		
		this.m4 = Automaton.create('b');
		
		this.m4.getStart().add('a', this.m4.getStart());
	}

	@Test
	public void testRecognizes() {
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

}
