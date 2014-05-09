package automaton;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AutomatonTest {

	private Automaton m1;
	private Automaton m2;
	private Automaton m3;
	private Automaton m4;
	private Automaton m5;
	
	@Before
	public void setUp() throws Exception {
		this.m1 = Automaton.create();
		this.m2 = Automaton.create('a');
		this.m3 = Automaton.create('a');
		this.m3.getStart().add('a', this.m3.getStart());
		this.m4 = Automaton.create('b');
		this.m4.getStart().add('a', this.m4.getStart());
		this.m5 = Automaton.create('a');
		this.m5.getStart().add('b', this.m5.getEnd());
		this.m5.setOptional(true);
		this.m5.iterate();
	}

	@Test
	public void testRecognizes() {
		Assert.assertFalse(this.m1.recognizes("irgendwas"));
		Assert.assertFalse(this.m2.recognizes("aa"));
		Assert.assertTrue(this.m2.recognizes("a"));
		String input = "a";
		for (int i = 0; i < 30; i++) {
			Assert.assertTrue(this.m3.recognizes(input));
			input = input + "a";
		}
		Assert.assertFalse(this.m4.recognizes(""));
		Assert.assertTrue(this.m4.recognizes("aaaaaab"));
		Assert.assertFalse(this.m4.recognizes("aaba"));
		Assert.assertTrue(this.m5.recognizes("abbbaaaaabaabba"));
	}

}
