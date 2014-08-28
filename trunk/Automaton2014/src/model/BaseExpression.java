package model;

/**
 * A generic base expression recognizing just the given element once.
 * 
 * @param <Element>
 * @param <ElementType>
 */
public class BaseExpression<Element, ElementType extends Type<Element>> extends
		RegularExpression<Element, ElementType> {

	/**
	 * Creates a base expression with the given element.
	 * 
	 * @param elementType
	 * @return the created base expression.
	 */
	public static <Element, ElementType extends Type<Element>> BaseExpression<Element, ElementType> create(
			ElementType elementType) {
		return new BaseExpression<Element, ElementType>(elementType);
	}

	private ElementType element;

	private BaseExpression(final ElementType elementType) {
		this.setElement(elementType);
	}

	@Override
	protected Automaton<Element, ElementType> toBaseAutomaton() {
		Automaton<Element, ElementType> result = Automaton.create(this
				.getElement());
		return result;
	}

	public ElementType getElement() {
		return element;
	}

	public void setElement(ElementType element) {
		this.element = element;
	}
}
