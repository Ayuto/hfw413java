package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Vector;

import model.NegativeLengthException;
import model.Point;
import model.Rectangle;
import model.RectangularPart;
import model.RectangularPartCollection;
import model.Window;
import model.WindowManager;

import org.junit.Before;
import org.junit.Test;

public class WindowTest {

	Vector<Window> windows = new Vector<Window>();
	Window w1;
	Window w2;
	Window w3;
	Window w4;
	RectangularPart w1AsPart;
	RectangularPart w2AsPart;
	RectangularPart w3AsPart;
	RectangularPart w4AsPart;
	RectangularPartCollection partsW1;
	RectangularPartCollection partsW2;
	RectangularPartCollection partsW3;
	RectangularPartCollection partsW4;
	
	@Before
	public void before() {
		WindowManager.getTheWindowManager().getWindowStack().clear();
		windows.clear();
		WindowManager.getTheWindowManager().newWindow();
		WindowManager.getTheWindowManager().newWindow();
		WindowManager.getTheWindowManager().newWindow();
		WindowManager.getTheWindowManager().newWindow();
		windows = WindowManager.getTheWindowManager().getWindowStack();
	}	

	@Test
	public void testDoesNotOverlap() {
		w1 = windows.get(0);
		w1AsPart = new RectangularPart(w1.getLeftUpperCorner(),w1.getWidth(),w1.getHeight());
		w2 = windows.get(1);
		w2.move(100, 50);
		w2AsPart = new RectangularPart(w2.getLeftUpperCorner(),w2.getWidth(),w2.getHeight());
		w3 = windows.get(2);
		w3.move(250, 250);
		w3AsPart = new RectangularPart(w3.getLeftUpperCorner(),w3.getWidth(),w3.getHeight());
		w4 = windows.get(3);
		w4.move(50, 150);
		w4AsPart = new RectangularPart(w4.getLeftUpperCorner(),w4.getWidth(),w4.getHeight());
		
		assertFalse(w1AsPart.doesNotOverlap(w1AsPart));
		assertFalse(w1AsPart.doesNotOverlap(w2AsPart));
		assertTrue(w1AsPart.doesNotOverlap(w3AsPart));
		assertTrue(w1AsPart.doesNotOverlap(w4AsPart));
		
		assertFalse(w2AsPart.doesNotOverlap(w1AsPart));
		assertFalse(w2AsPart.doesNotOverlap(w2AsPart));
		assertTrue(w2AsPart.doesNotOverlap(w3AsPart));
		assertTrue(w2AsPart.doesNotOverlap(w4AsPart));
		
		assertTrue(w3AsPart.doesNotOverlap(w1AsPart));
		assertTrue(w3AsPart.doesNotOverlap(w2AsPart));
		assertFalse(w3AsPart.doesNotOverlap(w3AsPart));
		assertTrue(w3AsPart.doesNotOverlap(w4AsPart));
		
		assertTrue(w4AsPart.doesNotOverlap(w1AsPart));
		assertTrue(w4AsPart.doesNotOverlap(w2AsPart));
		assertTrue(w4AsPart.doesNotOverlap(w3AsPart));
		assertFalse(w4AsPart.doesNotOverlap(w4AsPart));
	}
	
	@Test
	public void testGetOverlappedArea() throws NegativeLengthException {
		// 1 Ecke von r2 in r1
		Rectangle r1 = new Rectangle(new Point(0, 0), 200, 100);
		Rectangle r2 = new Rectangle(new Point(50, 50), 200, 100);
		assertEquals(new Rectangle(new Point(50, 50), 150, 50), r1.getOverlappedArea(r2));
		
		// 2 Ecken von r3 in r1
		Rectangle r3 = new Rectangle(new Point(50, 25), 175, 25);
		assertEquals(new Rectangle(new Point(50, 25), 150, 25), r1.getOverlappedArea(r3));
		
		// 4 Ecken von r4 in r1
		Rectangle r4 = new Rectangle(new Point(50, 25), 25, 25);
		assertEquals(r4, r1.getOverlappedArea(r4));
	}
}
