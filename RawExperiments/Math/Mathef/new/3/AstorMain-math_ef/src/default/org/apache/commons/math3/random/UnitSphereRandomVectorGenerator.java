package org.apache.commons.math3.random;


public class UnitSphereRandomVectorGenerator implements org.apache.commons.math3.random.RandomVectorGenerator {
	private final org.apache.commons.math3.random.RandomGenerator rand;

	private final int dimension;

	public UnitSphereRandomVectorGenerator(final int dimension ,final org.apache.commons.math3.random.RandomGenerator rand) {
		this.dimension = dimension;
		this.rand = rand;
	}

	public UnitSphereRandomVectorGenerator(final int dimension) {
		this(dimension, new org.apache.commons.math3.random.MersenneTwister());
	}

	public double[] nextVector() {
		final double[] v = new double[dimension];
		double normSq = 0;
		for (int i = 0 ; i < (dimension) ; i++) {
			final double comp = rand.nextGaussian();
			v[i] = comp;
			normSq += comp * comp;
		}
		final double f = 1 / (org.apache.commons.math3.util.FastMath.sqrt(normSq));
		for (int i = 0 ; i < (dimension) ; i++) {
			v[i] *= f;
		}
		return v;
	}
}

