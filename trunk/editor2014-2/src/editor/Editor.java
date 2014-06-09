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
	private Stack<Command> undoHistory;
	private String copiedText = null;
	
	private Editor (){
		this.text = new StringBuffer();
		this.firstPosition = 0;
		this.secondPosition = 0;
		this.shiftMode = false;
		this.commandHistory = new Stack<Command>();
		this.undoHistory = new Stack<Command>();
	}
	
	private Stack<Command> getUndoHistory() {
		return this.undoHistory;
	}
	
	private Stack<Command> getCommandHistory() {
		return this.commandHistory;
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
		this.getUndoHistory().clear();
	}
	
	public void undo() {
		try {
			Command command = this.getCommandHistory().pop();
			command.undo();
			this.getUndoHistory().push(command);
		} catch (EmptyStackException e) {
			System.out.println("There is nothing to undo...");
		}
	}
	
	public void redo() {
		try {
			Command command = this.getUndoHistory().pop();
			command.redo();
			this.getCommandHistory().push(command);
		}
		catch(EmptyStackException e) {
			System.out.println("Nothing to redo...");
		}
	}
	
	public void restore(EditorState state) {
		this.text.replace(0, this.text.length(), state.textData);
		this.firstPosition = state.firstPositionData;
		this.secondPosition = state.secondPositionData;
		this.shiftMode = state.shiftModeData;
		this.copiedText = state.copiedTextData;
	}
	
	public void copy() {
		this.undoCommand(new CopyCommand());
	}
	
	public void cut() {
		this.undoCommand(new CutCommand());
	}
	
	public void paste() {
		this.undoCommand(new PasteCommand());
	}
	
	/**
	 * An abstract interface for all Commands.
	 */
	private abstract class Command {
		private boolean executed = false;
		
		protected abstract void executeMethod();
		protected abstract void undoMethod();
		
		/**
		 * Executes the command and sets a flag that this command has been executed.
		 */
		public void execute() {
			if (this.executed) 
				return;
			this.executeMethod();
			this.executed = true;
		}

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
					Editor.this.setFirstPosition(Editor.this.getFirstPosition() + 1);
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
	
	private class CopyCommand extends Command {
		private String oldCopiedText;
		
		@Override
		protected void executeMethod() {
			this.oldCopiedText = Editor.this.copiedText;
			int first, second;
			if (Editor.this.getFirstPosition() > Editor.this.getSecondPosition()) {
				first = Editor.this.getSecondPosition();
				second = Editor.this.getFirstPosition();
			}
			else {
				second = Editor.this.getSecondPosition();
				first = Editor.this.getFirstPosition();
			}
			Editor.this.copiedText = Editor.this.getText().substring(first, second);
		}
		
		@Override
		protected void undoMethod() {
			Editor.this.copiedText = this.oldCopiedText;
		}
	}
	
	private class CutCommand extends Command {
		EditorState oldState;
		
		@Override
		protected void executeMethod() {
			this.oldState = new EditorState(
					Editor.this.getEditorText(),
					Editor.this.getFirstPosition(),
					Editor.this.getSecondPosition(),
					Editor.this.getShiftMode(),
					Editor.this.copiedText
			);
			
			int first, second;
			if (Editor.this.getFirstPosition() > Editor.this.getSecondPosition()) {
				first = Editor.this.getSecondPosition();
				second = Editor.this.getFirstPosition();
			}
			else {
				second = Editor.this.getSecondPosition();
				first = Editor.this.getFirstPosition();
			}
			Editor.this.copiedText = Editor.this.getText().substring(first, second);
			Editor.this.text = Editor.this.text.delete(first, second);
			Editor.this.setPosition(first);
		}
		
		@Override
		protected void undoMethod() {
			Editor.this.restore(this.oldState);
		}
	}
	
	private class PasteCommand extends Command {
		EditorState oldState;
		@Override
		protected void executeMethod() {
			if (Editor.this.copiedText == null)
			{
				System.out.println("Nothing to paste...");
				return;
			}
			
			this.oldState = new EditorState(
					Editor.this.getEditorText(),
					Editor.this.getFirstPosition(),
					Editor.this.getSecondPosition(),
					Editor.this.getShiftMode(),
					Editor.this.copiedText
			);
			
			if (Editor.this.getFirstPosition() == Editor.this.getSecondPosition())
			{
				Editor.this.text.insert(Editor.this.getPosition(), Editor.this.copiedText);
			}
			else
			{
				Editor.this.text = Editor.this.text.replace(Editor.this.getFirstPosition(), Editor.this.getSecondPosition(), Editor.this.copiedText);
			}
			Editor.this.setPosition(Editor.this.getPosition() + Editor.this.copiedText.length());
		}
		
		@Override
		protected void undoMethod() {
			if (this.oldState == null)
				return;
			
			Editor.this.restore(this.oldState);
		}
	}
	
	private class EditorState {		
		private String textData;
		private int firstPositionData;
		private int secondPositionData;
		private boolean shiftModeData;
		private String copiedTextData;		
		
		private EditorState(String textData, int firstPositionData, int secondPositionData,
				boolean shiftModeData, String copiedTextData) {
			this.textData = textData;
			this.firstPositionData = firstPositionData;
			this.secondPositionData = secondPositionData;
			this.shiftModeData = shiftModeData;
			this.copiedTextData = copiedTextData;
		}
	}
}