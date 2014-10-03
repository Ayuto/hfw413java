package test;

import static org.junit.Assert.*;

import java.util.Vector;

import model.RectangularPart;
import model.Window;
import model.WindowManager;

import org.junit.Before;
import org.junit.Test;

public class WindowTest {

	@Before
	public void befor() {
		WindowManager.getTheWindowManager().newWindow();
		WindowManager.getTheWindowManager().newWindow();
		WindowManager.getTheWindowManager().newWindow();
		WindowManager.getTheWindowManager().newWindow();
		
	}

	@Test
	public void testDoNotOverlap() {
		Vector<Window> windows = WindowManager.getTheWindowManager().getWindowStack();
		Window w1 = windows.get(0);
		RectangularPart w1AsPart = new RectangularPart(w1.getLeftUpperCorner(),w1.getWidth(),w1.getHeight());
		Window w2 = windows.get(1);
		w2.move(200, 100);
		RectangularPart w2AsPart = new RectangularPart(w2.getLeftUpperCorner(),w2.getWidth(),w2.getHeight());
		Window w3 = windows.get(2);
		w3.move(250, 250);
		RectangularPart w3AsPart = new RectangularPart(w3.getLeftUpperCorner(),w3.getWidth(),w3.getHeight());
		Window w4 = windows.get(3);
		w4.move(50, 50);
		RectangularPart w4AsPart = new RectangularPart(w4.getLeftUpperCorner(),w4.getWidth(),w4.getHeight());
		
		assertFalse(w1AsPart.doNotOverlap(w1AsPart));
		assertFalse(w1AsPart.doNotOverlap(w2AsPart));
		assertTrue(w1AsPart.doNotOverlap(w3AsPart));
		assertFalse(w1AsPart.doNotOverlap(w4AsPart));
		assertFalse(w2AsPart.doNotOverlap(w1AsPart));
		assertFalse(w2AsPart.doNotOverlap(w2AsPart));
		assertTrue(w2AsPart.doNotOverlap(w3AsPart));
		assertFalse(w2AsPart.doNotOverlap(w4AsPart));
		assertTrue(w3AsPart.doNotOverlap(w1AsPart));
		assertTrue(w3AsPart.doNotOverlap(w2AsPart));
		assertFalse(w3AsPart.doNotOverlap(w3AsPart));
		assertTrue(w3AsPart.doNotOverlap(w4AsPart));
		assertFalse(w4AsPart.doNotOverlap(w1AsPart));
		assertFalse(w4AsPart.doNotOverlap(w2AsPart));
		assertTrue(w4AsPart.doNotOverlap(w3AsPart));
		assertFalse(w4AsPart.doNotOverlap(w4AsPart));
	}

}
