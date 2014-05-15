package model;

public class QuantifiedComponent {

	private static final String QuantityOpenBracket = "(";
	private static final String QuantityCloseBracket = ")";
	private static final String ADDING_ERROR = "Can not add the quantity of one Component to another.";

	/**
	 * Creates a quantified component with the quantity <quantity> and the
	 * component <component>.
	 */
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

	/**
	 * Returns the component of <this>.
	 */
	public Component getComponent() {
		return this.component;
	}

	public int getQuantity() {
		return this.quantity;
	}

	private void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Adds the quantity <quantity> to <this>.
	 */
	public void addQuantity(final int quantity) {
		this.setQuantity(this.getQuantity() + quantity);
	}

	/**
	 * Adds the quantity of <quantifiedComponent> to the quantity of <this> if
	 * they represent the same component. Throws an Error if they do not
	 * represent the same component.
	 */
	public void addQuantity(final QuantifiedComponent quantifiedComponent) {
		if (this.getComponent().equals(quantifiedComponent.getComponent())) {
			this.addQuantity(quantifiedComponent.getQuantity());
		} else {
			throw new Error(QuantifiedComponent.ADDING_ERROR);
		}
	}

	/**
	 * Multiplies the Quantity of <this> with the factor <factor>.
	 */
	public void multiplyQuantity(final int factor) {
		this.setQuantity(this.getQuantity() * factor);
	}

	@Override
	public boolean equals(final Object argument) {
		if (argument instanceof QuantifiedComponent) {
			final QuantifiedComponent argumentAsQualifiedComponent = (QuantifiedComponent) argument;
			return argumentAsQualifiedComponent.getQuantity() == this
					.getQuantity()
					&& argumentAsQualifiedComponent.getComponent().equals(
							this.getComponent());
		} else {
			return false;
		}
	}

	/**
	 * Returns true if <this> equals <part> or contains it as a subcomponent.
	 */
	public boolean contains(final Component part) {
		return this.getComponent().contains(part);
	}

	@Override
	public String toString() {
		return this.getComponent().toString()
				+ QuantifiedComponent.QuantityOpenBracket + this.getQuantity()
				+ QuantifiedComponent.QuantityCloseBracket;
	}

	/**
	 * Returns the materiallist of <this>.
	 */
	public MaterialList getMaterialList() {
		MaterialList result = this.getComponent().getMaterialList();
		result = result.multiply(this.getQuantity());
		return result;
	}

	/**
	 * Returns the amount of materials needed for <this>.
	 */
	public int getNumberOfMaterials() {
		return this.getComponent().getNumberOfMaterials() * this.getQuantity();
	}

	/**
	 * Returns the price of <this>.
	 */
	public int getPrice() {
		return this.getComponent().getPrice() * this.getQuantity();
	}

	public int containsHowOften(Component component) {
		return this.getComponent().containsHowOften(component) * this.getQuantity();
	}
}
