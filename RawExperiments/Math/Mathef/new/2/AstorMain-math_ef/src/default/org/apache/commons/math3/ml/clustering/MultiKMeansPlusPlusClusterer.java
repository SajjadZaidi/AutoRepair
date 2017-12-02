package org.apache.commons.math3.ml.clustering;


public class MultiKMeansPlusPlusClusterer<T extends org.apache.commons.math3.ml.clustering.Clusterable> extends org.apache.commons.math3.ml.clustering.Clusterer<T> {
	private final org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer<T> clusterer;

	private final int numTrials;

	private final org.apache.commons.math3.ml.clustering.evaluation.ClusterEvaluator<T> evaluator;

	public MultiKMeansPlusPlusClusterer(final org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer<T> clusterer ,final int numTrials) {
		this(clusterer, numTrials, new org.apache.commons.math3.ml.clustering.evaluation.SumOfClusterVariances<T>(clusterer.getDistanceMeasure()));
	}

	public MultiKMeansPlusPlusClusterer(final org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer<T> clusterer ,final int numTrials ,final org.apache.commons.math3.ml.clustering.evaluation.ClusterEvaluator<T> evaluator) {
		super(clusterer.getDistanceMeasure());
		this.clusterer = clusterer;
		this.numTrials = numTrials;
		this.evaluator = evaluator;
	}

	public org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer<T> getClusterer() {
		return clusterer;
	}

	public int getNumTrials() {
		return numTrials;
	}

	public org.apache.commons.math3.ml.clustering.evaluation.ClusterEvaluator<T> getClusterEvaluator() {
		return evaluator;
	}

	@java.lang.Override
	public java.util.List<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> cluster(final java.util.Collection<T> points) throws org.apache.commons.math3.exception.ConvergenceException, org.apache.commons.math3.exception.MathIllegalArgumentException {
		java.util.List<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> best = null;
		double bestVarianceSum = java.lang.Double.POSITIVE_INFINITY;
		for (int i = 0 ; i < (numTrials) ; ++i) {
			java.util.List<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> clusters = clusterer.cluster(points);
			final double varianceSum = evaluator.score(clusters);
			if (evaluator.isBetterScore(varianceSum, bestVarianceSum)) {
				best = clusters;
				bestVarianceSum = varianceSum;
			} 
		}
		return best;
	}
}

