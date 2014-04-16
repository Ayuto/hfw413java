package view;

import model.Counter;
import model.Observer;

@SuppressWarnings("serial")
public class CounterObserver extends View implements Observer {

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
		this.getCounter().deregister(this);
	}

	protected void register() {
		this.getCounter().register(this);
	}

	protected void down() {
		this.counter.down();
	}

	protected void up() {
		this.counter.up();
	}

	@Override
	public void update() {
		this.refresh();
	}
}
