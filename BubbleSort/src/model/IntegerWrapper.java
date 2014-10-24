package model;

public class IntegerWrapper implements BufferEntry<IntegerWrapper> {

	private final int value;
	
	public IntegerWrapper(final int value) {
		this.value = value;
	}
	
	@Override
	public boolean isStopCommand() {
		return false;
	}

	@Override
	public int compareTo(IntegerWrapper o) {
		return this.value - o.value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IntegerWrapper) {
			IntegerWrapper objAsIntegerWrapper = (IntegerWrapper) obj;
			return this.value == objAsIntegerWrapper.value;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.value + "";
	}
}
