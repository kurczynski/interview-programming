package org.kurczynski.interviewprogramming.lovelyluckylambs;

public class Main {
	public static void main(String args[]) {
		System.out.println(Main.answer(10));
		System.out.println(Main.answer(143));
	}

	public static int generous(int lambs) {
		return (int) ((Math.log(lambs) / Math.log(2)));
	}

	public static int stingy(int total) {
		int n1 = 1;
		int n2 = 1;
		int n;

		int sum = n1 + n2;
		int counter = 1;

		while (sum <= total) {
			n = n2 + n1;
			sum += n;
			n2 = n1;
			n1 = n;

			counter++;
		}

		return counter;
	}

	public static int answer(int lambs) {
		return Math.abs(Main.generous(lambs) - Main.stingy(lambs));
	}
}
