package com.Netcracker.CalculatorImplementaiton;

import java.util.*;

public class Calculator {
	static Scanner scanner;

	public static void main(String[] args) {
		System.out.println("CALCULATOR IN JAVA");
		System.out.println("How to use: ");
		System.out.println("1. Enter the expression with space separated elements");
		System.out.println("2. For signed integers do not put space between sign and value");

		while (true) {
			try {
				scanner = new Scanner(System.in);
				System.out.print("Enter the expression or type Quit to quit: ");
				String exp;
				exp = scanner.nextLine();
				exp = exp.trim();
				if (exp.equals("quit"))
					break;
				System.out.println("Answer:  " + Calculate(exp));
			} catch (IncorrectInputException e) {
				System.out.println(e);
			}
		}
		System.out.println("Calculator stopped.");
	}

	private static String Calculate(String exp) throws IncorrectInputException {
		String[] strArray = exp.split(" ");
		if (strArray.length == 0) {
			throw new IncorrectInputException("Input expression is empty");
		}
		if (checkExpression(strArray) == 1) {
			return singleOperation(strArray);
		} else if (checkExpression(strArray) == 2) {
			return multipleOperation(strArray);
		} else {
			throw new IncorrectInputException("Wrong expression entered: "+exp);
		}
	}

	private static String singleOperation(String[] strArray) throws IncorrectInputException {
		List<Double> numList = new ArrayList<>();
		for (String x : strArray) {
			numList.add(Double.parseDouble(x));
		}
		System.out.println("Enter any of the following operation: + - * / :");
		String operation = scanner.nextLine();
		operation.toLowerCase();
		double temp;
		switch (operation) {

		case "-":
			temp = 2 * numList.get(0);
			for (double d : numList)
				temp -= d;
			break;
		case "*":
			temp = 1;
			for (double d : numList)
				temp *= d;
			break;
		case "/":
			temp = numList.get(0) * numList.get(0);
			for (double d : numList)
				temp /= d;
			break;
		default:
			throw new IncorrectInputException();
		}
		return Double.toString(temp);
	}

	private static String multipleOperation(String[] strArray) {
		List<Double> numList = new ArrayList<>();
		for (int x = 0; x < strArray.length; x += 2) {
			numList.add(Double.parseDouble(strArray[x]));
		}
		List<String> oprList = new ArrayList<>();
		for (int x = 1; x < strArray.length; x += 2) {
			oprList.add(strArray[x]);
		}
		double finalAns = 0;
		while (true) {
			int status = 0;
			if (oprList.size() < 1 || numList.size() < 2)
				break;
			for (int i = 0; i < oprList.size(); i++) {
				if (oprList.get(i).equals("/")) {
					finalAns = (numList.get(i)) / numList.get(i + 1);
					// System.out.println(numList.get(i)+oprList.get(i)+numList.get(i+1));
					numList.remove(i);
					numList.remove(i);
					oprList.remove(i);
					numList.add(i, finalAns);
					status = 1;
				}
			}
			if (status == 1)
				continue;
			for (int i = 0; i < oprList.size(); i++) {
				if (oprList.get(i).equals("*")) {
					finalAns = (numList.get(i)) * numList.get(i + 1);
					// System.out.println(numList.get(i)+oprList.get(i)+numList.get(i+1));
					numList.remove(i);
					numList.remove(i);
					oprList.remove(i);
					numList.add(i, finalAns);
					status = 1;
				}
			}
			if (status == 1)
				continue;
			for (int i = 0; i < oprList.size(); i++) {
				if (oprList.get(i).equals("-")) {
					finalAns = (numList.get(i)) - numList.get(i + 1);
					// System.out.println(numList.get(i)+oprList.get(i)+numList.get(i+1));
					numList.remove(i);
					numList.remove(i);
					oprList.remove(i);
					numList.add(i, finalAns);
					status = 1;
				}
			}
			if (status == 1)
				continue;
			for (int i = 0; i < oprList.size(); i++) {
				if (oprList.get(i).equals("+")) {
					finalAns = (numList.get(i)) + numList.get(i + 1);
					// System.out.println(numList.get(i)+oprList.get(i)+numList.get(i+1));
					numList.remove(i);
					numList.remove(i);
					oprList.remove(i);
					numList.add(i, finalAns);
					status = 1;
				}
			}
			if (status == 1)
				continue;
		}

		return Double.toString(finalAns);
	}

	private static int checkExpression(String[] strArray) {
		int ret = 1;
		for (int i = 0; i < strArray.length; i++) {
			try {
				@SuppressWarnings("unused")
				double d = Double.parseDouble(strArray[i]);
			} catch (NumberFormatException nfe) {
				ret = 2;
			}
		}
		if (ret == 2) {
			if (strArray.length % 2 == 0) {
				ret = -1;
			} else {
				for (int i = 0; i < strArray.length; i += 2) {
					try {
						@SuppressWarnings("unused")
						double d = Double.parseDouble(strArray[i]);
					} catch (NumberFormatException nfe) {
						ret = -1;
					}
				}
			}
		}
		if (ret == 2) {
			for (int i = 1; i < strArray.length; i += 2) {
				if (!strArray[i].equals("+") && !strArray[i].equals("-") && !strArray[i].equals("*")
						&& !strArray[i].equals("/")) {
					ret = -1;
					break;
				}
			}
		}
		return ret;
	}

}
