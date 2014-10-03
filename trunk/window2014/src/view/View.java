package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.NegativeLengthException;
import model.RectangularPart;
import model.Window;
import model.WindowManager;

import java.awt.event.KeyEvent;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JSplitPane mainSplitPane = null;

	private JPanel managerPanel = null;

	private JPanel visiblePartsPanel = null;

	private JList<Window> windowList = null;

	private JList<RectangularPart> visibleContextList = null;

	private JToolBar managerToolBar = null;

	private JButton toTopButton = null;

	private JToolBar windowToolBar = null;

	private JButton openButton = null;

	private JButton closeButton = null;

	private JLabel xLabel = null;

	private JTextField xTextField = null;

	private JLabel yLabel = null;

	private JTextField yTextField = null;

	private JButton moveButton = null;

	private JButton newButton = null;

	private JButton resizeButton = null;

	private JLabel statusLabel = null;

	private JButton disposeButton = null;

	public View() {
		super();
		initialize();
	}
	private void initialize() {
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("Toy Windowing System");
	}
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			statusLabel = new JLabel();
			statusLabel.setText("");
			statusLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			statusLabel.setVisible(false);
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getMainSplitPane(), BorderLayout.CENTER);
			jContentPane.add(statusLabel, BorderLayout.SOUTH);
		}
		return jContentPane;
	}
	private JSplitPane getMainSplitPane() {
		if (mainSplitPane == null) {
			mainSplitPane = new JSplitPane();
			mainSplitPane.setDividerLocation(360);
			mainSplitPane.setDividerSize(5);
			mainSplitPane.setRightComponent(getVisiblePartsPanel());
			mainSplitPane.setLeftComponent(getManagerPanel());
		}
		return mainSplitPane;
	}
	private JPanel getManagerPanel() {
		if (managerPanel == null) {
			managerPanel = new JPanel();
			managerPanel.setLayout(new BorderLayout());
			managerPanel.setBorder(BorderFactory.createTitledBorder(null, "Window Manager", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			managerPanel.add(getWindowList(), BorderLayout.CENTER);
			managerPanel.add(getManagerToolBar(), BorderLayout.NORTH);
			managerPanel.add(getWindowToolBar(), BorderLayout.SOUTH);
		}
		return managerPanel;
	}
	private JPanel getVisiblePartsPanel() {
		if (visiblePartsPanel == null) {
			visiblePartsPanel = new JPanel();
			visiblePartsPanel.setLayout(new BorderLayout());
			visiblePartsPanel.setBorder(BorderFactory.createTitledBorder(null, "Visible Parts", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			visiblePartsPanel.add(getVisibleContextList(), BorderLayout.CENTER);
		}
		return visiblePartsPanel;
	}
	private JList<Window> getWindowList() {
		if (windowList == null) {
			windowList = new JList<Window>();
		}
		return windowList;
	}
	private JList<RectangularPart> getVisibleContextList() {
		if (visibleContextList == null) {
			visibleContextList = new JList<RectangularPart>();
		}
		return visibleContextList;
	}

	private JToolBar getManagerToolBar() {
		if (managerToolBar == null) {
			managerToolBar = new JToolBar();
			managerToolBar.add(getNewButton());
			managerToolBar.add(getToTopButton());
			managerToolBar.add(getDisposeButton());
		}
		return managerToolBar;
	}
	private JButton getToTopButton() {
		if (toTopButton == null) {
			toTopButton = new JButton();
			toTopButton.setText("As top window");
			toTopButton.setToolTipText("Puts selected window on top of all other windows");
			toTopButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					asTop();
				}
			});
		}
		return toTopButton;
	}
	protected void asTop() {
		try {
			WindowManager.getTheWindowManager().setAsNewTopWindow(this.getSelectedWindow());
			refresh(false);
		} catch (ViewException e) {
			return;
		}
	}
	private JToolBar getWindowToolBar() {
		if (windowToolBar == null) {
			yLabel = new JLabel();
			yLabel.setText("  Y-Axis: ");
			xLabel = new JLabel();
			xLabel.setText("  X-Axis: ");
			windowToolBar = new JToolBar();
			windowToolBar.add(getOpenButton());
			windowToolBar.add(getCloseButton());
			windowToolBar.add(xLabel);
			windowToolBar.add(getXTextField());
			windowToolBar.add(yLabel);
			windowToolBar.add(getYTextField());
			windowToolBar.add(getMoveButton());
			windowToolBar.add(getResizeButton());
		}
		return windowToolBar;
	}
	private JButton getOpenButton() {
		if (openButton == null) {
			openButton = new JButton();
			openButton.setText("Open");
			openButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					open();
				}
			});
		}
		return openButton;
	}

	protected void open() {
		try {
			this.getSelectedWindow().setOpen(true);
			refresh(true);
		} catch (ViewException e) {
			return;
		}
	}
	protected void close() {
		try {
			this.getSelectedWindow().setOpen(false);
			refresh(true);
		} catch (ViewException e) {
			return;
		}
	}

	private Window getSelectedWindow() throws ViewException {
		int selected = getWindowList().getSelectedIndex();
		if (selected >= 0) return (Window) getWindowList().getSelectedValue();
		this.setStatusLabel("Select window!");
		throw new ViewException();
	}
	@SuppressWarnings("serial")
	class ViewException extends Exception{
		public ViewException() {
			super("");
		}
	}
	private JButton getCloseButton() {
		if (closeButton == null) {
			closeButton = new JButton();
			closeButton.setText("Close");
			closeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					close();
				}
			});
		}
		return closeButton;
	}
	private JTextField getXTextField() {
		if (xTextField == null) {
			xTextField = new JTextField();
			xTextField.setText("100");
		}
		return xTextField;
	}

	private JTextField getYTextField() {
		if (yTextField == null) {
			yTextField = new JTextField();
			yTextField.setText("100");
		}
		return yTextField;
	}

	private JButton getMoveButton() {
		if (moveButton == null) {
			moveButton = new JButton();
			moveButton.setText("Move");
			moveButton.setToolTipText("Moves the selected windows as specified by X-Axis and Y-Axis");
			moveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					moveWindow();
				}
			});
		}
		return moveButton;
	}

	protected void moveWindow() {
		try {
			Window window = getSelectedWindow();
			int deltaX = getInt(getXTextField().getText());
			int deltaY = getInt(getYTextField().getText());
			window.move(deltaX, deltaY);
			refresh(true);
		} catch (ViewException e) {
			return;
		}
	}
	protected void resizeWindow() {
		try {
			Window window = getSelectedWindow();
			int width = getWidthSpec();
			int height = getHeightSpec();
			try {
				window.resize(width, height);
				refresh(true);
			} catch (NegativeLengthException e) {
				throw new Error("Conditions should be checked!");
			}
		} catch (ViewException e) {
			return;
		}
	}
	private int getHeightSpec() throws ViewException {
		return getPositiveInt(getYTextField().getText());
	}
	private int getWidthSpec() throws ViewException {
		return getPositiveInt(getXTextField().getText());
	}
	private int getPositiveInt(String text) throws ViewException {
		int value = getInt(text);
		if (value >= 0) return value;
		this.setStatusLabel("No positive integer: " + text);
		throw new ViewException();
	}
	private int getInt(String text) throws ViewException {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException nfe) {
			this.setStatusLabel("No integer: " + text);
			throw new ViewException();
		}
	}
	private void setStatusLabel(String message) {
		this.statusLabel.setText(message);
		this.statusLabel.setVisible(true);
	}
	private JButton getNewButton() {
		if (newButton == null) {
			newButton = new JButton();
			newButton.setText("New window");
			newButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					WindowManager.getTheWindowManager().newWindow();
					refresh(false);
				}
			});
		}
		return newButton;
	}

	protected void refresh(boolean keepSelection) {
		int selectedIndex = keepSelection ? this.getWindowList().getSelectedIndex() : 0;
		this.getWindowList().setListData(WindowManager.getTheWindowManager().getWindowStack());
		if (selectedIndex >= 0) this.getWindowList().setSelectedIndex(selectedIndex);
		this.getVisibleContextList().setListData(WindowManager.getTheWindowManager().getVisibleParts());
		this.statusLabel.setVisible(false);
	}

	private JButton getResizeButton() {
		if (resizeButton == null) {
			resizeButton = new JButton();
			resizeButton.setText("Set size");
			resizeButton.setToolTipText("Sets the size of the selected windows as specified by X-Axis and Y-Axis");
			resizeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					resizeWindow();
				}
			});
		}
		return resizeButton;
	}
	/**
	 * This method initializes disposeButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDisposeButton() {
		if (disposeButton == null) {
			disposeButton = new JButton();
			disposeButton.setText("Dispose");
			disposeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					disposeWindow();
				}
			});
		}
		return disposeButton;
	}
	protected void disposeWindow() {
		try {
			WindowManager.getTheWindowManager().dispose(this.getSelectedWindow());
			refresh(true);
		} catch (ViewException e) {
			return;
		}
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
