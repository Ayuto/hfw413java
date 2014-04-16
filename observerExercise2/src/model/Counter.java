package model;

public class Counter {
	
	private static final int CounterInitialvalue = 0;
	
	public static Counter createCounter(){
		return new Counter(CounterInitialvalue);
	}
	
	private int currentValue;
	
	private Counter(int value){
		this.currentValue = value;
	}
	public void up(){
		currentValue = this.getCurrentValue() + 1;
	}
	public void down(){
		currentValue = getCurrentValue() - 1;
	}
	public int getCurrentValue() {
		return this.currentValue;
	}
}
