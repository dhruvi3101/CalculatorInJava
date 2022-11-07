package com.Netcracker.CalculatorImplementaiton;

@SuppressWarnings("serial")
public class IncorrectInputException extends Exception {
	public IncorrectInputException() {
		super("Wrong Input. Please try again.");
	}

	public IncorrectInputException(String string) {
		super(string);
	}
}