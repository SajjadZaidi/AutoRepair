package org.apache.commons.math.ode.nonstiff;


abstract class RungeKuttaStepInterpolator extends org.apache.commons.math.ode.sampling.AbstractStepInterpolator {
	protected RungeKuttaStepInterpolator() {
		super();
		yDotK = null;
		equations = null;
	}

	public RungeKuttaStepInterpolator(final org.apache.commons.math.ode.nonstiff.RungeKuttaStepInterpolator interpolator) {
		super(interpolator);
		if ((interpolator.currentState) != null) {
			final int dimension = currentState.length;
			yDotK = new double[interpolator.yDotK.length][];
			for (int k = 0 ; k < (interpolator.yDotK.length) ; ++k) {
				yDotK[k] = new double[dimension];
				java.lang.System.arraycopy(interpolator.yDotK[k], 0, yDotK[k], 0, dimension);
			}
		} else {
			yDotK = null;
		}
		equations = null;
	}

	public void reinitialize(final org.apache.commons.math.ode.FirstOrderDifferentialEquations equations, final double[] y, final double[][] yDotK, final boolean forward) {
		reinitialize(y, forward);
		org.apache.commons.math.ode.nonstiff.RungeKuttaStepInterpolator.this.yDotK = yDotK;
		org.apache.commons.math.ode.nonstiff.RungeKuttaStepInterpolator.this.equations = equations;
	}

	public void writeExternal(final java.io.ObjectOutput out) throws java.io.IOException {
		writeBaseExternal(out);
		out.writeInt(yDotK.length);
		for (int k = 0 ; k < (yDotK.length) ; ++k) {
			for (int i = 0 ; i < (currentState.length) ; ++i) {
				out.writeDouble(yDotK[k][i]);
			}
		}
	}

	public void readExternal(final java.io.ObjectInput in) throws java.io.IOException {
		final double t = readBaseExternal(in);
		final int kMax = in.readInt();
		yDotK = new double[kMax][];
		for (int k = 0 ; k < kMax ; ++k) {
			yDotK[k] = new double[currentState.length];
			for (int i = 0 ; i < (currentState.length) ; ++i) {
				yDotK[k][i] = in.readDouble();
			}
		}
		equations = null;
		try {
			setInterpolatedTime(t);
		} catch (org.apache.commons.math.ode.DerivativeException e) {
			throw org.apache.commons.math.MathRuntimeException.createIOException(e);
		}
	}

	protected double[][] yDotK;

	protected org.apache.commons.math.ode.FirstOrderDifferentialEquations equations;
}

