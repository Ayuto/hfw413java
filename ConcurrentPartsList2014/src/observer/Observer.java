package observer;

import events.Event;

/**
 * Interface for observing objects.
 */
public interface Observer {

	void update(Event event);
}
