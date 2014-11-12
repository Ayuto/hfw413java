package cache;

import model.MaterialList;
import model.Product;

/**
 * State of a cached materialList.
 */
public class MaterialListCached extends AtLeastMaterialListCached {

	public static MaterialListCached create(Product product, MaterialList materialList) {
		return new MaterialListCached(product, materialList);
	}
	
	private MaterialListCached(Product product, MaterialList materialList) {
		super(product, materialList);
	}
	
	@Override
	public int getPrice() {
		int price = this.getProduct().calculatePrice();
		this.getProduct().setCacheState(PriceAndMaterialListCached.create(getProduct(), getMaterialList(), price));
		return price;
	}

	@Override
	public void priceChanged() {
	}

	@Override
	public void structureChanged() {
		getProduct().setCacheState(NothingCached.create(getProduct()));
	}
}
