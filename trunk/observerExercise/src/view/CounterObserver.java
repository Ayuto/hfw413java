package view;

import model.Counter;

@SuppressWarnings("serial")
public class CounterObserver extends View {
	
	public static CounterObserver createCounterObserver(Counter counter){
		return new CounterObserver(counter);
	}
	
	private final Counter counter;
	
	private CounterObserver(Counter counter){
		this.counter = counter;
	}
	private Counter getCounter() {
		return this.counter;
	}
	private void refresh(){
		super.refreshView(this.getCounter().getCurrentValue());
	}
	protected void deregister() {
		// TODO implement deregister
	}
	protected void register() {
		// TODO implement register
	}
	protected void down() {
		this.counter.down();
		this.refresh();
	}
	protected void up() {
		this.counter.up();
		this.refresh();
	}
}
