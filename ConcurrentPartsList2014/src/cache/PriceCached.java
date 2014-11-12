package cache;

import model.MaterialList;
import model.Product;

/**
 * State of a cached price.
 */
public class PriceCached extends CacheState {

	public static PriceCached create (Product product, int price) {
		return new PriceCached(product, price);
	}
	
	private final int price;

	private PriceCached(Product product, int price) {
		super(product);
		this.price = price;
	}

	@Override
	public MaterialList getMaterialList() {
		MaterialList materialList = this.getProduct().calculateMaterialList();
		this.getProduct().setCacheState(PriceAndMaterialListCached.create(getProduct(), materialList, this.getPrice()));
		return materialList;
	}

	@Override
	public int getPrice() {
		return this.price;
	}

	@Override
	public void priceChanged() {
		getProduct().setCacheState(NothingCached.create(getProduct()));
	}

	@Override
	public void structureChanged() {
		getProduct().setCacheState(NothingCached.create(getProduct()));
	}
}
