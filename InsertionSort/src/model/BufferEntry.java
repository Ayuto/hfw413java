package model;

/**
 * Represents a buffer entry of any type <T>.
 */
public interface BufferEntry<T> extends Comparable<T> {
	boolean isStopCommand();
}
