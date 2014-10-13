package observer;

import java.util.List;
import java.util.Vector;

import lock.AbstractLock;
import lock.Lock;


public class BufferSolution<X> implements AbstractBuffer<X> {

	private List<X> data;
	private AbstractLock lock;
	private AbstractLock criticalSection;
	
	public BufferSolution(){
		this.data = new Vector<X>();
		this.lock = new Lock(true);
		this.criticalSection = new Lock(false);
	}

	public void put(X object) {
		this.criticalSection.lock();					//0
		this.data.add(object); 							//1
		this.lock.unlock();	   							//2
		this.criticalSection.unlock();					//3
	}

	public X get() {
		this.lock.lock();								//4
		this.criticalSection.lock();					//5
		X result = this.data.get(0);					//6
		this.data.remove(0);							//7
		if (!data.isEmpty()) this.lock.unlock();		//8
		this.criticalSection.unlock();					//9
		return result;									//10
	}
	

}
