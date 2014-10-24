package model;

public class StopCommand<T> implements BufferEntry<T> {

	public StopCommand(){
	}
	
	@Override
	public int compareTo(T t) {
		return 1;
	}
	
	@Override
	public boolean isStopCommand() {
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "Stop!";
	}
}
