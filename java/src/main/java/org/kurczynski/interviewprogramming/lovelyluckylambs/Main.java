package org.kurczynski.interviewprogramming.lovelyluckylambs;

import java.math.BigInteger;

public class Main {
	public static void main(String args[]) {
		System.out.println(Main.answer(10));
		System.out.println(Main.answer(143));
	}

	public static int generous(int lambs) {
		Double tmp = ((Math.log(lambs) / Math.log(2)));
		return tmp.intValue();
	}

	public static int stingy(int lambs) {
		BigInteger n1 = BigInteger.valueOf(1);
		BigInteger n2 = BigInteger.valueOf(1);
		BigInteger n;

		BigInteger sum = n1.add(n2);
		int counter = 1;

		while (sum.compareTo(BigInteger.valueOf(lambs)) <= 0) {
			n = n2.add(n1);
			sum = sum.add(n);
			n2 = n1;
			n1 = n;

			counter++;
		}

		return counter;
	}

	public static int answer(int lambs) {
		return Main.stingy(lambs) - Main.generous(lambs);
	}
}
