package model;

import java.util.LinkedList;

public class RegularExpressionFacade {

	public static RegularExpressionFacade create() {
		return new RegularExpressionFacade();
	}

	/**
	 * @return RegularBaseExpression, the language of which is {c} 
	 */
	public RegularExpression createBaseExpression(final char c) {
		System.out.println("Base:" + c);
		return BaseExpression.create(c);
	}

	/**
	 * @return RegularExpression, the language of which is the empty set
	 */
	public RegularExpression createChoiceExpression() {
		System.out.println("Choice");
		return Choice.create(new LinkedList<RegularExpression>());
	}
	/**
	 * @return RegularExpression, the language of which is {""}
	 */
	public RegularExpression createSequenceExpression() {
		System.out.println("Sequence");
		return Sequence.create(new LinkedList<RegularExpression>());
	}

	/**Adds the containee as a direct subexpression to the container. 
	 * Order of addition is significant!
	 * @param container The whole
	 * @param containee The part
	 */
	public void add(final RegularExpression container, final RegularExpression containee) {
		container.add(containee);
		System.out.println("add");
	}

	public void setIterated(final RegularExpression expression) {
		expression.setIterated(true);
		System.out.println("iterated");
	}

	public void setOptional(final RegularExpression result) {
		result.setOptional(true);
		System.out.println("optional");
	}


}
