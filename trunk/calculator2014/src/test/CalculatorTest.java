package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Addition;
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
}
