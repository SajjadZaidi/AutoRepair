package org.apache.commons.math3.ml.clustering;


public class Cluster<T extends org.apache.commons.math3.ml.clustering.Clusterable> implements java.io.Serializable {
	private static final long serialVersionUID = -3442297081515880464L;

	private final java.util.List<T> points;

	public Cluster() {
		points = new java.util.ArrayList<T>();
	}

	public void addPoint(final T point) {
		points.add(point);
	}

	public java.util.List<T> getPoints() {
		return points;
	}
}

