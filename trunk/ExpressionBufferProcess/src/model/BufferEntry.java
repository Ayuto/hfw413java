package model;

import visitor.Visitor;

public interface BufferEntry {
	void accept(Visitor visitor);
}