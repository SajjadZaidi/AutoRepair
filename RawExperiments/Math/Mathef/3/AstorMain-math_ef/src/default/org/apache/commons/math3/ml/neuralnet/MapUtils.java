package org.apache.commons.math3.ml.neuralnet;


public class MapUtils {
	private MapUtils() {
	}

	public static org.apache.commons.math3.ml.neuralnet.Neuron findBest(double[] features, java.lang.Iterable<org.apache.commons.math3.ml.neuralnet.Neuron> neurons, org.apache.commons.math3.ml.distance.DistanceMeasure distance) {
		org.apache.commons.math3.ml.neuralnet.Neuron best = null;
		double min = java.lang.Double.POSITIVE_INFINITY;
		for (final org.apache.commons.math3.ml.neuralnet.Neuron n : neurons) {
			final double d = distance.compute(n.getFeatures(), features);
			if (d < min) {
				min = d;
				best = n;
			} 
		}
		return best;
	}

	public static org.apache.commons.math3.util.Pair<org.apache.commons.math3.ml.neuralnet.Neuron, org.apache.commons.math3.ml.neuralnet.Neuron> findBestAndSecondBest(double[] features, java.lang.Iterable<org.apache.commons.math3.ml.neuralnet.Neuron> neurons, org.apache.commons.math3.ml.distance.DistanceMeasure distance) {
		org.apache.commons.math3.ml.neuralnet.Neuron[] best = new org.apache.commons.math3.ml.neuralnet.Neuron[]{ null , null };
		double[] min = new double[]{ java.lang.Double.POSITIVE_INFINITY , java.lang.Double.POSITIVE_INFINITY };
		for (final org.apache.commons.math3.ml.neuralnet.Neuron n : neurons) {
			final double d = distance.compute(n.getFeatures(), features);
			if (d < (min[0])) {
				min[1] = min[0];
				best[1] = best[0];
				min[0] = d;
				best[0] = n;
			} else if (d < (min[1])) {
				min[1] = d;
				best[1] = n;
			} 
		}
		return new org.apache.commons.math3.util.Pair<org.apache.commons.math3.ml.neuralnet.Neuron, org.apache.commons.math3.ml.neuralnet.Neuron>(best[0] , best[1]);
	}

	public static double[][] computeU(org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D map, org.apache.commons.math3.ml.distance.DistanceMeasure distance) {
		final int numRows = map.getNumberOfRows();
		final int numCols = map.getNumberOfColumns();
		final double[][] uMatrix = new double[numRows][numCols];
		final org.apache.commons.math3.ml.neuralnet.Network net = map.getNetwork();
		for (int i = 0 ; i < numRows ; i++) {
			for (int j = 0 ; j < numCols ; j++) {
				final org.apache.commons.math3.ml.neuralnet.Neuron neuron = map.getNeuron(i, j);
				final java.util.Collection<org.apache.commons.math3.ml.neuralnet.Neuron> neighbours = net.getNeighbours(neuron);
				final double[] features = neuron.getFeatures();
				double d = 0;
				int count = 0;
				for (org.apache.commons.math3.ml.neuralnet.Neuron n : neighbours) {
					++count;
					d += distance.compute(features, n.getFeatures());
				}
				uMatrix[i][j] = d / count;
			}
		}
		return uMatrix;
	}

	public static int[][] computeHitHistogram(java.lang.Iterable<double[]> data, org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D map, org.apache.commons.math3.ml.distance.DistanceMeasure distance) {
		final java.util.HashMap<org.apache.commons.math3.ml.neuralnet.Neuron, java.lang.Integer> hit = new java.util.HashMap<org.apache.commons.math3.ml.neuralnet.Neuron, java.lang.Integer>();
		final org.apache.commons.math3.ml.neuralnet.Network net = map.getNetwork();
		for (double[] f : data) {
			final org.apache.commons.math3.ml.neuralnet.Neuron best = org.apache.commons.math3.ml.neuralnet.MapUtils.findBest(f, net, distance);
			final java.lang.Integer count = hit.get(best);
			if (count == null) {
				hit.put(best, 1);
			} else {
				hit.put(best, (count + 1));
			}
		}
		final int numRows = map.getNumberOfRows();
		final int numCols = map.getNumberOfColumns();
		final int[][] histo = new int[numRows][numCols];
		for (int i = 0 ; i < numRows ; i++) {
			for (int j = 0 ; j < numCols ; j++) {
				final org.apache.commons.math3.ml.neuralnet.Neuron neuron = map.getNeuron(i, j);
				final java.lang.Integer count = hit.get(neuron);
				if (count == null) {
					histo[i][j] = 0;
				} else {
					histo[i][j] = count;
				}
			}
		}
		return histo;
	}

	public static double computeQuantizationError(java.lang.Iterable<double[]> data, java.lang.Iterable<org.apache.commons.math3.ml.neuralnet.Neuron> neurons, org.apache.commons.math3.ml.distance.DistanceMeasure distance) {
		double d = 0;
		int count = 0;
		for (double[] f : data) {
			++count;
			d += distance.compute(f, org.apache.commons.math3.ml.neuralnet.MapUtils.findBest(f, neurons, distance).getFeatures());
		}
		if (count == 0) {
			throw new org.apache.commons.math3.exception.NoDataException();
		} 
		return d / count;
	}

	public static double computeTopographicError(java.lang.Iterable<double[]> data, org.apache.commons.math3.ml.neuralnet.Network net, org.apache.commons.math3.ml.distance.DistanceMeasure distance) {
		int notAdjacentCount = 0;
		int count = 0;
		for (double[] f : data) {
			++count;
			final org.apache.commons.math3.util.Pair<org.apache.commons.math3.ml.neuralnet.Neuron, org.apache.commons.math3.ml.neuralnet.Neuron> p = org.apache.commons.math3.ml.neuralnet.MapUtils.findBestAndSecondBest(f, net, distance);
			if (!(net.getNeighbours(p.getFirst()).contains(p.getSecond()))) {
				++notAdjacentCount;
			} 
		}
		if (count == 0) {
			throw new org.apache.commons.math3.exception.NoDataException();
		} 
		return ((double)(notAdjacentCount)) / count;
	}
}

