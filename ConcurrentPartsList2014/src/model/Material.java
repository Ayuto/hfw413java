package model;

import java.util.Vector;

public class Material extends ComponentCommon {

	private static final String UnstructuredMaterialMessage = "Materialien haben kein Struktur!";

	public static Material create(final String name, final int price) {
		return new Material(name, price);
	}

	public Material(final String name, final int price) {
		super(name, price);
	}

	@Override
	public void addPart(final Component part, final int amount)
			throws Exception {
		throw new Exception(Material.UnstructuredMaterialMessage);
	}

	@Override
	public boolean contains(final Component component) {
		return this.equals(component);
	}

	@Override
	public Vector<QuantifiedComponent> getDirectParts() {
		return new Vector<QuantifiedComponent>();
	}

	@Override
	public int getNumberOfMaterials() {
		return 1;
	}

	@Override
	public MaterialList getMaterialList() {
		final Vector<QuantifiedComponent> result = new Vector<QuantifiedComponent>();
		result.add(QuantifiedComponent.createQuantifiedComponent(1, this));
		return MaterialList.create(result);
	}

	@Override
	public boolean equals(final Object argument) {
		if (argument instanceof Material) {
			final Material argumentAsMaterial = (Material) argument;
			return argumentAsMaterial.getName().equals(this.getName())
					&& argumentAsMaterial.getPrice() == this.getPrice();
		} else {
			return false;
		}
	}

	@Override
	public int containsHowOften(Component component) {
		return this.equals(component) ? 1 : 0;
	}
}
