package org.apache.commons.math3.ml.clustering;


public abstract class Clusterer<T extends org.apache.commons.math3.ml.clustering.Clusterable> {
	private org.apache.commons.math3.ml.distance.DistanceMeasure measure;

	protected Clusterer(final org.apache.commons.math3.ml.distance.DistanceMeasure measure) {
		org.apache.commons.math3.ml.clustering.Clusterer.this.measure = measure;
	}

	public abstract java.util.List<? extends org.apache.commons.math3.ml.clustering.Cluster<T>> cluster(java.util.Collection<T> points) throws org.apache.commons.math3.exception.ConvergenceException, org.apache.commons.math3.exception.MathIllegalArgumentException;

	public org.apache.commons.math3.ml.distance.DistanceMeasure getDistanceMeasure() {
		return measure;
	}

	protected double distance(final org.apache.commons.math3.ml.clustering.Clusterable p1, final org.apache.commons.math3.ml.clustering.Clusterable p2) {
		return measure.compute(p1.getPoint(), p2.getPoint());
	}
}

