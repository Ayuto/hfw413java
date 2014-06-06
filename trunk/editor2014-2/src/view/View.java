package view;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import editor.Editor;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;

	private Editor editor;

	private JPanel jContentPane = null;

	private JSplitPane mainSplitPane = null;

	private JScrollPane textScrollPane = null;

	private JTextArea textArea = null;

	private JPanel keyPanel = null;


	/**
	 * This is the default constructor
	 */
	public View() {
		super();
		initialize();
	}

	public View(Editor facade) {
		super();
		this.editor = facade;
		initialize();
		this.refresh();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(810, 412);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("Editor");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getMainSplitPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes mainSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getMainSplitPane() {
		if (mainSplitPane == null) {
			mainSplitPane = new JSplitPane();
			mainSplitPane.setDividerSize(5);
			mainSplitPane.setDividerLocation(230);
			mainSplitPane.setBottomComponent(getKeyPanel());
			mainSplitPane.setTopComponent(getTextPanel());
			mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		}
		return mainSplitPane;
	}

	/**
	 * This method initializes textScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getTextScrollPane() {
		if (textScrollPane == null) {
			textScrollPane = new JScrollPane();
			textScrollPane.setViewportView(getTextArea());
		}
		return textScrollPane;
	}

	/**
	 * This method initializes textArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
		}
		return textArea;
	}

	/**
	 * This method initializes keyPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getKeyPanel() {
		if (keyPanel == null) {
			keyPanel = new JPanel();
			keyPanel.setLayout(new GridBagLayout());
			this.addButton(new LetterButton('Q'), 0, 0);
			this.addButton(new LetterButton('W'), 1, 0);
			this.addButton(new LetterButton('E'), 2, 0);
			this.addButton(new LetterButton('R'), 3, 0);
			this.addButton(new LetterButton('T'), 4, 0);
			this.addButton(new LetterButton('Z'), 5, 0);
			this.addButton(new LetterButton('U'), 6, 0);
			this.addButton(new LetterButton('I'), 7, 0);
			this.addButton(new LetterButton('O'), 8, 0);
			this.addButton(new LetterButton('P'), 9, 0);
			this.addButton(new LetterButton('Ü'), 10, 0);
			this.addButton(new LetterButton('+'), 11, 0);
			this.addDistance(12,0);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 13;
			gridBagConstraints.gridy = 0;
			keyPanel.add(this.getDeleteLeftButton(),gridBagConstraints);
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 14;
			gridBagConstraints.gridy = 0;
			keyPanel.add(this.getDeleteRightButton(),gridBagConstraints);
			
			this.addButton(new LetterButton('A'), 0, 1);
			this.addButton(new LetterButton('S'), 1, 1);
			this.addButton(new LetterButton('D'), 2, 1);
			this.addButton(new LetterButton('F'), 3, 1);
			this.addButton(new LetterButton('G'), 4, 1);
			this.addButton(new LetterButton('H'), 5, 1);
			this.addButton(new LetterButton('J'), 6, 1);
			this.addButton(new LetterButton('K'), 7, 1);
			this.addButton(new LetterButton('L'), 8, 1);
			this.addButton(new LetterButton('Ö'), 9, 1);
			this.addButton(new LetterButton('Ä'), 10, 1);
			this.addButton(new LetterButton('#'), 11, 1);
			this.addDistance(12,1);
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 13;
			gridBagConstraints.gridy = 1;
			keyPanel.add(this.getLeftButton(),gridBagConstraints);
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 14;
			gridBagConstraints.gridy = 1;
			keyPanel.add(this.getRightButton(),gridBagConstraints);

			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			keyPanel.add(this.getShiftButton1(),gridBagConstraints);
			this.addButton(new LetterButton('Y'), 1, 2);
			this.addButton(new LetterButton('X'), 2, 2);
			this.addButton(new LetterButton('C'), 3, 2);
			this.addButton(new LetterButton('V'), 4, 2);
			this.addButton(new LetterButton('B'), 5, 2);
			this.addButton(new LetterButton('N'), 6, 2);
			this.addButton(new LetterButton('M'), 7, 2);
			this.addButton(new LetterButton(','), 8, 2);
			this.addButton(new LetterButton('.'), 9, 2);
			this.addButton(new LetterButton('_'), 10, 2);
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 11;
			gridBagConstraints.gridy = 2;
			keyPanel.add(this.getShiftButton2(),gridBagConstraints);
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 13;
			gridBagConstraints.gridy = 2;
			keyPanel.add(this.getNewLineButton(),gridBagConstraints);
			
			this.addButton(new LetterButton(' '), 4, 3);
			this.addButton(new LetterButton(' '), 5, 3);
			this.addButton(new LetterButton(' '), 6, 3);
			this.addButton(new LetterButton(' '), 7, 3);
		}
		return keyPanel;
	}
	private JButton newLineButton = null;
	private JButton getNewLineButton() {
		if (this.newLineButton == null){
			this.newLineButton = new JButton();
			this.newLineButton.setText("<<<----");
			this.newLineButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					getEditor().newLine();
					refresh();
				}
			});
		}
		return newLineButton;
	}
	private JButton deleteRightButton = null;
	private JButton getDeleteRightButton() {
		if (this.deleteRightButton == null){
			this.deleteRightButton = new JButton();
			this.deleteRightButton.setText("Entf >>");
			this.deleteRightButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					getEditor().deleteRight();
					refresh();
				}
			});
		}
		return deleteRightButton;
	}
	private JButton deleteLeftButton = null;
	private JButton getDeleteLeftButton() {
		if (this.deleteLeftButton == null){
			this.deleteLeftButton = new JButton();
			this.deleteLeftButton.setText("<< Entf");
			this.deleteLeftButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					getEditor().deleteLeft();
					refresh();
				}
			});
		}
		return deleteLeftButton;
	}
	private JToggleButton shiftButton1 = null;
	private JToggleButton getShiftButton1() {
		if (this.shiftButton1 == null){
			this.shiftButton1 = new JToggleButton();
			this.shiftButton1.setText("^");
			this.shiftButton1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					shift_action(shiftButton1);
				}
			});
		}
		return shiftButton1;
	}
	protected void shift_action(JToggleButton shiftButton) {
		getEditor().shift();
		this.getShiftButton1().setSelected(shiftButton.isSelected());
		this.getShiftButton2().setSelected(shiftButton.isSelected());
		refresh();
	}
	private JToggleButton shiftButton2 = null;
	private JToggleButton getShiftButton2() {
		if (this.shiftButton2 == null){
			this.shiftButton2 = new JToggleButton();
			this.shiftButton2.setText("^");
			this.shiftButton2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					shift_action(shiftButton2);
				}
			});
		}
		return shiftButton2;
	}

	private JButton rightButton = null;
	private JButton getRightButton() {
		if (this.rightButton == null){
			this.rightButton = new JButton();
			this.rightButton.setText(">>>>>");
			this.rightButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					getEditor().right();
					refresh();
				}
			});
		}
		return rightButton;
	}
	private JButton leftButton = null;

	private JPanel textPanel = null;

	private JToolBar textToolBar = null;

	private JButton undoButton = null;

	private JButton redoButton = null;

	private JLabel sepratorLabel = null;

	private JButton copyButton = null;

	private JButton pasteButton = null;

	private JButton cutButton = null;
	private JButton getLeftButton() {
		if (this.leftButton == null){
			this.leftButton = new JButton();
			this.leftButton.setText("<<<<<");
			this.leftButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					getEditor().left();
					refresh();
				}
			});
		}
		return leftButton;
	}

	private void addDistance(int x, int y) {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		this.getKeyPanel().add(new JLabel("    "),gridBagConstraints);
	}

	private void addButton (KeyButton button, int x, int y){
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		this.getKeyPanel().add(button,gridBagConstraints);
	}

	@SuppressWarnings("serial")
	abstract class KeyButton extends JButton {
		
	}
	@SuppressWarnings("serial")
	class LetterButton extends KeyButton {
		
		public LetterButton(final Character c) {
			super();
			this.setText(c.toString());
			this.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					View.this.keyTyped(c);
				}
			});
		}
	}
	protected void keyTyped(char c) {
		this.getEditor().keyTyped(c);
		this.refresh();
	}

	private void refresh() {
		this.getTextArea().setText(this.getEditor().getEditorText());
		final int firstPosition = this.getEditor().getPosition();
		this.getTextArea().setCaretPosition(firstPosition);
		final int secondPosition = this.getEditor().getSecondPosition();
		this.getTextArea().moveCaretPosition(secondPosition);
		this.getTextArea().getCaret().setVisible(true);
		this.getTextArea().getCaret().setSelectionVisible(true);
		final boolean cutPasteEnabled = firstPosition != secondPosition;
		this.getCopyButton().setEnabled(cutPasteEnabled);
		this.getCutButton().setEnabled(cutPasteEnabled);
	}

	private Editor getEditor() {
		return this.editor;
	}

	/**
	 * This method initializes textPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTextPanel() {
		if (textPanel == null) {
			textPanel = new JPanel();
			textPanel.setLayout(new BorderLayout());
			textPanel.add(getTextScrollPane(), BorderLayout.CENTER);
			textPanel.add(getTextToolBar(), BorderLayout.NORTH);
		}
		return textPanel;
	}

	/**
	 * This method initializes textToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getTextToolBar() {
		if (textToolBar == null) {
			sepratorLabel = new JLabel();
			sepratorLabel.setText("          ");
			textToolBar = new JToolBar();
			textToolBar.add(getUndoButton());
			textToolBar.add(getRedoButton());
			textToolBar.add(sepratorLabel);
			textToolBar.add(getCopyButton());
			textToolBar.add(getCutButton());
			textToolBar.add(getPasteButton());
		}
		return textToolBar;
	}

	/**
	 * This method initializes undoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getUndoButton() {
		if (undoButton == null) {
			undoButton = new JButton();
			undoButton.setText("UNDO");
			undoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					undo_action();
				}
			});
		}
		return undoButton;
	}

	protected void undo_action() {
		this.getEditor().undo();
		this.refresh();
	}

	/**
	 * This method initializes redoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRedoButton() {
		if (redoButton == null) {
			redoButton = new JButton();
			redoButton.setText("REDO");
			redoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					redo_action();
				}
			});
		}
		return redoButton;
	}

	protected void redo_action() {
		this.getEditor().redo();
		this.refresh();
	}

	/**
	 * This method initializes copyButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCopyButton() {
		if (copyButton == null) {
			copyButton = new JButton();
			copyButton.setText("Kopieren");
			copyButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					copy_action();
				}
			});
		}
		return copyButton;
	}

	protected void copy_action() {
		this.getEditor().copy();
	}

	/**
	 * This method initializes pasteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPasteButton() {
		if (pasteButton == null) {
			pasteButton = new JButton();
			pasteButton.setText("Einfügen");
			pasteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					paste_action();
				}
			});
		}
		return pasteButton;
	}

	protected void paste_action() {
		this.getEditor().paste();
		this.refresh();
	}

	/**
	 * This method initializes cutButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCutButton() {
		if (cutButton == null) {
			cutButton = new JButton();
			cutButton.setText("Ausschneiden");
			cutButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cut_action();
				}
			});
		}
		return cutButton;
	}

	protected void cut_action() {
		this.getEditor().cut();
		this.refresh();
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"

