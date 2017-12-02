package org.apache.commons.math3.util;


public class RandomPivotingStrategy implements java.io.Serializable , org.apache.commons.math3.util.PivotingStrategyInterface {
	private static final long serialVersionUID = 20140713L;

	private final org.apache.commons.math3.random.RandomGenerator random;

	public RandomPivotingStrategy(final org.apache.commons.math3.random.RandomGenerator random) {
		this.random = random;
	}

	public int pivotIndex(final double[] work, final int begin, final int end) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		org.apache.commons.math3.util.MathArrays.verifyValues(work, begin, (end - begin));
		return begin + (random.nextInt(((end - begin) - 1)));
	}
}

