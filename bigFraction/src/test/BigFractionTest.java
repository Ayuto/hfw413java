package test;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import model.BigFraction;

import org.junit.Assert;
import org.junit.Test;

public class BigFractionTest {

	@Test
	public void test() {
		final BigFraction half = BigFraction.create(BigInteger.ONE,
				BigInteger.valueOf(2));
		final BigFraction quarter1 = BigFraction.create(BigInteger.valueOf(1),
				BigInteger.valueOf(4));
		final BigFraction quarter2 = BigFraction.create(BigInteger.valueOf(-2),
				BigInteger.valueOf(-8));
		final BigFraction quarterSquare = BigFraction.create(BigInteger.ONE,
				BigInteger.valueOf(16));

		assertEquals(quarter1, quarter2);

		assertEquals(quarterSquare, quarter1.multiply(quarter2));
		assertEquals("1/8", quarter1.multiply(half).toString());
		assertEquals(BigFraction.ZERO, half.multiply(BigFraction.ZERO));
		assertEquals(quarter1.multiply(quarter2), quarter2.multiply(quarter1));

		assertEquals("1/4", quarter1.toString());
		assertEquals("1/16", quarterSquare.toString());

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

		assertEquals(quarter1, BigFraction.parse("1/4"));
		assertEquals(quarterSquare, BigFraction.parse("2/32"));
		assertEquals(half, BigFraction.parse("2/4"));
		assertEquals(quarter2, BigFraction.parse(quarter2.toString()));

		assertEquals(quarter1, BigFraction.parse("0.25"));
		assertEquals("1/8", BigFraction.parse("0.125").toString());
		assertEquals(half, BigFraction.parse("0000.5000"));
		assertEquals(quarterSquare, BigFraction.parse(".0625"));

		assertEquals(quarter1, BigFraction.parse("25E2"));
		assertEquals("1/1000", BigFraction.parse("1E3").toString());
		assertEquals(half, BigFraction.parse("50E2"));
		assertEquals("1/8", BigFraction.parse("125E3").toString());

		assertEquals("1/1", quarter1.divide(quarter2).toString());
		assertEquals(quarter1, half.divide(BigFraction.create(
				BigInteger.valueOf(2), BigInteger.ONE)));
		assertEquals(BigFraction.create(BigInteger.valueOf(4), BigInteger.ONE),
				quarter1.divide(quarterSquare));
		assertEquals(quarterSquare, quarterSquare.divide(BigFraction.ONE));

		assertEquals(half, quarter1.add(quarter2));
		assertEquals("3/4", half.add(quarter2).toString());
		assertEquals(BigFraction.ONE, half.add(half));
		assertEquals("5/16", quarterSquare.add(quarter1).toString());

		assertEquals(BigFraction.ZERO, half.subtract(half));
		assertEquals(BigFraction.ONE,
				BigFraction.ONE.add(quarter1).subtract(quarter2));
		assertEquals("-1/4", quarter1.subtract(half).toString());
		assertEquals(quarterSquare, quarterSquare.subtract(BigFraction.ZERO));
	}
}
