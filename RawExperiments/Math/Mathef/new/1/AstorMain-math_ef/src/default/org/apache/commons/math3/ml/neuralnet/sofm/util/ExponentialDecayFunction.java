package org.apache.commons.math3.ml.neuralnet.sofm.util;


public class ExponentialDecayFunction {
	private final double a;

	private final double oneOverB;

	public ExponentialDecayFunction(double initValue ,double valueAtNumCall ,long numCall) {
		if (initValue <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(initValue);
		} 
		if (valueAtNumCall <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(valueAtNumCall);
		} 
		if (valueAtNumCall >= initValue) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(valueAtNumCall , initValue , false);
		} 
		if (numCall <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(numCall);
		} 
		a = initValue;
		oneOverB = (-(org.apache.commons.math3.util.FastMath.log((valueAtNumCall / initValue)))) / numCall;
	}

	public double value(long numCall) {
		return (a) * (org.apache.commons.math3.util.FastMath.exp(((-numCall) * (oneOverB))));
	}
}

