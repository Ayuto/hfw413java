package view;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JLabel;

import expressions.RegularExpression;

import parser.RegularExpressionParser;
import parser.RegularExpressionParserException;

@SuppressWarnings("serial")
public class View extends JFrame {

	private JPanel jContentPane = null;
	private JSplitPane mainSplitPane = null;
	private JPanel expressionPanel = null;
	private JPanel inputPanel = null;
	private JScrollPane expressionScrollPane = null;
	private JTextArea expressionTextArea = null;
	private JToolBar expressionToolBar = null;
	private JButton checkButton = null;
	private JLabel expressionToolbarDistanceLabel = null;
	private JLabel expressionStatusLabel = null;
	private JScrollPane inputScrollPane = null;
	private JTextArea inputTextArea = null;
	private JToolBar inputToolBar = null;
	private JButton inputCheckButton = null;
	private JLabel inputDistanceLabel = null;
	private JLabel inputStatusLabel = null;
	private RegularExpression expression;

	/**
	 * This is the default constructor
	 */
	public View() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(626, 328);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("Reguläre Ausdrücke");
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
			jContentPane.add(getMainSplitPane(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}

	public static View create() {
		return new View();
	}

	/**
	 * This method initializes mainSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getMainSplitPane() {
		if (mainSplitPane == null) {
			mainSplitPane = new JSplitPane();
			mainSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
			mainSplitPane.setTopComponent(getExpressionPanel());
			mainSplitPane.setBottomComponent(getInputPanel());
			mainSplitPane.setDividerLocation(130);
		}
		return mainSplitPane;
	}

	/**
	 * This method initializes expressionPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getExpressionPanel() {
		if (expressionPanel == null) {
			expressionPanel = new JPanel();
			expressionPanel.setLayout(new BorderLayout());
			expressionPanel.add(getExpressionScrollPane(), java.awt.BorderLayout.CENTER);
			expressionPanel.add(getExpressionToolBar(), java.awt.BorderLayout.SOUTH);
		}
		return expressionPanel;
	}

	/**
	 * This method initializes inputPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getInputPanel() {
		if (inputPanel == null) {
			inputPanel = new JPanel();
			inputPanel.setLayout(new BorderLayout());
			inputPanel.add(getInputScrollPane(), java.awt.BorderLayout.CENTER);
			inputPanel.add(getInputToolBar(), java.awt.BorderLayout.SOUTH);
		}
		return inputPanel;
	}

	/**
	 * This method initializes expressionScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getExpressionScrollPane() {
		if (expressionScrollPane == null) {
			expressionScrollPane = new JScrollPane();
			expressionScrollPane.setViewportView(getExpressionTextArea());
		}
		return expressionScrollPane;
	}

	/**
	 * This method initializes expressionTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getExpressionTextArea() {
		if (expressionTextArea == null) {
			expressionTextArea = new JTextArea();
		}
		return expressionTextArea;
	}

	/**
	 * This method initializes expressionToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getExpressionToolBar() {
		if (expressionToolBar == null) {
			expressionStatusLabel = new JLabel();
			expressionStatusLabel.setText("");
			expressionToolbarDistanceLabel = new JLabel();
			expressionToolbarDistanceLabel.setText("    ");
			expressionToolBar = new JToolBar();
			expressionToolBar.add(getCheckButton());
			expressionToolBar.add(expressionToolbarDistanceLabel);
			expressionToolBar.add(expressionStatusLabel);
		}
		return expressionToolBar;
	}

	/**
	 * This method initializes checkButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCheckButton() {
		if (checkButton == null) {
			checkButton = new JButton();
			checkButton.setText("Ausdruck prüfen");
			checkButton.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(final java.awt.event.ActionEvent e) {
					checkExpression_action();
				}
			});
		}
		return checkButton;
	}

	protected void checkExpression_action() {
		try {
			this.setRegularExpression(RegularExpressionParser.create(getExpressionTextArea().getText()).parse());
			this.resetExpressionStatus();
		}catch (final RegularExpressionParserException e){
			this.setExpressionStatus(e.getMessage());
		}
	}

	private void setExpressionStatus(final String message) {
		this.expressionStatusLabel.setText(message);
	}

	private void resetExpressionStatus() {
		this.expressionStatusLabel.setText("");
	}

	private void setRegularExpression(final RegularExpression expression) {
		this.expression = expression;
	}

	/**
	 * This method initializes inputScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getInputScrollPane() {
		if (inputScrollPane == null) {
			inputScrollPane = new JScrollPane();
			inputScrollPane.setViewportView(getInputTextArea());
		}
		return inputScrollPane;
	}

	/**
	 * This method initializes inputTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getInputTextArea() {
		if (inputTextArea == null) {
			inputTextArea = new JTextArea();
		}
		return inputTextArea;
	}

	/**
	 * This method initializes inputToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getInputToolBar() {
		if (inputToolBar == null) {
			inputStatusLabel = new JLabel();
			inputStatusLabel.setText("");
			inputDistanceLabel = new JLabel();
			inputDistanceLabel.setText("    ");
			inputToolBar = new JToolBar();
			inputToolBar.add(getInputCheckButton());
			inputToolBar.add(inputDistanceLabel);
			inputToolBar.add(inputStatusLabel);
		}
		return inputToolBar;
	}

	/**
	 * This method initializes inputCheckButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInputCheckButton() {
		if (inputCheckButton == null) {
			inputCheckButton = new JButton();
			inputCheckButton.setText("Eingabe prüfen");
			inputCheckButton.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(final java.awt.event.ActionEvent e) {
					checkInput_action();
				}
			});
		}
		return inputCheckButton;
	}

	protected void checkInput_action() {
		if (this.getExpression() == null){
			this.setInputStatus("Ein geprüfter Ausdruck ist Voraussetzung!");
			return;			
		}
		if (this.getExpression().check(this.getInputTextArea().getText())){
			this.setInputStatus("O.K.");					
		}else{
			this.setInputStatus("Nicht O.K.");

		}
	}

	private RegularExpression getExpression() {
		return this.expression;
	}

	private void setInputStatus(final String message) {
		this.inputStatusLabel.setText(message);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
