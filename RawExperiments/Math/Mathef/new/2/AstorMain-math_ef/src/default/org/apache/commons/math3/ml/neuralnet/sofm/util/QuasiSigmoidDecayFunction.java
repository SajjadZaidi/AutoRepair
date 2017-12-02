package org.apache.commons.math3.ml.neuralnet.sofm.util;


public class QuasiSigmoidDecayFunction {
	private final org.apache.commons.math3.analysis.function.Logistic sigmoid;

	private final double scale;

	public QuasiSigmoidDecayFunction(double initValue ,double slope ,long numCall) {
		if (initValue <= 0) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(initValue);
		} 
		if (slope >= 0) {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(slope , 0 , false);
		} 
		if (numCall <= 1) {
			throw new org.apache.commons.math3.exception.NotStrictlyPositiveException(numCall);
		} 
		final double k = initValue;
		final double m = numCall;
		final double b = (4 * slope) / initValue;
		final double q = 1;
		final double a = 0;
		final double n = 1;
		sigmoid = new org.apache.commons.math3.analysis.function.Logistic(k , m , b , q , a , n);
		final double y0 = sigmoid.value(0);
		scale = k / y0;
	}

	public double value(long numCall) {
		return (scale) * (sigmoid.value(numCall));
	}
}

