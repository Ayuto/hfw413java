package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A generic sequence expression which recognizes all added element after each
 * other.
 * 
 * @param <Element>
 * @param <ElementType>
 */
public class Sequence<Element, ElementType extends Type<Element>> extends
		CompositeExpression<Element, ElementType> {

	/**
	 * Creates a new sequence expression with the given expression. The created
	 * expression recognizes the same inputs as the given expression.
	 * 
	 * @param expression
	 * @return the created sequence expression.
	 */
	public static <Element, ElementType extends Type<Element>> Sequence<Element, ElementType> create(
			RegularExpression<Element, ElementType> expression) {
		List<RegularExpression<Element, ElementType>> subExpressions = new LinkedList<>();
		subExpressions.add(expression);
		Sequence<Element, ElementType> result = new Sequence<Element, ElementType>(
				subExpressions);
		if (expression.isOptional()) {
			result.setOptional(true);
		}
		return result;
	}

	private Sequence(
			List<RegularExpression<Element, ElementType>> subExpressions) {
		super(subExpressions);
	}

	@Override
	protected Automaton<Element, ElementType> toBaseAutomaton() {
		Iterator<RegularExpression<Element, ElementType>> iterator = this
				.getSubExpressions().iterator();
		Automaton<Element, ElementType> result = iterator.next().toAutomaton();
		while (iterator.hasNext()) {
			RegularExpression<Element, ElementType> current = iterator.next();
			result.sequence(current.toAutomaton());
		}
		return result;
	}

	@Override
	protected void baseAddSubExpression(
			RegularExpression<Element, ElementType> regularExpression) {
		if (!regularExpression.isOptional()) {
			this.setOptional(false);
		}
	}

}
