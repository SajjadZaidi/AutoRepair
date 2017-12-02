package org.apache.commons.math3.distribution;


public class MixtureMultivariateNormalDistribution extends org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution<org.apache.commons.math3.distribution.MultivariateNormalDistribution> {
	public MixtureMultivariateNormalDistribution(double[] weights ,double[][] means ,double[][][] covariances) {
		super(org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution.createComponents(weights, means, covariances));
	}

	public MixtureMultivariateNormalDistribution(java.util.List<org.apache.commons.math3.util.Pair<java.lang.Double, org.apache.commons.math3.distribution.MultivariateNormalDistribution>> components) {
		super(components);
	}

	public MixtureMultivariateNormalDistribution(org.apache.commons.math3.random.RandomGenerator rng ,java.util.List<org.apache.commons.math3.util.Pair<java.lang.Double, org.apache.commons.math3.distribution.MultivariateNormalDistribution>> components) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NotPositiveException {
		super(rng, components);
	}

	private static java.util.List<org.apache.commons.math3.util.Pair<java.lang.Double, org.apache.commons.math3.distribution.MultivariateNormalDistribution>> createComponents(double[] weights, double[][] means, double[][][] covariances) {
		final java.util.List<org.apache.commons.math3.util.Pair<java.lang.Double, org.apache.commons.math3.distribution.MultivariateNormalDistribution>> mvns = new java.util.ArrayList<org.apache.commons.math3.util.Pair<java.lang.Double, org.apache.commons.math3.distribution.MultivariateNormalDistribution>>(weights.length);
		for (int i = 0 ; i < (weights.length) ; i++) {
			final org.apache.commons.math3.distribution.MultivariateNormalDistribution dist = new org.apache.commons.math3.distribution.MultivariateNormalDistribution(means[i] , covariances[i]);
			mvns.add(new org.apache.commons.math3.util.Pair<java.lang.Double, org.apache.commons.math3.distribution.MultivariateNormalDistribution>(weights[i] , dist));
		}
		return mvns;
	}
}

