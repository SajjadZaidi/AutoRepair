package org.apache.commons.math3.analysis.integration.gauss;


public class GaussIntegratorFactory {
	private final org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory<java.lang.Double> legendre = new org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory();

	private final org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory<java.math.BigDecimal> legendreHighPrecision = new org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory();

	private final org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory<java.lang.Double> hermite = new org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory();

	public org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator legendre(int numberOfPoints) {
		return new org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator(org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorFactory.getRule(legendre, numberOfPoints));
	}

	public org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator legendre(int numberOfPoints, double lowerBound, double upperBound) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		return new org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator(org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorFactory.transform(org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorFactory.getRule(legendre, numberOfPoints), lowerBound, upperBound));
	}

	public org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator legendreHighPrecision(int numberOfPoints) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		return new org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator(org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorFactory.getRule(legendreHighPrecision, numberOfPoints));
	}

	public org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator legendreHighPrecision(int numberOfPoints, double lowerBound, double upperBound) throws org.apache.commons.math3.exception.NotStrictlyPositiveException {
		return new org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator(org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorFactory.transform(org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorFactory.getRule(legendreHighPrecision, numberOfPoints), lowerBound, upperBound));
	}

	public org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator hermite(int numberOfPoints) {
		return new org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator(org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorFactory.getRule(hermite, numberOfPoints));
	}

	private static org.apache.commons.math3.util.Pair<double[], double[]> getRule(org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory<? extends java.lang.Number> factory, int numberOfPoints) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NotStrictlyPositiveException {
		return factory.getRule(numberOfPoints);
	}

	private static org.apache.commons.math3.util.Pair<double[], double[]> transform(org.apache.commons.math3.util.Pair<double[], double[]> rule, double a, double b) {
		final double[] points = rule.getFirst();
		final double[] weights = rule.getSecond();
		final double scale = (b - a) / 2;
		final double shift = a + scale;
		for (int i = 0 ; i < (points.length) ; i++) {
			points[i] = ((points[i]) * scale) + shift;
			weights[i] *= scale;
		}
		return new org.apache.commons.math3.util.Pair<double[], double[]>(points , weights);
	}
}

