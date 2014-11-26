package model;

public class Relation {
	private NetObject from;
	private NetObject to;
	private int quantifier;

	public Relation(Transition from, Position to, int quantifier) {
		this.initialize(from, to, quantifier);
	}

	public Relation(Position from, Transition to, int quantifier) {
		this.initialize(from, to, quantifier);
	}

	private void initialize(NetObject from, NetObject to, int quantifier) {
		this.from = from;
		this.to = to;
		this.quantifier = quantifier;
	}

	public NetObject getFrom() {
		return from;
	}

	public NetObject getTo() {
		return to;
	}

	public int getQuantifier() {
		return quantifier;
	}
}
