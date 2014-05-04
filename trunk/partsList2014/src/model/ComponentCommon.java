package model;

import events.PriceChangedEvent;
import observer.Observee;

public abstract class ComponentCommon extends Observee implements Component {

	private final String name;
	private int price;

	protected ComponentCommon(final String name, final int price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public int getPrice() {
		return this.price;
	}

	@Override
	public void setPrice(final int price) {
		this.price = price;
		this.notifyObservers(PriceChangedEvent.create());
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public abstract boolean equals(final Object argument);
}
