package observer;

import lock.AbstractLock;
import lock.Lock;

public class BufferLimited<X> implements AbstractBuffer<X> {
	
	private final int capacity;
	private final X[] data;
	private int first;
	private int behindLast;
	private final AbstractLock lockRead;
	private final AbstractLock lockWrite;
	private final AbstractLock mutex;

	@SuppressWarnings("unchecked")
	public BufferLimited(int capacity){
		if (capacity <= 0) {
			capacity = 1;
		}
		this.capacity = capacity + 1;
		this.data = (X[]) new Object[this.capacity];
		this.first = 0;
		this.behindLast = 0;
		this.lockRead = new Lock(true);
		this.lockWrite = new Lock(false);
		this.mutex = new Lock(false);
	}
	
	private boolean isEmpty(){
		return this.first == this.behindLast;
	}
	
	private boolean isFull(){
		return this.behindLast + 1 % this.capacity == this.first;
	}
	
	@Override
	public void put(final X object) {
		this.lockWrite.lock();
		this.mutex.lock();
		this.data[this.behindLast] = object;
		this.behindLast = (this.behindLast + 1) % this.capacity ;
		if (!this.isFull()) {
			this.lockWrite.unlock();
		}
		this.lockRead.unlock();
		this.mutex.unlock();
	}

	@Override
	public X get() {
		this.lockRead.lock();
		this.mutex.lock();
		final X result = this.data[this.first];
		this.first = (this.first + 1) % this.capacity ;
		if (!this.isEmpty()) {
			this.lockRead.unlock();
		}
		this.lockWrite.unlock();
		this.mutex.unlock();
		return result;
	}
}