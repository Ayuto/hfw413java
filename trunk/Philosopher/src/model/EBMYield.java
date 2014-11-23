package model;

import lock.Lock;

/**
 * Implementation of the EBM using Thread.yield().
 */
public class EBMYield implements EBM {
	private boolean available;
	private final Lock mutex;
	
	
	public EBMYield() {
		this.available = true;
		this.mutex = new Lock(false);
	}
	
	@Override
	public void put() {
		this.mutex.lock();
		boolean current = this.isAvailable();
		while (current) {
			this.mutex.unlock();
			Thread.yield();
			this.mutex.lock();
			current = this.isAvailable();
		}
		
		this.setAvailable(true);
		this.mutex.unlock();
	}

	@Override
	public void get() {
		this.mutex.lock();
		boolean current = !this.isAvailable();
		while (current) {
			this.mutex.unlock();
			Thread.yield();
			this.mutex.lock();
			current = !this.isAvailable();
		}
			
		this.setAvailable(false);
		this.mutex.unlock();
	}
	
	/**
	 * Getter for the flag whether the EBM is available.
	 */
	private boolean isAvailable() {
		final boolean result = this.available;
		return result;
	}
	
	/**
	 * Setter for the flag whether the EBM is available.
	 */
	private void setAvailable(final boolean value) {
		this.available = value;
	}
}
