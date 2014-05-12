package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parser.RegularExpressionParser;
import expressions.RegularExpression;

public class RegularExpressionTester {

	private RegularExpression e1;
	private RegularExpression e2;
	private RegularExpression e3;
	private RegularExpression e4;
	private RegularExpression e5;

	@Before
	public void setUp() throws Exception {
		this.e1 = RegularExpressionParser.create("a+").parse();
		this.e2 = RegularExpressionParser.create("b").parse();
		this.e3 = RegularExpressionParser.create("(ab)+").parse();
		this.e4 = RegularExpressionParser.create("[]").parse();
		this.e5 = RegularExpressionParser.create("[ab]*").parse();
	}

	@Test
	public void testE1() {
		String test = "";
		Assert.assertFalse(this.e1.check(test));
		for (int i = 0; i < 30; i++) {
			test += "a";
			Assert.assertTrue(this.e1.check(test));
		}
		test += "b";
		Assert.assertFalse(this.e1.check(test));
		Assert.assertFalse(this.e1.check("abaaaa"));
	}
	
	@Test
	public void testE2(){
		Assert.assertFalse(this.e2.check(""));
		Assert.assertFalse(this.e2.check("a"));
		Assert.assertTrue(this.e2.check("b"));
		Assert.assertFalse(this.e2.check("bb"));
		Assert.assertFalse(this.e2.check("bbb"));
		Assert.assertFalse(this.e2.check("ba"));
	}
	
	@Test
	public void testE3(){
		Assert.assertFalse(this.e3.check(""));
		Assert.assertFalse(this.e3.check("a"));
		Assert.assertTrue(this.e3.check("ab"));
		Assert.assertTrue(this.e3.check("abab"));
		Assert.assertFalse(this.e3.check("aba"));
		Assert.assertFalse(this.e3.check(" "));
		Assert.assertFalse(this.e3.check("hugo ist doof"));
		Assert.assertFalse(this.e3.check("irgendwas"));
	}
	
	@Test
	public void testE4(){
		Assert.assertFalse(this.e4.check(""));
		Assert.assertFalse(this.e4.check("a"));
		Assert.assertFalse(this.e4.check("b"));
		Assert.assertFalse(this.e4.check(" "));
		Assert.assertFalse(this.e4.check("c"));
		Assert.assertFalse(this.e4.check("aa"));
		Assert.assertFalse(this.e4.check("bb"));
		Assert.assertFalse(this.e4.check("hugo"));
		Assert.assertFalse(this.e4.check("ist"));
		Assert.assertFalse(this.e4.check("vielleicht"));
		Assert.assertFalse(this.e4.check("doof"));
		Assert.assertFalse(this.e4.check("helloWorld"));
	}
	
	@Test
	public void testE5(){
		Assert.assertTrue(this.e5.check(""));
		Assert.assertTrue(this.e5.check("a"));
		Assert.assertTrue(this.e5.check("ab"));
		Assert.assertTrue(this.e5.check("b"));
		Assert.assertTrue(this.e5.check("ba"));
		Assert.assertTrue(this.e5.check("abba"));
		Assert.assertTrue(this.e5.check("bbaabaaab"));
		Assert.assertTrue(this.e5.check("abaaaaaaba"));
		Assert.assertTrue(this.e5.check("aaaaaaa"));
		Assert.assertTrue(this.e5.check("bbbbbbb"));
		Assert.assertTrue(this.e5.check("aaaabbbbbbbaaa"));
		Assert.assertFalse(this.e5.check("aaaabbdbbbbbaaa"));
		Assert.assertFalse(this.e5.check("Hugo"));
	}
}
