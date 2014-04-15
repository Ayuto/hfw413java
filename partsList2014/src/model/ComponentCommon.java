package model;

public abstract class ComponentCommon implements Component {

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
	public void setPrice(int price) {
		this.price = price;
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
	public boolean equals(Object argument) {
		return super.equals(argument);
	}
}
