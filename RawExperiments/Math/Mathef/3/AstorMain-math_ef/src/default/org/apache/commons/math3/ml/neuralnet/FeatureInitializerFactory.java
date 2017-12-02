package org.apache.commons.math3.ml.neuralnet;


public class FeatureInitializerFactory {
	private FeatureInitializerFactory() {
	}

	public static org.apache.commons.math3.ml.neuralnet.FeatureInitializer uniform(final org.apache.commons.math3.random.RandomGenerator rng, final double min, final double max) {
		return org.apache.commons.math3.ml.neuralnet.FeatureInitializerFactory.randomize(new org.apache.commons.math3.distribution.UniformRealDistribution(rng , min , max), org.apache.commons.math3.ml.neuralnet.FeatureInitializerFactory.function(new org.apache.commons.math3.analysis.function.Constant(0), 0, 0));
	}

	public static org.apache.commons.math3.ml.neuralnet.FeatureInitializer uniform(final double min, final double max) {
		return org.apache.commons.math3.ml.neuralnet.FeatureInitializerFactory.randomize(new org.apache.commons.math3.distribution.UniformRealDistribution(min , max), org.apache.commons.math3.ml.neuralnet.FeatureInitializerFactory.function(new org.apache.commons.math3.analysis.function.Constant(0), 0, 0));
	}

	public static org.apache.commons.math3.ml.neuralnet.FeatureInitializer function(final org.apache.commons.math3.analysis.UnivariateFunction f, final double init, final double inc) {
		return new org.apache.commons.math3.ml.neuralnet.FeatureInitializer() {
			private double arg = init;

			public double value() {
				final double result = f.value(arg);
				arg += inc;
				return result;
			}
		};
	}

	public static org.apache.commons.math3.ml.neuralnet.FeatureInitializer randomize(final org.apache.commons.math3.distribution.RealDistribution random, final org.apache.commons.math3.ml.neuralnet.FeatureInitializer orig) {
		return new org.apache.commons.math3.ml.neuralnet.FeatureInitializer() {
			public double value() {
				return (orig.value()) + (random.sample());
			}
		};
	}
}

