package org.apache.commons.math3.fitting.leastsquares;


public interface ValueAndJacobianFunction extends org.apache.commons.math3.fitting.leastsquares.MultivariateJacobianFunction {
	org.apache.commons.math3.linear.RealVector computeValue(final double[] params);

	org.apache.commons.math3.linear.RealMatrix computeJacobian(final double[] params);
}

