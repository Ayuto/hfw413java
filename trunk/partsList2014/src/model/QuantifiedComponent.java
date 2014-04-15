package model;

public class QuantifiedComponent {

	private static final String QuantityOpenBracket = "(";
	private static final String QuantityCloseBracket = ")";

	public static QuantifiedComponent createQuantifiedComponent(
			final int quantity, final Component component) {
		return new QuantifiedComponent(quantity, component);
	}

	private int quantity;
	final private Component component;

	private QuantifiedComponent(final int quantity, final Component component) {
		this.quantity = quantity;
		this.component = component;
	}

	public Component getComponent() {
		return component;
	}

	private int getQuantity() {
		return quantity;
	}

	private void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	public void addQuantity(final int quantity) {
		this.setQuantity(this.getQuantity() + quantity);
	}
	
	public void addQuantity(final QuantifiedComponent quantifiedComponent) {
		this.addQuantity(quantifiedComponent.getQuantity());
	}
	
	public void multiplyQuantity(final int factor) {
		this.setQuantity(this.getQuantity() * factor);
	}

	@Override
	public boolean equals(Object argument) {
		return super.equals(argument);
	}

	public boolean contains(final Component part) {
		return this.getComponent().contains(part);
	}

	@Override
	public String toString() {
		return this.getComponent().toString() + QuantityOpenBracket
				+ this.getQuantity() + QuantityCloseBracket;
	}
	
	public MaterialList getMaterialList() {
		final MaterialList result = this.getComponent().getMaterialList();
		result.multiply(this.getQuantity());
		return result;
	}

	public int getNumberOfMaterials() {
		return this.getComponent().getNumberOfMaterials() * this.getQuantity();
	}

	public int getPrice() {
		return this.getComponent().getPrice() * this.getQuantity();
	}
}
