package expressions.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parser.RegularExpressionParser;
import expressions.RegularExpression;
import expressions.RegularExpressionFacade;

public class RegularExpressionTest {

	private RegularExpressionFacade facade = RegularExpressionFacade.create();
	private RegularExpression baseA;
	private RegularExpression simpleSequence;
	private RegularExpression sequence;
	private RegularExpression simpleChoice;
	private RegularExpression choice;
	
	@Before
	public void setUp() throws Exception {
		baseA = facade.createBaseExpression('a');
		simpleSequence = facade.createSequenceExpression();
		simpleChoice = facade.createChoiceExpression();
		choice = RegularExpressionParser.create("[ab]*").parse();
		sequence = RegularExpressionParser.create("(a*b*)").parse();
	}

	@Test
	public void test() {
		Assert.assertTrue(baseA.check("a"));
		Assert.assertTrue(simpleSequence.check(""));
		Assert.assertFalse(simpleChoice.check(""));
		Assert.assertTrue(choice.check("abbaaababb"));
		Assert.assertTrue(sequence.check(""));
		Assert.assertFalse(sequence.check("abbabbb"));
		Assert.assertFalse(sequence.check("aaabbbbb"));
	}

}
