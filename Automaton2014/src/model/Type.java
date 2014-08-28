package model;

/**
 * A generic type.
 * 
 * @param <Element>
 */
public interface Type<Element> {
	/**
	 * Checks whether the receiver is a type for the given Element.
	 * 
	 * @param element
	 * @return {@code True} only if the receiver is a type for the given
	 *         Element, otherwise {@code False}.
	 */
	public boolean isTypeFor(Element element);
}
