package integerType;

public class IntegerWrapper implements IntegerType {

	private final int value;
	
	public IntegerWrapper(int value) {
		this.value = value;
	}
	
	@Override
	public int compareTo(IntegerType o) {
		if (o.isStopCommand()) {
			return -1;
		}
		return this.value - ((IntegerWrapper)o).value;
	}

	@Override
	public boolean isStopCommand() {
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IntegerWrapper) {
			return this.value == ((IntegerWrapper)obj).value;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.value + "";
	}
}
