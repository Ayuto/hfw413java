package model;

import observer.Observee;

public class ConcreteObservee extends Observee {
	
	private int value;
	
	public ConcreteObservee(){
		this.value = 0;
	}
	public int getValue(){
		return this.value;
	}
	public void setValue(int value){
		this.value = value;
		this.notifyObservers(value);
	}
}
