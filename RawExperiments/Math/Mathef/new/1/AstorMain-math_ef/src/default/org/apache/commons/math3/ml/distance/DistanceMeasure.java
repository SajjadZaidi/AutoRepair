package org.apache.commons.math3.ml.distance;


public interface DistanceMeasure extends java.io.Serializable {
	double compute(double[] a, double[] b);
}

