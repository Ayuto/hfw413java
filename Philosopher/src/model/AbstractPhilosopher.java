package model;

import java.util.Random;

import lock.Lock;

public abstract class AbstractPhilosopher implements Runnable {

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
	
	private boolean isRunning() {
		this.mutex.lock();
		final boolean result = this.running;
		this.mutex.unlock();
		return result;
	}

	public PhilosopherStatus getStatus() {
		this.mutex.lock();
		final PhilosopherStatus result = this.status;
		this.mutex.unlock();
		return result;
	}

	private void eat() {
		this.beforeEating();
		this.mutex.lock();
		this.status = PhilosopherStatus.EATING;
		this.mutex.unlock();
		
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

	protected abstract void beforeEating();
	protected abstract void afterEating();

	private void think() {
		try {
			synchronized (this) {
				this.wait(new Random().nextInt(this.monitor.getMaxThinkingTime()) + 1);
			}
		} catch (final InterruptedException e) {
			throw new Error("Interupt!");
		}
	}

	public void start() {
		this.running = true;
		new Thread(this).start();
	}

	public void stop() {
		this.mutex.lock();
		this.running = false;
		this.mutex.unlock();
	}

	public int getIndex() {
		return this.index;
	}

}