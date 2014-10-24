package model;

public class IntegerWrapper implements BufferEntry<IntegerWrapper> {

	private final int value;
	
	public IntegerWrapper(final int value) {
		this.value = value;
	}
	
	@Override
	public int compareTo(final IntegerWrapper o) {
		return this.value - o.value;
	}

	@Override
	public boolean isStopCommand() {
		return false;
	}

}
