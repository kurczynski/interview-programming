package org.kurczynski.interviewprogramming.fuelingectionperfection;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class Answer {
	public static void main(String args[]) {
		for (int i = 1; i < 20; i++)
			System.out.println(i + ": " + Answer.answer(String.valueOf(i)));
		System.out.println(Answer.answer(
				"210394610234012983741982476098216357698769087690870987203148712034987120938462109837402198409012394098213740961231987654987634987230982130498723492103946102340129837419824760982163576987690876908709872031487120349871209384621098374021984090123940982137409612319876549876349872309821304987234935498734598735498"));

	}

	public static final BigInteger BIG_INTEGER_TWO = BigInteger.valueOf(2);
	public static final int MAX_PELLETS_DIGITS = 309;

	/**
	 * Finds the minimum number of operations needed to process the given fuel pellets.
	 *
	 * @param n string of the number of fuel pellets to process.
	 * @return number of operations.
	 * @throws IllegalArgumentException thrown if a number is given that is larger than {@link Answer#MAX_PELLETS_DIGITS}.
	 */
	static int answer(String n) throws IllegalArgumentException {
		if (n.length() > Answer.MAX_PELLETS_DIGITS)
			throw new IllegalArgumentException(
					"Max number of digits in pellets is " + Answer.MAX_PELLETS_DIGITS + ", found " + n.length());

		BigInteger fuelPellets = new BigInteger(n);

		return Answer.getMinOperations(fuelPellets);
	}

	/**
	 * Finds the logarithm base 10 of the given {@link BigInteger}. This method only has the precision of a
	 * {@code double} because it uses {@link Math#log10(double)}. The loss of precision doesn't matter in this
	 * application.
	 *
	 * @param bigInteger the number to find the logarithm base 10 of.
	 * @return the logarithm base 10 of the input.
	 */
	static double bigLog10(BigInteger bigInteger) {
		int length = bigInteger.toString().length();

		double coefficient = new BigDecimal(bigInteger)
				.divide(BigDecimal.valueOf(10).pow(length), MathContext.DECIMAL64)
				.doubleValue();

		/* log10(10^length) is just length, so don't bother expanding the possibly enormous number. */
		return length + Math.log10(coefficient);
	}

	/**
	 * Calculates the exponential power of a given base and power using {@link BigInteger}.
	 *
	 * @param base base of the exponential numbers.
	 * @param pow  power of the exponential numbers.
	 * @return result of the exponential numbers.
	 */
	static BigInteger bigPow(BigInteger base, BigInteger pow) {
		if (pow.equals(BigInteger.ZERO))
			return BigInteger.ONE;

		BigInteger i = BigInteger.ONE;
		BigInteger output = base;

		while (!pow.equals(i)) {
			output = output.multiply(base);
			i = i.add(BigInteger.ONE);
		}

		return output;
	}

	/**
	 * Finds the minimum number of operations needed to process the fuel pellets.
	 *
	 * @param fuelPellets number of fuel pellets given to be processed.
	 * @return number of operations.
	 */
	static int getMinOperations(BigInteger fuelPellets) {
		int operations = 0;

		while (fuelPellets.compareTo(BigInteger.ONE) == 1) {
			if (fuelPellets.mod(Answer.BIG_INTEGER_TWO).equals(BigInteger.ZERO)) {
				fuelPellets = fuelPellets.divide(Answer.BIG_INTEGER_TWO);

				operations++;
			} else {
				BigDecimal logarithm = BigDecimal.valueOf(Answer.bigLog10(fuelPellets) / Math.log10(2));

				BigInteger powerCeiling = Answer.bigPow(Answer.BIG_INTEGER_TWO, logarithm.setScale(0, RoundingMode.CEILING).toBigInteger());
				BigInteger addDifference = powerCeiling.subtract(fuelPellets);

				BigInteger powerFloor = Answer.bigPow(Answer.BIG_INTEGER_TWO, logarithm.setScale(0, RoundingMode.FLOOR).toBigInteger());
				BigInteger subtractDifference = fuelPellets.subtract(powerFloor);

				int difference = subtractDifference.compareTo(addDifference);

				if (difference == -1 || difference == 0) {
					operations += subtractDifference.intValue();

					fuelPellets = powerFloor;
				} else {
					operations += addDifference.intValue();

					fuelPellets = powerCeiling;
				}
			}
		}

		return operations;
	}
}