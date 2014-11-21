package model;


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
		this.left.get();
		this.right.get();
	}
	
	@Override
	protected void afterEating() {
		this.right.put();
		this.left.put();
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
