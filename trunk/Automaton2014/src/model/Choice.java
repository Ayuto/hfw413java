package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A generic choice expression which recognizes every single element added to
 * it.
 * 
 * @param <Element>
 * @param <ElementType>
 */
public class Choice<Element, ElementType extends Type<Element>> extends
		CompositeExpression<Element, ElementType> {

	/**
	 * Creates a new empty choice expression which recognizes nothing.
	 * 
	 * @return the created choice expression.
	 */
	public static <Element, ElementType extends Type<Element>> Choice<Element, ElementType> create() {
		List<RegularExpression<Element, ElementType>> subExpressions = new LinkedList<RegularExpression<Element, ElementType>>();
		return new Choice<Element, ElementType>(subExpressions);
	}

	private Choice(List<RegularExpression<Element, ElementType>> subExpressions) {
		super(subExpressions);
	}

	@Override
	protected Automaton<Element, ElementType> toBaseAutomaton() {
		Automaton<Element, ElementType> result = Automaton.create();
		Iterator<RegularExpression<Element, ElementType>> iterator = this
				.getSubExpressions().iterator();
		while (iterator.hasNext()) {
			RegularExpression<Element, ElementType> current = iterator.next();
			result.choice(current.toAutomaton());
		}
		return result;
	}

	@Override
	protected void baseAddSubExpression(
			RegularExpression<Element, ElementType> regularExpression) {
		if (regularExpression.isOptional()) {
			this.setOptional(true);
		}
	}
}