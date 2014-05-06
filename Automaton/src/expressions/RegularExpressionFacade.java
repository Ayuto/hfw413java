package expressions;

public class RegularExpressionFacade {

	public static RegularExpressionFacade create() {
		return new RegularExpressionFacade();
	}

	/**
	 * @return RegularBaseExpression, the language of which is {c} 
	 */
	public RegularExpression createBaseExpression(final char c) {
		//TODO
		System.out.println("Base:" + c);
		return DummyExpression.create();
	}

	/**
	 * @return RegularExpression, the language of which is the empty set
	 */
	public RegularExpression createChoiceExpression() {
		//TODO
		System.out.println("Choice");
		return DummyExpression.create();
	}
	/**
	 * @return RegularExpression, the language of which is {""}
	 */
	public RegularExpression createSequenceExpression() {
		//TODO
		System.out.println("Sequence");
		return DummyExpression.create();
	}

	/**Adds the containee as a direct subexpression to the container. 
	 * Order of addition is significant!
	 * @param container The whole
	 * @param containee The part
	 */
	public void add(final RegularExpression container, final RegularExpression containee) {
		//TODO
		System.out.println("add");
	}

	public void setIterated(final RegularExpression expression) {
		//TODO
		System.out.println("iterated");
	}

	public void setOptional(final RegularExpression result) {
		//TODO
		System.out.println("optional");
	}


}
