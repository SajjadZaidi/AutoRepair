package org.apache.commons.math3.random;


public class JDKRandomGenerator extends java.util.Random implements org.apache.commons.math3.random.RandomGenerator {
	private static final long serialVersionUID = -7745277476784028798L;

	public void setSeed(int seed) {
		setSeed(((long)(seed)));
	}

	public void setSeed(int[] seed) {
		setSeed(org.apache.commons.math3.random.RandomGeneratorFactory.convertToLong(seed));
	}
}

