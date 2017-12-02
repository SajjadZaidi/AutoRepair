package org.apache.commons.math3.ml.clustering;


public class CentroidCluster<T extends org.apache.commons.math3.ml.clustering.Clusterable> extends org.apache.commons.math3.ml.clustering.Cluster<T> {
	private static final long serialVersionUID = -3075288519071812288L;

	private final org.apache.commons.math3.ml.clustering.Clusterable center;

	public CentroidCluster(final org.apache.commons.math3.ml.clustering.Clusterable center) {
		super();
		this.center = center;
	}

	public org.apache.commons.math3.ml.clustering.Clusterable getCenter() {
		return center;
	}
}

