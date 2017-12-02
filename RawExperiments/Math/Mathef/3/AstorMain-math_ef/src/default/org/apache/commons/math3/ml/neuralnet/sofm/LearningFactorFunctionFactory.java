package org.apache.commons.math3.ml.neuralnet.sofm;


public class LearningFactorFunctionFactory {
	private LearningFactorFunctionFactory() {
	}

	public static org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunction exponentialDecay(final double initValue, final double valueAtNumCall, final long numCall) {
		if ((initValue <= 0) || (initValue > 1)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(initValue , 0 , 1);
		} 
		return new org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunction() {
			private final org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction decay = new org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction(initValue , valueAtNumCall , numCall);

			public double value(long n) {
				return decay.value(n);
			}
		};
	}

	public static org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunction quasiSigmoidDecay(final double initValue, final double slope, final long numCall) {
		if ((initValue <= 0) || (initValue > 1)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(initValue , 0 , 1);
		} 
		return new org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunction() {
			private final org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction decay = new org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction(initValue , slope , numCall);

			public double value(long n) {
				return decay.value(n);
			}
		};
	}
}

