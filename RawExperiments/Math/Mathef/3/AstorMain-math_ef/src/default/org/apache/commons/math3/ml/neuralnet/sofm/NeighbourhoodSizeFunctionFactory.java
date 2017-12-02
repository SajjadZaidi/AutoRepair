package org.apache.commons.math3.ml.neuralnet.sofm;


public class NeighbourhoodSizeFunctionFactory {
	private NeighbourhoodSizeFunctionFactory() {
	}

	public static org.apache.commons.math3.ml.neuralnet.sofm.NeighbourhoodSizeFunction exponentialDecay(final double initValue, final double valueAtNumCall, final long numCall) {
		return new org.apache.commons.math3.ml.neuralnet.sofm.NeighbourhoodSizeFunction() {
			private final org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction decay = new org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction(initValue , valueAtNumCall , numCall);

			public int value(long n) {
				return ((int)(org.apache.commons.math3.util.FastMath.rint(decay.value(n))));
			}
		};
	}

	public static org.apache.commons.math3.ml.neuralnet.sofm.NeighbourhoodSizeFunction quasiSigmoidDecay(final double initValue, final double slope, final long numCall) {
		return new org.apache.commons.math3.ml.neuralnet.sofm.NeighbourhoodSizeFunction() {
			private final org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction decay = new org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction(initValue , slope , numCall);

			public int value(long n) {
				return ((int)(org.apache.commons.math3.util.FastMath.rint(decay.value(n))));
			}
		};
	}
}

