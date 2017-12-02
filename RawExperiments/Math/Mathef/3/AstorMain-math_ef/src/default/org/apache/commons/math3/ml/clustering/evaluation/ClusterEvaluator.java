package org.apache.commons.math3.ml.clustering.evaluation;


public abstract class ClusterEvaluator<T extends org.apache.commons.math3.ml.clustering.Clusterable> {
	private final org.apache.commons.math3.ml.distance.DistanceMeasure measure;

	public ClusterEvaluator() {
		this(new org.apache.commons.math3.ml.distance.EuclideanDistance());
	}

	public ClusterEvaluator(final org.apache.commons.math3.ml.distance.DistanceMeasure measure) {
		this.measure = measure;
	}

	public abstract double score(java.util.List<? extends org.apache.commons.math3.ml.clustering.Cluster<T>> clusters);

	public boolean isBetterScore(double score1, double score2) {
		return score1 < score2;
	}

	protected double distance(final org.apache.commons.math3.ml.clustering.Clusterable p1, final org.apache.commons.math3.ml.clustering.Clusterable p2) {
		return measure.compute(p1.getPoint(), p2.getPoint());
	}

	protected org.apache.commons.math3.ml.clustering.Clusterable centroidOf(final org.apache.commons.math3.ml.clustering.Cluster<T> cluster) {
		final java.util.List<T> points = cluster.getPoints();
		if (points.isEmpty()) {
			return null;
		} 
		if (cluster instanceof org.apache.commons.math3.ml.clustering.CentroidCluster) {
			return ((org.apache.commons.math3.ml.clustering.CentroidCluster<T>)(cluster)).getCenter();
		} 
		final int dimension = points.get(0).getPoint().length;
		final double[] centroid = new double[dimension];
		for (final T p : points) {
			final double[] point = p.getPoint();
			for (int i = 0 ; i < (centroid.length) ; i++) {
				centroid[i] += point[i];
			}
		}
		for (int i = 0 ; i < (centroid.length) ; i++) {
			centroid[i] /= points.size();
		}
		return new org.apache.commons.math3.ml.clustering.DoublePoint(centroid);
	}
}

