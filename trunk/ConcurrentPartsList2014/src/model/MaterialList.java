package model;

import java.util.Iterator;
import java.util.Vector;

public class MaterialList {

	/**
	 * Creates a new MaterialList with the given <data>.
	 */
	public static MaterialList create(final Vector<QuantifiedComponent> data) {
		return new MaterialList(data);
	}

	private final Vector<QuantifiedComponent> data;

	private MaterialList(final Vector<QuantifiedComponent> data) {
		this.data = data;
	}

	/**
	 * Multiplies the amount of a component <this> with the factor
	 * <value>.
	 */
	public MaterialList multiply(final int value) {
		final MaterialList result = MaterialList.create(new Vector<QuantifiedComponent>());
		final Iterator<QuantifiedComponent> i = this.getData().iterator();
		while (i.hasNext()) {
			final QuantifiedComponent current = i.next();
			result.add(QuantifiedComponent.createQuantifiedComponent(current.getQuantity()*value, current.getComponent()));
		}
		return result;
	}

	/**
	 * Adds the components from <list> to <this>.
	 */
	public MaterialList add(final MaterialList list) {
		final MaterialList result = MaterialList.create(new Vector<QuantifiedComponent>());
		result.getData().addAll(this.getData());
		final Iterator<QuantifiedComponent> i = list.getData().iterator();
		while (i.hasNext()) {
			final QuantifiedComponent current = i.next();
			result.add(current);
		}
		return result;
	}

	/**
	 * Adds the component <quantifiedComponent> to <this>.
	 */
	public void add(final QuantifiedComponent quantifiedComponent) {
		final Iterator<QuantifiedComponent> i = this.getData().iterator();
		while (i.hasNext()) {
			final QuantifiedComponent current = i.next();
			if (current.getComponent().equals(
					quantifiedComponent.getComponent())) {
				current.addQuantity(quantifiedComponent);
				return;
			}
		}
		this.getData().add(quantifiedComponent);
	}

	/**
	 * Returns the data of <this>.
	 */
	public Vector<QuantifiedComponent> getData() {
		return this.data;
	}
	
	@Override
	public boolean equals(final Object argument) {
		if (argument instanceof MaterialList) {
			final MaterialList argumentAsMaterialList = (MaterialList) argument;
			
			for (final QuantifiedComponent current : this.getData()) {
				if (!argumentAsMaterialList.getData().contains(current)) {
					return false;
				}
			}

			for (final QuantifiedComponent current : argumentAsMaterialList.getData()) {
				if (!this.getData().contains(current)) {
					return false;
				}
			}
			
			return true;
		}
		return false;
	}
}
