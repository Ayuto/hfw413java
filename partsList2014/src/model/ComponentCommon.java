package model;


public abstract class ComponentCommon implements Component {

	private final String name;

	protected ComponentCommon(final String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public String toString(){
		return this.getName();
	}
	@Override
	public boolean equals(Object argument) {
		return super.equals(argument);
	}
}
