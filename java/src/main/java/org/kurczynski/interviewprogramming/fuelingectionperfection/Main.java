package org.kurczynski.interviewprogramming.fuelingectionperfection;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {
	public static void main(String args[]) {
		Main.bigLog(BigInteger.valueOf(144));
		System.out.println(Main.answer("4"));
		System.out.println(Main.answer("15"));
		System.out.println(Main.answer("1"));
		System.out.println(Main.answer("100"));
	}

	static int answer(String fuelPellets) {
		int counter = 0;
		BigInteger fuelPelletsInt = new BigInteger(fuelPellets);
		BigDecimal bigDecimal = Main.bigLog(fuelPelletsInt);
		Double output = Math.log(Integer.parseInt(fuelPellets)) / Math.log(2);

		int offset = Math.round(output - Math.floor(output)) == 0 ? -1 : 1;
		long base = Math.round(output);

		Double roundedTwo = Math.pow(2, base);

		/* Get the pallets to a power of two. */
		/*
		while (fuelPelletsInt != roundedTwo.intValue()) {
			fuelPelletsInt += offset;

			counter++;
		}

		while (fuelPelletsInt != 1) {
			fuelPelletsInt = fuelPelletsInt / 2;

			counter++;
		}
		*/

		return counter;
	}

	static BigDecimal bigLog(BigInteger bigInteger) {
		int length = bigInteger.toString().length();
		/* Lost precision. */
		double coefficient = new BigDecimal(bigInteger).divide(BigDecimal.valueOf(10).pow(length)).doubleValue();

		BigDecimal a = new BigDecimal(Math.log10(Math.pow(10, length)));
		BigDecimal b = new BigDecimal(Math.log10(coefficient));

		return a.add(b);
	}
}
