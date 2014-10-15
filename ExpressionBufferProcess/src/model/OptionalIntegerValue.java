package model;

import visitor.Visitor;

public class OptionalIntegerValue implements BufferEntry {

	private final int value;
	private final boolean nan;
	
	public OptionalIntegerValue (final int value) {
		this.value = value;
		this.nan = false;
	}
	
	public OptionalIntegerValue(final boolean nan) {
		this.value = 0;
		this.nan = nan;
	}
	
	public OptionalIntegerValue() {
		this.value = 0;
		this.nan = false;
	}
	
	public OptionalIntegerValue add(final OptionalIntegerValue arg) {
		if (this.nan || arg.nan) {
			return new OptionalIntegerValue(true);
		}
		return new OptionalIntegerValue(this.value + arg.value);
	}
	
	public OptionalIntegerValue sub(final OptionalIntegerValue arg) {
		if (this.nan || arg.nan) {
			return new OptionalIntegerValue(true);
		}
		return new OptionalIntegerValue(this.value - arg.value);
	}
	
	public OptionalIntegerValue mul(final OptionalIntegerValue arg) {
		if (this.nan || arg.nan) {
			return new OptionalIntegerValue(true);
		}
		return new OptionalIntegerValue(this.value * arg.value);
	}
	
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
			return (this.nan && value.nan) || (!this.nan && !value.nan && this.value == value.value);
		}
		return false;
	}
}
