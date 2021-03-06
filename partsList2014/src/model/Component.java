package model;

import java.util.Vector;

import observer.Observer;

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
	 * Registers an Observer on <this>.
	 */
	public void register(Observer o);

	/**
	 * Deregisters an Observer on <this>.
	 */
	public void deregister(Observer o);

	/**
	 * Returns true if <argument> equals <this>.
	 */
	@Override
	public boolean equals(Object argument);
	
	/**
	 * Returns how often <this> contains <component>.
	 */
	public int containsHowOften(Component component);
	
	/**
	 * Returns how often <this> is built in <component>.
	 */
	public int isBuiltInHowOften(Component component);
}
