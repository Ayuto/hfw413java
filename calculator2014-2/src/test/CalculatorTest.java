package test;

import model.Addition;
import model.Division;
import model.DivisionByZeroException;
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
	
	@Before
	public void setUp() {
		this.a = Variable.createVariable("a");
		this.b = Variable.createVariable("b");
		this.c = Variable.createVariable("c");
		this.d = Variable.createVariable("d");
		
		for (int i = 0; i < 5; i++){
			this.a.up();
		}
		for (int i = 0; i < 3; i++){
			this.b.up();
		}
		for (int i = 0; i < 2; i++){
			this.c.down();
		}
		for (int i = 0; i < 5; i++){
			this.d.down();
		}
	}
	
	@Test
	public void testVariable() {
		Assert.assertEquals(5, this.a.getValue());
		Assert.assertEquals(3, this.b.getValue());
		Assert.assertEquals(-2, this.c.getValue());
		Assert.assertEquals(-5, this.d.getValue());
	}
	
	@Test
	public void testAddition() {
		final Addition aa = Addition.create(this.a, this.a);
		// 5+5
		Assert.assertEquals(10, aa.getValue());
		
		final Addition ab = Addition.create(this.a, this.b);
		// 5+3
		Assert.assertEquals(8, ab.getValue());
		
		final Addition ac = Addition.create(this.a, this.c);
		// 5+(-2)
		Assert.assertEquals(3, ac.getValue());
		
		final Addition ad = Addition.create(this.a, this.d);
		// 5+(-5)
		Assert.assertEquals(0, ad.getValue());
		
		final Addition cd = Addition.create(this.c, this.d);
		// (-2)+(-5)
		Assert.assertEquals(-7, cd.getValue());
		
		final Addition aaa = Addition.create(aa, this.a);
		// (5+5)+5
		Assert.assertEquals(15, aaa.getValue());
		
		this.a.up();
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
		final Subtraction aa = Subtraction.create(this.a, this.a);
		// 5-5
		Assert.assertEquals(0, aa.getValue());
		
		final Subtraction ab = Subtraction.create(this.a, this.b);
		// 5-3
		Assert.assertEquals(2, ab.getValue());
		
		final Subtraction ac = Subtraction.create(this.a, this.c);
		// 5-(-2)
		Assert.assertEquals(7, ac.getValue());
		
		final Subtraction ad = Subtraction.create(this.a, this.d);
		// 5-(-5)
		Assert.assertEquals(10, ad.getValue());
		
		final Subtraction cd = Subtraction.create(this.c, this.d);
		// (-2)-(-5)
		Assert.assertEquals(3, cd.getValue());
		
		final Subtraction aaa = Subtraction.create(aa, this.a);
		// (5-5)-5
		Assert.assertEquals(-5, aaa.getValue());
		
		this.a.up();
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
	final Multiplication aa = Multiplication.create(this.a, this.a);
		// 5*5
	    Assert.assertEquals(25, aa.getValue());
	   
	    final Multiplication ab = Multiplication.create(this.a, this.b);
	    // 5*3
	    Assert.assertEquals(15, ab.getValue());
	   
	    final Multiplication ac = Multiplication.create(this.a, this.c);
	    // 5*(-2)
	    Assert.assertEquals(-10, ac.getValue());
	   
	    final Multiplication ad = Multiplication.create(this.a, this.d);
	    // 5*(-5)
	    Assert.assertEquals(-25, ad.getValue());
	   
	    final Multiplication cd = Multiplication.create(this.c, this.d);
	    // (-2)*(-5)
	    Assert.assertEquals(10, cd.getValue());
	   
	    final Multiplication aac = Multiplication.create(aa, this.c);
	    // (5*5)*(-2)
	    Assert.assertEquals(-50, aac.getValue());

	    this.a.up();
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
		final Division aa = Division.create(this.a, this.a);
		// 5/5
		Assert.assertEquals(1, aa.getValue());
		
		final Division ab = Division.create(this.a, this.b);
		// 5/3
		Assert.assertEquals(1, ab.getValue());
		
		final Division ac = Division.create(this.a, this.c);
		// 5/(-2)
		Assert.assertEquals(-2, ac.getValue());
		
		final Division ad = Division.create(this.a, this.d);
		// 5/(-5)
		Assert.assertEquals(-1, ad.getValue());
		
		final Division cd = Division.create(this.c, this.d);
		// (-2)/(-5)
		Assert.assertEquals(0, cd.getValue());
		
		final Division aaa = Division.create(aa, this.a);
		// (5/5)/5
		Assert.assertEquals(0, aaa.getValue());
		
		this.a.up();
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
			Division.create(this.a, Variable.createVariable("NULL"));
			Assert.fail();
		} catch (final DivisionByZeroException e) {
		}
	}
	
	@Test
	public void testToString() {
		Assert.assertEquals("a(5)", this.a.toString());
		Assert.assertEquals("b(3)", this.b.toString());
		Assert.assertEquals("c(-2)", this.c.toString());
		Assert.assertEquals("d(-5)", this.d.toString());
		
		final Addition aa = Addition.create(this.a, this.a);
		Assert.assertEquals("(a(5)+a(5)=10)", aa.toString());
		
		final Subtraction ab = Subtraction.create(this.a, this.b);
		Assert.assertEquals("(a(5)-b(3)=2)", ab.toString());
		
		final Multiplication ac = Multiplication.create(this.a, this.c);
		Assert.assertEquals("(a(5)*c(-2)=-10)", ac.toString());
		
		final Division ad = Division.create(this.a, this.d);
		Assert.assertEquals("(a(5)/d(-5)=-1)", ad.toString());

		final Addition aaa = Addition.create(aa, this.a);
		Assert.assertEquals("((a(5)+a(5)=10)+a(5)=15)", aaa.toString());
	}
	
	@Test
	public void testMixed()
	{
		final Addition aa = Addition.create(this.a, this.a);
		final Subtraction aab = Subtraction.create(aa, this.b);
		
		// (5+5)-3
		Assert.assertEquals(7, aab.getValue());
		
		final Division aac = Division.create(aa, this.c);
		// (5+5)/(-2)
		Assert.assertEquals(-5, aac.getValue());
		
		final Multiplication aacd = Multiplication.create(aac, this.d);
		// ((5+5)/(-2))*(-5)
		Assert.assertEquals(25, aacd.getValue());
	}
	
	@Test
	public void testContains()
	{
		Assert.assertEquals(true, this.a.contains(this.a));
		Assert.assertEquals(false, this.a.contains(this.b));
		
		// 5+5
		final Addition aa = Addition.create(this.a, this.a);
		Assert.assertEquals(true, aa.contains(aa));
		Assert.assertEquals(true, aa.contains(this.a));
		Assert.assertEquals(false, aa.contains(this.b));
		
		// (5+5)+3
		final Addition aab = Addition.create(aa, this.b);
		Assert.assertEquals(true, aab.contains(aa));
	}
}
