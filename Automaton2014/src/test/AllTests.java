package test;

import model.Type;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RegularExpressionCharacterTest.class, Test.class, RegularExpressionIntegerTest.class, RegularExpressionStringTest.class })
public class AllTests {

}

class CharacterType implements Type<Character> {
	public static CharacterType create(char c) {
		return new CharacterType(c);
	}
	private char character;
	private CharacterType(char c) {
		this.character = c;
	}
	public boolean isTypeFor(Character character) {
		return this.character == character;
	}
}

class IntegerType implements Type<Integer> {
	public static IntegerType create(int value) {
		return new IntegerType(value);
	}
	private int value;
	private IntegerType(int value) {
		this.value = value;
	}
	public boolean isTypeFor(Integer element) {
		return this.value == element;
	}
}

class StringType implements Type<String> {
	public static StringType create(String str) {
		return new StringType(str);
	}
	private String element;
	private StringType(String str) {
		this.element = str;
	}
	public boolean isTypeFor(String element) {
		return this.element.equals(element);
	}
}
