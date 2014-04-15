package model;

import java.util.Iterator;
import java.util.Vector;

public class MaterialList {

	/**
	 * Creates a new MaterialList with the given <data>.
	 */
	public static MaterialList create(Vector<QuantifiedComponent> data) {
		return new MaterialList(data);
	}
	
	private Vector<QuantifiedComponent> data;
	
	private MaterialList(Vector<QuantifiedComponent> data) {
		this.data = data;
	}
	
	/**
	 * Multiplies the amount of a components this <this> with the factor <value>. 
	 */
	public void multiply(int value) {
		Iterator<QuantifiedComponent> i = this.getData().iterator();
		while (i.hasNext()){
			QuantifiedComponent current = i.next();
			current.multiplyQuantity(value);
		}
	}
	
	/**
	 * Adds the components from <list> to <this>.
	 */
	public void add(MaterialList list) {
		Iterator<QuantifiedComponent> i = list.getData().iterator();
		while(i.hasNext()){
			QuantifiedComponent current = i.next();
			this.add(current);
		}
	}
	
	/**
	 * Adds the component <quantifiedComponent> to <this>.
	 */
	public void add(QuantifiedComponent quantifiedComponent){
		Iterator<QuantifiedComponent> i = this.getData().iterator();
		while(i.hasNext()) {
			QuantifiedComponent current = i.next();
			if (current.getComponent().equals(quantifiedComponent.getComponent())){
				current.addQuantity(quantifiedComponent);
				return;
			}
		}
		this.getData().add(quantifiedComponent);
	}
	
	/**
	 * Returns the data of <this>.
	 */
	public Vector<QuantifiedComponent> getData() {
		return this.data;
	}
}
