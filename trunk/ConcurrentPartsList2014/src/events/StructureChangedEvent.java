package events;

/**
 * Event for a changed structure.
 */
public class StructureChangedEvent implements Event {

	public static StructureChangedEvent create() {
		return new StructureChangedEvent();
	}

	private StructureChangedEvent() {
	}

	@Override
	public void accept(EventVisitor visitor) {
		visitor.handleStructureChangedEvent(this);
	}
}
