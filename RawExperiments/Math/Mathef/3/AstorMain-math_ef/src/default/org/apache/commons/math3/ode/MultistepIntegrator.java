package org.apache.commons.math3.ode;


public abstract class MultistepIntegrator extends org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator {
	protected double[] scaled;

	protected org.apache.commons.math3.linear.Array2DRowRealMatrix nordsieck;

	private org.apache.commons.math3.ode.FirstOrderIntegrator starter;

	private final int nSteps;

	private double exp;

	private double safety;

	private double minReduction;

	private double maxGrowth;

	protected MultistepIntegrator(final java.lang.String name ,final int nSteps ,final int order ,final double minStep ,final double maxStep ,final double scalAbsoluteTolerance ,final double scalRelativeTolerance) throws org.apache.commons.math3.exception.NumberIsTooSmallException {
		super(name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
		if (nSteps < 2) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(org.apache.commons.math3.exception.util.LocalizedFormats.INTEGRATION_METHOD_NEEDS_AT_LEAST_TWO_PREVIOUS_POINTS , nSteps , 2 , true);
		} 
		starter = new org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator(minStep , maxStep , scalAbsoluteTolerance , scalRelativeTolerance);
		this.nSteps = nSteps;
		exp = (-1.0) / order;
		setSafety(0.9);
		setMinReduction(0.2);
		setMaxGrowth(org.apache.commons.math3.util.FastMath.pow(2.0, (-(exp))));
	}

	protected MultistepIntegrator(final java.lang.String name ,final int nSteps ,final int order ,final double minStep ,final double maxStep ,final double[] vecAbsoluteTolerance ,final double[] vecRelativeTolerance) {
		super(name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
		starter = new org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator(minStep , maxStep , vecAbsoluteTolerance , vecRelativeTolerance);
		this.nSteps = nSteps;
		exp = (-1.0) / order;
		setSafety(0.9);
		setMinReduction(0.2);
		setMaxGrowth(org.apache.commons.math3.util.FastMath.pow(2.0, (-(exp))));
	}

	public org.apache.commons.math3.ode.ODEIntegrator getStarterIntegrator() {
		return starter;
	}

	public void setStarterIntegrator(org.apache.commons.math3.ode.FirstOrderIntegrator starterIntegrator) {
		org.apache.commons.math3.ode.MultistepIntegrator.this.starter = starterIntegrator;
	}

	protected void start(final double t0, final double[] y0, final double t) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException, org.apache.commons.math3.exception.NumberIsTooSmallException {
		starter.clearEventHandlers();
		starter.clearStepHandlers();
		starter.addStepHandler(new org.apache.commons.math3.ode.MultistepIntegrator.NordsieckInitializer(nSteps , y0.length));
		try {
			if ((starter) instanceof org.apache.commons.math3.ode.AbstractIntegrator) {
				((org.apache.commons.math3.ode.AbstractIntegrator)(starter)).integrate(getExpandable(), t);
			} else {
				starter.integrate(new org.apache.commons.math3.ode.FirstOrderDifferentialEquations() {
					public int getDimension() {
						return getExpandable().getTotalDimension();
					}

					public void computeDerivatives(double t, double[] y, double[] yDot) {
						getExpandable().computeDerivatives(t, y, yDot);
					}
				}, t0, y0, t, new double[y0.length]);
			}
		} catch (org.apache.commons.math3.ode.MultistepIntegrator.InitializationCompletedMarkerException icme) {
			getEvaluationsCounter().incrementCount(starter.getEvaluations());
		}
		starter.clearStepHandlers();
	}

	protected abstract org.apache.commons.math3.linear.Array2DRowRealMatrix initializeHighOrderDerivatives(final double h, final double[] t, final double[][] y, final double[][] yDot);

	public double getMinReduction() {
		return minReduction;
	}

	public void setMinReduction(final double minReduction) {
		org.apache.commons.math3.ode.MultistepIntegrator.this.minReduction = minReduction;
	}

	public double getMaxGrowth() {
		return maxGrowth;
	}

	public void setMaxGrowth(final double maxGrowth) {
		org.apache.commons.math3.ode.MultistepIntegrator.this.maxGrowth = maxGrowth;
	}

	public double getSafety() {
		return safety;
	}

	public void setSafety(final double safety) {
		org.apache.commons.math3.ode.MultistepIntegrator.this.safety = safety;
	}

	protected double computeStepGrowShrinkFactor(final double error) {
		return org.apache.commons.math3.util.FastMath.min(maxGrowth, org.apache.commons.math3.util.FastMath.max(minReduction, ((safety) * (org.apache.commons.math3.util.FastMath.pow(error, exp)))));
	}

	public interface NordsieckTransformer {
		org.apache.commons.math3.linear.Array2DRowRealMatrix initializeHighOrderDerivatives(final double h, final double[] t, final double[][] y, final double[][] yDot);
	}

	private class NordsieckInitializer implements org.apache.commons.math3.ode.sampling.StepHandler {
		private int count;

		private final double[] t;

		private final double[][] y;

		private final double[][] yDot;

		public NordsieckInitializer(final int nSteps ,final int n) {
			org.apache.commons.math3.ode.MultistepIntegrator.NordsieckInitializer.this.count = 0;
			this.t = new double[nSteps];
			this.y = new double[nSteps][n];
			this.yDot = new double[nSteps][n];
		}

		public void handleStep(org.apache.commons.math3.ode.sampling.StepInterpolator interpolator, boolean isLast) throws org.apache.commons.math3.exception.MaxCountExceededException {
			final double prev = interpolator.getPreviousTime();
			final double curr = interpolator.getCurrentTime();
			if ((count) == 0) {
				interpolator.setInterpolatedTime(prev);
				t[0] = prev;
				final org.apache.commons.math3.ode.ExpandableStatefulODE expandable = getExpandable();
				final org.apache.commons.math3.ode.EquationsMapper primary = expandable.getPrimaryMapper();
				primary.insertEquationData(interpolator.getInterpolatedState(), y[count]);
				primary.insertEquationData(interpolator.getInterpolatedDerivatives(), yDot[count]);
				int index = 0;
				for (final org.apache.commons.math3.ode.EquationsMapper secondary : expandable.getSecondaryMappers()) {
					secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index), y[count]);
					secondary.insertEquationData(interpolator.getInterpolatedSecondaryDerivatives(index), yDot[count]);
					++index;
				}
			} 
			++(count);
			interpolator.setInterpolatedTime(curr);
			t[count] = curr;
			final org.apache.commons.math3.ode.ExpandableStatefulODE expandable = getExpandable();
			final org.apache.commons.math3.ode.EquationsMapper primary = expandable.getPrimaryMapper();
			primary.insertEquationData(interpolator.getInterpolatedState(), y[count]);
			primary.insertEquationData(interpolator.getInterpolatedDerivatives(), yDot[count]);
			int index = 0;
			for (final org.apache.commons.math3.ode.EquationsMapper secondary : expandable.getSecondaryMappers()) {
				secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index), y[count]);
				secondary.insertEquationData(interpolator.getInterpolatedSecondaryDerivatives(index), yDot[count]);
				++index;
			}
			if ((count) == ((t.length) - 1)) {
				stepStart = t[0];
				stepSize = ((t[((t.length) - 1)]) - (t[0])) / ((t.length) - 1);
				scaled = yDot[0].clone();
				for (int j = 0 ; j < (scaled.length) ; ++j) {
					scaled[j] *= stepSize;
				}
				nordsieck = initializeHighOrderDerivatives(stepSize, t, y, yDot);
				throw new org.apache.commons.math3.ode.MultistepIntegrator.InitializationCompletedMarkerException();
			} 
		}

		public void init(double t0, double[] y0, double time) {
		}
	}

	private static class InitializationCompletedMarkerException extends java.lang.RuntimeException {
		private static final long serialVersionUID = -1914085471038046418L;

		public InitializationCompletedMarkerException() {
			super(((java.lang.Throwable)(null)));
		}
	}
}

