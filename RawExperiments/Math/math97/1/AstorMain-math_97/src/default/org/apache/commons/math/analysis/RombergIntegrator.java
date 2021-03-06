package org.apache.commons.math.analysis;


public class RombergIntegrator extends org.apache.commons.math.analysis.UnivariateRealIntegratorImpl {
	private static final long serialVersionUID = -1058849527738180243L;

	public RombergIntegrator(org.apache.commons.math.analysis.UnivariateRealFunction f) {
		super(f, 32);
	}

	public double integrate(double min, double max) throws java.lang.IllegalArgumentException, org.apache.commons.math.FunctionEvaluationException, org.apache.commons.math.MaxIterationsExceededException {
		int i = 1;
		int j;
		int m = (maximalIterationCount) + 1;
		double r;
		double[][] t = new double[m][m];
		double s;
		double olds;
		clearResult();
		verifyInterval(min, max);
		verifyIterationCount();
		org.apache.commons.math.analysis.TrapezoidIntegrator qtrap = new org.apache.commons.math.analysis.TrapezoidIntegrator(org.apache.commons.math.analysis.RombergIntegrator.this.f);
		t[0][0] = qtrap.stage(min, max, 0);
		olds = t[0][0];
		while (i <= (maximalIterationCount)) {
			t[i][0] = qtrap.stage(min, max, i);
			for (j = 1 ; j <= i ; j++) {
				r = (1L << (2 * j)) - 1;
				t[i][j] = (t[i][(j - 1)]) + (((t[i][(j - 1)]) - (t[(i - 1)][(j - 1)])) / r);
			}
			s = t[i][i];
			if (i >= (minimalIterationCount)) {
				if ((java.lang.Math.abs((s - olds))) <= (java.lang.Math.abs(((relativeAccuracy) * olds)))) {
					setResult(s, i);
					return result;
				} 
			} 
			olds = s;
			i++;
		}
		throw new org.apache.commons.math.MaxIterationsExceededException(maximalIterationCount);
	}

	protected void verifyIterationCount() throws java.lang.IllegalArgumentException {
		super.verifyIterationCount();
		if ((maximalIterationCount) > 32) {
			throw new java.lang.IllegalArgumentException(("Iteration upper limit out of [0, 32] range: " + (maximalIterationCount)));
		} 
	}
}

