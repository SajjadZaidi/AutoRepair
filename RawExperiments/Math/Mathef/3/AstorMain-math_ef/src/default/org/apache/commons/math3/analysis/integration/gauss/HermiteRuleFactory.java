package org.apache.commons.math3.analysis.integration.gauss;


public class HermiteRuleFactory extends org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory<java.lang.Double> {
	private static final double SQRT_PI = 1.772453850905516;

	private static final double H0 = 0.7511255444649425;

	private static final double H1 = 1.0622519320271968;

	@java.lang.Override
	protected org.apache.commons.math3.util.Pair<java.lang.Double[], java.lang.Double[]> computeRule(int numberOfPoints) throws org.apache.commons.math3.exception.DimensionMismatchException {
		if (numberOfPoints == 1) {
			return new org.apache.commons.math3.util.Pair<java.lang.Double[], java.lang.Double[]>(new java.lang.Double[]{ 0.0 } , new java.lang.Double[]{ org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.SQRT_PI });
		} 
		final int lastNumPoints = numberOfPoints - 1;
		final java.lang.Double[] previousPoints = getRuleInternal(lastNumPoints).getFirst();
		final java.lang.Double[] points = new java.lang.Double[numberOfPoints];
		final java.lang.Double[] weights = new java.lang.Double[numberOfPoints];
		final double sqrtTwoTimesLastNumPoints = org.apache.commons.math3.util.FastMath.sqrt((2 * lastNumPoints));
		final double sqrtTwoTimesNumPoints = org.apache.commons.math3.util.FastMath.sqrt((2 * numberOfPoints));
		final int iMax = numberOfPoints / 2;
		for (int i = 0 ; i < iMax ; i++) {
			double a = i == 0 ? -sqrtTwoTimesLastNumPoints : previousPoints[(i - 1)].doubleValue();
			double b = iMax == 1 ? -0.5 : previousPoints[i].doubleValue();
			double hma = org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.H0;
			double ha = (org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.H1) * a;
			double hmb = org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.H0;
			double hb = (org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.H1) * b;
			for (int j = 1 ; j < numberOfPoints ; j++) {
				final double jp1 = j + 1;
				final double s = org.apache.commons.math3.util.FastMath.sqrt((2 / jp1));
				final double sm = org.apache.commons.math3.util.FastMath.sqrt((j / jp1));
				final double hpa = ((s * a) * ha) - (sm * hma);
				final double hpb = ((s * b) * hb) - (sm * hmb);
				hma = ha;
				ha = hpa;
				hmb = hb;
				hb = hpb;
			}
			double c = 0.5 * (a + b);
			double hmc = org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.H0;
			double hc = (org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.H1) * c;
			boolean done = false;
			while (!done) {
				done = (b - a) <= (java.lang.Math.ulp(c));
				hmc = org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.H0;
				hc = (org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.H1) * c;
				for (int j = 1 ; j < numberOfPoints ; j++) {
					final double jp1 = j + 1;
					final double s = org.apache.commons.math3.util.FastMath.sqrt((2 / jp1));
					final double sm = org.apache.commons.math3.util.FastMath.sqrt((j / jp1));
					final double hpc = ((s * c) * hc) - (sm * hmc);
					hmc = hc;
					hc = hpc;
				}
				if (!done) {
					if ((ha * hc) < 0) {
						b = c;
						hmb = hmc;
						hb = hc;
					} else {
						a = c;
						hma = hmc;
						ha = hc;
					}
					c = 0.5 * (a + b);
				} 
			}
			final double d = sqrtTwoTimesNumPoints * hmc;
			final double w = 2 / (d * d);
			points[i] = c;
			weights[i] = w;
			final int idx = lastNumPoints - i;
			points[idx] = -c;
			weights[idx] = w;
		}
		if ((numberOfPoints % 2) != 0) {
			double hm = org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory.H0;
			for (int j = 1 ; j < numberOfPoints ; j += 2) {
				final double jp1 = j + 1;
				hm = (-(org.apache.commons.math3.util.FastMath.sqrt((j / jp1)))) * hm;
			}
			final double d = sqrtTwoTimesNumPoints * hm;
			final double w = 2 / (d * d);
			points[iMax] = 0.0;
			weights[iMax] = w;
		} 
		return new org.apache.commons.math3.util.Pair<java.lang.Double[], java.lang.Double[]>(points , weights);
	}
}

