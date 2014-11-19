package model;

public interface AbstractPhilosopher {

	public abstract String toString();

	public abstract void run();

	public abstract PhilosopherStatus getStatus();

	public abstract void start();

	public abstract void stop();

	public abstract int getIndex();

}