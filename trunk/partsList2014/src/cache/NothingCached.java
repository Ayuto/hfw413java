package cache;

import model.MaterialList;
import model.Product;

/**
 * State of nothing cached.
 */
public class NothingCached extends CacheState {

	public static NothingCached create(Product product) {
		return new NothingCached(product);
	}
	
	private NothingCached(Product product) {
		super(product);
	}

	@Override
	public MaterialList getMaterialList() {
		MaterialList materialList = this.getProduct().calculateMaterialList();
		this.getProduct().setCacheState(MaterialListCached.create(getProduct(), materialList));
		return materialList;
	}

	@Override
	public int getPrice() {
		int price = this.getProduct().calculatePrice();
		this.getProduct().setCacheState(PriceCached.create(getProduct(), price));
		return price;
	}

	@Override
	public void priceChanged() {
	}

	@Override
	public void structureChanged() {
	}
}
