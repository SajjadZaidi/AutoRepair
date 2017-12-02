package org.apache.commons.math3.ml.clustering.evaluation;


public class SumOfClusterVariances<T extends org.apache.commons.math3.ml.clustering.Clusterable> extends org.apache.commons.math3.ml.clustering.evaluation.ClusterEvaluator<T> {
	public SumOfClusterVariances(final org.apache.commons.math3.ml.distance.DistanceMeasure measure) {
		super(measure);
	}

	@java.lang.Override
	public double score(final java.util.List<? extends org.apache.commons.math3.ml.clustering.Cluster<T>> clusters) {
		double varianceSum = 0.0;
		for (final org.apache.commons.math3.ml.clustering.Cluster<T> cluster : clusters) {
			if (!(cluster.getPoints().isEmpty())) {
				final org.apache.commons.math3.ml.clustering.Clusterable center = centroidOf(cluster);
				final org.apache.commons.math3.stat.descriptive.moment.Variance stat = new org.apache.commons.math3.stat.descriptive.moment.Variance();
				for (final T point : cluster.getPoints()) {
					stat.increment(distance(point, center));
				}
				varianceSum += stat.getResult();
			} 
		}
		return varianceSum;
	}
}

