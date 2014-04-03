package test;


import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import model.BigFraction;

import org.junit.Test;

public class BigFractionTest {

	@Test
	public void test() {
		final BigFraction quarter1 = BigFraction.create(BigInteger.valueOf(1), BigInteger.valueOf(4));
		final BigFraction quarter2 = BigFraction.create(BigInteger.valueOf(-2), BigInteger.valueOf(-8));
		assertEquals(quarter1, quarter2);
		
		final BigFraction quarterSquare = BigFraction.create(BigInteger.ONE, BigInteger.valueOf(16));
		//assertEquals(BigFraction.parse("1/16"), quarter1.multiply(quarter2));
		assertEquals("1/4", quarter1.toString());
		assertEquals("1/16", quarterSquare.toString());
	}
}
