package model;
import java.util.Random;

import observer.Observer;

public class ConcreteObserver extends Observer {

	private static final int CalculationTime = 5000;
	private static final Random random = new Random();
	
	private ConcreteObserverViewer view;
	
	private boolean toggle;
	
	public ConcreteObserver(ConcreteObserverViewer view){
		this.view = view;
	}

	public void update(int value) {
		this.doSomeCalculations();
		int currentValue = value;
		this.view.setValue(this.view.getValue() + (toggle ? currentValue : currentValue * -1));
		this.toggle = !this.toggle;
	}
	private synchronized void doSomeCalculations(){
		try {
			this.wait((long) (CalculationTime * random.nextFloat()) + 1);
		} catch (InterruptedException e) {}
	}
}
