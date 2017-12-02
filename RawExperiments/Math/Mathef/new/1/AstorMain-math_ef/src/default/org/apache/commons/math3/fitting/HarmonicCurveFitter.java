package org.apache.commons.math3.fitting;


public class HarmonicCurveFitter extends org.apache.commons.math3.fitting.AbstractCurveFitter {
	private static final org.apache.commons.math3.analysis.function.HarmonicOscillator.Parametric FUNCTION = new org.apache.commons.math3.analysis.function.HarmonicOscillator.Parametric();

	private final double[] initialGuess;

	private final int maxIter;

	private HarmonicCurveFitter(double[] initialGuess ,int maxIter) {
		this.initialGuess = initialGuess;
		this.maxIter = maxIter;
	}

	public static org.apache.commons.math3.fitting.HarmonicCurveFitter create() {
		return new org.apache.commons.math3.fitting.HarmonicCurveFitter(null , java.lang.Integer.MAX_VALUE);
	}

	public org.apache.commons.math3.fitting.HarmonicCurveFitter withStartPoint(double[] newStart) {
		return new org.apache.commons.math3.fitting.HarmonicCurveFitter(newStart.clone() , maxIter);
	}

	public org.apache.commons.math3.fitting.HarmonicCurveFitter withMaxIterations(int newMaxIter) {
		return new org.apache.commons.math3.fitting.HarmonicCurveFitter(initialGuess , newMaxIter);
	}

	@java.lang.Override
	protected org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem getProblem(java.util.Collection<org.apache.commons.math3.fitting.WeightedObservedPoint> observations) {
		final int len = observations.size();
		final double[] target = new double[len];
		final double[] weights = new double[len];
		int i = 0;
		for (org.apache.commons.math3.fitting.WeightedObservedPoint obs : observations) {
			target[i] = obs.getY();
			weights[i] = obs.getWeight();
			++i;
		}
		final org.apache.commons.math3.fitting.AbstractCurveFitter.TheoreticalValuesFunction model = new org.apache.commons.math3.fitting.AbstractCurveFitter.TheoreticalValuesFunction(org.apache.commons.math3.fitting.HarmonicCurveFitter.FUNCTION , observations);
		final double[] startPoint = (initialGuess) != null ? initialGuess : new org.apache.commons.math3.fitting.HarmonicCurveFitter.ParameterGuesser(observations).guess();
		return new org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder().maxEvaluations(java.lang.Integer.MAX_VALUE).maxIterations(maxIter).start(startPoint).target(target).weight(new org.apache.commons.math3.linear.DiagonalMatrix(weights)).model(model.getModelFunction(), model.getModelFunctionJacobian()).build();
	}

	public static class ParameterGuesser {
		private final double a;

		private final double omega;

		private final double phi;

		public ParameterGuesser(java.util.Collection<org.apache.commons.math3.fitting.WeightedObservedPoint> observations) {
			if ((observations.size()) < 4) {
				throw new org.apache.commons.math3.exception.NumberIsTooSmallException(org.apache.commons.math3.exception.util.LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE , observations.size() , 4 , true);
			} 
			final org.apache.commons.math3.fitting.WeightedObservedPoint[] sorted = sortObservations(observations).toArray(new org.apache.commons.math3.fitting.WeightedObservedPoint[0]);
			final double[] aOmega = guessAOmega(sorted);
			a = aOmega[0];
			omega = aOmega[1];
			phi = guessPhi(sorted);
		}

		public double[] guess() {
			return new double[]{ a , omega , phi };
		}

		private java.util.List<org.apache.commons.math3.fitting.WeightedObservedPoint> sortObservations(java.util.Collection<org.apache.commons.math3.fitting.WeightedObservedPoint> unsorted) {
			final java.util.List<org.apache.commons.math3.fitting.WeightedObservedPoint> observations = new java.util.ArrayList<org.apache.commons.math3.fitting.WeightedObservedPoint>(unsorted);
			org.apache.commons.math3.fitting.WeightedObservedPoint curr = observations.get(0);
			final int len = observations.size();
			for (int j = 1 ; j < len ; j++) {
				org.apache.commons.math3.fitting.WeightedObservedPoint prec = curr;
				curr = observations.get(j);
				if ((curr.getX()) < (prec.getX())) {
					int i = j - 1;
					org.apache.commons.math3.fitting.WeightedObservedPoint mI = observations.get(i);
					while ((i >= 0) && ((curr.getX()) < (mI.getX()))) {
						observations.set((i + 1), mI);
						if ((i--) != 0) {
							mI = observations.get(i);
						} 
					}
					observations.set((i + 1), curr);
					curr = observations.get(j);
				} 
			}
			return observations;
		}

		private double[] guessAOmega(org.apache.commons.math3.fitting.WeightedObservedPoint[] observations) {
			final double[] aOmega = new double[2];
			double sx2 = 0;
			double sy2 = 0;
			double sxy = 0;
			double sxz = 0;
			double syz = 0;
			double currentX = observations[0].getX();
			double currentY = observations[0].getY();
			double f2Integral = 0;
			double fPrime2Integral = 0;
			final double startX = currentX;
			for (int i = 1 ; i < (observations.length) ; ++i) {
				final double previousX = currentX;
				final double previousY = currentY;
				currentX = observations[i].getX();
				currentY = observations[i].getY();
				final double dx = currentX - previousX;
				final double dy = currentY - previousY;
				final double f2StepIntegral = (dx * (((previousY * previousY) + (previousY * currentY)) + (currentY * currentY))) / 3;
				final double fPrime2StepIntegral = (dy * dy) / dx;
				final double x = currentX - startX;
				f2Integral += f2StepIntegral;
				fPrime2Integral += fPrime2StepIntegral;
				sx2 += x * x;
				sy2 += f2Integral * f2Integral;
				sxy += x * f2Integral;
				sxz += x * fPrime2Integral;
				syz += f2Integral * fPrime2Integral;
			}
			double c1 = (sy2 * sxz) - (sxy * syz);
			double c2 = (sxy * sxz) - (sx2 * syz);
			double c3 = (sx2 * sy2) - (sxy * sxy);
			if (((c1 / c2) < 0) || ((c2 / c3) < 0)) {
				final int last = (observations.length) - 1;
				final double xRange = (observations[last].getX()) - (observations[0].getX());
				if (xRange == 0) {
					throw new org.apache.commons.math3.exception.ZeroException();
				} 
				aOmega[1] = (2 * (java.lang.Math.PI)) / xRange;
				double yMin = java.lang.Double.POSITIVE_INFINITY;
				double yMax = java.lang.Double.NEGATIVE_INFINITY;
				for (int i = 1 ; i < (observations.length) ; ++i) {
					final double y = observations[i].getY();
					if (y < yMin) {
						yMin = y;
					} 
					if (y > yMax) {
						yMax = y;
					} 
				}
				aOmega[0] = 0.5 * (yMax - yMin);
			} else {
				if (c2 == 0) {
					throw new org.apache.commons.math3.exception.MathIllegalStateException(org.apache.commons.math3.exception.util.LocalizedFormats.ZERO_DENOMINATOR);
				} 
				aOmega[0] = org.apache.commons.math3.util.FastMath.sqrt((c1 / c2));
				aOmega[1] = org.apache.commons.math3.util.FastMath.sqrt((c2 / c3));
			}
			return aOmega;
		}

		private double guessPhi(org.apache.commons.math3.fitting.WeightedObservedPoint[] observations) {
			double fcMean = 0;
			double fsMean = 0;
			double currentX = observations[0].getX();
			double currentY = observations[0].getY();
			for (int i = 1 ; i < (observations.length) ; ++i) {
				final double previousX = currentX;
				final double previousY = currentY;
				currentX = observations[i].getX();
				currentY = observations[i].getY();
				final double currentYPrime = (currentY - previousY) / (currentX - previousX);
				double omegaX = (omega) * currentX;
				double cosine = org.apache.commons.math3.util.FastMath.cos(omegaX);
				double sine = org.apache.commons.math3.util.FastMath.sin(omegaX);
				fcMean += (((omega) * currentY) * cosine) - (currentYPrime * sine);
				fsMean += (((omega) * currentY) * sine) + (currentYPrime * cosine);
			}
			return org.apache.commons.math3.util.FastMath.atan2((-fsMean), fcMean);
		}
	}
}

