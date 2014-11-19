package model;

import java.util.Random;

import lock.Lock;

public class EBMPhilosopher implements Runnable, AbstractPhilosopher {

	private final PTOMonitor monitor;
	private final int index;
	private final Lock mutex;
	private PhilosopherStatus status;
	private boolean running;
	private final EBM left;
	private EBM right;
	
	private static int nextIndex = 0;

	public EBMPhilosopher(final PTOMonitor monitor, final EBM left, final EBM right) {
		this.monitor = monitor;
		this.index = EBMPhilosopher.nextIndex;
		EBMPhilosopher.nextIndex++;
		
		this.mutex = new Lock(false);
		this.status = PhilosopherStatus.UNDEFINED;
		this.running = false;
		this.left = left;
		this.right = right;
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
		this.terminate();
		this.monitor.philosopherTerminated(this);
	}
	
	private void terminate() {
		this.mutex.lock();
		this.status = PhilosopherStatus.UNDEFINED;
		this.mutex.unlock();
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
		this.left.get();
		this.right.get();
		
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
		this.right.put();
		this.left.put();
		
		this.monitor.stopsEating(this);
	}

	private void think() {
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

	public EBM getRight() {
		this.mutex.lock();
		final EBM result = this.right;
		this.mutex.unlock();
		return result;
	}

	public EBM getLeft() {
		this.mutex.lock();
		final EBM result = this.left;
		this.mutex.unlock();
		return result;
	}

	public void setNewRight(final EBM ebm) {
		this.mutex.lock();
		this.right = ebm;
		this.mutex.unlock();
	}
}
