package org.apache.commons.math.optimization.fitting;


public class HarmonicFitter {
	private final org.apache.commons.math.optimization.fitting.CurveFitter fitter;

	private double[] parameters;

	public HarmonicFitter(final org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer optimizer) {
		this.fitter = new org.apache.commons.math.optimization.fitting.CurveFitter(optimizer);
		parameters = null;
	}

	public HarmonicFitter(final org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer optimizer ,final double[] initialGuess) {
		this.fitter = new org.apache.commons.math.optimization.fitting.CurveFitter(optimizer);
		org.apache.commons.math.optimization.fitting.HarmonicFitter.this.parameters = initialGuess.clone();
	}

	public void addObservedPoint(double weight, double x, double y) {
		fitter.addObservedPoint(weight, x, y);
	}

	public org.apache.commons.math.optimization.fitting.HarmonicFunction fit() throws org.apache.commons.math.optimization.OptimizationException {
		if ((parameters) == null) {
			final org.apache.commons.math.optimization.fitting.WeightedObservedPoint[] observations = fitter.getObservations();
			if ((observations.length) < 4) {
				throw new org.apache.commons.math.exception.NumberIsTooSmallException(org.apache.commons.math.exception.util.LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE , observations.length , 4 , true);
			} 
			org.apache.commons.math.optimization.fitting.HarmonicCoefficientsGuesser guesser = new org.apache.commons.math.optimization.fitting.HarmonicCoefficientsGuesser(observations);
			guesser.guess();
			parameters = new double[]{ guesser.getGuessedAmplitude() , guesser.getGuessedPulsation() , guesser.getGuessedPhase() };
		} 
		double[] fitted = fitter.fit(new org.apache.commons.math.optimization.fitting.HarmonicFitter.ParametricHarmonicFunction(), parameters);
		return new org.apache.commons.math.optimization.fitting.HarmonicFunction(fitted[0] , fitted[1] , fitted[2]);
	}

	private static class ParametricHarmonicFunction implements org.apache.commons.math.optimization.fitting.ParametricRealFunction {
		public double value(double x, double[] parameters) {
			final double a = parameters[0];
			final double omega = parameters[1];
			final double phi = parameters[2];
			return a * (org.apache.commons.math.util.FastMath.cos(((omega * x) + phi)));
		}

		public double[] gradient(double x, double[] parameters) {
			final double a = parameters[0];
			final double omega = parameters[1];
			final double phi = parameters[2];
			final double alpha = (omega * x) + phi;
			final double cosAlpha = org.apache.commons.math.util.FastMath.cos(alpha);
			final double sinAlpha = org.apache.commons.math.util.FastMath.sin(alpha);
			return new double[]{ cosAlpha , ((-a) * x) * sinAlpha , (-a) * sinAlpha };
		}
	}
}

