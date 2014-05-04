package cache;

import model.MaterialList;
import model.Product;

/**
 * State of a cached price and a cached materialList.
 */
public class PriceAndMaterialListCached extends AtLeastMaterialListCached {

	public static PriceAndMaterialListCached create(Product product, MaterialList materialList, int price) {
		return new PriceAndMaterialListCached(product, materialList, price);
	}
	
	private final int price;
	
	private PriceAndMaterialListCached(Product product, MaterialList materialList, int price) {
		super(product, materialList);
		this.price = price;
	}

	@Override
	public int getPrice() {
		return this.price;
	}

	@Override
	public void priceChanged() {
		getProduct().setCacheState(MaterialListCached.create(getProduct(), getMaterialList()));
	}

	@Override
	public void structureChanged() {
		getProduct().setCacheState(NothingCached.create(getProduct()));
	}
}
