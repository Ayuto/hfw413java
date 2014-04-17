package model;

import java.util.Vector;

public interface Component {

	/**
	 * Adds <part> <amount> times to the componentlist from <this>.
	 */
	public void addPart(Component part, int amount) throws Exception;

	/**
	 * Returns true if <this> contains <component> in his componentlist.
	 */
	public boolean contains(Component component);

	/**
	 * Returns all direct parts from <this>.
	 */
	public Vector<QuantifiedComponent> getDirectParts();

	/**
	 * Returns the amount if materials from <this>.
	 */
	public int getNumberOfMaterials();

	/**
	 * Returns the name of <this>.
	 */
	public String getName();

	/**
	 * Sets the price of <this>.
	 */
	public void setPrice(int price);

	/**
	 * Returns the price of <this>.
	 */
	public int getPrice();

	/**
	 * Returns a list of all materials needed to build <this>.
	 */
	public MaterialList getMaterialList();

	/**
	 * Returns true if <argument> equals <this>.
	 * 
	 * @param argument
	 * @return
	 */
	@Override
	public boolean equals(Object argument);
}
