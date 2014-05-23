package test;

import model.Addition;
import model.Division;
import model.IntValue;
import model.Multiplication;
import model.Subtraction;
import model.Variable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	private Variable a;
	private Variable b; 
	private Variable c; 
	private Variable d;
	private Variable z;
	
	@Before
	public void setUp() {
		a = Variable.createVariable("a");
		b = Variable.createVariable("b");
		c = Variable.createVariable("c");
		d = Variable.createVariable("d");
		z = Variable.createVariable("z");
		
		for (int i = 0; i < 5; i++){
			a.up();
		}
		for (int i = 0; i < 3; i++){
			b.up();
		}
		for (int i = 0; i < 2; i++){
			c.down();
		}
		for (int i = 0; i < 5; i++){
			d.down();
		}
	}
	
	@Test
	public void testVariable() {
		Assert.assertEquals(new IntValue(5), a.getValue());
		Assert.assertEquals(new IntValue(3), b.getValue());
		Assert.assertEquals(new IntValue(-2), c.getValue());
		Assert.assertEquals(new IntValue(-5), d.getValue());
	}
	
	@Test
	public void testAddition() {
		Addition aa = Addition.create(a, a);
		// 5+5
		Assert.assertEquals(new IntValue(10), aa.getValue());
		
		Addition ab = Addition.create(a, b);
		// 5+3
		Assert.assertEquals(new IntValue(8), ab.getValue());
		
		Addition ac = Addition.create(a, c);
		// 5+(-2)
		Assert.assertEquals(new IntValue(3), ac.getValue());
		
		Addition ad = Addition.create(a, d);
		// 5+(-5)
		Assert.assertEquals(new IntValue(0), ad.getValue());
		
		Addition cd = Addition.create(c, d);
		// (-2)+(-5)
		Assert.assertEquals(new IntValue(-7), cd.getValue());
		
		Addition aaa = Addition.create(aa, a);
		// (5+5)+5
		Assert.assertEquals(new IntValue(15), aaa.getValue());
		
		a.up();
		// 6+6
		Assert.assertEquals(new IntValue(12), aa.getValue());
		// 6+3
		Assert.assertEquals(new IntValue(9), ab.getValue());
		// 6+(-2)
		Assert.assertEquals(new IntValue(4), ac.getValue());
		// 6+(-5)
		Assert.assertEquals(new IntValue(1), ad.getValue());
		// (6+6)+6
		Assert.assertEquals(new IntValue(18), aaa.getValue());
	}
	
	@Test
	public void testSubtract()
	{
		Subtraction aa = Subtraction.create(a, a);
		// 5-5
		Assert.assertEquals(new IntValue(0), aa.getValue());
		
		Subtraction ab = Subtraction.create(a, b);
		// 5-3
		Assert.assertEquals(new IntValue(2), ab.getValue());
		
		Subtraction ac = Subtraction.create(a, c);
		// 5-(-2)
		Assert.assertEquals(new IntValue(7), ac.getValue());
		
		Subtraction ad = Subtraction.create(a, d);
		// 5-(-5)
		Assert.assertEquals(new IntValue(10), ad.getValue());
		
		Subtraction cd = Subtraction.create(c, d);
		// (-2)-(-5)
		Assert.assertEquals(new IntValue(3), cd.getValue());
		
		Subtraction aaa = Subtraction.create(aa, a);
		// (5-5)-5
		Assert.assertEquals(new IntValue(-5), aaa.getValue());
		
		a.up();
		// 6-6
		Assert.assertEquals(new IntValue(0), aa.getValue());
		// 6-3
		Assert.assertEquals(new IntValue(3), ab.getValue());
		// 6-(-2)
		Assert.assertEquals(new IntValue(8), ac.getValue());
		// 6-(-5)
		Assert.assertEquals(new IntValue(11), ad.getValue());
		// (6-6)-6
		Assert.assertEquals(new IntValue(-6), aaa.getValue());
	}
	
	@Test
    public void testMultiplication() {
	Multiplication aa = Multiplication.create(a, a);
		// 5*5
	    Assert.assertEquals(new IntValue(25), aa.getValue());
	   
	    Multiplication ab = Multiplication.create(a, b);
	    // 5*3
	    Assert.assertEquals(new IntValue(15), ab.getValue());
	   
	    Multiplication ac = Multiplication.create(a, c);
	    // 5*(-2)
	    Assert.assertEquals(new IntValue(-10), ac.getValue());
	   
	    Multiplication ad = Multiplication.create(a, d);
	    // 5*(-5)
	    Assert.assertEquals(new IntValue(-25), ad.getValue());
	   
	    Multiplication cd = Multiplication.create(c, d);
	    // (-2)*(-5)
	    Assert.assertEquals(new IntValue(10), cd.getValue());
	   
	    Multiplication aac = Multiplication.create(aa, c);
	    // (5*5)*(-2)
	    Assert.assertEquals(new IntValue(-50), aac.getValue());

	    a.up();
	    // 6*6
		Assert.assertEquals(new IntValue(36), aa.getValue());
		// 6*3
		Assert.assertEquals(new IntValue(18), ab.getValue());
		// 6*(-2)
		Assert.assertEquals(new IntValue(-12), ac.getValue());
		// 6*(-5)
		Assert.assertEquals(new IntValue(-30), ad.getValue());
		// (6*6)*(-2)
		Assert.assertEquals(new IntValue(-72), aac.getValue());
	}

	
	@Test
	public void testDivision()
	{
		Division aa = Division.create(a, a);
		// 5/5
		Assert.assertEquals(new IntValue(1), aa.getValue());
		
		Division ab = Division.create(a, b);
		// 5/3
		Assert.assertEquals(new IntValue(1), ab.getValue());
		
		Division ac = Division.create(a, c);
		// 5/(-2)
		Assert.assertEquals(new IntValue(-2), ac.getValue());
		
		Division ad = Division.create(a, d);
		// 5/(-5)
		Assert.assertEquals(new IntValue(-1), ad.getValue());
		
		Division cd = Division.create(c, d);
		// (-2)/(-5)
		Assert.assertEquals(new IntValue(0), cd.getValue());
		
		Division aaa = Division.create(aa, a);
		// (5/5)/5
		Assert.assertEquals(new IntValue(0), aaa.getValue());
		
		a.up();
		// 6/6
		Assert.assertEquals(new IntValue(1), aa.getValue());
		// 6/3
		Assert.assertEquals(new IntValue(2), ab.getValue());
		// 6/(-2)
		Assert.assertEquals(new IntValue(-3), ac.getValue());
		// 6/(-5)
		Assert.assertEquals(new IntValue(-1), ad.getValue());
		// (6/6)/6
		Assert.assertEquals(new IntValue(0), aaa.getValue());
		
		// 6/0
		Division a0 = Division.create(a, z);
		Assert.assertFalse(a0.getValue().isValid());
	}
	
	@Test
	public void testToString() {
		Assert.assertEquals("a(5)", a.toString());
		Assert.assertEquals("b(3)", b.toString());
		Assert.assertEquals("c(-2)", c.toString());
		Assert.assertEquals("d(-5)", d.toString());
		
		Addition aa = Addition.create(a, a);
		Assert.assertEquals("(a(5)+a(5)=10)", aa.toString());
		
		Subtraction ab = Subtraction.create(a, b);
		Assert.assertEquals("(a(5)-b(3)=2)", ab.toString());
		
		Multiplication ac = Multiplication.create(a, c);
		Assert.assertEquals("(a(5)*c(-2)=-10)", ac.toString());
		
		Division ad = Division.create(a, d);
		Assert.assertEquals("(a(5)/d(-5)=-1)", ad.toString());

		Addition aaa = Addition.create(aa, a);
		Assert.assertEquals("((a(5)+a(5)=10)+a(5)=15)", aaa.toString());
	}
	
	@Test
	public void testMixed()
	{
		Addition aa = Addition.create(a, a);
		Subtraction aab = Subtraction.create(aa, b);
		
		// (5+5)-3
		Assert.assertEquals(new IntValue(7), aab.getValue());
		
		Division aac = Division.create(aa, c);
		// (5+5)/(-2)
		Assert.assertEquals(new IntValue(-5), aac.getValue());
		
		Multiplication aacd = Multiplication.create(aac, d);
		// ((5+5)/(-2))*(-5)
		Assert.assertEquals(new IntValue(25), aacd.getValue());
	}
	
	@Test
	public void testContains()
	{
		Assert.assertEquals(true, a.contains(a));
		Assert.assertEquals(false, a.contains(b));
		
		// 5+5
		Addition aa = Addition.create(a, a);
		Assert.assertEquals(true, aa.contains(aa));
		Assert.assertEquals(true, aa.contains(a));
		Assert.assertEquals(false, aa.contains(b));
		
		// (5+5)+3
		Addition aab = Addition.create(aa, b);
		Assert.assertEquals(true, aab.contains(aa));
	}
	
	@Test
	public void testSubstitute()
	{
		// aa = (5+5)
		Addition aa = Addition.create(a, a);
		
		// aab = (5+5)+3
		Addition aab = Addition.create(aa,  b);
		
		// aab = (-2+-2)+3
		aab.substitute(a, c);
		
		Assert.assertEquals(new IntValue(-1), aab.getValue());
		
		// aab = (-2+-2)+-5
		aab.substitute(b, d);
		
		Assert.assertEquals(new IntValue(-9), aab.getValue());
		
		// aab = (-2+-2)+-6
		d.down();
		
		Assert.assertEquals(new IntValue(-10), aab.getValue());
		
		// aab = (-1+-1)+-6
		c.up();
		
		Assert.assertEquals(new IntValue(-8), aab.getValue());
		
		// ab = (5+3)
		Addition ab = Addition.create(a, b);
		
		// aab = (-1+-1)+(5+3)
		aab.substitute(d, ab);
		
		Assert.assertEquals(new IntValue(6), aab.getValue());
		
		// aab = (-1+-1)+(6+3)
		a.up();

		Assert.assertEquals(new IntValue(7), aab.getValue());
	}
}
