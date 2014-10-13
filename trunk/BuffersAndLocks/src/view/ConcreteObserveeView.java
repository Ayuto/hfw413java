package view;
import javax.swing.JFrame;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTextField;

import model.ConcreteObservee;

@SuppressWarnings("serial")
public class ConcreteObserveeView extends JFrame {

	private ConcreteObservee observee;

	private javax.swing.JPanel jContentPane = null;

	private JToolBar jToolBar = null;
	private JButton actionCommand = null;
	private JTextField observeeView = null;

	public ConcreteObserveeView() {
		super();
		initialize();
	}
	public void setObservee(ConcreteObservee observee){
		this.observee = observee;
	}
	private void initialize() {
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 60);
		this.setContentPane(getJContentPane());
		this.setTitle("Observee");
	}

	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getJToolBar(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;
	}
	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */    
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.add(getTheActionCommand());
			jToolBar.add(getObserveeView());
		}
		return jToolBar;
	}
 
	private JButton getTheActionCommand() {
		if (actionCommand == null) {
			actionCommand = new JButton();
			actionCommand.setText("The Action");
			actionCommand.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					theActionCommand();
				}
			});
		}
		return actionCommand;
	}
	
	private void theActionCommand(){
		this.observee.setValue(this.observee.getValue() + 1);
		this.observeeView.setText(new Integer(this.observee.getValue()).toString());
	}

	private JTextField getObserveeView() {
		if (observeeView == null) {
			observeeView = new JTextField();
		}
		return observeeView;
	}
	
}  //  @jve:decl-index=0:visual-constraint="237,55"
