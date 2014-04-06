package model;

import java.math.BigInteger;

public class BigFraction {

	private static final String FRACTION_SEPERATOR = "/";
	private static final String DECIMAL_SEPERATOR = ".";
	private static final String DECIMAL_POW_SEPERATOR = "E";
	private static final int DECIMAL_BASE = 10;

	private final BigInteger enumerator;
	private final BigInteger denominator;

	/**
	 * Initializes a fraction.
	 */
	private BigFraction(final BigInteger enumerator,
			final BigInteger denominator) {
		this.enumerator = enumerator;
		this.denominator = denominator;
	}

	/**
	 * Parses the given string and returns a new BigFraction object. Throws a
	 * NumberFormatException if the given string is no BigFraction
	 * representation.
	 */
	public static BigFraction parse(final String str) {
		if (str == null)
			throw new NullPointerException();

		// Format [enumerator]/[denominator]
		if (str.contains(FRACTION_SEPERATOR)) {
			final String[] parts = str.split(FRACTION_SEPERATOR);
			if (parts.length != 2)
				throw new NumberFormatException();
			return BigFraction.create(new BigInteger(parts[0].trim()),
					new BigInteger(parts[1].trim()));
		}

		// Format decimal number e.g. 5.2135 or .125
		if (str.contains(DECIMAL_SEPERATOR)) {
			final int decimalpositions = (str.length() - 1)
					- str.indexOf(DECIMAL_SEPERATOR);
			return BigFraction.create(
					new BigInteger(str.replace(DECIMAL_SEPERATOR, "")),
					BigInteger.valueOf((long) Math.pow(DECIMAL_BASE,
							decimalpositions)));
		}

		// Format [number]E[-decimal exponent]
		if (str.contains(DECIMAL_POW_SEPERATOR)) {
			final String[] parts = str.split(DECIMAL_POW_SEPERATOR);
			if (parts.length != 2)
				throw new NumberFormatException();
			return BigFraction.create(
					new BigInteger(parts[0].trim()),
					BigInteger.valueOf((long) Math.pow(DECIMAL_BASE,
							Long.valueOf(parts[1].trim()))));
		}

		throw new NumberFormatException();
	}

	/**
	 * Returns the best (shortest) representation of the fraction <enumerator>
	 * divided by <denominator>. Returns an error if the denominator equals
	 * zero.
	 */
	public static BigFraction create(final BigInteger enumerator,
			final BigInteger denominator) {
		if (denominator.equals(BigInteger.ZERO))
			throw new Error();

		final BigInteger greatestCommonDivisor = enumerator.gcd(denominator);
		final BigInteger Normaliser = !denominator.abs().equals(denominator) ? BigInteger
				.valueOf(-1) : BigInteger.ONE;
		final BigInteger normalisedEnumerator = enumerator.multiply(Normaliser);
		final BigInteger normalisedDenominator = denominator
				.multiply(Normaliser);
		return new BigFraction(
				normalisedEnumerator.divide(greatestCommonDivisor),
				normalisedDenominator.divide(greatestCommonDivisor));
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
			return this
					.getEnumerator()
					.multiply(argumentAsBigFraction.getDenominator())
					.equals(this.getDenominator().multiply(
							argumentAsBigFraction.getEnumerator()));
		}
		return false;
	}

	/**
	 * Multiplies <this> and <factor> and returns the result as a new object.
	 */
	public BigFraction multiply(BigFraction factor) {
		return BigFraction.create(
				this.getEnumerator().multiply(factor.getEnumerator()), this
						.getDenominator().multiply(factor.getDenominator()));
	}

	/**
	 * Stringifies the fraction.
	 */
	public String toString() {
		return this.getEnumerator().toString() + FRACTION_SEPERATOR
				+ this.getDenominator().toString();
	}
}