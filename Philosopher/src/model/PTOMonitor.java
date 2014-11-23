package model;

import java.util.List;
import java.util.Vector;

import lock.Lock;

/**
 * Monitor which checks whether all philosophers are following the PTO. Also
 * monitors the average and maximum amount of simultaneously eating
 * philosophers.
 */
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

	/**
	 * Getter for the instance of the singleton-class PTOMonitor.
	 */
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

	/**
	 * Starts simulations with the receivers philosophers and the given
	 * simulation time.
	 * 
	 * @param simulationTime
	 *            given simulation time
	 */
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

	/**
	 * Adds one new philosopher to the receiver. The receiver decides whether
	 * the new philosopher uses EBMs.
	 */
	public void addPhilosopher() {
		this.mutex.lock();
		AbstractPhilosopher philosopher = null;
		if (USING_EBM) {
			if (this.philosophers.isEmpty()) {
				EBM left = USE_LOCK_EBM ? new EBMLock() : new EBMYield();
				EBM right = USE_LOCK_EBM ? new EBMLock() : new EBMYield();
				philosopher = new EBMPhilosopher(this, left, right);
			} else {
				final EBMPhilosopher first = (EBMPhilosopher) this.philosophers
						.get(0);
				final EBMPhilosopher last = (EBMPhilosopher) this.philosophers
						.get(this.philosophers.size() - 1);

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

	/**
	 * Adds {@code count} new philosophers to the receiver.
	 */
	public void addPhilosopher(final int count) {
		for (int i = 0; i < count; i++) {
			this.addPhilosopher();
		}
	}

	/**
	 * Getter for the maximum thinking time of the philosophers.
	 */
	public int getMaxThinkingTime() {
		return PTOMonitor.MAX_THINKING_TIME;
	}

	/**
	 * Getter for the maximum eating time of the philosophers.
	 */
	public int getMaxEatingTime() {
		return PTOMonitor.MAX_EATING_TIME;
	}

	/**
	 * This method should be called if one philosopher starts eating.
	 * 
	 * @param philosopher
	 *            philosopher who starts eating
	 */
	public void startEating(final AbstractPhilosopher philosopher) {
		refreshStatistics(true);

		checkPTO(philosopher);
	}

	/**
	 * This method should be called if a philosopher stops eating.
	 * 
	 * @param philosopher
	 *            philosopher who stops eating.
	 */
	public void stopsEating(final AbstractPhilosopher philosopher) {
		this.refreshStatistics(false);
	}

	/**
	 * This method should be called if a philosopher terminates.
	 * 
	 * @param philosopher
	 *            philosopher who terminates.
	 */
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

	/**
	 * Refreshes all statistics regarding simultaneously eating philosophers.
	 * 
	 * @param startEating
	 *            flag if a new philosopher starts eating after the refresh
	 *            otherwise a philosopher stops eating afterwards.
	 */
	private void refreshStatistics(boolean startEating) {
		this.mutex.lock();

		this.averageEating = (this.currentlyEating
				* (System.currentTimeMillis() - this.lastChangeTime) + this.averageEating
				* (this.lastChangeTime - this.startTime))
				/ (System.currentTimeMillis() - this.startTime);
		this.lastChangeTime = System.currentTimeMillis();

		this.currentlyEating = startEating ? this.currentlyEating + 1
				: this.currentlyEating - 1;
		if (this.currentlyEating > this.maxEating) {
			this.maxEating = this.currentlyEating;
		}
		this.mutex.unlock();
	}

	/**
	 * Checks whether the given philosopher follows the PTO and prints out a
	 * statement if the philosophers is not following the PTO.
	 * 
	 * @param philosopher
	 *            given philosopher
	 */
	private void checkPTO(final AbstractPhilosopher philosopher) {
		final int index = philosopher.getIndex();
		final int count = this.getPhilosopherCount();

		final AbstractPhilosopher prev = this
				.getPhilosopher((index - 1 + count) % count);
		final AbstractPhilosopher next = this.getPhilosopher((index + 1)
				% count);
		if (prev.getStatus() == PhilosopherStatus.EATING) {
			System.out.println(philosopher + " and " + prev + " are eating!");
		}

		if (next.getStatus() == PhilosopherStatus.EATING) {
			System.out.println(philosopher + " and " + next + " are eating!");
		}
	}

	/**
	 * Getter for the amount of current philosophers of the receiver.
	 */
	private int getPhilosopherCount() {
		this.mutex.lock();
		final int result = this.philosophers.size();
		this.mutex.unlock();
		return result;
	}

	/**
	 * Getter for the philosopher of the receiver with the given index.
	 * 
	 * @param index
	 *            given index
	 */
	private AbstractPhilosopher getPhilosopher(final int index) {
		this.mutex.lock();
		final AbstractPhilosopher result = this.philosophers.get(index);
		this.mutex.unlock();
		return result;
	}
}
