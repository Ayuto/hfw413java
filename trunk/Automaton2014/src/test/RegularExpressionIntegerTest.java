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

public class RegularExpressionIntegerTest {

	private Automaton<Integer, IntegerType> automatonExpression1;
	private Automaton<Integer, IntegerType> automatonExpression2;
	private Automaton<Integer, IntegerType> automatonExpression12;
	private Automaton<Integer, IntegerType> automatonExpression123;
	private Automaton<Integer, IntegerType> automatonExpression5;
	private Automaton<Integer, IntegerType> automatonExpression6;
	private Automaton<Integer, IntegerType> automatonExpression7;
	private Automaton<Integer, IntegerType> automatonExpression567;
	private Automaton<Integer, IntegerType> automatonExpression123or567;
	private Automaton<Integer, IntegerType> automatonExpressionOptional123or567;
	private Automaton<Integer, IntegerType> automatonExpressionOptional123or567AndEnd9;
	private Automaton<Integer, IntegerType> automatonExpressionOptional567AndOptional123;
	private List<Integer> input;
	
	@Before
	public void setUp() throws Exception {
		RegularExpression<Integer, IntegerType> expression1 = BaseExpression.create(IntegerType.create(1));
		RegularExpression<Integer, IntegerType> expression2 = BaseExpression.create(IntegerType.create(2));
		CompositeExpression<Integer, IntegerType> expression12 = Sequence.create(expression1);
		expression12.addSubExpression(expression2);
		CompositeExpression<Integer, IntegerType> expression123 = Sequence.create(expression12);
		expression123.addSubExpression(BaseExpression.create(IntegerType.create(3)));
		RegularExpression<Integer, IntegerType> expression5 = BaseExpression.create(IntegerType.create(5));
		RegularExpression<Integer, IntegerType> expression6 = BaseExpression.create(IntegerType.create(6));
		RegularExpression<Integer, IntegerType> expression7 = BaseExpression.create(IntegerType.create(7));
		CompositeExpression<Integer, IntegerType> expression567 = Sequence.create(expression5);
		expression567.addSubExpression(expression6);
		expression567.addSubExpression(expression7);
		CompositeExpression<Integer, IntegerType> expressionOptional567 = Choice.create();
		expressionOptional567.addSubExpression(expression567);
		expressionOptional567.setOptional(true);
		
		CompositeExpression<Integer, IntegerType> expressionOptional123 = Sequence.create(expression1);
		expressionOptional123.addSubExpression(expression2);
		expressionOptional123.addSubExpression(BaseExpression.create(IntegerType.create(3)));
		expressionOptional123.setOptional(true);
		
		CompositeExpression<Integer, IntegerType> expressionOptional567AndOptional123 = Sequence.create(expressionOptional567);
		expressionOptional567AndOptional123.addSubExpression(expressionOptional123);
		
		CompositeExpression<Integer, IntegerType> expression123or567 = Choice.create();
		expression123or567.addSubExpression(expression123);
		expression123or567.addSubExpression(expression567);
		
		CompositeExpression<Integer, IntegerType> expressionOptional123or567 = Choice.create();
		expressionOptional123or567.addSubExpression(expressionOptional123);
		expressionOptional123or567.addSubExpression(expression567);
		
		CompositeExpression<Integer, IntegerType> expressionOptional123or567AndEnd9 = Sequence.create(expressionOptional123or567);
		expressionOptional123or567AndEnd9.addSubExpression(BaseExpression.create(IntegerType.create(9)));
		
		automatonExpression1 = expression1.toAutomaton();
		automatonExpression2 = expression2.toAutomaton();
		automatonExpression12 = expression12.toAutomaton();
		automatonExpression123 = expression123.toAutomaton();
		automatonExpression5 = expression5.toAutomaton();
		automatonExpression6 = expression6.toAutomaton();
		automatonExpression7 = expression7.toAutomaton();
		automatonExpression567 = expression567.toAutomaton();
		automatonExpression123or567 = expression123or567.toAutomaton();
		automatonExpressionOptional123or567 = expressionOptional123or567.toAutomaton();
		automatonExpressionOptional123or567AndEnd9 = expressionOptional123or567AndEnd9.toAutomaton();
		automatonExpressionOptional567AndOptional123 = expressionOptional567AndOptional123.toAutomaton();
		input = new ArrayList<Integer>(); // leer
	}
	
	@Test
	public void testExpressionsIntegerEmpty() {
		Assert.assertTrue(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertFalse(automatonExpression1.recognizes(input));
		Assert.assertFalse(automatonExpression2.recognizes(input));
		Assert.assertFalse(automatonExpression12.recognizes(input));
		Assert.assertFalse(automatonExpression123.recognizes(input));
		Assert.assertFalse(automatonExpression5.recognizes(input));
		Assert.assertFalse(automatonExpression6.recognizes(input));
		Assert.assertFalse(automatonExpression7.recognizes(input));
		Assert.assertFalse(automatonExpression567.recognizes(input));
		Assert.assertFalse(automatonExpression123or567.recognizes(input));
		Assert.assertTrue(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
	
	@Test
	public void testExpressionsInteger9() {
		input.add(9); // 9
		Assert.assertFalse(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertFalse(automatonExpression1.recognizes(input));
		Assert.assertFalse(automatonExpression2.recognizes(input));
		Assert.assertFalse(automatonExpression12.recognizes(input));
		Assert.assertFalse(automatonExpression123.recognizes(input));
		Assert.assertFalse(automatonExpression5.recognizes(input));
		Assert.assertFalse(automatonExpression6.recognizes(input));
		Assert.assertFalse(automatonExpression7.recognizes(input));
		Assert.assertFalse(automatonExpression567.recognizes(input));
		Assert.assertFalse(automatonExpression123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertTrue(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
	
	@Test
	public void testExpressionsInteger2() {
		input.add(2); // 2
		Assert.assertFalse(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertFalse(automatonExpression1.recognizes(input));
		Assert.assertTrue(automatonExpression2.recognizes(input));
		Assert.assertFalse(automatonExpression12.recognizes(input));
		Assert.assertFalse(automatonExpression123.recognizes(input));
		Assert.assertFalse(automatonExpression5.recognizes(input));
		Assert.assertFalse(automatonExpression6.recognizes(input));
		Assert.assertFalse(automatonExpression7.recognizes(input));
		Assert.assertFalse(automatonExpression567.recognizes(input));
		Assert.assertFalse(automatonExpression123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
	
	@Test
	public void testExpressionsInteger1() {
		input.add(1); // 1
		Assert.assertFalse(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertTrue(automatonExpression1.recognizes(input));
		Assert.assertFalse(automatonExpression2.recognizes(input));
		Assert.assertFalse(automatonExpression12.recognizes(input));
		Assert.assertFalse(automatonExpression123.recognizes(input));
		Assert.assertFalse(automatonExpression5.recognizes(input));
		Assert.assertFalse(automatonExpression6.recognizes(input));
		Assert.assertFalse(automatonExpression7.recognizes(input));
		Assert.assertFalse(automatonExpression567.recognizes(input));
		Assert.assertFalse(automatonExpression123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
	
	@Test
	public void testExpressionsInteger12() {
		input.add(1); // 1
		input.add(2); // 12
		Assert.assertFalse(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertFalse(automatonExpression1.recognizes(input));
		Assert.assertFalse(automatonExpression2.recognizes(input));
		Assert.assertTrue(automatonExpression12.recognizes(input));
		Assert.assertFalse(automatonExpression123.recognizes(input));
		Assert.assertFalse(automatonExpression5.recognizes(input));
		Assert.assertFalse(automatonExpression6.recognizes(input));
		Assert.assertFalse(automatonExpression7.recognizes(input));
		Assert.assertFalse(automatonExpression567.recognizes(input));
		Assert.assertFalse(automatonExpression123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
	
	@Test
	public void testExpressionsInteger123() {
		input.add(1); // 1
		input.add(2); // 12
		input.add(3); // 123
		Assert.assertTrue(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertFalse(automatonExpression1.recognizes(input));
		Assert.assertFalse(automatonExpression2.recognizes(input));
		Assert.assertFalse(automatonExpression12.recognizes(input));
		Assert.assertTrue(automatonExpression123.recognizes(input));
		Assert.assertFalse(automatonExpression5.recognizes(input));
		Assert.assertFalse(automatonExpression6.recognizes(input));
		Assert.assertFalse(automatonExpression7.recognizes(input));
		Assert.assertFalse(automatonExpression567.recognizes(input));
		Assert.assertTrue(automatonExpression123or567.recognizes(input));
		Assert.assertTrue(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
	
	@Test
	public void testExpressionsInteger1239() {
		input.add(1); // 1
		input.add(2); // 12
		input.add(3); // 123
		input.add(9); // 1239
		Assert.assertFalse(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertFalse(automatonExpression1.recognizes(input));
		Assert.assertFalse(automatonExpression2.recognizes(input));
		Assert.assertFalse(automatonExpression12.recognizes(input));
		Assert.assertFalse(automatonExpression123.recognizes(input));
		Assert.assertFalse(automatonExpression5.recognizes(input));
		Assert.assertFalse(automatonExpression6.recognizes(input));
		Assert.assertFalse(automatonExpression7.recognizes(input));
		Assert.assertFalse(automatonExpression567.recognizes(input));
		Assert.assertFalse(automatonExpression123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertTrue(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
	
	@Test
	public void testExpressionsInteger5() {
		input.add(5); // 5
		Assert.assertFalse(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertFalse(automatonExpression1.recognizes(input));
		Assert.assertFalse(automatonExpression2.recognizes(input));
		Assert.assertFalse(automatonExpression12.recognizes(input));
		Assert.assertFalse(automatonExpression123.recognizes(input));
		Assert.assertTrue(automatonExpression5.recognizes(input));
		Assert.assertFalse(automatonExpression6.recognizes(input));
		Assert.assertFalse(automatonExpression7.recognizes(input));
		Assert.assertFalse(automatonExpression567.recognizes(input));
		Assert.assertFalse(automatonExpression123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
	
	@Test
	public void testExpressionsInteger56() {
		input.add(5); // 5
		input.add(6); // 56
		Assert.assertFalse(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertFalse(automatonExpression1.recognizes(input));
		Assert.assertFalse(automatonExpression2.recognizes(input));
		Assert.assertFalse(automatonExpression12.recognizes(input));
		Assert.assertFalse(automatonExpression123.recognizes(input));
		Assert.assertFalse(automatonExpression5.recognizes(input));
		Assert.assertFalse(automatonExpression6.recognizes(input));
		Assert.assertFalse(automatonExpression7.recognizes(input));
		Assert.assertFalse(automatonExpression567.recognizes(input));
		Assert.assertFalse(automatonExpression123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
	
	@Test
	public void testExpressionsInteger567() {
		input.add(5); // 5
		input.add(6); // 56
		input.add(7); // 567
		Assert.assertTrue(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertFalse(automatonExpression1.recognizes(input));
		Assert.assertFalse(automatonExpression2.recognizes(input));
		Assert.assertFalse(automatonExpression12.recognizes(input));
		Assert.assertFalse(automatonExpression123.recognizes(input));
		Assert.assertFalse(automatonExpression5.recognizes(input));
		Assert.assertFalse(automatonExpression6.recognizes(input));
		Assert.assertFalse(automatonExpression7.recognizes(input));
		Assert.assertTrue(automatonExpression567.recognizes(input));
		Assert.assertTrue(automatonExpression123or567.recognizes(input));
		Assert.assertTrue(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
	
	@Test
	public void testExpressionsInteger5679() {
		input.add(5); // 5
		input.add(6); // 56
		input.add(7); // 567
		input.add(9); // 5679
		Assert.assertFalse(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertFalse(automatonExpression1.recognizes(input));
		Assert.assertFalse(automatonExpression2.recognizes(input));
		Assert.assertFalse(automatonExpression12.recognizes(input));
		Assert.assertFalse(automatonExpression123.recognizes(input));
		Assert.assertFalse(automatonExpression5.recognizes(input));
		Assert.assertFalse(automatonExpression6.recognizes(input));
		Assert.assertFalse(automatonExpression7.recognizes(input));
		Assert.assertFalse(automatonExpression567.recognizes(input));
		Assert.assertFalse(automatonExpression123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertTrue(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
	
	@Test
	public void testExpressionsInteger6() {
		input.add(6); // 6
		Assert.assertFalse(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertFalse(automatonExpression1.recognizes(input));
		Assert.assertFalse(automatonExpression2.recognizes(input));
		Assert.assertFalse(automatonExpression12.recognizes(input));
		Assert.assertFalse(automatonExpression123.recognizes(input));
		Assert.assertFalse(automatonExpression5.recognizes(input));
		Assert.assertTrue(automatonExpression6.recognizes(input));
		Assert.assertFalse(automatonExpression7.recognizes(input));
		Assert.assertFalse(automatonExpression567.recognizes(input));
		Assert.assertFalse(automatonExpression123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
	
	@Test
	public void testExpressionsInteger7() {
		input.add(7); // 7
		Assert.assertFalse(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertFalse(automatonExpression1.recognizes(input));
		Assert.assertFalse(automatonExpression2.recognizes(input));
		Assert.assertFalse(automatonExpression12.recognizes(input));
		Assert.assertFalse(automatonExpression123.recognizes(input));
		Assert.assertFalse(automatonExpression5.recognizes(input));
		Assert.assertFalse(automatonExpression6.recognizes(input));
		Assert.assertTrue(automatonExpression7.recognizes(input));
		Assert.assertFalse(automatonExpression567.recognizes(input));
		Assert.assertFalse(automatonExpression123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
	
	@Test
	public void testExpressionsInteger567123() {
		input.add(5); // 5
		input.add(6); // 56
		input.add(7); // 567
		input.add(1); // 5671
		input.add(2); // 56712
		input.add(3); // 567123
		Assert.assertTrue(automatonExpressionOptional567AndOptional123.recognizes(input));
		Assert.assertFalse(automatonExpression1.recognizes(input));
		Assert.assertFalse(automatonExpression2.recognizes(input));
		Assert.assertFalse(automatonExpression12.recognizes(input));
		Assert.assertFalse(automatonExpression123.recognizes(input));
		Assert.assertFalse(automatonExpression5.recognizes(input));
		Assert.assertFalse(automatonExpression6.recognizes(input));
		Assert.assertFalse(automatonExpression7.recognizes(input));
		Assert.assertFalse(automatonExpression567.recognizes(input));
		Assert.assertFalse(automatonExpression123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567.recognizes(input));
		Assert.assertFalse(automatonExpressionOptional123or567AndEnd9.recognizes(input));
	}
}
