package view;

import model.Counter;

@SuppressWarnings("serial")
public class CounterObserver extends View {

	public static CounterObserver createCounterObserver(Counter counter) {
		return new CounterObserver(counter);
	}

	private final Counter counter;

	private CounterObserver(Counter counter) {
		this.counter = counter;
	}

	private Counter getCounter() {
		return this.counter;
	}

	public void refresh() {
		super.refreshView(this.getCounter().getCurrentValue());
	}

	protected void deregister() {
		this.getCounter().removeObserver(this);
	}

	protected void register() {
		this.getCounter().addObserver(this);
		this.refresh();
	}

	protected void down() {
		this.counter.down();
	}

	protected void up() {
		this.counter.up();
	}
}
