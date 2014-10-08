package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Vector;

import model.NegativeLengthException;
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
	public void testDoNotOverlap() {
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
		
		assertFalse(w1AsPart.doNotOverlap(w1AsPart));
		assertFalse(w1AsPart.doNotOverlap(w2AsPart));
		assertTrue(w1AsPart.doNotOverlap(w3AsPart));
		assertTrue(w1AsPart.doNotOverlap(w4AsPart));
		
		assertFalse(w2AsPart.doNotOverlap(w1AsPart));
		assertFalse(w2AsPart.doNotOverlap(w2AsPart));
		assertTrue(w2AsPart.doNotOverlap(w3AsPart));
		assertTrue(w2AsPart.doNotOverlap(w4AsPart));
		
		assertTrue(w3AsPart.doNotOverlap(w1AsPart));
		assertTrue(w3AsPart.doNotOverlap(w2AsPart));
		assertFalse(w3AsPart.doNotOverlap(w3AsPart));
		assertTrue(w3AsPart.doNotOverlap(w4AsPart));
		
		assertTrue(w4AsPart.doNotOverlap(w1AsPart));
		assertTrue(w4AsPart.doNotOverlap(w2AsPart));
		assertTrue(w4AsPart.doNotOverlap(w3AsPart));
		assertFalse(w4AsPart.doNotOverlap(w4AsPart));
	}

	@Test
	public void testGetVisibleContextWithoutOverlap() throws NegativeLengthException {
		w1 = windows.get(0);
		w1AsPart = new RectangularPart(w1.getLeftUpperCorner(),w1.getWidth(),w1.getHeight());
		w2 = windows.get(1);
		w2.move(100, 50);
		w2.setOpen(false);
		w2AsPart = new RectangularPart(w2.getLeftUpperCorner(),w2.getWidth(),w2.getHeight());
		w3 = windows.get(2);
		w3.move(250, 250);
		w3AsPart = new RectangularPart(w3.getLeftUpperCorner(),w3.getWidth(),w3.getHeight());
		w4 = windows.get(3);
		w4.move(50, 150);
		w4AsPart = new RectangularPart(w4.getLeftUpperCorner(),w4.getWidth(),w4.getHeight());
		
		partsW1 = new RectangularPartCollection();

		partsW1.add(w1AsPart);
		assertEquals(partsW1, w1.getVisibleContext());
		
		
		partsW3 = new RectangularPartCollection();
		partsW3.add(w3AsPart);
		
		assertEquals(partsW3, w3.getVisibleContext());
	}
	
	@Test
	public void testgetVisibleContext() throws NegativeLengthException {
		w1 = windows.get(0);
		w1AsPart = new RectangularPart(w1.getLeftUpperCorner(),w1.getWidth(),w1.getHeight());
		w2 = windows.get(1);
		w2.move(100, 50);
		w2AsPart = new RectangularPart(w2.getLeftUpperCorner(),w2.getWidth(),w2.getHeight());
		w3 = windows.get(2);
		w3.move(250, 250);
		w3AsPart = new RectangularPart(w3.getLeftUpperCorner(),w3.getWidth(),w3.getHeight());
		w4 = windows.get(3);
		w4.move(25, 150);
		w4AsPart = new RectangularPart(w4.getLeftUpperCorner(),w4.getWidth(),w4.getHeight());
		
		partsW2 = new RectangularPartCollection();
		RectangularPartCollection result = new RectangularPartCollection();
		RectangularPartCollection partsOfW2 = w2.splitAt(w1.getRightUpperCorner());
		
		Iterator<RectangularPart> i = partsOfW2.getParts().iterator();
		while(i.hasNext()) {
			RectangularPart current = i.next();
//			try {
//				current.setParent(w1);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			if(current.doNotOverlap(w1)) {
				result.add(current);
			}
		}
		
		assertEquals(result, w2.getVisibleContext());
	}
}
