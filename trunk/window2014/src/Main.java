import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.UIManager;

import view.View;


public class Main {
	
		
	public Main() {
	}

	public static void main(String[] args) {
	    try{
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }
	    catch (Exception e){
	        e.printStackTrace();
	    }
	    View view = new View();
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension frameSize = view.getSize();
	    if (frameSize.height > screenSize.height){
	        frameSize.height = screenSize.height;
	    }
	    if (frameSize.width > screenSize.width){
	        frameSize.width = screenSize.width;
	    }
	    view.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		view.setVisible(true);
	}


}
