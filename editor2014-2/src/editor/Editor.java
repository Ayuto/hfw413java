package editor;

import java.util.EmptyStackException;
import java.util.Stack;

public class Editor {

	public static Editor createEditor() {
		return new Editor();
	}

	private StringBuffer text;
	private int firstPosition;
	private int secondPosition;
	private boolean shiftMode;
	private Stack<Command> commandHistory;
	private Stack<Command> redoHistory;
	private String copiedText = null;
	
	private Editor (){
		this.text = new StringBuffer();
		this.firstPosition = 0;
		this.secondPosition = 0;
		this.shiftMode = false;
		this.commandHistory = new Stack<Command>();
		this.redoHistory = new Stack<Command>();
	}
	private Stack<Command> getRedoHistory() {
		return this.redoHistory;
	}
	private StringBuffer getText() {
		return this.text;
	}
	public int getPosition() {
		return this.getFirstPosition();
	}
	private int getFirstPosition() {
		return this.firstPosition;
	}
	private void setPosition(int position) {
		this.setFirstPosition(position);
		this.setSecondPosition(position);
	}
	private void setFirstPosition(int position) {
		this.firstPosition = position;
	}
	public int getSecondPosition() {
		return this.secondPosition;
	}
	private void setSecondPosition(int position) {
		this.secondPosition = position;
	}
	public String getEditorText() {
		return this.getText().toString();
	}
	public void keyTyped(final Character c) {
		this.undoCommand(new KeyTypedCommand(c));
	}
	public void deleteLeft() {
		this.undoCommand(new DeleteLeftCommand());
	}
	public void deleteRight() {
		this.undoCommand(new DeleteRightCommand());
	}
	public void left() {
		this.undoCommand(new LeftCommand());
	}
	public void right() {
		this.undoCommand(new RightCommand());
	}
	public void newLine() {
		this.undoCommand(new NewLineCommand());
	}
	public void shift() {
		this.undoCommand(new ShiftCommand());
	}
	private void setShiftMode(boolean b) {
		this.shiftMode = b;
	}
	private boolean getShiftMode() {
		return this.shiftMode;
	}
	public void undoCommand(Command command)
	{
		this.getCommandHistory().push(command);
		command.execute();
		this.getRedoHistory().clear();
	}

	public void undo() {
		try {
			Command command = this.getCommandHistory().pop();
			command.undo();
			this.getRedoHistory().push(command);
		} catch (EmptyStackException e) {
			System.out.println("There is nothing to undo...");
		}
	}
	public void redo() {
		try {
			Command command = this.getRedoHistory().pop();
			command.redo();
			this.getCommandHistory().push(command);
		}
		catch(EmptyStackException e) {
			System.out.println("Nothing to redo...");
		}
	}
	public void copy() {
		this.copiedText = this.getText().substring(this.getSecondPosition(), this.getFirstPosition());
	}
	public void cut() {
		this.copiedText = this.getText().substring(this.getFirstPosition(), this.getSecondPosition());
		this.text = this.text.delete(this.getFirstPosition(), this.getSecondPosition());
		this.setPosition(this.getFirstPosition());
	}
	public void paste() {
		if (this.copiedText == null)
		{
			System.out.println("Nothing to paste...");
			return;
		}
		
		if (this.getFirstPosition() == this.getSecondPosition())
		{
			this.text.insert(this.getPosition(), this.copiedText);
		}
		else
		{
			this.text = this.text.replace(this.getFirstPosition(), this.getSecondPosition(), this.copiedText);
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
		
		public void redo() {
			this.execute();
		}
		protected abstract void undoMethod();
	}
	/**
	 * A Command for a position movement to the left.
	 */
	private class LeftCommand extends Command {
		@Override
		protected void executeMethod() {
			if (Editor.this.getPosition() > 0) {
				if (Editor.this.getShiftMode()){
					Editor.this.setFirstPosition(Editor.this.getFirstPosition() - 1);
				}else{
					Editor.this.setPosition(Editor.this.getPosition() - 1);
				}
			}
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
			if (Editor.this.getPosition() < Editor.this.getText().length()) {
				if (Editor.this.getShiftMode()){
					Editor.this.setSecondPosition(Editor.this.getSecondPosition() + 1);
				}else{
					Editor.this.setPosition(Editor.this.getPosition() + 1);
				}
			}
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