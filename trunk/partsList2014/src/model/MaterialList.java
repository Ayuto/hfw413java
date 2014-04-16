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
	 * Multiplies the amount of a components this <this> with the factor
	 * <value>.
	 */
	public void multiply(final int value) {
		final Iterator<QuantifiedComponent> i = this.getData().iterator();
		while (i.hasNext()) {
			final QuantifiedComponent current = i.next();
			current.multiplyQuantity(value);
		}
	}

	/**
	 * Adds the components from <list> to <this>.
	 */
	public void add(final MaterialList list) {
		final Iterator<QuantifiedComponent> i = list.getData().iterator();
		while (i.hasNext()) {
			final QuantifiedComponent current = i.next();
			this.add(current);
		}
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
}
