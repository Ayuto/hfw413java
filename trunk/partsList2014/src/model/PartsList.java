package model;

import java.util.HashMap;
import java.util.Vector;

public class PartsList {

	private static final String DoubleDefinitionMessage = "Name bereits vorhanden!";

	/**
	 * Creates a new PartsList.
	 */
	public static PartsList create() {
		return new PartsList(new HashMap<String, Component>());
	}

	final private HashMap<String, Component> componentsMap;

	private PartsList(final HashMap<String, Component> componentsMap) {
		this.componentsMap = componentsMap;
	}

	@Override
	public boolean equals(Object argument) {
		return super.equals(argument);
	}

	private HashMap<String, Component> getComponentsMap() {
		return this.componentsMap;
	}

	/**
	 * Creates a new material with the name <name> and the price <price> and
	 * adds it to the PartsList. 
	 * Throws an Exception if the name is already in use.
	 */
	public void createMaterial(final String name, final int price)
			throws Exception {
		if (this.getComponentsMap().containsKey(name))
			throw new Exception(DoubleDefinitionMessage);
		final Material newMaterial = Material.create(name, price);
		this.getComponentsMap().put(name, newMaterial);
	}

	/**
	 * Creates a new product with the name <name> and the price <price> and
	 * adds it to the PartsList. The product has no subcomponents after creation.
	 * Throws an Exception if the name is already in use.
	 */
	public void createProduct(final String name, final int price)
			throws Exception {
		if (this.getComponentsMap().containsKey(name))
			throw new Exception(DoubleDefinitionMessage);
		final Product newProduct = Product.create(name, price);
		this.getComponentsMap().put(name, newProduct);
	}

	/**
	 * Adds the component <part> <amount> times to the component <whole>.
	 * Throws an Exception if <part> already contains <whole>.
	 */
	public void addPart(final Component whole, final Component part,
			final int amount) throws Exception {
		whole.addPart(part, amount);
	}

	/**
	 * Returns the amount of materials from <component>.
	 */
	public int getPartCount(final Component component) {
		return component.getNumberOfMaterials();
	}

	/**
	 * Returns a vector with all components.
	 */
	public Vector<Component> getComponents() {
		return new Vector<Component>(this.getComponentsMap().values());
	}

	/**
	 * Returns a vector with all quantified parts from <component>.
	 */
	public Vector<QuantifiedComponent> getParts(final Component component) {
		return component.getDirectParts();
	}

	/**
	 * Returns a vector with all quantified materials needed for <component>.
	 */
	public Vector<QuantifiedComponent> getMaterialList(final Component component) {
		return component.getMaterialList().getData();
	}

	/**
	 * Returns the overall price of <component> as a String.
	 */
	public String getOverallPrice(final Component component) {
		return String.valueOf(component.getPrice());
	}

	/**
	 * Changes the price of <component> to <newPrice>.
	 */
	public void changePrice(final Component component, final int newPrice) {
		component.setPrice(newPrice);
	}
}
