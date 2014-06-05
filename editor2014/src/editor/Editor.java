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
			System.out.println("There is nothing to undo...");
		}
	}
	private Stack<Command> getCommandHistory() {
		return this.commandHistory;
	}
	/**
	 * An abstract interface for all Commands.
	 */
	private abstract class Command {
		private boolean executed = false;
		/**
		 * Executes the command and sets a flag that this command has been executed.
		 */
		public void execute() {
			if (this.executed) 
				return;
			this.executeMethod();
			this.executed = true;
		}
		protected abstract void executeMethod();
		/**
		 * Undos the command if the command has been executed before.
		 */
		public void undo() {
			if (!this.executed)
				return;
			this.undoMethod();
			this.executed = false;
		}
		protected abstract void undoMethod();
	}
	/**
	 * A Command for a position movement to the left.
	 */
	private class LeftCommand extends Command {
		@Override
		protected void executeMethod() {
			if (Editor.this.getPosition() > 0) Editor.this.setPosition(Editor.this.getPosition() - 1);
		}
		@Override
		protected void undoMethod() {
			new RightCommand().execute();
		}
	}
	/**
	 * A Command for a position movement to the right.
	 */
	private class RightCommand extends Command {
		@Override
		protected void executeMethod() {
			if (Editor.this.getPosition() < Editor.this.getText().length()) Editor.this.setPosition(Editor.this.getPosition() + 1);
		}
		@Override
		protected void undoMethod() {
			new LeftCommand().execute();
		}
	}
	/**
	 * A Command for adding a new line to the text.
	 */
	private class NewLineCommand extends Command {
		@Override
		protected void executeMethod() {
			Editor.this.getText().insert(Editor.this.getPosition(), "\n");
			Editor.this.setPosition(Editor.this.getPosition() + 1);
		}
		@Override
		protected void undoMethod() {
			new DeleteLeftCommand().execute();
		}
	}
	/**
	 * A Command for switching the shift mode.
	 */
	private class ShiftCommand extends Command {
		@Override
		protected void executeMethod() {
			Editor.this.setShiftMode(!Editor.this.getShiftMode());
		}
		@Override
		protected void undoMethod() {
			Editor.this.setShiftMode(!Editor.this.getShiftMode());
		}
	}
	/**
	 * A Command for adding a character to the text.
	 */
	private class KeyTypedCommand extends Command {
		private char c;
		/**
		 * Creates a Command for adding the given character to the text.
		 * @param c Given character to be added to the text. 
		 */
		private KeyTypedCommand (char c) {
			this.c = c;
		}
		@Override
		protected void executeMethod() {
			Editor.this.getText().insert(Editor.this.getPosition(), Editor.this.getShiftMode()?this.c:Character.toLowerCase(this.c));
			Editor.this.setPosition(Editor.this.getPosition() + 1);
		}
		@Override
		protected void undoMethod() {
			new DeleteLeftCommand().execute();
		}
	}
	/**
	 * A Command for deleting the character to the left of the current position.
	 */
	private class DeleteLeftCommand extends Command {
		private char deleted = 0;
		@Override
		protected void executeMethod() {
			this.deleted = Editor.this.getText().charAt(Editor.this.getPosition() - 1);
			Editor.this.getText().deleteCharAt(Editor.this.getPosition() - 1);
			Editor.this.setPosition(Editor.this.getPosition() - 1);
		}
		@Override
		protected void undoMethod() {
			new KeyTypedCommand(this.deleted).execute();
		}
	}
	/**
	 * A Command for deleting the character to the right of the current position.
	 */
	private class DeleteRightCommand extends Command {
		private char deleted = 0;
		@Override
		protected void executeMethod() {
			this.deleted = Editor.this.getText().charAt(Editor.this.getPosition());
			Editor.this.getText().deleteCharAt(Editor.this.getPosition());
		}
		@Override
		protected void undoMethod() {
			new KeyTypedCommand(this.deleted).execute();
			new LeftCommand().execute();
			}
	}
}