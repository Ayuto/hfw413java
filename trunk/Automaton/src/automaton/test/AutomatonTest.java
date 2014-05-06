package automaton.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import automaton.model.Automaton;

public class AutomatonTest {

	private Automaton m1;
	private Automaton m2;
	private Automaton m3;
	private Automaton m4;
	
	@Before
	public void setUp() throws Exception {
		m1 = Automaton.create();
		m2 = Automaton.create('a');
		m3 = Automaton.create('a');
		m3.getStart().add('a', m3.getStart());
		m4 = Automaton.create('b');
		m4.getStart().add('a', m4.getStart());
	}

	@Test
	public void testRecognizes() {
		Assert.assertFalse(m1.recognizes("irgendwas"));
		Assert.assertFalse(m2.recognizes("aa"));
		Assert.assertTrue(m2.recognizes("a"));
		String input = "a";
		for (int i = 0; i < 30; i++) {
			Assert.assertTrue(m3.recognizes(input));
			input = input + "a";
		}
		Assert.assertFalse(m4.recognizes(""));
		Assert.assertTrue(m4.recognizes("aaaaaab"));
		Assert.assertFalse(m4.recognizes("aaba"));
	}

}
