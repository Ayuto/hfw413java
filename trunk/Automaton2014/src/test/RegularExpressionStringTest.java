package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Automaton;
import model.BaseExpression;
import model.Choice;
import model.CompositeExpression;
import model.RegularExpression;
import model.Sequence;

public class RegularExpressionStringTest {

	private Automaton<String, StringType> automatonExpressionHallo;
	private Automaton<String, StringType> automatonExpressionHalloBis;
	private Automaton<String, StringType> automatonExpressionHalloBisBaldODERHi;
	private Automaton<String, StringType> automatonExpressionHalloBisODEROptionalHi;
	private Automaton<String, StringType> automatonExpressionOptionalHallo;
	private List<String> input;
	
	@Before
	public void setUp() throws Exception {
		RegularExpression<String, StringType> expressionHallo = BaseExpression.create(StringType.create("Hallo"));
		RegularExpression<String, StringType> expressionBis = BaseExpression.create(StringType.create("Bis"));
		RegularExpression<String, StringType> expressionBald = BaseExpression.create(StringType.create("Bald"));
		RegularExpression<String, StringType> expressionHi = BaseExpression.create(StringType.create("Hi"));
		
		CompositeExpression<String, StringType> expressionHalloBis = Sequence.create(expressionHallo);
		expressionHalloBis.addSubExpression(expressionBis);
		CompositeExpression<String, StringType> expressionHalloBisBaldODERHi = Choice.create();
		CompositeExpression<String, StringType> expressionHalloBisBald = Sequence.create(expressionHallo);
		expressionHalloBisBald.addSubExpression(expressionBis);
		expressionHalloBisBald.addSubExpression(expressionBald);
		expressionHalloBisBaldODERHi.addSubExpression(expressionHalloBisBald);
		expressionHalloBisBaldODERHi.addSubExpression(expressionHi);
		
		CompositeExpression<String, StringType> expressionHalloBisBaldODEROptionalHi = Choice.create();
		expressionHalloBisBaldODEROptionalHi.addSubExpression(expressionHalloBisBald);
		RegularExpression<String, StringType> expressionOptionalHi = BaseExpression.create(StringType.create("Hi"));
		expressionOptionalHi.setOptional(true);
		expressionHalloBisBaldODEROptionalHi.addSubExpression(expressionOptionalHi);
		
		RegularExpression<String, StringType> expressionOptionalHallo = BaseExpression.create(StringType.create("Hallo"));
		expressionOptionalHallo.setOptional(true);
		
		automatonExpressionHallo = expressionHallo.toAutomaton();
		automatonExpressionHalloBis = expressionHalloBis.toAutomaton();
		automatonExpressionHalloBisBaldODERHi = expressionHalloBisBaldODERHi.toAutomaton();
		automatonExpressionHalloBisODEROptionalHi = expressionHalloBisBaldODEROptionalHi.toAutomaton();
		automatonExpressionOptionalHallo = expressionOptionalHallo.toAutomaton();
		input = new ArrayList<String>();  // leer
	}
	
	@Test
	public void testExpressionsStringEmpty() {
		Assert.assertFalse(automatonExpressionHallo.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBis.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBisBaldODERHi.recognizes(input));
		Assert.assertTrue(automatonExpressionHalloBisODEROptionalHi.recognizes(input));
		Assert.assertTrue(automatonExpressionOptionalHallo.recognizes(input));
	}
	
	@Test
	public void testExpressionsStringLowerHallo() {
		input.add("hallo"); // hallo
		Assert.assertFalse(automatonExpressionHallo.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBis.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBisBaldODERHi.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBisODEROptionalHi.recognizes(input));
		Assert.assertFalse(automatonExpressionOptionalHallo.recognizes(input));
	}
	
	@Test
	public void testExpressionsStringHallo() {
		input.add("Hallo"); // Hallo
		Assert.assertTrue(automatonExpressionHallo.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBis.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBisBaldODERHi.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBisODEROptionalHi.recognizes(input));
		Assert.assertTrue(automatonExpressionOptionalHallo.recognizes(input));
	}
	
	@Test
	public void testExpressionsStringHalloBis() {
		input.add("Hallo"); // Hallo
		input.add("Bis"); 	// Hallo|Bis
		Assert.assertFalse(automatonExpressionHallo.recognizes(input));
		Assert.assertTrue(automatonExpressionHalloBis.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBisBaldODERHi.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBisODEROptionalHi.recognizes(input));
		Assert.assertFalse(automatonExpressionOptionalHallo.recognizes(input));
	}
	
	@Test
	public void testExpressionsStringHalloBisBald() {
		input.add("Hallo"); // Hallo
		input.add("Bis"); 	// Hallo|Bis
		input.add("Bald"); 	// Hallo|Bis|Bald
		Assert.assertFalse(automatonExpressionHallo.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBis.recognizes(input));
		Assert.assertTrue(automatonExpressionHalloBisBaldODERHi.recognizes(input));
		Assert.assertTrue(automatonExpressionHalloBisODEROptionalHi.recognizes(input));
		Assert.assertFalse(automatonExpressionOptionalHallo.recognizes(input));
	}
	
	@Test
	public void testExpressionsStringHalloBisBaldHallo() {
		input.add("Hallo"); // Hallo
		input.add("Bis"); 	// Hallo|Bis
		input.add("Bald"); 	// Hallo|Bis|Bald
		input.add("Hallo"); // Hallo|Bis|Bald|Hallo
		Assert.assertFalse(automatonExpressionHallo.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBis.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBisBaldODERHi.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBisODEROptionalHi.recognizes(input));
		Assert.assertFalse(automatonExpressionOptionalHallo.recognizes(input));
	}
	
	@Test
	public void testExpressionsStringHi() {
		input.add("Hi"); // Hi
		Assert.assertFalse(automatonExpressionHallo.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBis.recognizes(input));
		Assert.assertTrue(automatonExpressionHalloBisBaldODERHi.recognizes(input));
		Assert.assertTrue(automatonExpressionHalloBisODEROptionalHi.recognizes(input));
		Assert.assertFalse(automatonExpressionOptionalHallo.recognizes(input));
	}
	
	@Test
	public void testExpressionsStringHiHi() {
		input.add("Hi"); // Hi
		input.add("Hi"); // Hi|Hi
		Assert.assertFalse(automatonExpressionHallo.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBis.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBisBaldODERHi.recognizes(input));
		Assert.assertFalse(automatonExpressionHalloBisODEROptionalHi.recognizes(input));
		Assert.assertFalse(automatonExpressionOptionalHallo.recognizes(input));
	}
}
