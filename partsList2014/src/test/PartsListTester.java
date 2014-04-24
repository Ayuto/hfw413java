package test;

import java.util.Vector;

import model.Material;
import model.MaterialList;
import model.Product;
import model.QuantifiedComponent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PartsListTester {

	private Material m1;
	private Material m2;
	private Material m3;

	private Product p0;
	private Product p00;
	private Product p000;
	private Product p1;
	private Product p2;
	private Product p3;
	private Product p4;

	@Before
	public void setUp() throws Exception {
		this.m1 = Material.create("m1", 1);
		this.m2 = Material.create("m2", 2);
		this.m3 = Material.create("m3", 3);

		this.p0 = Product.create("p0", 50);
		
		this.p00 = Product.create("p00", 10);
		this.p00.addPart(this.p0, 2);
		
		this.p000 = Product.create("p000", 25);
		this.p000.addPart(this.p00, 1);
		this.p000.addPart(this.p0, 5);

		this.p1 = Product.create("p1", 5);
		this.p1.addPart(this.m1, 1);
		this.p1.addPart(this.m2, 2);
		this.p1.addPart(this.m3, 3);

		this.p2 = Product.create("p2", 6);
		this.p2.addPart(this.p1, 2);
		this.p2.addPart(this.m3, 10);

		this.p3 = Product.create("p3", 7);
		this.p3.addPart(this.p1, 5);
		this.p3.addPart(this.p2, 2);
		this.p3.addPart(this.m1, 1);

		this.p4 = Product.create("p4", 100);
		this.p4.addPart(this.p3, 10);
		this.p4.addPart(this.m2, 2);
	}

	@Test
	public void testGesamtpreis() {
		Assert.assertEquals(1, this.m1.getPrice());
		Assert.assertEquals(2, this.m2.getPrice());
		Assert.assertEquals(3, this.m3.getPrice());
		Assert.assertEquals(50, this.p0.getPrice());
		Assert.assertEquals(110, this.p00.getPrice());
		Assert.assertEquals(385, this.p000.getPrice());
		Assert.assertEquals(19, this.p1.getPrice());
		Assert.assertEquals(74, this.p2.getPrice());
		Assert.assertEquals(5 * this.p1.getPrice() + 2 * this.p2.getPrice() + 1
				* this.m1.getPrice() + 7, this.p3.getPrice());
		Assert.assertEquals(2614, this.p4.getPrice());
	}

	@Test
	public void testMaterialList() {
		// m1 MaterialList
		Vector<QuantifiedComponent> expected = new Vector<QuantifiedComponent>();
		expected.add(QuantifiedComponent.createQuantifiedComponent(1, this.m1));
		Assert.assertEquals(expected, this.m1.getMaterialList().getData());

		// m2 MaterialList
		expected = new Vector<QuantifiedComponent>();
		expected.add(QuantifiedComponent.createQuantifiedComponent(1, this.m2));
		Assert.assertEquals(expected, this.m2.getMaterialList().getData());

		// m3 MaterialList
		expected = new Vector<QuantifiedComponent>();
		expected.add(QuantifiedComponent.createQuantifiedComponent(1, this.m3));
		Assert.assertEquals(expected, this.m3.getMaterialList().getData());

		// p0 MaterialList
		expected = new Vector<QuantifiedComponent>();
		Assert.assertEquals(expected, this.p0.getMaterialList().getData());
		
		// p00 MaterialList
		Assert.assertEquals(expected, this.p00.getMaterialList().getData());
		
		// p000 MaterialList
		Assert.assertEquals(expected, this.p000.getMaterialList().getData());

		// p1 MaterialList
		expected = new Vector<QuantifiedComponent>();
		expected.add(QuantifiedComponent.createQuantifiedComponent(1, this.m1));
		expected.add(QuantifiedComponent.createQuantifiedComponent(2, this.m2));
		expected.add(QuantifiedComponent.createQuantifiedComponent(3, this.m3));
		Assert.assertEquals(expected, this.p1.getMaterialList().getData());

		// p2 MaterialList
		expected = new Vector<QuantifiedComponent>();
		expected.add(QuantifiedComponent.createQuantifiedComponent(2, this.m1));
		expected.add(QuantifiedComponent.createQuantifiedComponent(4, this.m2));
		expected.add(QuantifiedComponent.createQuantifiedComponent(16, this.m3));
		Assert.assertEquals(expected, this.p2.getMaterialList().getData());

		// p3 MaterialList
		final MaterialList result = MaterialList
				.create(new Vector<QuantifiedComponent>());
		MaterialList list = this.p1.getMaterialList();
		list.multiply(5);
		result.add(list);
		list = this.p2.getMaterialList();
		list.multiply(2);
		result.add(list);
		list = this.m1.getMaterialList();
		result.add(list);
		Assert.assertEquals(result.getData(), this.p3.getMaterialList()
				.getData());

		// p4 MaterialList
		result.multiply(10);
		list = this.m2.getMaterialList();
		list.multiply(2);
		result.add(list);
		Assert.assertEquals(result.getData(), this.p4.getMaterialList()
				.getData());
	}

}
