package model;

import java.util.List;

/**
 * A abstract generic composite expression, formed out of zero to many
 * expressions.
 * 
 * @param <Element>
 * @param <ElementType>
 */
public abstract class CompositeExpression<Element, ElementType extends Type<Element>>
		extends RegularExpression<Element, ElementType> {

	private List<RegularExpression<Element, ElementType>> subExpressions;

	protected CompositeExpression(
			List<RegularExpression<Element, ElementType>> subExpressions) {
		this.subExpressions = subExpressions;
	}

	protected abstract void baseAddSubExpression(
			RegularExpression<Element, ElementType> regularExpression);

	/**
	 * Adds the given expression to the subexpressions of the receiver.
	 * 
	 * @param regularExpression
	 */
	public void addSubExpression(
			RegularExpression<Element, ElementType> regularExpression) {
		baseAddSubExpression(regularExpression);
		this.getSubExpressions().add(regularExpression);
	}

	public List<RegularExpression<Element, ElementType>> getSubExpressions() {
		return subExpressions;
	}

	public void setSubExpressions(
			List<RegularExpression<Element, ElementType>> subExpressions) {
		this.subExpressions = subExpressions;
	}
}
