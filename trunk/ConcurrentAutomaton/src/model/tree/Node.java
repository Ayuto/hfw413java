package model.tree;

import java.util.LinkedList;

public class Node<T> {
	private final LinkedList<Node<T>> elements;
	private final T value;
	private final Node<T> parent;

	public Node(T value, Node<T> parent) {
		this.value = value;
		this.elements = new LinkedList<Node<T>>();
		this.parent = parent;
	}

	public Node(T value) {
		this(value, null);
	}

	public Node<T> addElement(T element) {
		Node<T> value = new Node<T>(element, this);
		this.elements.add(value);
		return value;
	}

	public boolean isRoot() {
		return this.parent == null;
	}

	public T getValue() {
		return value;
	}
	
	public Node<T> getParent() {
		return parent;
	}
}
