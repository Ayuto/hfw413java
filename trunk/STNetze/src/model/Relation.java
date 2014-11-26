package model;

/**
 * Represents a relation between two different P/T net objects.
 */
public class Relation {
	private PTNetObject from;
	private PTNetObject to;
	private int quantifier;

	/**
	 * Creates a new relation from a transition to a place.
	 */
	public Relation(Transition from, Place to, int quantifier) {
		this.initialize(from, to, quantifier);
	}

	/**
	 * Creates a new relation from a place to a transition.
	 */
	public Relation(Place from, Transition to, int quantifier) {
		this.initialize(from, to, quantifier);
	}

	/**
	 * Initializes the object's attributes.
	 */
	private void initialize(PTNetObject from, PTNetObject to, int quantifier) {
		this.from = from;
		this.to = to;
		this.quantifier = quantifier;
	}

	public PTNetObject getFrom() {
		return from;
	}

	public PTNetObject getTo() {
		return to;
	}

	public int getQuantifier() {
		return quantifier;
	}
}
