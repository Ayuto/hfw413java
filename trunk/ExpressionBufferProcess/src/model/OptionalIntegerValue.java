package model;

import visitor.Visitor;

/**
 * A wrapper for an integer which also could be not a number because of an
 * exception while calculating.
 */
public class OptionalIntegerValue implements BufferEntry {

	private final int value;
	private final boolean nan;

	/**
	 * Constructor for an optional integer value with the given value.
	 * 
	 * @param value
	 *            given value for the optional integer value
	 */
	public OptionalIntegerValue(final int value) {
		this.value = value;
		this.nan = false;
	}

	/**
	 * Constructor for an optional integer value with the flag whether it is not
	 * a number and the default value. 
	 * Default value is zero.
	 * 
	 * @param nan
	 *            flag for the "not a number" state of the new optional integer
	 *            value.
	 */
	public OptionalIntegerValue(final boolean nan) {
		this.value = 0;
		this.nan = nan;
	}

	/**
	 * Default-Constructor for an optional integer value. 
	 * Default value is zero.
	 * The default optional integer value is not "not a number".
	 */
	public OptionalIntegerValue() {
		this.value = 0;
		this.nan = false;
	}

	/**
	 * Calculates the sum of the receiver and the argument.
	 * 
	 * @param arg
	 *            argument value.
	 * @return the sum of the receiver and the argument as a new optional
	 *         integer value.
	 */
	public OptionalIntegerValue add(final OptionalIntegerValue arg) {
		if (this.nan || arg.nan) {
			return new OptionalIntegerValue(true);
		}
		return new OptionalIntegerValue(this.value + arg.value);
	}

	/**
	 * Calculates the difference of the receiver and the argument.
	 * 
	 * @param arg
	 *            argument value.
	 * @return the difference of the receiver and the argument as a new optional
	 *         integer value.
	 */
	public OptionalIntegerValue sub(final OptionalIntegerValue arg) {
		if (this.nan || arg.nan) {
			return new OptionalIntegerValue(true);
		}
		return new OptionalIntegerValue(this.value - arg.value);
	}

	/**
	 * Calculates the product of the receiver and the argument.
	 * 
	 * @param arg
	 *            argument value.
	 * @return the product of the receiver and the argument as a new optional
	 *         integer value.
	 */
	public OptionalIntegerValue mul(final OptionalIntegerValue arg) {
		if (this.nan || arg.nan) {
			return new OptionalIntegerValue(true);
		}
		return new OptionalIntegerValue(this.value * arg.value);
	}

	/**
	 * Calculates the quotient of the receiver and the argument.
	 * 
	 * @param arg
	 *            argument value.
	 * @return the quotient of the receiver and the argument as a new optional
	 *         integer value.
	 */
	public OptionalIntegerValue div(final OptionalIntegerValue arg) {
		if (arg.value == 0 || this.nan || arg.nan) {
			return new OptionalIntegerValue(true);
		}
		return new OptionalIntegerValue(this.value / arg.value);
	}

	@Override
	public String toString() {
		if (this.nan) {
			return "This value is not a number!";
		}
		return "" + this.value;
	}

	@Override
	public void accept(final Visitor visitor) {
		visitor.handleOptionalIntegerValue(this);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof OptionalIntegerValue) {
			final OptionalIntegerValue value = (OptionalIntegerValue) obj;
			return (this.nan && value.nan)
					|| (!this.nan && !value.nan && this.value == value.value);
		}
		return false;
	}
}
