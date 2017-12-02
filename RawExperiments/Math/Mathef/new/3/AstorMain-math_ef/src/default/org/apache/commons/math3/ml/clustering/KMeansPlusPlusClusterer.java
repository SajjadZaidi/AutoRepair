package org.apache.commons.math3.ml.clustering;


public class KMeansPlusPlusClusterer<T extends org.apache.commons.math3.ml.clustering.Clusterable> extends org.apache.commons.math3.ml.clustering.Clusterer<T> {
	public static enum EmptyClusterStrategy {
LARGEST_VARIANCE, LARGEST_POINTS_NUMBER, FARTHEST_POINT, ERROR;	}

	private final int k;

	private final int maxIterations;

	private final org.apache.commons.math3.random.RandomGenerator random;

	private final org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.EmptyClusterStrategy emptyStrategy;

	public KMeansPlusPlusClusterer(final int k) {
		this(k, (-1));
	}

	public KMeansPlusPlusClusterer(final int k ,final int maxIterations) {
		this(k, maxIterations, new org.apache.commons.math3.ml.distance.EuclideanDistance());
	}

	public KMeansPlusPlusClusterer(final int k ,final int maxIterations ,final org.apache.commons.math3.ml.distance.DistanceMeasure measure) {
		this(k, maxIterations, measure, new org.apache.commons.math3.random.JDKRandomGenerator());
	}

	public KMeansPlusPlusClusterer(final int k ,final int maxIterations ,final org.apache.commons.math3.ml.distance.DistanceMeasure measure ,final org.apache.commons.math3.random.RandomGenerator random) {
		this(k, maxIterations, measure, random, org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.EmptyClusterStrategy.LARGEST_VARIANCE);
	}

	public KMeansPlusPlusClusterer(final int k ,final int maxIterations ,final org.apache.commons.math3.ml.distance.DistanceMeasure measure ,final org.apache.commons.math3.random.RandomGenerator random ,final org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.EmptyClusterStrategy emptyStrategy) {
		super(measure);
		this.k = k;
		this.maxIterations = maxIterations;
		this.random = random;
		this.emptyStrategy = emptyStrategy;
	}

	public int getK() {
		return k;
	}

	public int getMaxIterations() {
		return maxIterations;
	}

	public org.apache.commons.math3.random.RandomGenerator getRandomGenerator() {
		return random;
	}

	public org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer.EmptyClusterStrategy getEmptyClusterStrategy() {
		return emptyStrategy;
	}

	@java.lang.Override
	public java.util.List<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> cluster(final java.util.Collection<T> points) throws org.apache.commons.math3.exception.ConvergenceException, org.apache.commons.math3.exception.MathIllegalArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(points);
		if ((points.size()) < (k)) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(points.size() , k , false);
		} 
		java.util.List<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> clusters = chooseInitialCenters(points);
		int[] assignments = new int[points.size()];
		assignPointsToClusters(clusters, points, assignments);
		final int max = (maxIterations) < 0 ? java.lang.Integer.MAX_VALUE : maxIterations;
		for (int count = 0 ; count < max ; count++) {
			boolean emptyCluster = false;
			java.util.List<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> newClusters = new java.util.ArrayList<org.apache.commons.math3.ml.clustering.CentroidCluster<T>>();
			for (final org.apache.commons.math3.ml.clustering.CentroidCluster<T> cluster : clusters) {
				final org.apache.commons.math3.ml.clustering.Clusterable newCenter;
				if (cluster.getPoints().isEmpty()) {
					switch (emptyStrategy) {
						case LARGEST_VARIANCE :
							newCenter = getPointFromLargestVarianceCluster(clusters);
							break;
						case LARGEST_POINTS_NUMBER :
							newCenter = getPointFromLargestNumberCluster(clusters);
							break;
						case FARTHEST_POINT :
							newCenter = getFarthestPoint(clusters);
							break;
						default :
							throw new org.apache.commons.math3.exception.ConvergenceException(org.apache.commons.math3.exception.util.LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS);
					}
					emptyCluster = true;
				} else {
					newCenter = centroidOf(cluster.getPoints(), cluster.getCenter().getPoint().length);
				}
				newClusters.add(new org.apache.commons.math3.ml.clustering.CentroidCluster<T>(newCenter));
			}
			int changes = assignPointsToClusters(newClusters, points, assignments);
			clusters = newClusters;
			if ((changes == 0) && (!emptyCluster)) {
				return clusters;
			} 
		}
		return clusters;
	}

	private int assignPointsToClusters(final java.util.List<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> clusters, final java.util.Collection<T> points, final int[] assignments) {
		int assignedDifferently = 0;
		int pointIndex = 0;
		for (final T p : points) {
			int clusterIndex = getNearestCluster(clusters, p);
			if (clusterIndex != (assignments[pointIndex])) {
				assignedDifferently++;
			} 
			org.apache.commons.math3.ml.clustering.CentroidCluster<T> cluster = clusters.get(clusterIndex);
			cluster.addPoint(p);
			assignments[(pointIndex++)] = clusterIndex;
		}
		return assignedDifferently;
	}

	private java.util.List<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> chooseInitialCenters(final java.util.Collection<T> points) {
		final java.util.List<T> pointList = java.util.Collections.unmodifiableList(new java.util.ArrayList<T>(points));
		final int numPoints = pointList.size();
		final boolean[] taken = new boolean[numPoints];
		final java.util.List<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> resultSet = new java.util.ArrayList<org.apache.commons.math3.ml.clustering.CentroidCluster<T>>();
		final int firstPointIndex = random.nextInt(numPoints);
		final T firstPoint = pointList.get(firstPointIndex);
		resultSet.add(new org.apache.commons.math3.ml.clustering.CentroidCluster<T>(firstPoint));
		taken[firstPointIndex] = true;
		final double[] minDistSquared = new double[numPoints];
		for (int i = 0 ; i < numPoints ; i++) {
			if (i != firstPointIndex) {
				double d = distance(firstPoint, pointList.get(i));
				minDistSquared[i] = d * d;
			} 
		}
		while ((resultSet.size()) < (k)) {
			double distSqSum = 0.0;
			for (int i = 0 ; i < numPoints ; i++) {
				if (!(taken[i])) {
					distSqSum += minDistSquared[i];
				} 
			}
			final double r = (random.nextDouble()) * distSqSum;
			int nextPointIndex = -1;
			double sum = 0.0;
			for (int i = 0 ; i < numPoints ; i++) {
				if (!(taken[i])) {
					sum += minDistSquared[i];
					if (sum >= r) {
						nextPointIndex = i;
						break;
					} 
				} 
			}
			if (nextPointIndex == (-1)) {
				for (int i = numPoints - 1 ; i >= 0 ; i--) {
					if (!(taken[i])) {
						nextPointIndex = i;
						break;
					} 
				}
			} 
			if (nextPointIndex >= 0) {
				final T p = pointList.get(nextPointIndex);
				resultSet.add(new org.apache.commons.math3.ml.clustering.CentroidCluster<T>(p));
				taken[nextPointIndex] = true;
				if ((resultSet.size()) < (k)) {
					for (int j = 0 ; j < numPoints ; j++) {
						if (!(taken[j])) {
							double d = distance(p, pointList.get(j));
							double d2 = d * d;
							if (d2 < (minDistSquared[j])) {
								minDistSquared[j] = d2;
							} 
						} 
					}
				} 
			} else {
				break;
			}
		}
		return resultSet;
	}

	private T getPointFromLargestVarianceCluster(final java.util.Collection<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> clusters) throws org.apache.commons.math3.exception.ConvergenceException {
		double maxVariance = java.lang.Double.NEGATIVE_INFINITY;
		org.apache.commons.math3.ml.clustering.Cluster<T> selected = null;
		for (final org.apache.commons.math3.ml.clustering.CentroidCluster<T> cluster : clusters) {
			if (!(cluster.getPoints().isEmpty())) {
				final org.apache.commons.math3.ml.clustering.Clusterable center = cluster.getCenter();
				final org.apache.commons.math3.stat.descriptive.moment.Variance stat = new org.apache.commons.math3.stat.descriptive.moment.Variance();
				for (final T point : cluster.getPoints()) {
					stat.increment(distance(point, center));
				}
				final double variance = stat.getResult();
				if (variance > maxVariance) {
					maxVariance = variance;
					selected = cluster;
				} 
			} 
		}
		if (selected == null) {
			throw new org.apache.commons.math3.exception.ConvergenceException(org.apache.commons.math3.exception.util.LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS);
		} 
		final java.util.List<T> selectedPoints = selected.getPoints();
		return selectedPoints.remove(random.nextInt(selectedPoints.size()));
	}

	private T getPointFromLargestNumberCluster(final java.util.Collection<? extends org.apache.commons.math3.ml.clustering.Cluster<T>> clusters) throws org.apache.commons.math3.exception.ConvergenceException {
		int maxNumber = 0;
		org.apache.commons.math3.ml.clustering.Cluster<T> selected = null;
		for (final org.apache.commons.math3.ml.clustering.Cluster<T> cluster : clusters) {
			final int number = cluster.getPoints().size();
			if (number > maxNumber) {
				maxNumber = number;
				selected = cluster;
			} 
		}
		if (selected == null) {
			throw new org.apache.commons.math3.exception.ConvergenceException(org.apache.commons.math3.exception.util.LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS);
		} 
		final java.util.List<T> selectedPoints = selected.getPoints();
		return selectedPoints.remove(random.nextInt(selectedPoints.size()));
	}

	private T getFarthestPoint(final java.util.Collection<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> clusters) throws org.apache.commons.math3.exception.ConvergenceException {
		double maxDistance = java.lang.Double.NEGATIVE_INFINITY;
		org.apache.commons.math3.ml.clustering.Cluster<T> selectedCluster = null;
		int selectedPoint = -1;
		for (final org.apache.commons.math3.ml.clustering.CentroidCluster<T> cluster : clusters) {
			final org.apache.commons.math3.ml.clustering.Clusterable center = cluster.getCenter();
			final java.util.List<T> points = cluster.getPoints();
			for (int i = 0 ; i < (points.size()) ; ++i) {
				final double distance = distance(points.get(i), center);
				if (distance > maxDistance) {
					maxDistance = distance;
					selectedCluster = cluster;
					selectedPoint = i;
				} 
			}
		}
		if (selectedCluster == null) {
			throw new org.apache.commons.math3.exception.ConvergenceException(org.apache.commons.math3.exception.util.LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS);
		} 
		return selectedCluster.getPoints().remove(selectedPoint);
	}

	private int getNearestCluster(final java.util.Collection<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> clusters, final T point) {
		double minDistance = java.lang.Double.MAX_VALUE;
		int clusterIndex = 0;
		int minCluster = 0;
		for (final org.apache.commons.math3.ml.clustering.CentroidCluster<T> c : clusters) {
			final double distance = distance(point, c.getCenter());
			if (distance < minDistance) {
				minDistance = distance;
				minCluster = clusterIndex;
			} 
			clusterIndex++;
		}
		return minCluster;
	}

	private org.apache.commons.math3.ml.clustering.Clusterable centroidOf(final java.util.Collection<T> points, final int dimension) {
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

