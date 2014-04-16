import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.UIManager;

import model.ExpressionFacade;

import view.View;


public class Starter {

	public static void main(final String[] args) {
		try {
		  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(final Exception e) {
		  e.printStackTrace();
		}
		final View view = new View(ExpressionFacade.createExpressionFacade());
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final Dimension viewSize = view.getSize();
		final double leftMargin = (screenSize.getWidth() - viewSize.getWidth())/2;
		final double topMargin = (screenSize.getHeight() - viewSize.getHeight())/2;
		view.setLocation((int)leftMargin,(int)topMargin);
		view.setVisible(true);
	}
}

