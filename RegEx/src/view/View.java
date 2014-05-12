package view;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

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
		this.initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(626, 328);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setContentPane(this.getJContentPane());
		this.setTitle("Reguläre Ausdrücke");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (this.jContentPane == null) {
			this.jContentPane = new JPanel();
			this.jContentPane.setLayout(new BorderLayout());
			this.jContentPane.add(this.getMainSplitPane(), java.awt.BorderLayout.CENTER);
		}
		return this.jContentPane;
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
		if (this.mainSplitPane == null) {
			this.mainSplitPane = new JSplitPane();
			this.mainSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
			this.mainSplitPane.setTopComponent(this.getExpressionPanel());
			this.mainSplitPane.setBottomComponent(this.getInputPanel());
			this.mainSplitPane.setDividerLocation(130);
		}
		return this.mainSplitPane;
	}

	/**
	 * This method initializes expressionPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getExpressionPanel() {
		if (this.expressionPanel == null) {
			this.expressionPanel = new JPanel();
			this.expressionPanel.setLayout(new BorderLayout());
			this.expressionPanel.add(this.getExpressionScrollPane(), java.awt.BorderLayout.CENTER);
			this.expressionPanel.add(this.getExpressionToolBar(), java.awt.BorderLayout.SOUTH);
		}
		return this.expressionPanel;
	}

	/**
	 * This method initializes inputPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getInputPanel() {
		if (this.inputPanel == null) {
			this.inputPanel = new JPanel();
			this.inputPanel.setLayout(new BorderLayout());
			this.inputPanel.add(this.getInputScrollPane(), java.awt.BorderLayout.CENTER);
			this.inputPanel.add(this.getInputToolBar(), java.awt.BorderLayout.SOUTH);
		}
		return this.inputPanel;
	}

	/**
	 * This method initializes expressionScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getExpressionScrollPane() {
		if (this.expressionScrollPane == null) {
			this.expressionScrollPane = new JScrollPane();
			this.expressionScrollPane.setViewportView(this.getExpressionTextArea());
		}
		return this.expressionScrollPane;
	}

	/**
	 * This method initializes expressionTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getExpressionTextArea() {
		if (this.expressionTextArea == null) {
			this.expressionTextArea = new JTextArea();
		}
		return this.expressionTextArea;
	}

	/**
	 * This method initializes expressionToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getExpressionToolBar() {
		if (this.expressionToolBar == null) {
			this.expressionStatusLabel = new JLabel();
			this.expressionStatusLabel.setText("");
			this.expressionToolbarDistanceLabel = new JLabel();
			this.expressionToolbarDistanceLabel.setText("    ");
			this.expressionToolBar = new JToolBar();
			this.expressionToolBar.add(this.getCheckButton());
			this.expressionToolBar.add(this.expressionToolbarDistanceLabel);
			this.expressionToolBar.add(this.expressionStatusLabel);
		}
		return this.expressionToolBar;
	}

	/**
	 * This method initializes checkButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCheckButton() {
		if (this.checkButton == null) {
			this.checkButton = new JButton();
			this.checkButton.setText("Ausdruck prüfen");
			this.checkButton.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(final java.awt.event.ActionEvent e) {
					View.this.checkExpression_action();
				}
			});
		}
		return this.checkButton;
	}

	protected void checkExpression_action() {
		try {
			this.setRegularExpression(RegularExpressionParser.create(this.getExpressionTextArea().getText()).parse());
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
		if (this.inputScrollPane == null) {
			this.inputScrollPane = new JScrollPane();
			this.inputScrollPane.setViewportView(this.getInputTextArea());
		}
		return this.inputScrollPane;
	}

	/**
	 * This method initializes inputTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getInputTextArea() {
		if (this.inputTextArea == null) {
			this.inputTextArea = new JTextArea();
		}
		return this.inputTextArea;
	}

	/**
	 * This method initializes inputToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getInputToolBar() {
		if (this.inputToolBar == null) {
			this.inputStatusLabel = new JLabel();
			this.inputStatusLabel.setText("");
			this.inputDistanceLabel = new JLabel();
			this.inputDistanceLabel.setText("    ");
			this.inputToolBar = new JToolBar();
			this.inputToolBar.add(this.getInputCheckButton());
			this.inputToolBar.add(this.inputDistanceLabel);
			this.inputToolBar.add(this.inputStatusLabel);
		}
		return this.inputToolBar;
	}

	/**
	 * This method initializes inputCheckButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInputCheckButton() {
		if (this.inputCheckButton == null) {
			this.inputCheckButton = new JButton();
			this.inputCheckButton.setText("Eingabe prüfen");
			this.inputCheckButton.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(final java.awt.event.ActionEvent e) {
					View.this.checkInput_action();
				}
			});
		}
		return this.inputCheckButton;
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
