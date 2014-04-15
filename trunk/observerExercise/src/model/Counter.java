package model;

import java.util.ArrayList;
import java.util.List;

import view.CounterObserver;

public class Counter {

	private static final int CounterInitialvalue = 0;

	public static Counter createCounter() {
		return new Counter(CounterInitialvalue);
	}

	private int currentValue;
	private final List<CounterObserver> observers;

	private Counter(int value) {
		this.observers = new ArrayList<CounterObserver>();
		this.currentValue = value;
	}

	public void up() {
		currentValue = this.getCurrentValue() + 1;
		this.notifyObservers();
	}

	public void down() {
		currentValue = getCurrentValue() - 1;
		this.notifyObservers();
	}

	public void addObserver(CounterObserver observer) {
		this.getObservers().add(observer);
	}

	public void removeObserver(CounterObserver observer) {
		this.getObservers().remove(observer);
	}

	public void notifyObservers() {
		for (CounterObserver observer : observers) {
			observer.refresh();
		}
	}

	public int getCurrentValue() {
		return this.currentValue;
	}

	public List<CounterObserver> getObservers() {
		return this.observers;
	}
}
