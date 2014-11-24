package model;

/**
 * Implementation of the philosopher using EBMs.
 */
public class EBMPhilosopher extends AbstractPhilosopher {

	private final EBM left;
	private EBM right;
	
	public EBMPhilosopher(final PTOMonitor monitor, final EBM left, final EBM right) {
		super(monitor);
		this.left = left;
		this.right = right;
	}
	
	@Override
	protected void beforeEating() {
		System.out.println(this + " wants to get the left EBM");
		this.left.get();
		System.out.println(this + " wants to get the right EBM");
		this.right.get();
	}
	
	@Override
	protected void afterEating() {
		System.out.println(this + " puts the right EBM back");
		this.right.put();
		System.out.println(this + " puts the left EBM back");
		this.left.put();
	}

	/**
	 * Getter for the right EBM.
	 */
	public EBM getRight() {
		this.mutex.lock();
		final EBM result = this.right;
		this.mutex.unlock();
		return result;
	}

	/**
	 * Getter for the left EBM.
	 */
	public EBM getLeft() {
		this.mutex.lock();
		final EBM result = this.left;
		this.mutex.unlock();
		return result;
	}

	/**
	 * Setter for the right EBM.
	 */
	public void setNewRight(final EBM ebm) {
		this.mutex.lock();
		this.right = ebm;
		this.mutex.unlock();
	}
}
