package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class Product extends ComponentCommon {

	private static final String CycleMessage = "Zyklen sind in der Aufbaustruktur nicht erlaubt!";

	/**
	 * Creates a new Product with the name <name>, the price <price> 
	 * and without any subcomponents.
	 */
	public static Product create(final String name, final int price) {
		return new Product(name, price,
				new HashMap<String, QuantifiedComponent>());
	}

	private final HashMap<String, QuantifiedComponent> components;

	protected Product(final String name, final int price,
			final HashMap<String, QuantifiedComponent> components) {
		super(name, price);
		this.components = components;
	}

	@Override
	public void addPart(final Component part, final int amount)
			throws Exception {
		if (part.contains(this))
			throw new Exception(CycleMessage);
		final String partName = part.getName();
		if (this.getComponents().containsKey(partName)) {
			final QuantifiedComponent oldQuantification = this.getComponents()
					.get(partName);
			oldQuantification.addQuantity(amount);
		} else {
			this.getComponents()
					.put(partName,
							QuantifiedComponent.createQuantifiedComponent(
									amount, part));
		}
	}

	private HashMap<String, QuantifiedComponent> getComponents() {
		return this.components;
	}

	@Override
	public boolean contains(final Component component) {
		if (this.equals(component))
			return true;
		final Iterator<QuantifiedComponent> i = this.getComponents().values()
				.iterator();
		while (i.hasNext()) {
			final QuantifiedComponent current = i.next();
			if (current.contains(component))
				return true;
		}
		return false;
	}

	@Override
	public Vector<QuantifiedComponent> getDirectParts() {
		return new Vector<QuantifiedComponent>(this.getComponents().values());
	}

	@Override
	public int getNumberOfMaterials() {
		int result = 0;
		final Iterator<QuantifiedComponent> i = this.getComponents().values()
				.iterator();
		while (i.hasNext()) {
			final QuantifiedComponent current = i.next();
			result = result + current.getNumberOfMaterials();
		}
		return result;
	}

	public int getPrice() {
		int result = super.getPrice();
		final Iterator<QuantifiedComponent> subComponents = this
				.getComponents().values().iterator();
		while (subComponents.hasNext()) {
			final QuantifiedComponent current = subComponents.next();
			result += current.getPrice();
		}
		return result;
	}

	@Override
	public MaterialList getMaterialList() {
		final Iterator<QuantifiedComponent> i = this.getDirectParts().iterator();
		final MaterialList result = MaterialList.create(new Vector<QuantifiedComponent>());
		while(i.hasNext()){
			QuantifiedComponent current = i.next();
			result.add(current.getMaterialList());
		}
		return result;
	}
}
