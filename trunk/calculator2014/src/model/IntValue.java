package model;

public class IntValue {
	private int value;
	private boolean is_valid;
	private String error_message = "Error!";
	
	public IntValue(int value)
	{
		this.is_valid = true;
		this.value = value;
	}
	
	public IntValue(String error_message)
	{
		this.is_valid = false;
		this.error_message = error_message;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public String getErrorMessage()
	{
		return this.error_message;
	}
	
	public boolean isValid()
	{
		return this.is_valid;
	}
	
	public IntValue add(int value)
	{
		if (this.isValid())
			return new IntValue(this.getValue() + value);
		return new IntValue(this.getErrorMessage());
	}
	
	public IntValue add(IntValue value)
	{
		if (!value.isValid())
			return new IntValue(this.getErrorMessage());
		return this.add(value.getValue());
	}
	
	public IntValue multiply(int value)
	{
		if (this.isValid())
			return new IntValue(this.getValue() * value);
		return new IntValue(this.getErrorMessage());
	}
	
	public IntValue multiply(IntValue value)
	{
		if (!value.isValid())
			return new IntValue(this.getErrorMessage());
		return this.multiply(value.getValue());
	}
	
	public IntValue subtract(int value)
	{
		if (this.isValid())
			return new IntValue(this.getValue() - value);
		return new IntValue(this.getErrorMessage());
	}
	
	public IntValue subtract(IntValue value)
	{
		if (!value.isValid())
			return new IntValue(this.getErrorMessage());
		return this.subtract(value.getValue());
	}
	
	public IntValue divide(int value)
	{
		if (this.isValid())
			return new IntValue(this.getValue() / value);
		return new IntValue(this.getErrorMessage());
	}
	
	public IntValue divide(IntValue value)
	{
		if (!value.isValid())
			return new IntValue(this.getErrorMessage());
		return this.divide(value.getValue());
	}
	
	public boolean equals(Object value)
	{
		if (!this.isValid())
			return false;
		
		if (value instanceof Integer)
			return this.getValue() == (int) value;
		
		if (value instanceof IntValue)
			return this.getValue() == ((IntValue) value).getValue();
		
		return false;
	}
	
	public String toString()
	{
		return this.isValid() ? String.valueOf(this.value) : this.getErrorMessage(); 
	}
}
