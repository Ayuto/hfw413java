package observer;

import java.util.LinkedList;

import lock.AbstractLock;

public class BufferImplementation<X> implements AbstractBuffer<X> {

	private final LinkedList<X> data;
	private AbstractLock lock;
	
	public BufferImplementation(){
		this.data = new LinkedList<X>(); 
		this.lock = new lock.Lock(true);
	}
	
	public void put(X i) {
		this.data.add(i);								//1
		this.lock.unlock();								//2
	}

	public X get() {
		this.lock.lock();								//3
		X result = this.data.removeFirst();				//4
		if (!this.data.isEmpty())						//5 
			this.lock.unlock();							//6
		return result;
	}

}
