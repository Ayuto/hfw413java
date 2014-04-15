package model;

import java.util.Vector;

public interface Component {

	public void addPart(Component part, int amount) throws Exception;

	public boolean contains(Component component);

	public Vector<QuantifiedComponent> getDirectParts();

	public int getNumberOfMaterials();

	public String getName();

	public void setPrice(int price);

	public int getPrice();
	
	public MaterialList getMaterialList();
}
