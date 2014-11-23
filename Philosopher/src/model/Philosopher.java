package model;

/**
 * Implementation of the philosopher without any actions before and after eating.
 */
public class Philosopher extends AbstractPhilosopher {

	public Philosopher(PTOMonitor monitor) {
		super(monitor);
	}

	/**
	 * This Philosopher doesn't care about EBMs. He just starts eating if he is hungry.
	 */
	@Override
	protected void beforeEating() {
	}

	/**
	 * This Philosopher doesn't care about EBMs. He just stops eating if he is finished.
	 */
	@Override
	protected void afterEating() {
	}
}
