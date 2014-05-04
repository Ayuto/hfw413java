package cache;

import model.MaterialList;
import model.Product;

/**
 * Abstract class for the State of the cache of the price and the MaterialList.
 */
public abstract class CacheState {

	private final Product product;
	
	public CacheState(Product product) {
		this.product = product;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	/**
	 * CacheState reacts to a price change.
	 */
	public abstract void priceChanged();
	
	/**
	 * CacheState reacts to a structure change
	 */
	public abstract void structureChanged();

	public abstract MaterialList getMaterialList();
	
	public abstract int getPrice();
}
