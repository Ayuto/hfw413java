package model;

import visitor.Visitor;

/**
 * A singleton buffer entry which will end the process on which buffer it will
 * be written.
 */
public class StopCommand implements BufferEntry {

	private static StopCommand instance;

	/**
	 * Getter for the instance of the singleton stop command.
	 * 
	 * @return the instance of the singleton
	 */
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
