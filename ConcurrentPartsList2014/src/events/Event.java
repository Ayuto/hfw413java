package events;

/**
 * Abstract event interface which accepts visitors.
 */
public interface Event {

	void accept(EventVisitor visitor);
}
