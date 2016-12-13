package org.kurczynski.interviewprogramming.fuelingectionperfection;

public class Main {
	public static void main(String args[]) {
		System.out.println(Main.answer("15"));
		System.out.println(Main.answer("4"));
	}

	static int answer(String fuelPellets) {
		int counter = 0;
		int fuelPelletsInt = Integer.parseInt(fuelPellets);
		Double output = Math.log(Integer.parseInt(fuelPellets)) / Math.log(2);
		int offset = Math.round(output - Math.floor(output)) == 0 ? -1 : 1;
		long base = Math.round(output);

		Double roundedTwo = Math.pow(2, base);

		/* Get the pallets to a power of two. */
		while (fuelPelletsInt != roundedTwo.intValue()) {
			fuelPelletsInt += offset;

			counter++;
		}

		while (fuelPelletsInt != 1) {
			fuelPelletsInt = fuelPelletsInt / 2;

			counter++;
		}

		return counter;
	}
}
