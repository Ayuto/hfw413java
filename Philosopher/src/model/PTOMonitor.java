package model;

import java.util.List;
import java.util.Vector;

import lock.Lock;

public class PTOMonitor {

	private static final int MAX_THINKING_TIME = 100;
	private static final int MAX_EATING_TIME = 50;
	private static final boolean USING_EBM = true;
	private static final boolean USE_LOCK_EBM = true;

	private static PTOMonitor instance = null;

	private final List<AbstractPhilosopher> philosophers;
	private final Lock mutex;
	private final Lock finished;
	private int currentlyEating;
	private int maxEating;
	private long startTime;
	private long lastChangeTime;
	private float averageEating;
	private int activePhilosophers;

	public static PTOMonitor getMonitor() {
		if (PTOMonitor.instance == null) {
			PTOMonitor.instance = new PTOMonitor();
		}

		return PTOMonitor.instance;
	}

	public PTOMonitor() {
		this.philosophers = new Vector<AbstractPhilosopher>();
		this.mutex = new Lock(false);
		this.finished = new Lock(true);
		this.currentlyEating = 0;
		this.maxEating = 0;
		this.startTime = 0;
		this.lastChangeTime = 0;
		this.averageEating = 0;
		this.activePhilosophers = 0;
	}

	public void addPhilosopher() {
		this.mutex.lock();
		AbstractPhilosopher philosopher = null;
		if (USING_EBM) {
			if (this.philosophers.isEmpty()) {
				EBM left = USE_LOCK_EBM ? new EBMLock() : new EBMYield();
				EBM right = USE_LOCK_EBM ? new EBMLock() : new EBMYield();
				philosopher = new EBMPhilosopher(this, left, right);
			} else {
				final EBMPhilosopher first = (EBMPhilosopher) this.philosophers.get(0);
				final EBMPhilosopher last = (EBMPhilosopher) this.philosophers.get(this.philosophers.size() - 1);
				
				final EBM ebm = USE_LOCK_EBM ? new EBMLock() : new EBMYield();
				
				philosopher = new EBMPhilosopher(this, ebm, last.getLeft());
				first.setNewRight(ebm);
			}
		} else {
			philosopher = new Philosopher(this);
		}
		this.philosophers.add(philosopher);
		this.activePhilosophers++;
		this.mutex.unlock();
	}

	public void addPhilosopher(final int count) {
		for (int i = 0; i < count; i++) {
			this.addPhilosopher();
		}
	}

	public int getMaxThinkingTime() {
		return PTOMonitor.MAX_THINKING_TIME;
	}

	public int getMaxEatingTime() {
		return PTOMonitor.MAX_EATING_TIME;
	}

	public void startEating(final AbstractPhilosopher philosopher) {
		this.mutex.lock();

		this.averageEating = (this.currentlyEating
				* (System.currentTimeMillis() - this.lastChangeTime)
				+ this.averageEating * (this.lastChangeTime - this.startTime)) / (System.currentTimeMillis() - this.startTime);
		this.lastChangeTime = System.currentTimeMillis();

		this.currentlyEating++;
		if (this.currentlyEating > this.maxEating) {
			this.maxEating = this.currentlyEating;
		}
		this.mutex.unlock();

		final int index = philosopher.getIndex();
		final int count = this.getPhilosopherCount();

		final AbstractPhilosopher prev = this.getPhilosopher((index - 1 + count)
				% count);
		final AbstractPhilosopher next = this.getPhilosopher((index + 1) % count);
		if (prev.getStatus() == PhilosopherStatus.EATING) {
			System.out.println(philosopher + " and " + prev + " are eating!");
		}

		if (next.getStatus() == PhilosopherStatus.EATING) {
			System.out.println(philosopher + " and " + next + " are eating!");
		}
	}

	public void stopsEating(final AbstractPhilosopher philosopher) {
		this.mutex.lock();
		
		this.averageEating = (this.currentlyEating
				* (System.currentTimeMillis() - this.lastChangeTime)
				+ this.averageEating * (this.lastChangeTime - this.startTime)) / (System.currentTimeMillis() - this.startTime);
		this.lastChangeTime = System.currentTimeMillis();
		
		this.currentlyEating--;
		this.mutex.unlock();
	}

	private int getPhilosopherCount() {
		this.mutex.lock();
		final int result = this.philosophers.size();
		this.mutex.unlock();
		return result;
	}

	private AbstractPhilosopher getPhilosopher(final int index) {
		this.mutex.lock();
		final AbstractPhilosopher result = this.philosophers.get(index);
		this.mutex.unlock();
		return result;
	}

	public void startSimulation(final int simulationTime) {
		this.mutex.lock();

		this.startTime = System.currentTimeMillis();
		this.lastChangeTime = System.currentTimeMillis();
		this.averageEating = 0;
		this.maxEating = 0;
		this.currentlyEating = 0;
		this.activePhilosophers = this.philosophers.size();
		
		for (final AbstractPhilosopher current : this.philosophers) {
			current.start();
		}
		this.mutex.unlock();

		try {
			synchronized (this) {
				this.wait(simulationTime);
			}
		} catch (final InterruptedException e) {
			throw new Error("Interupt!");
		}

		this.mutex.lock();
		for (final AbstractPhilosopher current : this.philosophers) {
			current.stop();
		}
		this.mutex.unlock();
		this.finished.lock();
		System.out.println("Max eating: " + this.maxEating);
		System.out.println("Average eating: " + this.averageEating);
	}

	public void philosopherTerminated(final AbstractPhilosopher philosopher) {
		this.mutex.lock();
		this.activePhilosophers--;

		System.out.println(philosopher + " terminated.");
		if (this.activePhilosophers == 0) {
			System.out.println("Simulation finished!");
			this.finished.unlock();
		}
		this.mutex.unlock();
	}
}
