package integerType;


public class IntegerStopCommand implements IntegerType {

	private static IntegerStopCommand instance = null;
	
	public static IntegerStopCommand getInstance() {
		if (IntegerStopCommand.instance == null) {
			IntegerStopCommand.instance = new IntegerStopCommand();
		}
		return IntegerStopCommand.instance;
	}
	
	private IntegerStopCommand(){
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
