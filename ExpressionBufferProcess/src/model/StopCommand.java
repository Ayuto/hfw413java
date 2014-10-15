package model;

import visitor.Visitor;

public class StopCommand implements BufferEntry {

	private static StopCommand instance;

	public static StopCommand getInstance() {
		if (StopCommand.instance == null) {
			StopCommand.instance = new StopCommand();
		}
		return StopCommand.instance;
	}
	
	private StopCommand() {
	}

	@Override
	public void accept(final Visitor visitor) {
		visitor.handleStopCommand();
	}
}
