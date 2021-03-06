package org.apache.commons.math3.analysis.interpolation;


public class MicrosphereInterpolatingFunction implements org.apache.commons.math3.analysis.MultivariateFunction {
	private final int dimension;

	private final java.util.List<org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereSurfaceElement> microsphere;

	private final double brightnessExponent;

	private final java.util.Map<org.apache.commons.math3.linear.RealVector, java.lang.Double> samples;

	private static class MicrosphereSurfaceElement {
		private final org.apache.commons.math3.linear.RealVector normal;

		private double brightestIllumination;

		private java.util.Map.Entry<org.apache.commons.math3.linear.RealVector, java.lang.Double> brightestSample;

		MicrosphereSurfaceElement(double[] n) {
			normal = new org.apache.commons.math3.linear.ArrayRealVector(n);
		}

		org.apache.commons.math3.linear.RealVector normal() {
			return normal;
		}

		void reset() {
			brightestIllumination = 0;
			brightestSample = null;
		}

		void store(final double illuminationFromSample, final java.util.Map.Entry<org.apache.commons.math3.linear.RealVector, java.lang.Double> sample) {
			if (illuminationFromSample > (org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereSurfaceElement.this.brightestIllumination)) {
				org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereSurfaceElement.this.brightestIllumination = illuminationFromSample;
				org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereSurfaceElement.this.brightestSample = sample;
			} 
		}

		double illumination() {
			return brightestIllumination;
		}

		java.util.Map.Entry<org.apache.commons.math3.linear.RealVector, java.lang.Double> sample() {
			return brightestSample;
		}
	}

	public MicrosphereInterpolatingFunction(double[][] xval ,double[] yval ,int brightnessExponent ,int microsphereElements ,org.apache.commons.math3.random.UnitSphereRandomVectorGenerator rand) throws org.apache.commons.math3.exception.DimensionMismatchException , org.apache.commons.math3.exception.NoDataException , org.apache.commons.math3.exception.NullArgumentException {
		if ((xval == null) || (yval == null)) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
		if ((xval.length) == 0) {
			throw new org.apache.commons.math3.exception.NoDataException();
		} 
		if ((xval.length) != (yval.length)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(xval.length , yval.length);
		} 
		if ((xval[0]) == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
		dimension = xval[0].length;
		this.brightnessExponent = brightnessExponent;
		samples = new java.util.HashMap<org.apache.commons.math3.linear.RealVector, java.lang.Double>(yval.length);
		for (int i = 0 ; i < (xval.length) ; ++i) {
			final double[] xvalI = xval[i];
			if (xvalI == null) {
				throw new org.apache.commons.math3.exception.NullArgumentException();
			} 
			if ((xvalI.length) != (dimension)) {
				throw new org.apache.commons.math3.exception.DimensionMismatchException(xvalI.length , dimension);
			} 
			samples.put(new org.apache.commons.math3.linear.ArrayRealVector(xvalI), yval[i]);
		}
		microsphere = new java.util.ArrayList<org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereSurfaceElement>(microsphereElements);
		for (int i = 0 ; i < microsphereElements ; i++) {
			microsphere.add(new org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereSurfaceElement(rand.nextVector()));
		}
	}

	public double value(double[] point) throws org.apache.commons.math3.exception.DimensionMismatchException {
		final org.apache.commons.math3.linear.RealVector p = new org.apache.commons.math3.linear.ArrayRealVector(point);
		for (org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereSurfaceElement md : microsphere) {
			md.reset();
		}
		for (java.util.Map.Entry<org.apache.commons.math3.linear.RealVector, java.lang.Double> sd : samples.entrySet()) {
			final org.apache.commons.math3.linear.RealVector diff = sd.getKey().subtract(p);
			final double diffNorm = diff.getNorm();
			if ((org.apache.commons.math3.util.FastMath.abs(diffNorm)) < (org.apache.commons.math3.util.FastMath.ulp(1.0))) {
				return sd.getValue();
			} 
			for (org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereSurfaceElement md : microsphere) {
				final double w = org.apache.commons.math3.util.FastMath.pow(diffNorm, (-(brightnessExponent)));
				md.store(((cosAngle(diff, md.normal())) * w), sd);
			}
		}
		double value = 0;
		double totalWeight = 0;
		for (org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereSurfaceElement md : microsphere) {
			final double iV = md.illumination();
			final java.util.Map.Entry<org.apache.commons.math3.linear.RealVector, java.lang.Double> sd = md.sample();
			if (sd != null) {
				value += iV * (sd.getValue());
				totalWeight += iV;
			} 
		}
		return value / totalWeight;
	}

	private double cosAngle(final org.apache.commons.math3.linear.RealVector v, final org.apache.commons.math3.linear.RealVector w) {
		return (v.dotProduct(w)) / ((v.getNorm()) * (w.getNorm()));
	}
}

