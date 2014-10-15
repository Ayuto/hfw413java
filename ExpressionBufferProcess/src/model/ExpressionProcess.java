package model;

public interface ExpressionProcess extends Runnable {
	void stop();
	void start();
	void addDetectedValue(OptionalIntegerValue value);
}
