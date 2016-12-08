package org.kurczynski.interviewprogramming.lovelyluckylambs;

import java.security.spec.InvalidParameterSpecException;

public class Main {
	public static final int MAX_LAMBS = 1000000000;
	public static final int MIN_LAMBS = 10;

	public static void main(String args[]) {
		try {
			if (args.length != 1)
				System.exit(1);

			System.out.println(Main.answer(Integer.parseInt(args[0])));
		} catch (InvalidParameterSpecException e) {
			e.printStackTrace();

			System.exit(1);
		} catch (NumberFormatException e) {
			e.printStackTrace();

			System.exit(1);
		}
	}

	/**
	 * This method finds the number of henchmen that can be given the most generous number of LAMBs. Basically powers of
	 * two.
	 *
	 * @param lambs The number of LAMBs to be distributed.
	 * @return The number of henchmen that can be given LAMBs.
	 */
	private static int generous(int lambs) {
		return (int) (Math.log(lambs + 1) / Math.log(2));
	}

	/**
	 * This method finds the number of hendhmen that can be given the most stingy number of LAMBs. Basically
	 * Fibonacci's Sequence.
	 *
	 * @param lambs The number of LAMBs to be distributed.
	 * @return The number of henchmen that can be given LAMBs.
	 */
	private static int stingy(int lambs) {
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

	/**
	 * Finds the difference in number of henchmen that can be paid the most stingy and most generously.
	 *
	 * @param lambs Number of LAMBs that can be distributed.
	 * @return The difference in the number of henchmen to recieve stingy and generous LAMB distribution.
	 * @throws InvalidParameterSpecException Thrown if an invalid number of LAMBs are given.
	 */
	static int answer(int lambs) throws InvalidParameterSpecException {
		if (lambs < Main.MIN_LAMBS || lambs > Main.MAX_LAMBS)
			throw new InvalidParameterSpecException(
					"LAMBs must be between " + Main.MIN_LAMBS + " and " + Main.MAX_LAMBS);

		return Main.stingy(lambs) - Main.generous(lambs);
	}
}
