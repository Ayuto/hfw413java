package test;

import java.util.List;
import java.util.Vector;

import model.State;

import junit.framework.TestCase;

public class Test extends TestCase {
	
	public void test1(){
		CharacterType c$Type = CharacterType.create('c');
		CharacterType d$Type = CharacterType.create('d');
		CharacterType e$Type = CharacterType.create('e');
		List<Character> testInput$1 = new Vector<Character>();
		testInput$1.add('c'); // c
		model.Automaton<Character, CharacterType> aut = model.Automaton.create(c$Type); //sa -- c --> se
		assertEquals(true, aut.recognizes(testInput$1));
		testInput$1.add('c'); // cc
		assertEquals(false, aut.recognizes(testInput$1));
		aut.addTransition(aut.getStopState(), c$Type, aut.getStopState()); //se -- c --> se
		assertEquals(true, aut.recognizes(testInput$1));
		testInput$1.add('d'); // ccd
		assertEquals(false, aut.recognizes(testInput$1));
		aut.addTransition(aut.getStopState(), d$Type, aut.getStartState()); //se -- d --> sa
		assertEquals(false, aut.recognizes(testInput$1));
		testInput$1.add('c'); // ccdc
		assertEquals(true, aut.recognizes(testInput$1));
		State s1 = State.create();
		aut.addTransition(aut.getStartState(), e$Type, s1); //sa -- e --> s1
		testInput$1.add('e'); // ccdce
		assertEquals(false, aut.recognizes(testInput$1));		
		aut.addTransition(s1, e$Type, aut.getStopState()); //s1 -- e --> se
		assertEquals(false, aut.recognizes(testInput$1));		
		testInput$1.add('e'); // ccdcee
		assertEquals(false, aut.recognizes(testInput$1));		
		aut.addTransition(aut.getStopState(), e$Type, aut.getStartState()); //se -- e --> sa
		assertEquals(false, aut.recognizes(testInput$1));		
		testInput$1.add('e'); // ccdceee
		assertEquals(true, aut.recognizes(testInput$1));		
		testInput$1.add('e'); // ccdceeee
		testInput$1.add('e'); // ccdceeeee
		testInput$1.add('e'); // ccdceeeeee
		assertEquals(true, aut.recognizes(testInput$1));		
		aut.addTransition(aut.getStopState(), e$Type, s1); //se -- e --> s1
		assertEquals(true, aut.recognizes(testInput$1));		
		testInput$1.add('e'); // ccdceeeeeee
		assertEquals(true, aut.recognizes(testInput$1));		
		testInput$1.add('e'); // ccdceeeeeeee
		assertEquals(true, aut.recognizes(testInput$1));		
		testInput$1.add('d'); // ccdceeeeeeeed
		assertEquals(false, aut.recognizes(testInput$1));		
		testInput$1.add('e'); // ccdceeeeeeeede
		assertEquals(false, aut.recognizes(testInput$1));		
		testInput$1.add('e'); // ccdceeeeeeeedee
		assertEquals(true, aut.recognizes(testInput$1));		
	}
}
