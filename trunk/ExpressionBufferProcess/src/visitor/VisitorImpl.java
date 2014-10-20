package visitor;

import java.util.Map;

import model.BufferEntry;
import model.Constant;
import model.ExpressionProcess;
import model.OptionalIntegerValue;
import model.Process;
import model.Variable;

/**
 * Implementation of the visitor for all processes.
 */
public class VisitorImpl implements Visitor {

	private final ExpressionProcess owner;
	private Map<String, OptionalIntegerValue> constantEnvironment;
	private Map<String, Process> variableEnvironment;

	/**
	 * Constructor for the visitor implementation with the given parameters.
	 * 
	 * @param owner
	 *            the owner of the visitor which should get the detected values.
	 * @param constantEnvironment
	 *            the environment for the constants.
	 * @param variableEnvironment
	 *            the environment for the variables.
	 */
	public VisitorImpl(final ExpressionProcess owner,
			Map<String, OptionalIntegerValue> constantEnvironment,
			Map<String, Process> variableEnvironment) {
		this.owner = owner;
		this.constantEnvironment = constantEnvironment;
		this.variableEnvironment = variableEnvironment;
	}

	@Override
	public void handleStopCommand() {
		this.owner.stop();
	}

	@Override
	public void handleOptionalIntegerValue(final OptionalIntegerValue value) {
		this.owner.addDetectedValue(value);
	}

	@Override
	public void handleConstant(Constant constant) {
		this.owner.addDetectedValue(this.constantEnvironment.get(constant
				.getName()));
	}

	@Override
	public void handleVariable(Variable variable) {
		Process process = this.variableEnvironment.get(variable.getName());
		BufferEntry result = process.run();
		result.accept(this);
	}
	
	/**
	 * Setter for the constant environment.
	 */
	public void setConstantEnvironment(Map<String, OptionalIntegerValue> constantEnvironment) {
		this.constantEnvironment = constantEnvironment;
	}
	
	/**
	 * Setter for the variable environment
	 */
	public void setVariableEnvironment(Map<String, Process> variableEnvironment) {
		this.variableEnvironment = variableEnvironment;
	}
}
