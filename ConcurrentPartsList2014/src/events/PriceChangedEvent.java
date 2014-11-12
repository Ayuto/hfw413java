package events;

/**
 * Event for a changed price.
 */
public class PriceChangedEvent implements Event {

	public static PriceChangedEvent create() {
		return new PriceChangedEvent();
	}
	
	private PriceChangedEvent() {}

	@Override
	public void accept(EventVisitor visitor) {
		visitor.handlePriceChangedEvent(this);
	}
}
