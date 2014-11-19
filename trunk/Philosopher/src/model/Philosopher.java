package model;

import java.util.Random;

import lock.Lock;

public class Philosopher implements Runnable, AbstractPhilosopher {

	private final PTOMonitor monitor;
	private final int index;
	private final Lock mutex;
	private PhilosopherStatus status;
	private boolean running;
	
	private static int nextIndex = 0;

	public Philosopher(final PTOMonitor monitor) {
		this.monitor = monitor;
		this.index = Philosopher.nextIndex;
		Philosopher.nextIndex++;
		
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

	@Override
	public PhilosopherStatus getStatus() {
		this.mutex.lock();
		final PhilosopherStatus result = this.status;
		this.mutex.unlock();
		return result;
	}

	private void eat() {
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
		this.monitor.stopsEating(this);
	}

	private void think() {
		this.mutex.lock();
		this.status = PhilosopherStatus.THINKING;
		this.mutex.unlock();
		
		try {
			synchronized (this) {
				this.wait(new Random().nextInt(this.monitor.getMaxThinkingTime()) + 1);
			}
		} catch (final InterruptedException e) {
			throw new Error("Interupt!");
		}
	}

	@Override
	public void start() {
		this.running = true;
		new Thread(this).start();
	}

	@Override
	public void stop() {
		this.mutex.lock();
		this.running = false;
		this.mutex.unlock();
	}

	@Override
	public int getIndex() {
		return this.index;
	}
}
