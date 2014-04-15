package model;

import java.util.HashMap;
import java.util.Vector;

public class PartsList {

	private static final String DoubleDefinitionMessage = "Name bereits vorhanden!";

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

	public void createMaterial(final String name, final int price)
			throws Exception {
		if (this.getComponentsMap().containsKey(name))
			throw new Exception(DoubleDefinitionMessage);
		final Material newMaterial = Material.create(name, price);
		this.getComponentsMap().put(name, newMaterial);
	}

	public void createProduct(final String name, final int price)
			throws Exception {
		if (this.getComponentsMap().containsKey(name))
			throw new Exception(DoubleDefinitionMessage);
		final Product newProduct = Product.create(name, price);
		this.getComponentsMap().put(name, newProduct);
	}

	public void addPart(final Component whole, final Component part,
			final int amount) throws Exception {
		whole.addPart(part, amount);
	}

	public int getPartCount(final Component component) {
		return component.getNumberOfMaterials();
	}

	public Vector<Component> getComponents() {
		return new Vector<Component>(this.getComponentsMap().values());
	}

	public Vector<QuantifiedComponent> getParts(final Component component) {
		return component.getDirectParts();
	}

	public Vector<QuantifiedComponent> getMaterialList(final Component component) {
		return component.getMaterialList().getData();
	}

	public String getOverallPrice(final Component component) {
		return String.valueOf(component.getPrice());
	}

	public void changePrice(final Component component, final int newPrice) {
		component.setPrice(newPrice);
	}
}
