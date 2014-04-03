package model;

import java.math.BigInteger;

public class BigFraction {
	
	private static final String FRACTION_SEPERATOR = "/";
	
	private final BigInteger enumerator;
	private final BigInteger denominator;

	/**
	 * Initializes a fraction.
	 */
	private BigFraction(final BigInteger enumerator, final BigInteger denominator) {
		this.enumerator = enumerator;
		this.denominator = denominator;
	}

	/**
	 * Parses the given string and returns a new BigFraction object.
	 */
	public static BigFraction parse(String str) {
		if (str.equals(null))
			throw new NumberFormatException();


		return null;
	}
	
	/**
	 * Returns the best (shortest) representation of the fraction <enumerator> divided by <denominator>.
	 * Returns an error if the denominator equals zero.
	 */
	public static BigFraction create(final BigInteger enumerator, final BigInteger denominator) {
		if (denominator.equals(BigInteger.ZERO))
			throw new Error();
		
		final BigInteger greatestCommonDivisor = enumerator.gcd(denominator);
		final BigInteger Normaliser = ! denominator.abs().equals(denominator) ? BigInteger.valueOf(-1) : BigInteger.ONE;
		final BigInteger normalisedEnumerator = enumerator.multiply(Normaliser);
		final BigInteger normalisedDenominator = denominator.multiply(Normaliser);
		return new BigFraction(normalisedEnumerator.divide(greatestCommonDivisor), normalisedDenominator.divide(greatestCommonDivisor));
	}
	
	/**
	 * Returns the enumerator.
	 */
	private BigInteger getEnumerator() {
		return this.enumerator;
	}

	/**
	 * Returns the denominator.
	 */
	private BigInteger getDenominator() {
		return this.denominator;
	}

	/**
	 * Returns true if the given object represents the same fraction.
	 */
	public boolean equals(Object argument) {
		if (argument instanceof BigFraction) {
			final BigFraction argumentAsBigFraction = (BigFraction) argument;
			return this.getEnumerator().multiply(argumentAsBigFraction.getDenominator())
				.equals(this.getDenominator().multiply(argumentAsBigFraction.getEnumerator()));
		}
		return false;
	}

	/**
	 * Multiplies <this> and <factor> and returns the result as a new object.
	 */
	public BigFraction multiply(BigFraction factor) {
		return BigFraction.create(this.getEnumerator().multiply(factor.getEnumerator()), 
								  this.getDenominator().multiply(factor.getDenominator()));
	}

	/**
	 * Stringifies the fraction.
	 */
	public String toString() {
		return this.getEnumerator().toString() + FRACTION_SEPERATOR + this.getDenominator().toString();
	}
}