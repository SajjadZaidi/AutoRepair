package org.apache.commons.math3.fitting.leastsquares;


public interface MultivariateJacobianFunction {
	org.apache.commons.math3.util.Pair<org.apache.commons.math3.linear.RealVector, org.apache.commons.math3.linear.RealMatrix> value(org.apache.commons.math3.linear.RealVector point);
}

