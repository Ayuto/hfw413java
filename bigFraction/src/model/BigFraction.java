package model;

import java.math.BigInteger;

public class BigFraction {

	private static final String FRACTION_SEPERATOR = "/";
	private static final String DECIMAL_SEPERATOR = ".";
	private static final String DECIMAL_POW_SEPERATOR = "E";
	private static final int DECIMAL_BASE = 10;

	/**
	 * The BigFraction constant zero.
	 */
	public static final BigFraction ZERO = BigFraction.create(BigInteger.ZERO,
			BigInteger.ONE);
	
	/**
	 * The BigFraction constant one.
	 */
	public static final BigFraction ONE = BigFraction.create(BigInteger.ONE,
			BigInteger.ONE);

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
	 * Parses the given string <str> and returns a new BigFraction object. 
	 * Throws a NumberFormatException if the given string is no 
	 * BigFraction representation. 
	 * Throws a NullPointerException if the given string is null.
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

		// Format [number]E[negative decimal exponent]
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
	 * divided by <denominator>. 
	 * Throws an error if the denominator equals zero.
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
	 * Returns true if the given object <argument> represents the same fraction like <this>.
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
	 * Adds <value> to <this> and returns the result as a new object.
	 */
	public BigFraction add(BigFraction value) {
		return BigFraction.create(
				this.getEnumerator()
						.multiply(value.getDenominator())
						.add(value.getEnumerator().multiply(
								this.getDenominator())), this.getDenominator()
						.multiply(value.getDenominator()));
	}

	/**
	 * Subtracts <value> from <this> and returns the result as a new object.
	 */
	public BigFraction subtract(BigFraction value) {
		return BigFraction.create(
				this.getEnumerator()
						.multiply(value.getDenominator())
						.subtract(
								value.getEnumerator().multiply(
										this.getDenominator())), this
						.getDenominator().multiply(value.getDenominator()));
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
	 * Divides <this> by <divisor> and returns the result as a new object.
	 * Throws an IllegalArgumentException if the <divisor> equals zero.
	 */
	public BigFraction divide(BigFraction divisor) {
		if (divisor.equals(ZERO))
			throw new IllegalArgumentException("Divisor shouldn't be zero");
		return BigFraction.create(
				this.getEnumerator().multiply(divisor.getDenominator()), this
						.getDenominator().multiply(divisor.getEnumerator()));
	}

	/**
	 * Stringifies the fraction <this>.
	 */
	public String toString() {
		return this.getEnumerator().toString() + FRACTION_SEPERATOR
				+ this.getDenominator().toString();
	}
}