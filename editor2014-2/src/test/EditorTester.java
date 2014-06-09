package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import editor.Editor;

public class EditorTester {

	private Editor editor;

	@Before
	public void setUp() throws Exception {
		this.editor = Editor.createEditor();
	}

	@Test
	public void testLeft() {
		Assert.assertTrue(this.editor.getEditorText().isEmpty());

		// tippen von 123456789
		this.editor.keyTyped('1');
		this.editor.keyTyped('2');
		this.editor.keyTyped('3');
		this.editor.keyTyped('4');
		this.editor.keyTyped('5');
		this.editor.keyTyped('6');
		this.editor.keyTyped('7');
		this.editor.keyTyped('8');
		this.editor.keyTyped('9');
		Assert.assertEquals("123456789", this.editor.getEditorText());

		Assert.assertEquals(9, this.editor.getPosition());
		this.editor.left();
		Assert.assertEquals(8, this.editor.getPosition());
		this.editor.left();
		Assert.assertEquals(7, this.editor.getPosition());
		this.editor.left();
		Assert.assertEquals(6, this.editor.getPosition());
		this.editor.left();
		Assert.assertEquals(5, this.editor.getPosition());
		this.editor.left();
		Assert.assertEquals(4, this.editor.getPosition());
		this.editor.left();
		Assert.assertEquals(3, this.editor.getPosition());
		this.editor.left();
		Assert.assertEquals(2, this.editor.getPosition());
		this.editor.left();
		Assert.assertEquals(1, this.editor.getPosition());
		this.editor.left();
		Assert.assertEquals(0, this.editor.getPosition());

		Assert.assertEquals("123456789", this.editor.getEditorText());

		this.editor.undo();
		Assert.assertEquals(1, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(2, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(3, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(4, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(5, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(6, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(7, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(8, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(9, this.editor.getPosition());
	}

	@Test
	public void testRight() {
		Assert.assertTrue(this.editor.getEditorText().isEmpty());

		// tippen von 123456789 und nach ganz links laufen
		this.editor.keyTyped('1');
		this.editor.keyTyped('2');
		this.editor.keyTyped('3');
		this.editor.keyTyped('4');
		this.editor.keyTyped('5');
		this.editor.keyTyped('6');
		this.editor.keyTyped('7');
		this.editor.keyTyped('8');
		this.editor.keyTyped('9');
		this.editor.left();
		this.editor.left();
		this.editor.left();
		this.editor.left();
		this.editor.left();
		this.editor.left();
		this.editor.left();
		this.editor.left();
		this.editor.left();

		Assert.assertEquals("123456789", this.editor.getEditorText());
		Assert.assertEquals(0, this.editor.getPosition());

		this.editor.right();
		Assert.assertEquals(1, this.editor.getPosition());
		this.editor.right();
		Assert.assertEquals(2, this.editor.getPosition());
		this.editor.right();
		Assert.assertEquals(3, this.editor.getPosition());
		this.editor.right();
		Assert.assertEquals(4, this.editor.getPosition());
		this.editor.right();
		Assert.assertEquals(5, this.editor.getPosition());
		this.editor.right();
		Assert.assertEquals(6, this.editor.getPosition());
		this.editor.right();
		Assert.assertEquals(7, this.editor.getPosition());
		this.editor.right();
		Assert.assertEquals(8, this.editor.getPosition());
		this.editor.right();
		Assert.assertEquals(9, this.editor.getPosition());

		Assert.assertEquals("123456789", this.editor.getEditorText());

		this.editor.undo();
		Assert.assertEquals(8, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(7, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(6, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(5, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(4, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(3, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(2, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(1, this.editor.getPosition());
		this.editor.undo();
		Assert.assertEquals(0, this.editor.getPosition());

	}

	@Test
	public void testKeyTyped() {
		// a
		Assert.assertTrue(this.editor.getEditorText().isEmpty());
		this.editor.keyTyped('A');
		Assert.assertFalse(this.editor.getEditorText().isEmpty());
		this.editor.undo();
		Assert.assertTrue(this.editor.getEditorText().isEmpty());

		// aaa.... 20 mal
		for (int i = 0; i < 20; i++) {
			this.editor.keyTyped('A');
		}
		Assert.assertEquals(20, this.editor.getEditorText().length());
		for (int i = 0; i < 20; i++) {
			Assert.assertEquals('a', this.editor.getEditorText().charAt(i));
		}
		for (int i = 0; i < 20; i++) {
			this.editor.undo();
		}
		Assert.assertTrue(this.editor.getEditorText().isEmpty());

		// abab... 5 mal 'ab'
		for (int i = 0; i < 5; i++) {
			this.editor.keyTyped('A');
			this.editor.keyTyped('B');
		}
		Assert.assertEquals(10, this.editor.getEditorText().length());
		int index = 0;
		for (int i = 0; i < 5; i++) {
			Assert.assertEquals('a', this.editor.getEditorText()
					.charAt(index++));
			Assert.assertEquals('b', this.editor.getEditorText()
					.charAt(index++));
		}
		for (int i = 0; i < 10; i++) {
			this.editor.undo();
		}
		Assert.assertTrue(this.editor.getEditorText().isEmpty());

		// abcdefg
		this.editor.keyTyped('A');
		this.editor.keyTyped('B');
		this.editor.keyTyped('C');
		this.editor.keyTyped('D');
		this.editor.keyTyped('E');
		this.editor.keyTyped('F');
		this.editor.keyTyped('G');
		Assert.assertEquals(
				'g',
				this.editor.getEditorText().charAt(
						this.editor.getPosition() - 1));
		this.editor.undo();
		Assert.assertEquals(
				'f',
				this.editor.getEditorText().charAt(
						this.editor.getPosition() - 1));
		this.editor.undo();
		Assert.assertEquals(
				'e',
				this.editor.getEditorText().charAt(
						this.editor.getPosition() - 1));
		this.editor.undo();
		Assert.assertEquals(
				'd',
				this.editor.getEditorText().charAt(
						this.editor.getPosition() - 1));
		this.editor.undo();
		Assert.assertEquals(
				'c',
				this.editor.getEditorText().charAt(
						this.editor.getPosition() - 1));
		this.editor.undo();
		Assert.assertEquals(
				'b',
				this.editor.getEditorText().charAt(
						this.editor.getPosition() - 1));
		this.editor.undo();
		Assert.assertEquals(
				'a',
				this.editor.getEditorText().charAt(
						this.editor.getPosition() - 1));
		this.editor.undo();
		Assert.assertTrue(this.editor.getEditorText().isEmpty());
	}

	@Test
	public void testShiftMode() {
		Assert.assertTrue(this.editor.getEditorText().isEmpty());

		// Aa
		this.editor.shift();
		this.editor.keyTyped('A');
		this.editor.shift();
		this.editor.keyTyped('A');
		Assert.assertFalse(this.editor.getEditorText().isEmpty());
		Assert.assertEquals(2, this.editor.getPosition());
		Assert.assertEquals(2, this.editor.getEditorText().length());
		Assert.assertEquals('a', this.editor.getEditorText().charAt(1));
		this.editor.undo(); // rŸckgŠngig 'a'
		this.editor.undo(); // rŸckgŠngig shift
		Assert.assertEquals('A', this.editor.getEditorText().charAt(0));
		Assert.assertEquals(1, this.editor.getPosition());
		Assert.assertEquals(1, this.editor.getEditorText().length());
		this.editor.keyTyped('A'); // AA
		Assert.assertEquals("AA", this.editor.getEditorText());
		this.editor.undo(); // rŸckgŠngig 'A'
		this.editor.undo(); // rŸckgŠngig 'A'
		Assert.assertTrue(this.editor.getEditorText().isEmpty());
		this.editor.undo(); // rŸckgŠngig shift
		this.editor.keyTyped('B');
		Assert.assertEquals("b", this.editor.getEditorText());
	}

	@Test
	public void testDeleteLeft() {
		Assert.assertTrue(this.editor.getEditorText().isEmpty());

		// tippen von HaLlO WelT
		this.editor.shift();
		this.editor.keyTyped('H');
		this.editor.shift();
		this.editor.keyTyped('A');
		this.editor.shift();
		this.editor.keyTyped('L');
		this.editor.shift();
		this.editor.keyTyped('L');
		this.editor.shift();
		this.editor.keyTyped('O');
		this.editor.keyTyped(' ');
		this.editor.keyTyped('W');
		this.editor.shift();
		this.editor.keyTyped('E');
		this.editor.keyTyped('l');
		this.editor.shift();
		this.editor.keyTyped('T');

		Assert.assertEquals("HaLlO WelT", this.editor.getEditorText());
		this.editor.deleteLeft();
		Assert.assertEquals("HaLlO Wel", this.editor.getEditorText());
		this.editor.deleteLeft();
		Assert.assertEquals("HaLlO We", this.editor.getEditorText());
		this.editor.deleteLeft();
		Assert.assertEquals("HaLlO W", this.editor.getEditorText());
		this.editor.deleteLeft();
		Assert.assertEquals("HaLlO ", this.editor.getEditorText());
		this.editor.deleteLeft();
		Assert.assertEquals("HaLlO", this.editor.getEditorText());
		this.editor.deleteLeft();
		Assert.assertEquals("HaLl", this.editor.getEditorText());
		this.editor.deleteLeft();
		Assert.assertEquals("HaL", this.editor.getEditorText());
		this.editor.deleteLeft();
		Assert.assertEquals("Ha", this.editor.getEditorText());
		this.editor.deleteLeft();
		Assert.assertEquals("H", this.editor.getEditorText());
		this.editor.deleteLeft();
		Assert.assertEquals("", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("H", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("Ha", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("HaL", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("HaLl", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("HaLlO", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("HaLlO ", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("HaLlO W", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("HaLlO We", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("HaLlO Wel", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("HaLlO WelT", this.editor.getEditorText());
	}

	@Test
	public void testDeleteRight() {
		Assert.assertTrue(this.editor.getEditorText().isEmpty());

		// tippen von HaLlO WelT und bewegen nach ganz links
		this.editor.shift();
		this.editor.keyTyped('H');
		this.editor.shift();
		this.editor.keyTyped('A');
		this.editor.shift();
		this.editor.keyTyped('L');
		this.editor.shift();
		this.editor.keyTyped('L');
		this.editor.shift();
		this.editor.keyTyped('O');
		this.editor.keyTyped(' ');
		this.editor.keyTyped('W');
		this.editor.shift();
		this.editor.keyTyped('E');
		this.editor.keyTyped('l');
		this.editor.shift();
		this.editor.keyTyped('T');
		for (int i = 0; i < 10; i++)
			this.editor.left();

		Assert.assertEquals("HaLlO WelT", this.editor.getEditorText());
		Assert.assertEquals(0, this.editor.getPosition());

		this.editor.deleteRight();
		Assert.assertEquals("aLlO WelT", this.editor.getEditorText());
		this.editor.deleteRight();
		Assert.assertEquals("LlO WelT", this.editor.getEditorText());
		this.editor.deleteRight();
		Assert.assertEquals("lO WelT", this.editor.getEditorText());
		this.editor.deleteRight();
		Assert.assertEquals("O WelT", this.editor.getEditorText());
		this.editor.deleteRight();
		Assert.assertEquals(" WelT", this.editor.getEditorText());
		this.editor.deleteRight();
		Assert.assertEquals("WelT", this.editor.getEditorText());
		this.editor.deleteRight();
		Assert.assertEquals("elT", this.editor.getEditorText());
		this.editor.deleteRight();
		Assert.assertEquals("lT", this.editor.getEditorText());
		this.editor.deleteRight();
		Assert.assertEquals("T", this.editor.getEditorText());
		this.editor.deleteRight();
		Assert.assertEquals("", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("T", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("lT", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("elT", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("WelT", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals(" WelT", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("O WelT", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("lO WelT", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("LlO WelT", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("aLlO WelT", this.editor.getEditorText());
		this.editor.undo();
		Assert.assertEquals("HaLlO WelT", this.editor.getEditorText());

		Assert.assertEquals(0, this.editor.getPosition());
	}
	
	@Test
	public void testNewLine() {
		Assert.assertTrue(this.editor.getEditorText().isEmpty());
		
		// ab \n cd \n ef eingeben
		this.editor.keyTyped('A');
		this.editor.keyTyped('B');
		this.editor.newLine();
		this.editor.keyTyped('C');
		this.editor.keyTyped('D');
		this.editor.newLine();
		this.editor.keyTyped('E');
		this.editor.keyTyped('F');
		
		Assert.assertEquals("ab\ncd\nef", this.editor.getEditorText());
		Assert.assertEquals(8, this.editor.getPosition());
		
		this.editor.undo();
		this.editor.undo();
		this.editor.undo();
		
		Assert.assertEquals("ab\ncd", this.editor.getEditorText());
		
		this.editor.undo();
		this.editor.undo();
		this.editor.undo();
		
		Assert.assertEquals("ab", this.editor.getEditorText());
		
		this.editor.newLine();
		this.editor.newLine();
		
		Assert.assertEquals("ab\n\n", this.editor.getEditorText());
		
		this.editor.deleteLeft();
		
		Assert.assertEquals("ab\n", this.editor.getEditorText());
	}

	@Test
	public void testMixed() {
		Assert.assertTrue(this.editor.getEditorText().isEmpty());
		
		// abc left delRight d undo undo
		this.editor.keyTyped('A');
		this.editor.keyTyped('B');
		this.editor.keyTyped('C');
		this.editor.left();

		Assert.assertEquals("abc", this.editor.getEditorText());
		Assert.assertEquals(2, this.editor.getPosition());

		this.editor.deleteRight();
		Assert.assertEquals("ab", this.editor.getEditorText());

		this.editor.keyTyped('D');
		Assert.assertEquals("abd", this.editor.getEditorText());
		this.editor.undo();
		this.editor.undo();

		Assert.assertEquals("abc", this.editor.getEditorText());
	}

	@Test
	public void testCutPaste() {
		// aabbccd
		this.editor.keyTyped('A');
		this.editor.keyTyped('A');
		this.editor.keyTyped('B');
		this.editor.keyTyped('B');
		this.editor.keyTyped('C');
		this.editor.keyTyped('C');
		this.editor.keyTyped('D');
		
		Assert.assertEquals("aabbccd", this.editor.getEditorText());
		
		// aabb
		// |ccd|
		this.editor.shift();
		this.editor.left();
		this.editor.left();
		this.editor.left();
		this.editor.cut();
		this.editor.shift();
		
		Assert.assertEquals("aabb", this.editor.getEditorText());
		
		// aabbccd
		// |ccd|
		this.editor.paste();
		
		Assert.assertEquals("aabbccd", this.editor.getEditorText());
		
		// aabbccdccd
		// |ccd|
		this.editor.paste();
		
		Assert.assertEquals("aabbccdccd", this.editor.getEditorText());
		
		// aabbccdcccdcd
		// |ccd|
		this.editor.left();
		this.editor.left();
		this.editor.paste();
		
		Assert.assertEquals("aabbccdcccdcd", this.editor.getEditorText());
		
		// aabbcccdcd
		// |cdc|
		this.editor.left();
		this.editor.left();
		this.editor.left();
		this.editor.shift();
		this.editor.left();
		this.editor.left();
		this.editor.left();
		this.editor.cut();
		this.editor.shift();
		
		Assert.assertEquals("aabbcccdcd", this.editor.getEditorText());
		
		// aabbccdcccdcd
		// |ccd|
		this.editor.undo();
		this.editor.undo();
		
		Assert.assertEquals("aabbccdcccdcd", this.editor.getEditorText());
		

		// aabbcccdcd
		// |cdc|
		this.editor.redo();
		this.editor.redo();
		
		Assert.assertEquals("aabbcccdcd", this.editor.getEditorText());
	}
	
	@Test
	public void testCopyPaste() {
		// aabbccd|
		this.editor.keyTyped('A');
		this.editor.keyTyped('A');
		this.editor.keyTyped('B');
		this.editor.keyTyped('b');
		this.editor.keyTyped('C');
		this.editor.keyTyped('C');
		this.editor.keyTyped('D');
		
		Assert.assertEquals("aabbccd", this.editor.getEditorText());
		
		// aabbc|cd
		// |cd|
		this.editor.shift();
		this.editor.left();
		this.editor.left();
		this.editor.copy();
		this.editor.shift();

		Assert.assertEquals("aabbccd", this.editor.getEditorText());
		
		// aabbcc|cdd
		// |cd|
		this.editor.right();
		this.editor.paste();
		
		Assert.assertEquals("aabbcccdd", this.editor.getEditorText());
		
		// aabbcccd|cdd
		// |cd|
		this.editor.paste();
		
		Assert.assertEquals("aabbcccdcdd", this.editor.getEditorText());

		// aabdd
		// |d|
		this.editor.shift();
		this.editor.left();
		this.editor.copy();
		this.editor.left();
		this.editor.left();
		this.editor.left();
		this.editor.left();
		this.editor.left();
		this.editor.left();
		this.editor.paste();
		
		Assert.assertEquals("aabdd", this.editor.getEditorText());

		// aabbcccd|cdd
		// |cd|
		this.editor.undo();
		
		Assert.assertEquals("aabbcccdcdd", this.editor.getEditorText());
		

		// aabdd
		// |d|
		this.editor.redo();
		
		Assert.assertEquals("aabdd", this.editor.getEditorText());
	}
}
