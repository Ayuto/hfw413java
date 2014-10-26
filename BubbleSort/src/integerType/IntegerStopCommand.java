package integerType;

/**
 * Objects of this class indicate the end of a integer buffer.
 */
public class IntegerStopCommand implements IntegerType {

	private static IntegerStopCommand instance = null;

	/**
	 * Returns the singleton object of this class.
	 */
	public static IntegerStopCommand getInstance() {
		if (IntegerStopCommand.instance == null) {
			IntegerStopCommand.instance = new IntegerStopCommand();
		}
		return IntegerStopCommand.instance;
	}

	private IntegerStopCommand() {
	}

	@Override
	public int compareTo(IntegerType o) {
		return 1;
	}

	@Override
	public boolean isStopCommand() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Stop!";
	}
}
