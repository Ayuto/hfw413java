package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Addition;
import model.DivisionByZeroException;
import model.Subtraction;
import model.Division;
import model.Multiplication;
import model.Variable;

public class CalculatorTest {

	private Variable a;
	private Variable b; 
	private Variable c; 
	private Variable d;
	
	@Before
	public void setUp() {
		a = Variable.createVariable("a");
		b = Variable.createVariable("b");
		c = Variable.createVariable("c");
		d = Variable.createVariable("d");
		
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
		Assert.assertEquals(5, a.getValue());
		Assert.assertEquals(3, b.getValue());
		Assert.assertEquals(-2, c.getValue());
		Assert.assertEquals(-5, d.getValue());
	}
	
	@Test
	public void testAddition() {
		Addition aa = Addition.create(a, a);
		// 5+5
		Assert.assertEquals(10, aa.getValue());
		
		Addition ab = Addition.create(a, b);
		// 5+3
		Assert.assertEquals(8, ab.getValue());
		
		Addition ac = Addition.create(a, c);
		// 5+(-2)
		Assert.assertEquals(3, ac.getValue());
		
		Addition ad = Addition.create(a, d);
		// 5+(-5)
		Assert.assertEquals(0, ad.getValue());
		
		Addition cd = Addition.create(c, d);
		// (-2)+(-5)
		Assert.assertEquals(-7, cd.getValue());
		
		Addition aaa = Addition.create(aa, a);
		// (5+5)+5
		Assert.assertEquals(15, aaa.getValue());
		
		a.up();
		// 6+6
		Assert.assertEquals(12, aa.getValue());
		// 6+3
		Assert.assertEquals(9, ab.getValue());
		// 6+(-2)
		Assert.assertEquals(4, ac.getValue());
		// 6+(-5)
		Assert.assertEquals(1, ad.getValue());
		// (6+6)+6
		Assert.assertEquals(18, aaa.getValue());
	}
	
	@Test
	public void testSubtract()
	{
		Subtraction aa = Subtraction.create(a, a);
		// 5-5
		Assert.assertEquals(0, aa.getValue());
		
		Subtraction ab = Subtraction.create(a, b);
		// 5-3
		Assert.assertEquals(2, ab.getValue());
		
		Subtraction ac = Subtraction.create(a, c);
		// 5-(-2)
		Assert.assertEquals(7, ac.getValue());
		
		Subtraction ad = Subtraction.create(a, d);
		// 5-(-5)
		Assert.assertEquals(10, ad.getValue());
		
		Subtraction cd = Subtraction.create(c, d);
		// (-2)-(-5)
		Assert.assertEquals(3, cd.getValue());
		
		Subtraction aaa = Subtraction.create(aa, a);
		// (5-5)-5
		Assert.assertEquals(-5, aaa.getValue());
		
		a.up();
		// 6-6
		Assert.assertEquals(0, aa.getValue());
		// 6-3
		Assert.assertEquals(3, ab.getValue());
		// 6-(-2)
		Assert.assertEquals(8, ac.getValue());
		// 6-(-5)
		Assert.assertEquals(11, ad.getValue());
		// (6-6)-6
		Assert.assertEquals(-6, aaa.getValue());
	}
	
	@Test
    public void testMultiplication() {
	Multiplication aa = Multiplication.create(a, a);
		// 5*5
	    Assert.assertEquals(25, aa.getValue());
	   
	    Multiplication ab = Multiplication.create(a, b);
	    // 5*3
	    Assert.assertEquals(15, ab.getValue());
	   
	    Multiplication ac = Multiplication.create(a, c);
	    // 5*(-2)
	    Assert.assertEquals(-10, ac.getValue());
	   
	    Multiplication ad = Multiplication.create(a, d);
	    // 5*(-5)
	    Assert.assertEquals(-25, ad.getValue());
	   
	    Multiplication cd = Multiplication.create(c, d);
	    // (-2)*(-5)
	    Assert.assertEquals(10, cd.getValue());
	   
	    Multiplication aac = Multiplication.create(aa, c);
	    // (5*5)*(-2)
	    Assert.assertEquals(-50, aac.getValue());

	    a.up();
	    // 6*6
		Assert.assertEquals(36, aa.getValue());
		// 6*3
		Assert.assertEquals(18, ab.getValue());
		// 6*(-2)
		Assert.assertEquals(-12, ac.getValue());
		// 6*(-5)
		Assert.assertEquals(-30, ad.getValue());
		// (6*6)*(-2)
		Assert.assertEquals(-72, aac.getValue());
	}

	
	@Test
	public void testDivision()
	{
		Division aa = Division.create(a, a);
		// 5/5
		Assert.assertEquals(1, aa.getValue());
		
		Division ab = Division.create(a, b);
		// 5/3
		Assert.assertEquals(1, ab.getValue());
		
		Division ac = Division.create(a, c);
		// 5/(-2)
		Assert.assertEquals(-2, ac.getValue());
		
		Division ad = Division.create(a, d);
		// 5/(-5)
		Assert.assertEquals(-1, ad.getValue());
		
		Division cd = Division.create(c, d);
		// (-2)/(-5)
		Assert.assertEquals(0, cd.getValue());
		
		Division aaa = Division.create(aa, a);
		// (5/5)/5
		Assert.assertEquals(0, aaa.getValue());
		
		a.up();
		// 6/6
		Assert.assertEquals(1, aa.getValue());
		// 6/3
		Assert.assertEquals(2, ab.getValue());
		// 6/(-2)
		Assert.assertEquals(-3, ac.getValue());
		// 6/(-5)
		Assert.assertEquals(-1, ad.getValue());
		// (6/6)/6
		Assert.assertEquals(0, aaa.getValue());

		// 6/0
		try {
			Division.create(a, Variable.createVariable("NULL"));
			Assert.fail();
		} catch (final DivisionByZeroException e) {
		}
	}
}
