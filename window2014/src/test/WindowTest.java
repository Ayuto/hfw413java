package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	Window w5;

	@Before
	public void before() {
		WindowManager.getTheWindowManager().getWindowStack().clear();
		windows.clear();
		WindowManager.getTheWindowManager().newWindow();
		WindowManager.getTheWindowManager().newWindow();
		WindowManager.getTheWindowManager().newWindow();
		WindowManager.getTheWindowManager().newWindow();
		WindowManager.getTheWindowManager().newWindow();
		windows = WindowManager.getTheWindowManager().getWindowStack();
	}

	@Test
	public void testDoesNotOverlap() {
		w1 = windows.get(0);
		RectangularPart w1AsPart = new RectangularPart(w1.getLeftUpperCorner(), w1.getWidth(),
				w1.getHeight());
		w2 = windows.get(1);
		w2.move(100, 50);
		RectangularPart w2AsPart = new RectangularPart(w2.getLeftUpperCorner(), w2.getWidth(),
				w2.getHeight());
		w3 = windows.get(2);
		w3.move(250, 250);
		RectangularPart w3AsPart = new RectangularPart(w3.getLeftUpperCorner(), w3.getWidth(),
				w3.getHeight());
		w4 = windows.get(3);
		w4.move(50, 150);
		RectangularPart w4AsPart = new RectangularPart(w4.getLeftUpperCorner(), w4.getWidth(),
				w4.getHeight());

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
		assertEquals(new Rectangle(new Point(50, 50), 150, 50),
				r1.getOverlappedArea(r2));

		// 2 Ecken von r3 in r1
		Rectangle r3 = new Rectangle(new Point(50, 25), 175, 25);
		assertEquals(new Rectangle(new Point(50, 25), 150, 25),
				r1.getOverlappedArea(r3));

		// 4 Ecken von r4 in r1. Ergebnis muss r4 sein
		Rectangle r4 = new Rectangle(new Point(50, 25), 25, 25);
		assertEquals(r4, r1.getOverlappedArea(r4));
	}

	@Test
	public void testCalculateVisibleContext() {
		w1 = windows.get(0);
		w2 = windows.get(1);
		
		// w2 wird durch w1 komplett verdeckt
		assertEquals(new RectangularPartCollection(), w2.calculateVisibleContext());
		
		// w1 ist komplett sichtbar
		RectangularPartCollection result1 = new RectangularPartCollection();
		result1.add(new RectangularPart(new Point(0,  0), 200, 100));
		assertEquals(result1, w1.calculateVisibleContext());
	}
	
	@Test
	public void testCalculateVisibleContext2() {
		// Die obere linke Ecke von w1 verdeckt w2
		w1 = windows.get(0);
		w1.move(50, 50);
		w2 = windows.get(1);
		w3 = windows.get(2);
		w3.move(100, 0);
		
		// w1 verdeckt die rechte untere Ecke von w2
		RectangularPartCollection result1 = new RectangularPartCollection();
		result1.add(new RectangularPart(new Point(0, 0), 50, 50));
		result1.add(new RectangularPart(new Point(50, 0), 150, 50));
		result1.add(new RectangularPart(new Point(0, 50), 50, 50));
		assertEquals(result1, w2.calculateVisibleContext());
		
		// die linke untere Ecke und die gesamte linke Seite von w3 werden verdeckt
		RectangularPartCollection result2 = new RectangularPartCollection();
		result2.add(new RectangularPart(new Point(200, 0), 50, 50));
		result2.add(new RectangularPart(new Point(250, 0), 50, 50));
		result2.add(new RectangularPart(new Point(250, 50), 50, 50));
		assertEquals(result2, w3.calculateVisibleContext());
	}
	
	@Test
	public void testCalculateVisibleContext3() throws NegativeLengthException {
		w1 = windows.get(0);
		w1.move(0, 75);
		w1.resize(250, 100);
		w2 = windows.get(1);
		w2.move(100, 50);
		w3 = windows.get(2);
		w3.move(50, 0);
		w3.resize(150, 100);
		
		RectangularPartCollection result = new RectangularPartCollection();
		result.add(new RectangularPart(new Point(100, 50), 150, 25));
		result.add(new RectangularPart(new Point(250, 50), 50, 25));
		result.add(new RectangularPart(new Point(250, 75), 50, 75));
		assertEquals(result, w2.calculateVisibleContext());
		
		RectangularPartCollection result2 =  new RectangularPartCollection();
		result2.add(new RectangularPart(new Point(50, 0), 50, 50));
		result2.add(new RectangularPart(new Point(100, 0), 100, 50));
		result2.add(new RectangularPart(new Point(50, 50), 50, 25));
		assertEquals(result2, w3.calculateVisibleContext());
	}
	
	@Test
	public void testCalculateVisibleContext4() throws NegativeLengthException {
		w1 = windows.get(0);
		w1.move(50, 50);
		w2 = windows.get(1);
		w2.resize(300, 300);
		
		// w1 ligt in w2
		RectangularPartCollection result = new RectangularPartCollection();
		result.add(new RectangularPart(new Point(0, 0), 50, 50));
		result.add(new RectangularPart(new Point(50, 0), 200, 50));
		result.add(new RectangularPart(new Point(250, 0), 50, 50));
		result.add(new RectangularPart(new Point(0, 50), 50, 100));
		result.add(new RectangularPart(new Point(250, 50), 50, 100));
		result.add(new RectangularPart(new Point(0, 150), 50, 150));
		result.add(new RectangularPart(new Point(50, 150), 200, 150));
		result.add(new RectangularPart(new Point(250, 150), 50, 150));
		assertEquals(result, w2.calculateVisibleContext());
	}
	
	@Test
	public void testCalculateVisibleContext5() throws NegativeLengthException {
		w1 = windows.get(3);
		w1.move(50, 50);
		w1.resize(100, 250);
		w2 = windows.get(2);
		w3 = windows.get(1);
		w3.move(100, 100);
		w3.resize(100, 250);
		w4 = windows.get(0);
		w4.move(0, 150);
		
		//w3 wird von w4 in der Mitte �berdeckt
		RectangularPartCollection result1 = new RectangularPartCollection();
		result1.add(new RectangularPart(new Point(100, 100), 100, 50));
		result1.add(new RectangularPart(new Point(100, 250), 100, 100));
		assertEquals(result1, w3.calculateVisibleContext());
		
		//w1 wird von allen anderen oben, rechts und in der Mitte �berdeckt
		RectangularPartCollection result2 = new RectangularPartCollection();
		result2.add(new RectangularPart(new Point(50, 100), 50, 50));
		result2.add(new RectangularPart(new Point(50, 250), 50, 50));
		assertEquals(result2, w1.calculateVisibleContext());
	}
	
	@Test
	public void testCalculateVisibleContext6() throws NegativeLengthException {
		w1 = windows.get(0);
		w2 = windows.get(1);
		w2.move(300, 0);
		w3 = windows.get(2);
		w3.move(0, 300);
		w4 = windows.get(3);
		w4.move(300, 300);
		w5 = windows.get(4);
		w5.move(100, 50);
		w5.resize(300, 300);
		
		// w5 wird an jeder Ecke verdeckt
		RectangularPartCollection result = new RectangularPartCollection();
		result.add(new RectangularPart(new Point(200, 50), 100, 50));
		result.add(new RectangularPart(new Point(100, 100), 100, 200));
		result.add(new RectangularPart(new Point(200, 100), 100, 200));
		result.add(new RectangularPart(new Point(300, 100), 100, 200));
		result.add(new RectangularPart(new Point(200, 300), 100, 50));
		assertEquals(result, w5.calculateVisibleContext());
	}
}
