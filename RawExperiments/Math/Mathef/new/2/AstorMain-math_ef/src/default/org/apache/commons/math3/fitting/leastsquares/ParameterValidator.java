package org.apache.commons.math3.fitting.leastsquares;


public interface ParameterValidator {
	org.apache.commons.math3.linear.RealVector validate(org.apache.commons.math3.linear.RealVector params);
}

