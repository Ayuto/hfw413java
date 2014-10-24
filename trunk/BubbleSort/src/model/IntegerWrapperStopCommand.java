package model;

public class IntegerWrapperStopCommand extends IntegerWrapper {

	public static IntegerWrapperStopCommand getInstance() {
		if (IntegerWrapperStopCommand.instance == null) {
			IntegerWrapperStopCommand.instance = new IntegerWrapperStopCommand();
		}
		return IntegerWrapperStopCommand.instance;
	}
	
	private static IntegerWrapperStopCommand instance;
	
	private IntegerWrapperStopCommand(){
		super(0);
	}
	
	@Override
	public int compareTo(final IntegerWrapper o) {
		return 1;
	}

	@Override
	public boolean isStopCommand() {
		return true;
	}

}
