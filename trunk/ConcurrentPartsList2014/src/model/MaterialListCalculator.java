package model;

public class MaterialListCalculator extends Thread {
	private final QuantifiedComponent current;
	private final Product product;

	public MaterialListCalculator(final Product product, final QuantifiedComponent current) {
		this.product = product;
		this.current = current;
	}

	@Override
	public void run() {
		this.product.threadEnds(this.current.getMaterialList());
	}
}
