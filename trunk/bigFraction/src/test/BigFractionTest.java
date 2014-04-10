package test;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import model.BigFraction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BigFractionTest {

	private BigFraction half;
	private BigFraction half2;
	private BigFraction quarter1;
	private BigFraction quarter2;
	private BigFraction quarterSquare;

	@Before
	public void setUp() throws Exception {
		half = BigFraction.create(BigInteger.ONE,
				BigInteger.valueOf(2));
		
		half2 = BigFraction.create(BigInteger.valueOf(2),
				BigInteger.valueOf(4));
		
		quarter1 = BigFraction.create(BigInteger.valueOf(1),
				BigInteger.valueOf(4));
		
		quarter2 = BigFraction.create(BigInteger.valueOf(-2),
				BigInteger.valueOf(-8));
		
		quarterSquare = BigFraction.create(BigInteger.ONE,
				BigInteger.valueOf(16));
	}
	
	@Test
	public void equalsTest()
	{
		assertEquals(quarter1, quarter2);
		assertEquals(half, half2);
	}
	
	@Test
	public void multiplyTest()
	{
		assertEquals(quarterSquare, quarter1.multiply(quarter2));
		assertEquals("1/8", quarter1.multiply(half).toString());
		assertEquals(BigFraction.ZERO, half.multiply(BigFraction.ZERO));
		assertEquals(quarter1.multiply(quarter2), quarter2.multiply(quarter1));
	}

	@Test
	public void toStringTest()
	{
		assertEquals("1/4", quarter1.toString());
		assertEquals("1/16", quarterSquare.toString());
		assertEquals("1/2", half.toString());
	}
	
	@Test
	public void parseExceptionTest()
	{
		try {
			BigFraction.parse("Hugo ist doof!");
			Assert.fail();
		} catch (NumberFormatException e) {
		}
		
		try {
			BigFraction.parse("");
			Assert.fail();
		} catch (NumberFormatException e) {
		}
		
		try {
			BigFraction.parse(null);
			Assert.fail();
		} catch (NullPointerException e) {
		}
	}
	
	@Test
	public void parseFractionSeperatorTest()
	{
		// <enumerator>/<denominator>
		assertEquals(quarter1, BigFraction.parse("1/4"));
		assertEquals(quarterSquare, BigFraction.parse("2/32"));
		assertEquals(half, BigFraction.parse("2/4"));
		assertEquals(quarter2, BigFraction.parse(quarter2.toString()));
		

		// /<denominator>
		assertEquals(BigFraction.parse("/10"), BigFraction.parse("1/10"));
		assertEquals(BigFraction.parse("/213"), BigFraction.parse("1/213"));
		assertEquals(BigFraction.parse("/-10"), BigFraction.parse("-1/10"));
	}
	
	@Test
	public void parseDecimalSeperatorTest()
	{
		assertEquals(quarter1, BigFraction.parse("0.25"));
		assertEquals("1/8", BigFraction.parse("0.125").toString());
		assertEquals(half, BigFraction.parse("0000.5000"));
		assertEquals(quarterSquare, BigFraction.parse(".0625"));
	}
	
	@Test
	public void parseDecimalPowSeperatorTest()
	{
		assertEquals(BigFraction.parse("E5"), BigFraction.parse("100000"));
		assertEquals(BigFraction.parse("E-5"), BigFraction.parse("1/100000"));

		assertEquals(quarter1, BigFraction.parse("25E-2"));
		assertEquals("1/1000", BigFraction.parse("1E-3").toString());
		assertEquals(half, BigFraction.parse("50E-2"));
		assertEquals("1/8", BigFraction.parse("125E-3").toString());
		assertEquals(BigFraction.parse("100/1"), BigFraction.parse("E2"));
	}
	
	@Test
	public void parseNumberTest()
	{
		assertEquals(BigFraction.parse("10"), BigFraction.parse("10/1"));
		assertEquals(BigFraction.parse("15"), BigFraction.parse("15/1"));
		assertEquals(BigFraction.parse("-10"), BigFraction.parse("-10/1"));
	}
	
	@Test
	public void devideTest()
	{
		assertEquals("1/1", quarter1.divide(quarter2).toString());
		assertEquals(quarter1, half.divide(BigFraction.create(
				BigInteger.valueOf(2), BigInteger.ONE)));
		
		assertEquals(BigFraction.create(BigInteger.valueOf(4), BigInteger.ONE),
				quarter1.divide(quarterSquare));
		
		assertEquals(quarterSquare, quarterSquare.divide(BigFraction.ONE));
	}
	
	@Test
	public void addTest()
	{
		assertEquals(half, quarter1.add(quarter2));
		assertEquals("3/4", half.add(quarter2).toString());
		assertEquals(BigFraction.ONE, half.add(half));
		assertEquals("5/16", quarterSquare.add(quarter1).toString());
	}
	
	@Test
	public void subtractTest()
	{
		assertEquals(BigFraction.ZERO, half.subtract(half));
		assertEquals(BigFraction.ONE, BigFraction.ONE.add(quarter1).subtract(quarter2));
		assertEquals("-1/4", quarter1.subtract(half).toString());
		assertEquals(quarterSquare, quarterSquare.subtract(BigFraction.ZERO));
	}
}
