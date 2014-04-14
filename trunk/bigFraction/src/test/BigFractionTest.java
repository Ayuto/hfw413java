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
		this.half = BigFraction.create(BigInteger.ONE, BigInteger.valueOf(2));

		this.half2 = BigFraction.create(BigInteger.valueOf(2),
				BigInteger.valueOf(4));

		this.quarter1 = BigFraction.create(BigInteger.valueOf(1),
				BigInteger.valueOf(4));

		this.quarter2 = BigFraction.create(BigInteger.valueOf(-2),
				BigInteger.valueOf(-8));

		this.quarterSquare = BigFraction.create(BigInteger.ONE,
				BigInteger.valueOf(16));
	}

	@Test
	public void equalsTest() {
		assertEquals(this.quarter1, this.quarter2);
		assertEquals(this.half, this.half2);
		assertEquals(BigFraction.parse("5"), BigFraction.parse("5E0"));
	}

	@Test
	public void multiplyTest() {
		assertEquals(this.quarterSquare, this.quarter1.multiply(this.quarter2));
		assertEquals("1/8", this.quarter1.multiply(this.half).toString());
		assertEquals(BigFraction.ZERO, this.half.multiply(BigFraction.ZERO));
		assertEquals(this.quarter1.multiply(this.quarter2),
				this.quarter2.multiply(this.quarter1));
	}

	@Test
	public void toStringTest() {
		assertEquals("1/4", this.quarter1.toString());
		assertEquals("1/16", this.quarterSquare.toString());
		assertEquals("1/2", this.half.toString());
	}

	@Test
	public void parseExceptionTest() {
		try {
			BigFraction.parse("Hugo ist doof!");
			Assert.fail();
		} catch (final NumberFormatException e) {
		}

		try {
			BigFraction.parse("");
			Assert.fail();
		} catch (final NumberFormatException e) {
		}

		try {
			BigFraction.parse(null);
			Assert.fail();
		} catch (final NullPointerException e) {
		}
	}

	@Test
	public void parseFractionSeperatorTest() {
		// <enumerator>/<denominator>
		assertEquals(this.quarter1, BigFraction.parse("1/4"));
		assertEquals(this.quarterSquare, BigFraction.parse("2/32"));
		assertEquals(this.half, BigFraction.parse("2/4"));
		assertEquals(this.quarter2, BigFraction.parse(this.quarter2.toString()));

		// /<denominator>
		assertEquals(BigFraction.parse("/10"), BigFraction.parse("1/10"));
		assertEquals(BigFraction.parse("/213"), BigFraction.parse("1/213"));
		assertEquals(BigFraction.parse("/-10"), BigFraction.parse("-1/10"));
	}

	@Test
	public void parseDecimalSeperatorTest() {
		assertEquals(this.quarter1, BigFraction.parse("0.25"));
		assertEquals("1/8", BigFraction.parse("0.125").toString());
		assertEquals(this.half, BigFraction.parse("0000.5000"));
		assertEquals(this.quarterSquare, BigFraction.parse(".0625"));
	}

	@Test
	public void parseDecimalPowSeperatorTest() {
		assertEquals(BigFraction.parse("100000"), BigFraction.parse("E5"));
		assertEquals(BigFraction.parse("1/100000"), BigFraction.parse("E-5"));

		assertEquals(this.quarter1, BigFraction.parse("25E-2"));
		assertEquals("1/1000", BigFraction.parse("1E-3").toString());
		assertEquals(this.half, BigFraction.parse("50E-2"));
		assertEquals("1/8", BigFraction.parse("125E-3").toString());
		assertEquals(BigFraction.parse("100/1"), BigFraction.parse("E2"));
	}

	@Test
	public void parseNumberTest() {
		assertEquals(BigFraction.parse("10"), BigFraction.parse("10/1"));
		assertEquals(BigFraction.parse("15"), BigFraction.parse("15/1"));
		assertEquals(BigFraction.parse("-10"), BigFraction.parse("-10/1"));
		assertEquals(BigFraction.ZERO, BigFraction.parse("0"));
	}

	@Test
	public void devideTest() {
		assertEquals("1/1", this.quarter1.divide(this.quarter2).toString());
		assertEquals(this.quarter1, this.half.divide(BigFraction.create(
				BigInteger.valueOf(2), BigInteger.ONE)));
		assertEquals(BigFraction.create(BigInteger.valueOf(4), BigInteger.ONE),
				this.quarter1.divide(this.quarterSquare));
		assertEquals(this.quarterSquare,
				this.quarterSquare.divide(BigFraction.ONE));
	}

	@Test
	public void addTest() {
		assertEquals(this.half, this.quarter1.add(this.quarter2));
		assertEquals("3/4", this.half.add(this.quarter2).toString());
		assertEquals(BigFraction.ONE, this.half.add(this.half));
		assertEquals("5/16", this.quarterSquare.add(this.quarter1).toString());
	}

	@Test
	public void subtractTest() {
		assertEquals(BigFraction.ZERO, this.half.subtract(this.half));
		assertEquals(BigFraction.ONE, BigFraction.ONE.add(this.quarter1)
				.subtract(this.quarter2));
		assertEquals("-1/4", this.quarter1.subtract(this.half).toString());
		assertEquals(this.quarterSquare,
				this.quarterSquare.subtract(BigFraction.ZERO));
	}
}
