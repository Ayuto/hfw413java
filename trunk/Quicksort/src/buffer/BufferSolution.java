package buffer;

import java.util.List;
import java.util.Vector;

import lock.AbstractLock;
import lock.Lock;

/**
 * Implementation for an abstract unlimited buffer.
 */
public class BufferSolution<X> implements AbstractBuffer<X> {

	private List<X> data;
	private AbstractLock lock;
	private AbstractLock criticalSection;
	
	/**
	 * Constructor for a new empty buffer.
	 */
	public BufferSolution(){
		this.data = new Vector<X>();
		this.lock = new Lock(true);
		this.criticalSection = new Lock(false);
	}

	@Override
	public void put(X object) {
		this.criticalSection.lock();					
		this.data.add(object); 							
		this.lock.unlock();	   							
		this.criticalSection.unlock();					
	}

	@Override
	public X get() {
		this.lock.lock();								
		this.criticalSection.lock();					
		X result = this.data.get(0);					
		this.data.remove(0);							
		if (!data.isEmpty()) this.lock.unlock();		
		this.criticalSection.unlock();					
		return result;									
	}
	

}
