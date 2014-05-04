package events;

/**
 * Abstract event visitor.
 */
public interface EventVisitor {

	void handleStructureChangedEvent(Event event);

	void handlePriceChangedEvent(Event event);

}
