package view;

import model.Counter;

@SuppressWarnings("serial")
public class CounterObserver extends View {
	
	public static CounterObserver createCounterObserver(Counter counter1, Counter counter2){
		return new CounterObserver(counter1, counter2);
	}
	
	private final Counter counter1;
	private final Counter counter2;
	
	private CounterObserver(Counter counter1, Counter counter2){
		this.counter1 = counter1;
		this.counter2 = counter2;
	}
	private Counter getCounter1() {
		return this.counter1;
	}
	private Counter getCounter2() {
		return this.counter2;
	}
	private void refresh1(){
		super.refreshView1(this.getCounter1().getCurrentValue());
	}
	private void refresh2(){
		super.refreshView2(this.getCounter2().getCurrentValue());
	}
	protected void deregister1() {
		// TODO implement deregister
	}
	protected void register1() {
		// TODO implement register
	}
	protected void deregister2() {
		// TODO implement deregister
	}
	protected void register2() {
		// TODO implement register
	}
	protected void down1() {
		this.getCounter1().down();
		this.refresh1();
	}
	protected void up1() {
		this.getCounter1().up();
		this.refresh1();
	}
	protected void down2() {
		this.getCounter2().down();
		this.refresh2();
	}
	protected void up2() {
		this.getCounter2().up();
		this.refresh2();
	}
}
