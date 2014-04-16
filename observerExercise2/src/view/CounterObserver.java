package view;

import model.Counter;
import model.Observer;

@SuppressWarnings("serial")
public class CounterObserver extends View {

	public static CounterObserver createCounterObserver(Counter counter1,
			Counter counter2) {
		return new CounterObserver(counter1, counter2);
	}

	private final Counter counter1;
	private final Counter counter2;

	private CounterObserver(Counter counter1, Counter counter2) {
		this.counter1 = counter1;
		this.counter2 = counter2;
	}

	private Counter getCounter1() {
		return this.counter1;
	}

	private Counter getCounter2() {
		return this.counter2;
	}

	private void refresh1() {
		super.refreshView1(this.getCounter1().getCurrentValue());
	}

	private void refresh2() {
		super.refreshView2(this.getCounter2().getCurrentValue());
	}

	private Observer adapter1 = new Observer() {
		@Override
		public void update() {
			refresh1();
		}
	};

	protected void deregister1() {
		this.getCounter1().deregister(adapter1);
	}

	protected void register1() {
		this.getCounter1().register(adapter1);
	}

	private Observer adapter2 = new Observer() {
		@Override
		public void update() {
			refresh2();
		}
	};

	protected void deregister2() {
		this.getCounter2().deregister(adapter2);
	}

	protected void register2() {
		this.getCounter2().register(adapter2);
	}

	protected void down1() {
		this.getCounter1().down();
	}

	protected void up1() {
		this.getCounter1().up();
	}

	protected void down2() {
		this.getCounter2().down();
	}

	protected void up2() {
		this.getCounter2().up();
	}
}
