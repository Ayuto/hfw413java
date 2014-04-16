package view;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public abstract class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel toolBar1 = null;
	private JPanel toolBar2 = null;
	private JLabel lkabel = null;
	private JTextField counter1TextField = null;
	private JTextField counter2TextField = null;
	private JButton upButton1 = null;
	private JButton downButton1 = null;
	private JButton upButton2 = null;
	private JButton downButton2 = null;
	private JPanel registerToolBar1 = null;
	private JPanel registerToolBar2 = null;
	private JButton registerButton1 = null;
	private JButton deregisterButton1 = null;
	private JButton registerButton2 = null;
	private JButton deregisterButton2 = null;

	public View() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(340, 180);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("View");
		this.switchRegistered1(false);
		this.switchRegistered2(false);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(jContentPane, BoxLayout.Y_AXIS));
			jContentPane.add(getToolBar1());
			jContentPane.add(getRegisterToolBar1());
			jContentPane.add(getToolBar2());
			jContentPane.add(getRegisterToolBar2());
		}
		return jContentPane;
	}

	private JPanel getToolBar1() {
		if (toolBar1 == null) {
			lkabel = new JLabel();
			lkabel.setText(" Aktueller Stand: ");
			toolBar1 = new JPanel();
			toolBar1.add(lkabel);
			toolBar1.add(getCounter1TextField());
			toolBar1.add(getUpButton1());
			toolBar1.add(getDownButton1());
		}
		return toolBar1;
	}
	private JPanel getToolBar2() {
		if (toolBar2 == null) {
			lkabel = new JLabel();
			lkabel.setText(" Aktueller Stand: ");
			toolBar2 = new JPanel();
			toolBar2.add(lkabel);
			toolBar2.add(getCounter2TextField());
			toolBar2.add(getUpButton2());
			toolBar2.add(getDownButton2());
		}
		return toolBar2;
	}

	protected JTextField getCounter1TextField() {
		if (counter1TextField == null) {
			counter1TextField = new JTextField();
			counter1TextField.setPreferredSize(new Dimension(50,20));
			counter1TextField.setEditable(false);
		}
		return counter1TextField;
	}
	protected JTextField getCounter2TextField() {
		if (counter2TextField == null) {
			counter2TextField = new JTextField();
			counter2TextField.setPreferredSize(new Dimension(50,20));
			counter2TextField.setEditable(false);
		}
		return counter2TextField;
	}

	private JButton getUpButton1() {
		if (upButton1 == null) {
			upButton1 = new JButton();
			upButton1.setText("Rauf");
			upButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					up1_action();
				}
			});
		}
		return upButton1;
	}
	private JButton getUpButton2() {
		if (upButton2 == null) {
			upButton2 = new JButton();
			upButton2.setText("Rauf");
			upButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					up2_action();
				}
			});
		}
		return upButton2;
	}

	protected void up1_action() {
		this.up1();
	}
	protected void up2_action() {
		this.up2();
	}

	protected abstract void up1();
	protected abstract void down1();
	protected abstract void up2();
	protected abstract void down2();

	private JButton getDownButton1() {
		if (downButton1 == null) {
			downButton1 = new JButton();
			downButton1.setText("Runter");
			downButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					down1_action();
				}
			});
		}
		return downButton1;
	}
	private JButton getDownButton2() {
		if (downButton2 == null) {
			downButton2 = new JButton();
			downButton2.setText("Runter");
			downButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					down2_action();
				}
			});
		}
		return downButton2;
	}

	protected void down1_action() {
		this.down1();
	}
	protected void down2_action() {
		this.down2();
	}

	private JPanel getRegisterToolBar1() {
		if (registerToolBar1 == null) {
			registerToolBar1 = new JPanel();
			registerToolBar1.add(getRegisterButton1());
			registerToolBar1.add(getDeregisterButton1());
		}
		return registerToolBar1;
	}
	private JPanel getRegisterToolBar2() {
		if (registerToolBar2 == null) {
			registerToolBar2 = new JPanel();
			registerToolBar2.add(getRegisterButton2());
			registerToolBar2.add(getDeregisterButton2());
		}
		return registerToolBar2;
	}

	private JButton getRegisterButton1() {
		if (registerButton1 == null) {
			registerButton1 = new JButton();
			registerButton1.setText("Registrieren");
			registerButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					register1_action();
				}
			});
		}
		return registerButton1;
	}
	private JButton getRegisterButton2() {
		if (registerButton2 == null) {
			registerButton2 = new JButton();
			registerButton2.setText("Registrieren");
			registerButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					register2_action();
				}
			});
		}
		return registerButton2;
	}

	protected void register1_action() {
		this.switchRegistered1(true);
		this.register1();
	}
	protected void register2_action() {
		this.switchRegistered2(true);
		this.register2();
	}

	protected abstract void register1();
	protected abstract void deregister1();
	protected abstract void register2();
	protected abstract void deregister2();

	private void switchRegistered1(boolean registered) {
		this.getRegisterButton1().setEnabled(!registered);
		this.getDeregisterButton1().setEnabled(registered);
		this.getUpButton1().setEnabled(registered);
		this.getDownButton1().setEnabled(registered);
		this.getCounter1TextField().setEnabled(registered);
	}
	private void switchRegistered2(boolean registered) {
		this.getRegisterButton2().setEnabled(!registered);
		this.getDeregisterButton2().setEnabled(registered);
		this.getUpButton2().setEnabled(registered);
		this.getDownButton2().setEnabled(registered);
		this.getCounter2TextField().setEnabled(registered);
	}

	private JButton getDeregisterButton1() {
		if (deregisterButton1 == null) {
			deregisterButton1 = new JButton();
			deregisterButton1.setText("Deregistrieren");
			deregisterButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					deregister1_action();
				}
			});
		}
		return deregisterButton1;
	}
	private JButton getDeregisterButton2() {
		if (deregisterButton2 == null) {
			deregisterButton2 = new JButton();
			deregisterButton2.setText("Deregistrieren");
			deregisterButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					deregister2_action();
				}
			});
		}
		return deregisterButton2;
	}

	protected void deregister1_action() {
		this.switchRegistered1(false);
		this.deregister1();
	}
	protected void deregister2_action() {
		this.switchRegistered2(false);
		this.deregister2();
	}

	protected void refreshView1(int value) {
		this.getCounter1TextField().setText(new Integer(value).toString());
	}
	protected void refreshView2(int value) {
		this.getCounter2TextField().setText(new Integer(value).toString());
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
