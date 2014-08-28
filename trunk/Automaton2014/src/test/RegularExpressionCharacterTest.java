package test;

import java.util.ArrayList;
import java.util.List;

import model.Automaton;
import model.BaseExpression;
import model.Choice;
import model.CompositeExpression;
import model.RegularExpression;
import model.Sequence;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegularExpressionCharacterTest {

	private Automaton<Character, CharacterType> lowerAAutomaton;
	private Automaton<Character, CharacterType> lowerABAutomaton;
	private Automaton<Character, CharacterType> lowerABorLowerCAutomaton;
	private Automaton<Character, CharacterType> lowerABorLowerCAutomatonIterated;
	private Automaton<Character, CharacterType> emptyChoiceAutomaton;
	private Automaton<Character, CharacterType> optionalEmptyChoiceAutomaton;
	private List<Character> input;
	
	@Before
	public void setUp() throws Exception {
		RegularExpression<Character, CharacterType> expressionEmptyChoice = Choice.create();
		CompositeExpression<Character, CharacterType> expressionOptionalEmptyChoice = Choice.create();
		expressionOptionalEmptyChoice.setOptional(true);
		CharacterType lowerA = CharacterType.create('a');
		CharacterType lowerB = CharacterType.create('b');
		CharacterType lowerC = CharacterType.create('c');
		RegularExpression<Character, CharacterType> expressionLowerA = BaseExpression.create(lowerA);
		RegularExpression<Character, CharacterType> expressionLowerB = BaseExpression.create(lowerB);
		CompositeExpression<Character, CharacterType> expressionLowerAB = Sequence.create(expressionLowerA);
		expressionLowerAB.addSubExpression(expressionLowerB);
		CompositeExpression<Character, CharacterType> expressionLowerABorLowerC = Choice.create();
		expressionLowerABorLowerC.addSubExpression(expressionLowerAB);
		expressionLowerABorLowerC.addSubExpression(BaseExpression.create(lowerC));
		CompositeExpression<Character, CharacterType> iteratedExpressionLowerABorLowerC = Choice.create();
		iteratedExpressionLowerABorLowerC.addSubExpression(expressionLowerABorLowerC);
		iteratedExpressionLowerABorLowerC.setIterated(true);
		
		lowerAAutomaton = expressionLowerA.toAutomaton();
		lowerABAutomaton = expressionLowerAB.toAutomaton();
		lowerABorLowerCAutomaton = expressionLowerABorLowerC.toAutomaton();
		lowerABorLowerCAutomatonIterated = iteratedExpressionLowerABorLowerC.toAutomaton();
		emptyChoiceAutomaton = expressionEmptyChoice.toAutomaton();
		optionalEmptyChoiceAutomaton = expressionOptionalEmptyChoice.toAutomaton();
		input = new ArrayList<Character>(); // leer
		
	}
	
	@Test
	public void testExpressionsCharacterEmpty() {
		Assert.assertFalse(lowerAAutomaton.recognizes(input));
		Assert.assertFalse(lowerABAutomaton.recognizes(input));
		Assert.assertFalse(lowerABorLowerCAutomaton.recognizes(input));
		Assert.assertFalse(lowerABorLowerCAutomatonIterated.recognizes(input));
		Assert.assertFalse(emptyChoiceAutomaton.recognizes(input));
		Assert.assertTrue(optionalEmptyChoiceAutomaton.recognizes(input));
	}
	
	@Test
	public void testExpressionsCharacterLowerA() {
		input.add('a'); // a
		Assert.assertTrue(lowerAAutomaton.recognizes(input));
		Assert.assertFalse(lowerABAutomaton.recognizes(input));
		Assert.assertFalse(lowerABorLowerCAutomaton.recognizes(input));
		Assert.assertFalse(lowerABorLowerCAutomatonIterated.recognizes(input));
		Assert.assertFalse(emptyChoiceAutomaton.recognizes(input));
		Assert.assertFalse(optionalEmptyChoiceAutomaton.recognizes(input));
	}
	
	@Test
	public void testExpressionsCharacterLowerAB() {
		input.add('a'); // a
		input.add('b'); // ab
		Assert.assertFalse(lowerAAutomaton.recognizes(input));
		Assert.assertTrue(lowerABAutomaton.recognizes(input));
		Assert.assertTrue(lowerABorLowerCAutomaton.recognizes(input));
		Assert.assertTrue(lowerABorLowerCAutomatonIterated.recognizes(input));
		Assert.assertFalse(emptyChoiceAutomaton.recognizes(input));
		Assert.assertFalse(optionalEmptyChoiceAutomaton.recognizes(input));
	}
	
	@Test
	public void testExpressionsCharacterLowerC() {
		input.add('c'); // c
		Assert.assertFalse(lowerAAutomaton.recognizes(input));
		Assert.assertFalse(lowerABAutomaton.recognizes(input));
		Assert.assertTrue(lowerABorLowerCAutomaton.recognizes(input));
		Assert.assertTrue(lowerABorLowerCAutomatonIterated.recognizes(input));
		Assert.assertFalse(emptyChoiceAutomaton.recognizes(input));
		Assert.assertFalse(optionalEmptyChoiceAutomaton.recognizes(input));
	}
	
	@Test
	public void testExpressionsCharacterLowerCA() {
		input.add('c'); // c
		input.add('a'); // ca
		Assert.assertFalse(lowerAAutomaton.recognizes(input));
		Assert.assertFalse(lowerABAutomaton.recognizes(input));
		Assert.assertFalse(lowerABorLowerCAutomaton.recognizes(input));
		Assert.assertFalse(lowerABorLowerCAutomatonIterated.recognizes(input));
		Assert.assertFalse(emptyChoiceAutomaton.recognizes(input));
		Assert.assertFalse(optionalEmptyChoiceAutomaton.recognizes(input));
	}
	
	@Test
	public void testExpressionsCharacterLowerCAB() {
		input.add('c'); // c
		input.add('a'); // ca
		input.add('b'); // cab
		Assert.assertFalse(lowerAAutomaton.recognizes(input));
		Assert.assertFalse(lowerABAutomaton.recognizes(input));
		Assert.assertFalse(lowerABorLowerCAutomaton.recognizes(input));
		Assert.assertTrue(lowerABorLowerCAutomatonIterated.recognizes(input));
		Assert.assertFalse(emptyChoiceAutomaton.recognizes(input));
		Assert.assertFalse(optionalEmptyChoiceAutomaton.recognizes(input));
	}
	
	@Test
	public void testExpressionsCharacterLowerCABC() {
		input.add('c'); // c
		input.add('a'); // ca
		input.add('b'); // cab
		input.add('c'); // cabc
		Assert.assertFalse(lowerAAutomaton.recognizes(input));
		Assert.assertFalse(lowerABAutomaton.recognizes(input));
		Assert.assertFalse(lowerABorLowerCAutomaton.recognizes(input));
		Assert.assertTrue(lowerABorLowerCAutomatonIterated.recognizes(input));
		Assert.assertFalse(emptyChoiceAutomaton.recognizes(input));
		Assert.assertFalse(optionalEmptyChoiceAutomaton.recognizes(input));
	}
	
	@Test
	public void testExpressionsCharacterLowerCABCC() {
		input.add('c'); // c
		input.add('a'); // ca
		input.add('b'); // cab
		input.add('c'); // cabc
		input.add('c'); // cabcc
		Assert.assertFalse(lowerAAutomaton.recognizes(input));
		Assert.assertFalse(lowerABAutomaton.recognizes(input));
		Assert.assertFalse(lowerABorLowerCAutomaton.recognizes(input));
		Assert.assertTrue(lowerABorLowerCAutomatonIterated.recognizes(input));
		Assert.assertFalse(emptyChoiceAutomaton.recognizes(input));
		Assert.assertFalse(optionalEmptyChoiceAutomaton.recognizes(input));
	}
}
