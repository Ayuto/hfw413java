package observer;


public class ConcurrentObserver extends Observer {
	
	final private Observer observer;
	private final Thread myThread;
	private AbstractBuffer<Integer> buffer;
	public ConcurrentObserver(Observer observer){
		this.buffer = new BufferLimited<Integer>(5);
		this.observer = observer;
		this.myThread = new Thread(new Runnable() {
			public void run() {
				while (true){
					int nextValue = ConcurrentObserver.this.buffer.get();
					ConcurrentObserver.this.observer.update(nextValue); 	
				}
			}
		});
		this.myThread.start();
	}

	@Override
	protected void update(int value) {
		this.buffer.put(value);	
	}

}
