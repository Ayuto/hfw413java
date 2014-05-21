package editor;

import java.util.EmptyStackException;
import java.util.Stack;

public class Editor {

	public static Editor createEditor() {
		return new Editor();
	}

	private final StringBuffer text;
	private int position;
	private boolean shiftMode;
	private Stack<Command> commandHistory;
	
	private Editor (){
		this.text = new StringBuffer();
		this.position = 0;
		this.shiftMode = false;
		this.commandHistory = new Stack<Command>();
	}
	public void keyTyped(final Character c) {
		Command command = new KeyTypedCommand(c);
		this.getCommandHistory().push(command);
		command.execute();
	}
	private StringBuffer getText() {
		return this.text;
	}
	public int getPosition() {
		return this.position;
	}
	private void setPosition(final int position) {
		this.position = position;
	}
	public void deleteLeft() {
		Command c = new DeleteLeftCommand();
		this.getCommandHistory().push(c);
		c.execute();
	}
	public void deleteRight() {
		Command c = new DeleteRightCommand();
		this.getCommandHistory().push(c);
		c.execute();
	}
	public void left() {
		Command c = new LeftCommand();
		this.getCommandHistory().push(c);
		c.execute();
	}
	public void right() {
		Command c = new RightCommand();
		this.getCommandHistory().push(c);
		c.execute();
	}

	public void newLine() {
		Command c = new NewLineCommand();
		this.getCommandHistory().push(c);
		c.execute();
	}
	public String getEditorText() {
		return this.getText().toString();
	}
	public void shift() {
		Command c = new ShiftCommand();
		this.getCommandHistory().push(c);
		c.execute();
	}
	private void setShiftMode(final boolean b) {
		this.shiftMode = b;
	}
	private boolean getShiftMode() {
		return this.shiftMode;
	}
	public void undo() {
		try {
			this.getCommandHistory().pop().undo();
		} catch (EmptyStackException e) {
			System.out.println("Undo nicht möglich, da noch keine Befehle ausgeführt wurden.");
		}
	}
	private Stack<Command> getCommandHistory() {
		return this.commandHistory;
	}
	/**
	 * An abstract interface for all Commands.
	 */
	private interface Command {
		/**
		 * Executes the command and sets a flag that this command has been executed.
		 */
		void execute();
		/**
		 * Undos the command if the command has been executed before.
		 */
		void undo();
	}
	/**
	 * A Command for a position movement to the left.
	 */
	private class LeftCommand implements Command {
		private boolean executed = false;
		@Override
		public void execute() {
			if (this.executed) return;
			this.executed = true;
			if (Editor.this.getPosition() > 0) Editor.this.setPosition(Editor.this.getPosition() - 1);
		}
		@Override
		public void undo() {
			if (!this.executed) return;
			new RightCommand().execute();
		}
	}
	/**
	 * A Command for a position movement to the right.
	 */
	private class RightCommand implements Command {
		private boolean executed = false;
		@Override
		public void execute() {
			if (this.executed) return;
			this.executed = true;
			if (Editor.this.getPosition() < Editor.this.getText().length()) Editor.this.setPosition(Editor.this.getPosition() + 1);
		}
		@Override
		public void undo() {
			if (!this.executed) return;
			new LeftCommand().execute();
		}
	}
	/**
	 * A Command for adding a new line to the text.
	 */
	private class NewLineCommand implements Command {
		private boolean executed = false;
		@Override
		public void execute() {
			if (this.executed) return;
			this.executed = true;
			Editor.this.getText().insert(Editor.this.getPosition(), "\n");
			Editor.this.setPosition(Editor.this.getPosition() + 1);
		}
		@Override
		public void undo() {
			if (!this.executed) return;
			new DeleteLeftCommand().execute();
		}
	}
	/**
	 * A Command for switching the shift mode.
	 */
	private class ShiftCommand implements Command {
		private boolean executed = false;
		@Override
		public void execute() {
			if (this.executed) return;
			this.executed = true;
			Editor.this.setShiftMode(!Editor.this.getShiftMode());
		}
		@Override
		public void undo() {
			if (!this.executed) return;
			this.executed = false;
			this.execute();
		}
	}
	/**
	 * A Command for adding a character to the text.
	 */
	private class KeyTypedCommand implements Command {
		private char c;
		private boolean executed = false;
		/**
		 * Creates a Command for adding the given character to the text.
		 * @param c Given character to be added to the text. 
		 */
		private KeyTypedCommand (char c) {
			this.c = c;
		}
		@Override
		public void execute() {
			if (this.executed) return;
			this.executed = true;
			Editor.this.getText().insert(Editor.this.getPosition(), Editor.this.getShiftMode()?this.c:Character.toLowerCase(this.c));
			Editor.this.setPosition(Editor.this.getPosition() + 1);
		}
		@Override
		public void undo() {
			if (!this.executed) return;
			new DeleteLeftCommand().execute();
		}
	}
	/**
	 * A Command for deleting the character to the left of the current position.
	 */
	private class DeleteLeftCommand implements Command {
		private char deleted = 0;
		private boolean executed = false;
		@Override
		public void execute() {
			if (this.executed) return;
			this.executed = true;
			this.deleted = Editor.this.getText().charAt(Editor.this.getPosition() - 1);
			Editor.this.getText().deleteCharAt(Editor.this.getPosition() - 1);
			Editor.this.setPosition(Editor.this.getPosition() - 1);
		}
		@Override
		public void undo() {
			if (!this.executed) return;
			new KeyTypedCommand(this.deleted).execute();
		}
	}
	/**
	 * A Command for deleting the character to the right of the current position.
	 */
	private class DeleteRightCommand implements Command {
		private boolean executed = false;
		private char deleted = 0;
		@Override
		public void execute() {
			if (this.executed) return;
			this.executed = true;
			this.deleted = Editor.this.getText().charAt(Editor.this.getPosition());
			Editor.this.getText().deleteCharAt(Editor.this.getPosition());
		}
		@Override
		public void undo() {
			if (! this.executed) return;
			new KeyTypedCommand(this.deleted).execute();
		}
	}
}