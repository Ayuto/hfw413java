package model;

import java.util.Vector;

public class WindowManager {
	
	private static WindowManager theWindowManager;
	public static WindowManager getTheWindowManager(){
		if (theWindowManager == null) theWindowManager = new WindowManager();
		return theWindowManager;
	}
	
	private Vector<Window> windowStack;

	private WindowManager(){
		this.setWindowStack(new Vector<Window>());
	}
	private void setWindowStack(Vector<Window> windowStack) {
		this.windowStack = windowStack;
	}
	public Vector<Window> getWindowStack() {
		return windowStack;
	}
	public Vector<RectangularPart> getVisibleParts(){
		RectangularPartCollection result = new RectangularPartCollection();
		for (Window current : this.getWindowStack()) {
			result.add(current.getVisibleContext());
		}
		return result.toVector();
	}
	public void newWindow(){
		Window newWindow = new Window();
		for (Window current : this.getWindowStack()) current.newTop(newWindow);
		this.setAsNewTopWindow(newWindow);
	}
	public void setAsNewTopWindow(Window window) {
		this.getWindowStack().remove(window);
		this.getWindowStack().add(0, window);
		for (Window current : this.getWindowStack()) current.setAsTop(window);
	}
	public void dispose(Window window) {
		window.dispose();
		this.getWindowStack().remove(window);
	}
}
