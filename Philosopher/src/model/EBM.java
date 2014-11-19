package model;

import lock.Lock;

public class EBM {
	private boolean available;
	private final Lock mutex;
	
	
	public EBM() {
		this.available = true;
		this.mutex = new Lock(false);
	}
	
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
	
	private boolean isAvailable() {
		final boolean result = this.available;
		return result;
	}
	
	private void setAvailable(final boolean value) {
		this.available = value;
	}
}
