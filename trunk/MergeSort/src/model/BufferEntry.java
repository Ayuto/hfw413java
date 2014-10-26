package model;

public interface BufferEntry<T> extends Comparable<T> {
	boolean isStopCommand();
}
