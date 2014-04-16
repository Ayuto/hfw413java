package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import model.ExpressionFacade;
import model.Expression;
import model.Variable;

import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class View extends JFrame {

	private final ExpressionFacade facade;
	private Expression firstArgument;
	private Expression secondArgument;

	private JPanel jContentPane = null;

	private JScrollPane cellScrollPane = null;

	private JList<Expression> cellList = null;

	private JToolBar toolBar = null;

	private JLabel arg1Label = null;

	private JTextField argument1TextField = null;

	private JLabel argument2Label = null;

	private JTextField argument2TextField = null;

	private JButton addButton = null;

	private JButton subtractButton = null;

	private JButton multiplyButton = null;

	private JButton divideButton = null;

	private JButton select1Button = null;

	private JButton select2Button = null;

	private JToolBar variableToolBar = null;

	private JLabel variableLabel = null;

	private JTextField variableTextField = null;

	private JButton variableCreateButton = null;

	private JSplitPane mainSplitPane = null;

	private JPanel variablePanel = null;

	private JPanel cellPanel = null;

	private JScrollPane variableScrollPane = null;

	private JList<Variable> variableList = null;

	private JToolBar upDownToolBar = null;

	private JButton upButton = null;

	private JButton downButton = null;

	private JLabel variableStatusLabel = null;

	private JLabel expressionStatusLabel = null;

	/**
	 * This is the default constructor
	 */
	public View(final ExpressionFacade facade) {
		super();
		this.facade = facade;
		initialize();
		this.refresh();
	}

	private ExpressionFacade getFacade() {
		return this.facade;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(737, 531);
		this.setContentPane(getJContentPane());
		this.setTitle("Calculator");
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
			jContentPane.add(getToolBar(), BorderLayout.SOUTH);
			jContentPane.add(getVariableToolBar(), BorderLayout.NORTH);
			jContentPane.add(getMainSplitPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes cellScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getCellScrollPane() {
		if (cellScrollPane == null) {
			cellScrollPane = new JScrollPane();
			cellScrollPane.setViewportView(getCellList());
		}
		return cellScrollPane;
	}

	/**
	 * This method initializes cellList
	 * 
	 * @return javax.swing.JList
	 */
	private JList<Expression> getCellList() {
		if (cellList == null) {
			cellList = new JList<Expression>();
		}
		return cellList;
	}

	/**
	 * This method initializes toolBar
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getToolBar() {
		if (toolBar == null) {
			expressionStatusLabel = new JLabel();
			expressionStatusLabel.setText("");
			argument2Label = new JLabel();
			argument2Label.setText(" Argument2:");
			arg1Label = new JLabel();
			arg1Label.setText("Argument1: ");
			toolBar = new JToolBar();
			toolBar.add(arg1Label);
			toolBar.add(getArgument1TextField());
			toolBar.add(getSelect1Button());
			toolBar.add(argument2Label);
			toolBar.add(getArgument2TextField());
			toolBar.add(getSelect2Button());
			toolBar.add(getAddButton());
			toolBar.add(getSubtractButton());
			toolBar.add(getMultiplyButton());
			toolBar.add(getDivideButton());
			toolBar.add(expressionStatusLabel);
		}
		return toolBar;
	}

	/**
	 * This method initializes argument1TextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getArgument1TextField() {
		if (argument1TextField == null) {
			argument1TextField = new JTextField();
		}
		return argument1TextField;
	}

	/**
	 * This method initializes argument2TextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getArgument2TextField() {
		if (argument2TextField == null) {
			argument2TextField = new JTextField();
		}
		return argument2TextField;
	}

	/**
	 * This method initializes addButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText("add");
			addButton.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(final java.awt.event.ActionEvent e) {
					add_action();
				}
			});
		}
		return addButton;
	}

	protected void add_action() {
		this.getFacade().createAdd(this.firstArgument, this.secondArgument);
		this.refresh();
	}

	/**
	 * This method initializes subtractButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSubtractButton() {
		if (subtractButton == null) {
			subtractButton = new JButton();
			subtractButton.setText("subtract");
			subtractButton
					.addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(
								final java.awt.event.ActionEvent e) {
							subtract_action();
						}
					});
		}
		return subtractButton;
	}

	protected void subtract_action() {
		this.getFacade()
				.createSubtract(this.firstArgument, this.secondArgument);
		this.refresh();
	}

	/**
	 * This method initializes multiplyButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getMultiplyButton() {
		if (multiplyButton == null) {
			multiplyButton = new JButton();
			multiplyButton.setText("multiply");
			multiplyButton
					.addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(
								final java.awt.event.ActionEvent e) {
							multiply_action();
						}
					});
		}
		return multiplyButton;
	}

	protected void multiply_action() {
		this.getFacade()
				.createMultiply(this.firstArgument, this.secondArgument);
		this.refresh();
	}

	/**
	 * This method initializes divideButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getDivideButton() {
		if (divideButton == null) {
			divideButton = new JButton();
			divideButton.setText("divide");
			divideButton.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(final java.awt.event.ActionEvent e) {
					divide_action();
				}
			});
		}
		return divideButton;
	}

	protected void divide_action() {
		this.getFacade().createDivide(this.firstArgument, this.secondArgument);
		this.refresh();
	}

	/**
	 * This method initializes select1Button
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSelect1Button() {
		if (select1Button == null) {
			select1Button = new JButton();
			select1Button.setText("select");
			select1Button
					.addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(
								final java.awt.event.ActionEvent e) {
							select_action(true);
						}
					});
		}
		return select1Button;
	}

	protected void select_action(final boolean first) {
		final Expression selected = this.getCellList().getSelectedValue();
		if (selected != null) {
			if (first)
				this.firstArgument = selected;
			else
				this.secondArgument = selected;
			this.expressionStatusLabel.setText("");
			this.refresh();
		} else {
			this.expressionStatusLabel.setText("Select expression!");
		}

	}

	/**
	 * This method initializes select2Button
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSelect2Button() {
		if (select2Button == null) {
			select2Button = new JButton();
			select2Button.setText("select");
			select2Button
					.addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(
								final java.awt.event.ActionEvent e) {
							select_action(false);
						}
					});
		}
		return select2Button;
	}

	/**
	 * This method initializes variableToolBar
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getVariableToolBar() {
		if (variableToolBar == null) {
			variableLabel = new JLabel();
			variableLabel.setText(" Variable: ");
			variableToolBar = new JToolBar();
			variableToolBar.add(variableLabel);
			variableToolBar.add(getVariableTextField());
			variableToolBar.add(getVariableCreateButton());
		}
		return variableToolBar;
	}

	/**
	 * This method initializes variableTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getVariableTextField() {
		if (variableTextField == null) {
			variableTextField = new JTextField();
		}
		return variableTextField;
	}

	/**
	 * This method initializes variableCreateButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getVariableCreateButton() {
		if (variableCreateButton == null) {
			variableCreateButton = new JButton();
			variableCreateButton.setText("create");
			variableCreateButton
					.addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(
								final java.awt.event.ActionEvent e) {
							variableCreate_action();
						}
					});
		}
		return variableCreateButton;
	}

	protected void variableCreate_action() {
		final String name = this.getVariableTextField().getText();
		if (!name.equals(""))
			this.getFacade().createVariable(name);
		this.refresh();
	}

	private void refresh() {
		int selected = this.getCellList().getSelectedIndex();
		this.getCellList().setListData(this.getFacade().getExpressions());
		this.getCellList().setSelectedIndex(
				selected >= 0 ? selected : (this.getCellList().getModel()
						.getSize() - 1));
		selected = this.getVariableList().getSelectedIndex();
		this.getVariableList().setListData(this.getFacade().getVariables());
		this.getVariableList().setSelectedIndex(
				selected >= 0 ? selected : (this.getVariableList().getModel()
						.getSize() - 1));
		this.getArgument1TextField().setText(
				firstArgument != null ? this.firstArgument.toString()
						: "----------");
		this.getArgument2TextField().setText(
				secondArgument != null ? this.secondArgument.toString()
						: "----------");
		final boolean isSet = this.firstArgument != null
				&& this.secondArgument != null;
		this.getAddButton().setEnabled(isSet);
		this.getSubtractButton().setEnabled(isSet);
		this.getMultiplyButton().setEnabled(isSet);
		this.getDivideButton().setEnabled(isSet);
		this.getVariableTextField().setText("");
		this.getVariableTextField().grabFocus();
	}

	/**
	 * This method initializes mainSplitPane
	 * 
	 * @return javax.swing.JSplitPane
	 */
	private JSplitPane getMainSplitPane() {
		if (mainSplitPane == null) {
			mainSplitPane = new JSplitPane();
			mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			mainSplitPane.setTopComponent(getVariablePanel());
			mainSplitPane.setBottomComponent(getCellPanel());
			mainSplitPane.setDividerLocation(150);
		}
		return mainSplitPane;
	}

	/**
	 * This method initializes variablePanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getVariablePanel() {
		if (variablePanel == null) {
			variablePanel = new JPanel();
			variablePanel.setLayout(new BorderLayout());
			variablePanel.setBorder(BorderFactory.createTitledBorder(null,
					"Variables", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			variablePanel.add(getVariableScrollPane(), BorderLayout.CENTER);
			variablePanel.add(getUpDownToolBar(), BorderLayout.SOUTH);
		}
		return variablePanel;
	}

	/**
	 * This method initializes cellPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getCellPanel() {
		if (cellPanel == null) {
			cellPanel = new JPanel();
			cellPanel.setLayout(new BorderLayout());
			cellPanel.setBorder(BorderFactory.createTitledBorder(null,
					"Expressions", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			cellPanel.add(getCellScrollPane(), BorderLayout.CENTER);
		}
		return cellPanel;
	}

	/**
	 * This method initializes variableScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getVariableScrollPane() {
		if (variableScrollPane == null) {
			variableScrollPane = new JScrollPane();
			variableScrollPane.setViewportView(getVariableList());
		}
		return variableScrollPane;
	}

	/**
	 * This method initializes variableList
	 * 
	 * @return javax.swing.JList
	 */
	private JList<Variable> getVariableList() {
		if (variableList == null) {
			variableList = new JList<Variable>();
		}
		return variableList;
	}

	/**
	 * This method initializes upDownToolBar
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getUpDownToolBar() {
		if (upDownToolBar == null) {
			variableStatusLabel = new JLabel();
			variableStatusLabel.setText("");
			upDownToolBar = new JToolBar();
			upDownToolBar.add(getUpButton());
			upDownToolBar.add(getDownButton());
			upDownToolBar.add(variableStatusLabel);
		}
		return upDownToolBar;
	}

	/**
	 * This method initializes upButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getUpButton() {
		if (upButton == null) {
			upButton = new JButton();
			upButton.setText("up");
			upButton.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(final java.awt.event.ActionEvent e) {
					variable_action(true);
				}
			});
		}
		return upButton;
	}

	protected void variable_action(final boolean b) {
		final Variable selected = this.getVariableList().getSelectedValue();
		if (selected != null) {
			if (b)
				this.getFacade().up(selected);
			else
				this.getFacade().down(selected);
			this.variableStatusLabel.setText("");
			this.refresh();
		} else {
			this.variableStatusLabel.setText("Select variable!");
		}
	}

	/**
	 * This method initializes downButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getDownButton() {
		if (downButton == null) {
			downButton = new JButton();
			downButton.setText("down");
			downButton.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(final java.awt.event.ActionEvent e) {
					variable_action(false);
				}
			});
		}
		return downButton;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
