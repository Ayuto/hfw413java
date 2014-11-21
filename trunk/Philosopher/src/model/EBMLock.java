package model;

import lock.Lock;

public class EBMLock implements EBM {
	
	private final Lock getLock;
	private final Lock putLock;
	
	public EBMLock() {
		this.getLock = new Lock(false);
		this.putLock = new Lock(true);
	}

	@Override
	public void get() {
		this.getLock.lock();
		this.putLock.unlock();
	}

	@Override
	public void put() {
		this.putLock.lock();
		this.getLock.unlock();
	}

}
