package test;


import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import model.BigFraction;

import org.junit.Test;

public class BigFractionTest {

	@Test
	public void test() {
		final BigFraction half = BigFraction.create(BigInteger.ONE, BigInteger.valueOf(2));
		final BigFraction quarter1 = BigFraction.create(BigInteger.valueOf(1), BigInteger.valueOf(4));
		final BigFraction quarter2 = BigFraction.create(BigInteger.valueOf(-2), BigInteger.valueOf(-8));
		assertEquals(quarter1, quarter2);
		
		final BigFraction quarterSquare = BigFraction.create(BigInteger.ONE, BigInteger.valueOf(16));
		//assertEquals(BigFraction.parse("1/16"), quarter1.multiply(quarter2));
		assertEquals("1/4", quarter1.toString());
		assertEquals("1/16", quarterSquare.toString());
		
		assertEquals(quarter1, BigFraction.parse("1/4"));
		assertEquals(quarterSquare, BigFraction.parse("2/32"));
		assertEquals(half, BigFraction.parse("2/4"));
		assertEquals(quarter2, BigFraction.parse(quarter2.toString()));
	}
}
