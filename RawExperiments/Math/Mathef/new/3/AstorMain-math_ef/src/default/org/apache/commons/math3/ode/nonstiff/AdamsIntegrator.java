package org.apache.commons.math3.ode.nonstiff;


public abstract class AdamsIntegrator extends org.apache.commons.math3.ode.MultistepIntegrator {
	private final org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer transformer;

	public AdamsIntegrator(final java.lang.String name ,final int nSteps ,final int order ,final double minStep ,final double maxStep ,final double scalAbsoluteTolerance ,final double scalRelativeTolerance) throws org.apache.commons.math3.exception.NumberIsTooSmallException {
		super(name, nSteps, order, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
		transformer = org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.getInstance(nSteps);
	}

	public AdamsIntegrator(final java.lang.String name ,final int nSteps ,final int order ,final double minStep ,final double maxStep ,final double[] vecAbsoluteTolerance ,final double[] vecRelativeTolerance) throws java.lang.IllegalArgumentException {
		super(name, nSteps, order, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
		transformer = org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.getInstance(nSteps);
	}

	@java.lang.Override
	public abstract void integrate(final org.apache.commons.math3.ode.ExpandableStatefulODE equations, final double t) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException, org.apache.commons.math3.exception.NumberIsTooSmallException;

	@java.lang.Override
	protected org.apache.commons.math3.linear.Array2DRowRealMatrix initializeHighOrderDerivatives(final double h, final double[] t, final double[][] y, final double[][] yDot) {
		return transformer.initializeHighOrderDerivatives(h, t, y, yDot);
	}

	public org.apache.commons.math3.linear.Array2DRowRealMatrix updateHighOrderDerivativesPhase1(final org.apache.commons.math3.linear.Array2DRowRealMatrix highOrder) {
		return transformer.updateHighOrderDerivativesPhase1(highOrder);
	}

	public void updateHighOrderDerivativesPhase2(final double[] start, final double[] end, final org.apache.commons.math3.linear.Array2DRowRealMatrix highOrder) {
		transformer.updateHighOrderDerivativesPhase2(start, end, highOrder);
	}
}

