package cache;

import model.MaterialList;
import model.Product;

/**
 * Abstract class for all CacheStates with at least a cached MaterialList.
 */
public abstract class AtLeastMaterialListCached extends CacheState {
	
	private final MaterialList materialList;
	
	public AtLeastMaterialListCached(Product product, MaterialList materialList) {
		super(product);
		this.materialList = materialList;
	}
	
	public MaterialList getMaterialList() {
		return this.materialList;
	}

}
