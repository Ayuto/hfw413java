package model;

import java.util.Random;

import lock.Lock;

/**
 * Abstract Philosopher who switches between his two states eating and thinking
 * after a random period of time. The behavior before and after eating is
 * defined in the child classes as template method.
 */
public abstract class AbstractPhilosopher implements Runnable {

	/**
	 * Index of the next created philosopher.
	 */
	private static int nextIndex = 0;

	private final PTOMonitor monitor;
	private final int index;
	protected final Lock mutex;
	private PhilosopherStatus status;
	private boolean running;

	public AbstractPhilosopher(final PTOMonitor monitor) {
		this.monitor = monitor;
		this.index = AbstractPhilosopher.nextIndex;
		AbstractPhilosopher.nextIndex++;

		this.mutex = new Lock(false);
		this.status = PhilosopherStatus.UNDEFINED;
		this.running = false;
	}

	@Override
	public String toString() {
		return "Philosopher " + this.index;
	}

	@Override
	public void run() {
		while (this.isRunning()) {
			this.think();
			this.eat();
		}
		this.monitor.philosopherTerminated(this);
	}

	/**
	 * Getter for the flag whether the receiver is running.
	 */
	private boolean isRunning() {
		this.mutex.lock();
		final boolean result = this.running;
		this.mutex.unlock();
		return result;
	}

	/**
	 * Getter for the current status of the receiver.
	 */
	public PhilosopherStatus getStatus() {
		this.mutex.lock();
		final PhilosopherStatus result = this.status;
		this.mutex.unlock();
		return result;
	}

	/**
	 * This method is called if the receiver eats. 
	 * The monitor will be informed and the random eating 
	 * period will be calculated.
	 * Afterwards the status will be changed back to thinking.
	 */
	private void eat() {
		this.beforeEating();
		this.mutex.lock();
		this.status = PhilosopherStatus.EATING;
		this.mutex.unlock();
		System.out.println(this + " starts eating");
		this.monitor.startEating(this);
		try {
			synchronized (this) {
				this.wait(new Random().nextInt(this.monitor.getMaxEatingTime()) + 1);
			}
		} catch (final InterruptedException e) {
			throw new Error("Interupt!");
		}
		this.mutex.lock();
		this.status = PhilosopherStatus.THINKING;
		this.mutex.unlock();
		this.afterEating();
		this.monitor.stopsEating(this);
	}

	/**
	 * This method is called before the receiver starts to eat.
	 */
	protected abstract void beforeEating();

	/**
	 * This method is called after the receiver finished eating.
	 */
	protected abstract void afterEating();

	/**
	 * This method is called if the receiver is thinking.
	 * The thinking time will be calculated here, too.
	 */
	private void think() {
		System.out.println(this + " starts thinking");
		try {
			synchronized (this) {
				this.wait(new Random().nextInt(this.monitor
						.getMaxThinkingTime()) + 1);
			}
		} catch (final InterruptedException e) {
			throw new Error("Interupt!");
		}
	}

	/**
	 * Starts the receivers protocoll.
	 */
	public void start() {
		this.running = true;
		new Thread(this).start();
	}

	/**
	 * Tries to stop the receiver.
	 * It may take a while until the receiver stops.
	 */
	public void stop() {
		this.mutex.lock();
		this.running = false;
		this.mutex.unlock();
	}

	/**
	 * Getter for the index of the receiver.
	 */
	public int getIndex() {
		return this.index;
	}

}