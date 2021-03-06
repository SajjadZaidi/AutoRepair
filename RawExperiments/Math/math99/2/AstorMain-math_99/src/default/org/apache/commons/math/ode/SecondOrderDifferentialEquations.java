package org.apache.commons.math.ode;


public interface SecondOrderDifferentialEquations extends java.io.Serializable {
	public int getDimension();

	public void computeSecondDerivatives(double t, double[] y, double[] yDot, double[] yDDot) throws org.apache.commons.math.ode.DerivativeException;
}

