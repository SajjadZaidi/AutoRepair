package org.apache.commons.math3.ode.nonstiff;


class LutherStepInterpolator extends org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator {
	private static final long serialVersionUID = 20140416L;

	private static final double Q = org.apache.commons.math3.util.FastMath.sqrt(21);

	public LutherStepInterpolator() {
	}

	public LutherStepInterpolator(final org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator interpolator) {
		super(interpolator);
	}

	@java.lang.Override
	protected org.apache.commons.math3.ode.sampling.StepInterpolator doCopy() {
		return new org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator(org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.this);
	}

	@java.lang.Override
	protected void computeInterpolatedStateAndDerivatives(final double theta, final double oneMinusThetaH) {
		final double coeffDot1 = 1 + (theta * (((-54) / 5.0) + (theta * (36 + (theta * ((-47) + (theta * 21)))))));
		final double coeffDot2 = 0;
		final double coeffDot3 = theta * (((-208) / 15.0) + (theta * ((320 / 3.0) + (theta * (((-608) / 3.0) + (theta * 112))))));
		final double coeffDot4 = theta * ((324 / 25.0) + (theta * (((-486) / 5.0) + (theta * ((972 / 5.0) + ((theta * (-567)) / 5.0))))));
		final double coeffDot5 = theta * (((833 + (343 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 150.0) + (theta * ((((-637) - (357 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 30.0) + (theta * (((392 + (287 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 15.0) + ((theta * ((-49) - (49 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q)))) / 5.0))))));
		final double coeffDot6 = theta * (((833 - (343 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 150.0) + (theta * ((((-637) + (357 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 30.0) + (theta * (((392 - (287 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 15.0) + ((theta * ((-49) + (49 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q)))) / 5.0))))));
		final double coeffDot7 = theta * ((3 / 5.0) + (theta * ((-3) + (theta * 3))));
		if (((previousState) != null) && (theta <= 0.5)) {
			final double coeff1 = 1 + (theta * (((-27) / 5.0) + (theta * (12 + (theta * (((-47) / 4.0) + ((theta * 21) / 5.0)))))));
			final double coeff2 = 0;
			final double coeff3 = theta * (((-104) / 15.0) + (theta * ((320 / 9.0) + (theta * (((-152) / 3.0) + ((theta * 112) / 5.0))))));
			final double coeff4 = theta * ((162 / 25.0) + (theta * (((-162) / 5.0) + (theta * ((243 / 5.0) + ((theta * (-567)) / 25.0))))));
			final double coeff5 = theta * (((833 + (343 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 300.0) + (theta * ((((-637) - (357 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 90.0) + (theta * (((392 + (287 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 60.0) + ((theta * ((-49) - (49 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q)))) / 25.0))))));
			final double coeff6 = theta * (((833 - (343 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 300.0) + (theta * ((((-637) + (357 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 90.0) + (theta * (((392 - (287 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 60.0) + ((theta * ((-49) + (49 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q)))) / 25.0))))));
			final double coeff7 = theta * ((3 / 10.0) + (theta * ((-1) + (theta * (3 / 4.0)))));
			for (int i = 0 ; i < (interpolatedState.length) ; ++i) {
				final double yDot1 = yDotK[0][i];
				final double yDot2 = yDotK[1][i];
				final double yDot3 = yDotK[2][i];
				final double yDot4 = yDotK[3][i];
				final double yDot5 = yDotK[4][i];
				final double yDot6 = yDotK[5][i];
				final double yDot7 = yDotK[6][i];
				interpolatedState[i] = (previousState[i]) + ((theta * (h)) * (((((((coeff1 * yDot1) + (coeff2 * yDot2)) + (coeff3 * yDot3)) + (coeff4 * yDot4)) + (coeff5 * yDot5)) + (coeff6 * yDot6)) + (coeff7 * yDot7)));
				interpolatedDerivatives[i] = ((((((coeffDot1 * yDot1) + (coeffDot2 * yDot2)) + (coeffDot3 * yDot3)) + (coeffDot4 * yDot4)) + (coeffDot5 * yDot5)) + (coeffDot6 * yDot6)) + (coeffDot7 * yDot7);
			}
		} else {
			final double coeff1 = ((-1) / 20.0) + (theta * ((19 / 20.0) + (theta * (((-89) / 20.0) + (theta * ((151 / 20.0) + ((theta * (-21)) / 5.0)))))));
			final double coeff2 = 0;
			final double coeff3 = ((-16) / 45.0) + (theta * (((-16) / 45.0) + (theta * (((-328) / 45.0) + (theta * ((424 / 15.0) + ((theta * (-112)) / 5.0)))))));
			final double coeff4 = theta * (theta * ((162 / 25.0) + (theta * (((-648) / 25.0) + ((theta * 567) / 25.0)))));
			final double coeff5 = ((-49) / 180.0) + (theta * (((-49) / 180.0) + (theta * (((2254 + (1029 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 900.0) + (theta * ((((-1372) - (847 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 300.0) + ((theta * (49 + (49 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q)))) / 25.0)))))));
			final double coeff6 = ((-49) / 180.0) + (theta * (((-49) / 180.0) + (theta * (((2254 - (1029 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 900.0) + (theta * ((((-1372) + (847 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q))) / 300.0) + ((theta * (49 - (49 * (org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator.Q)))) / 25.0)))))));
			final double coeff7 = ((-1) / 20.0) + (theta * (((-1) / 20.0) + (theta * ((1 / 4.0) + (theta * ((-3) / 4.0))))));
			for (int i = 0 ; i < (interpolatedState.length) ; ++i) {
				final double yDot1 = yDotK[0][i];
				final double yDot2 = yDotK[1][i];
				final double yDot3 = yDotK[2][i];
				final double yDot4 = yDotK[3][i];
				final double yDot5 = yDotK[4][i];
				final double yDot6 = yDotK[5][i];
				final double yDot7 = yDotK[6][i];
				interpolatedState[i] = (currentState[i]) + (oneMinusThetaH * (((((((coeff1 * yDot1) + (coeff2 * yDot2)) + (coeff3 * yDot3)) + (coeff4 * yDot4)) + (coeff5 * yDot5)) + (coeff6 * yDot6)) + (coeff7 * yDot7)));
				interpolatedDerivatives[i] = ((((((coeffDot1 * yDot1) + (coeffDot2 * yDot2)) + (coeffDot3 * yDot3)) + (coeffDot4 * yDot4)) + (coeffDot5 * yDot5)) + (coeffDot6 * yDot6)) + (coeffDot7 * yDot7);
			}
		}
	}
}

