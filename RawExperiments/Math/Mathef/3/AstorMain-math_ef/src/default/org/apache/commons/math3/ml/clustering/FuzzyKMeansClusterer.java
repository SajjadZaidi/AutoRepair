package org.apache.commons.math3.ml.clustering;


public class FuzzyKMeansClusterer<T extends org.apache.commons.math3.ml.clustering.Clusterable> extends org.apache.commons.math3.ml.clustering.Clusterer<T> {
	private static final double DEFAULT_EPSILON = 0.001;

	private final int k;

	private final int maxIterations;

	private final double fuzziness;

	private final double epsilon;

	private final org.apache.commons.math3.random.RandomGenerator random;

	private double[][] membershipMatrix;

	private java.util.List<T> points;

	private java.util.List<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> clusters;

	public FuzzyKMeansClusterer(final int k ,final double fuzziness) throws org.apache.commons.math3.exception.NumberIsTooSmallException {
		this(k, fuzziness, (-1), new org.apache.commons.math3.ml.distance.EuclideanDistance());
	}

	public FuzzyKMeansClusterer(final int k ,final double fuzziness ,final int maxIterations ,final org.apache.commons.math3.ml.distance.DistanceMeasure measure) throws org.apache.commons.math3.exception.NumberIsTooSmallException {
		this(k, fuzziness, maxIterations, measure, org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.DEFAULT_EPSILON, new org.apache.commons.math3.random.JDKRandomGenerator());
	}

	public FuzzyKMeansClusterer(final int k ,final double fuzziness ,final int maxIterations ,final org.apache.commons.math3.ml.distance.DistanceMeasure measure ,final double epsilon ,final org.apache.commons.math3.random.RandomGenerator random) throws org.apache.commons.math3.exception.NumberIsTooSmallException {
		super(measure);
		if (fuzziness <= 1.0) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(fuzziness , 1.0 , false);
		} 
		this.k = k;
		this.fuzziness = fuzziness;
		this.maxIterations = maxIterations;
		this.epsilon = epsilon;
		this.random = random;
		org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.this.membershipMatrix = null;
		org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.this.points = null;
		org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer.this.clusters = null;
	}

	public int getK() {
		return k;
	}

	public double getFuzziness() {
		return fuzziness;
	}

	public int getMaxIterations() {
		return maxIterations;
	}

	public double getEpsilon() {
		return epsilon;
	}

	public org.apache.commons.math3.random.RandomGenerator getRandomGenerator() {
		return random;
	}

	public org.apache.commons.math3.linear.RealMatrix getMembershipMatrix() {
		if ((membershipMatrix) == null) {
			throw new org.apache.commons.math3.exception.MathIllegalStateException();
		} 
		return org.apache.commons.math3.linear.MatrixUtils.createRealMatrix(membershipMatrix);
	}

	public java.util.List<T> getDataPoints() {
		return points;
	}

	public java.util.List<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> getClusters() {
		return clusters;
	}

	public double getObjectiveFunctionValue() {
		if (((points) == null) || ((clusters) == null)) {
			throw new org.apache.commons.math3.exception.MathIllegalStateException();
		} 
		int i = 0;
		double objFunction = 0.0;
		for (final T point : points) {
			int j = 0;
			for (final org.apache.commons.math3.ml.clustering.CentroidCluster<T> cluster : clusters) {
				final double dist = distance(point, cluster.getCenter());
				objFunction += (dist * dist) * (org.apache.commons.math3.util.FastMath.pow(membershipMatrix[i][j], fuzziness));
				j++;
			}
			i++;
		}
		return objFunction;
	}

	@java.lang.Override
	public java.util.List<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> cluster(final java.util.Collection<T> dataPoints) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		org.apache.commons.math3.util.MathUtils.checkNotNull(dataPoints);
		final int size = dataPoints.size();
		if (size < (k)) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(size , k , false);
		} 
		points = java.util.Collections.unmodifiableList(new java.util.ArrayList<T>(dataPoints));
		clusters = new java.util.ArrayList<org.apache.commons.math3.ml.clustering.CentroidCluster<T>>();
		membershipMatrix = new double[size][k];
		final double[][] oldMatrix = new double[size][k];
		if (size == 0) {
			return clusters;
		} 
		initializeMembershipMatrix();
		final int pointDimension = points.get(0).getPoint().length;
		for (int i = 0 ; i < (k) ; i++) {
			clusters.add(new org.apache.commons.math3.ml.clustering.CentroidCluster<T>(new org.apache.commons.math3.ml.clustering.DoublePoint(new double[pointDimension])));
		}
		int iteration = 0;
		final int max = (maxIterations) < 0 ? java.lang.Integer.MAX_VALUE : maxIterations;
		double difference = 0.0;
		do {
			saveMembershipMatrix(oldMatrix);
			updateClusterCenters();
			updateMembershipMatrix();
			difference = calculateMaxMembershipChange(oldMatrix);
		} while ((difference > (epsilon)) && ((++iteration) < max) );
		return clusters;
	}

	private void updateClusterCenters() {
		int j = 0;
		final java.util.List<org.apache.commons.math3.ml.clustering.CentroidCluster<T>> newClusters = new java.util.ArrayList<org.apache.commons.math3.ml.clustering.CentroidCluster<T>>(k);
		for (final org.apache.commons.math3.ml.clustering.CentroidCluster<T> cluster : clusters) {
			final org.apache.commons.math3.ml.clustering.Clusterable center = cluster.getCenter();
			int i = 0;
			double[] arr = new double[center.getPoint().length];
			double sum = 0.0;
			for (final T point : points) {
				final double u = org.apache.commons.math3.util.FastMath.pow(membershipMatrix[i][j], fuzziness);
				final double[] pointArr = point.getPoint();
				for (int idx = 0 ; idx < (arr.length) ; idx++) {
					arr[idx] += u * (pointArr[idx]);
				}
				sum += u;
				i++;
			}
			org.apache.commons.math3.util.MathArrays.scaleInPlace((1.0 / sum), arr);
			newClusters.add(new org.apache.commons.math3.ml.clustering.CentroidCluster<T>(new org.apache.commons.math3.ml.clustering.DoublePoint(arr)));
			j++;
		}
		clusters.clear();
		clusters = newClusters;
	}

	private void updateMembershipMatrix() {
		for (int i = 0 ; i < (points.size()) ; i++) {
			final T point = points.get(i);
			double maxMembership = java.lang.Double.MIN_VALUE;
			int newCluster = -1;
			for (int j = 0 ; j < (clusters.size()) ; j++) {
				double sum = 0.0;
				final double distA = org.apache.commons.math3.util.FastMath.abs(distance(point, clusters.get(j).getCenter()));
				if (distA != 0.0) {
					for (final org.apache.commons.math3.ml.clustering.CentroidCluster<T> c : clusters) {
						final double distB = org.apache.commons.math3.util.FastMath.abs(distance(point, c.getCenter()));
						if (distB == 0.0) {
							sum = java.lang.Double.POSITIVE_INFINITY;
							break;
						} 
						sum += org.apache.commons.math3.util.FastMath.pow((distA / distB), (2.0 / ((fuzziness) - 1.0)));
					}
				} 
				double membership;
				if (sum == 0.0) {
					membership = 1.0;
				} else if (sum == (java.lang.Double.POSITIVE_INFINITY)) {
					membership = 0.0;
				} else {
					membership = 1.0 / sum;
				}
				membershipMatrix[i][j] = membership;
				if ((membershipMatrix[i][j]) > maxMembership) {
					maxMembership = membershipMatrix[i][j];
					newCluster = j;
				} 
			}
			clusters.get(newCluster).addPoint(point);
		}
	}

	private void initializeMembershipMatrix() {
		for (int i = 0 ; i < (points.size()) ; i++) {
			for (int j = 0 ; j < (k) ; j++) {
				membershipMatrix[i][j] = random.nextDouble();
			}
			membershipMatrix[i] = org.apache.commons.math3.util.MathArrays.normalizeArray(membershipMatrix[i], 1.0);
		}
	}

	private double calculateMaxMembershipChange(final double[][] matrix) {
		double maxMembership = 0.0;
		for (int i = 0 ; i < (points.size()) ; i++) {
			for (int j = 0 ; j < (clusters.size()) ; j++) {
				double v = org.apache.commons.math3.util.FastMath.abs(((membershipMatrix[i][j]) - (matrix[i][j])));
				maxMembership = org.apache.commons.math3.util.FastMath.max(v, maxMembership);
			}
		}
		return maxMembership;
	}

	private void saveMembershipMatrix(final double[][] matrix) {
		for (int i = 0 ; i < (points.size()) ; i++) {
			java.lang.System.arraycopy(membershipMatrix[i], 0, matrix[i], 0, clusters.size());
		}
	}
}

