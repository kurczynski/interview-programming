package org.kurczynski.interviewprogramming.lovelyluckylambs;

import java.security.spec.InvalidParameterSpecException;

public class Main {
	public static final int MAX_LAMBS = 1000000000;
	public static final int MIN_LAMBS = 10;

	public static void main(String args[]) {
		try {
			System.out.println(Main.answer(10));
			System.out.println(Main.answer(143));
		} catch (InvalidParameterSpecException e) {
			e.printStackTrace();
		}
	}

	public static int generous(int lambs) {
		return (int) (Math.log(lambs) / Math.log(2));
	}

	public static int stingy(int lambs) {
		int n1 = 1;
		int n2 = 1;
		int n;

		int sum = n1 + n2;
		int counter = 1;

		while (sum < lambs) {
			n = n2 + n1;
			sum = sum + n;
			n2 = n1;
			n1 = n;

			counter++;
		}

		return counter;
	}

	public static int answer(int lambs) throws InvalidParameterSpecException {
		if (lambs < Main.MIN_LAMBS || lambs > Main.MAX_LAMBS)
			throw new InvalidParameterSpecException(
					"LAMBs must be between " + Main.MIN_LAMBS + " and " + Main.MAX_LAMBS);

		return Main.stingy(lambs) - Main.generous(lambs);
	}
}
