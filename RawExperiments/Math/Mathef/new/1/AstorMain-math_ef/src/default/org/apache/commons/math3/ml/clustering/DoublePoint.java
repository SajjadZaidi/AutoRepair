package org.apache.commons.math3.ml.clustering;


public class DoublePoint implements java.io.Serializable , org.apache.commons.math3.ml.clustering.Clusterable {
	private static final long serialVersionUID = 3946024775784901369L;

	private final double[] point;

	public DoublePoint(final double[] point) {
		this.point = point;
	}

	public DoublePoint(final int[] point) {
		this.point = new double[point.length];
		for (int i = 0 ; i < (point.length) ; i++) {
			org.apache.commons.math3.ml.clustering.DoublePoint.this.point[i] = point[i];
		}
	}

	public double[] getPoint() {
		return point;
	}

	@java.lang.Override
	public boolean equals(final java.lang.Object other) {
		if (!(other instanceof org.apache.commons.math3.ml.clustering.DoublePoint)) {
			return false;
		} 
		return java.util.Arrays.equals(point, ((org.apache.commons.math3.ml.clustering.DoublePoint)(other)).point);
	}

	@java.lang.Override
	public int hashCode() {
		return java.util.Arrays.hashCode(point);
	}

	@java.lang.Override
	public java.lang.String toString() {
		return java.util.Arrays.toString(point);
	}
}

