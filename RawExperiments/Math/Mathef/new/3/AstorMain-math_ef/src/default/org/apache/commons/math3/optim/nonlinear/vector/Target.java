package org.apache.commons.math3.optim.nonlinear.vector;


@java.lang.Deprecated
public class Target implements org.apache.commons.math3.optim.OptimizationData {
	private final double[] target;

	public Target(double[] observations) {
		target = observations.clone();
	}

	public double[] getTarget() {
		return target.clone();
	}
}

