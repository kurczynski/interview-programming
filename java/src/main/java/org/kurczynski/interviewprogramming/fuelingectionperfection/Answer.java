package org.kurczynski.interviewprogramming.fuelingectionperfection;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Answer {
	public static void main(String args[]) {
		System.out.println(Answer.answer(
				"210394610234012983741982476098216357698769087690870987203148712034987120938462109837402198409012394098213740961231987654987634987230982130498723492103946102340129837419824760982163576987690876908709872031487120349871209384621098374021984090123940982137409612319876549876349872309821304987234935498734598735498"));

		for (int i = 1; i < 20; i++)
			System.out.println(i + ": " + Answer.answer(String.valueOf(i)));
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

		Double output = Answer.bigLog10(fuelPellets) / Math.log10(2);

		long logarithm = Math.round(output);
		BigInteger roundedPower = Answer.bigPow(Answer.BIG_INTEGER_TWO, BigInteger.valueOf(logarithm));

		return Answer.getMinOperations(fuelPellets, roundedPower);
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
	 * @param fuelPellets  number of fuel pellets given to be processed.
	 * @param roundedPower the power of two that is closest to the number of given fuel pellets.
	 * @return number of operations.
	 */
	static int getMinOperations(BigInteger fuelPellets, BigInteger roundedPower) {
		int operations = fuelPellets.subtract(roundedPower).abs().intValue();

		while (!roundedPower.equals(BigInteger.ONE)) {
			roundedPower = roundedPower.divide(Answer.BIG_INTEGER_TWO);

			operations++;
		}

		return operations;
	}
}