package buffer;

public interface AbstractBuffer<X> {
	
	/** Inserts a x-value at the end of the buffer.  */
	public void put(X object);
	
	/** Provides the first entry in the buffer. 
	 * Waits until the buffer is not empty, if the buffer is empty. */
	public X get();


}
