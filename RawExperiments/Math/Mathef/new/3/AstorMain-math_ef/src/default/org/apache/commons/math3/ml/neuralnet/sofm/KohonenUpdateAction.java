package org.apache.commons.math3.ml.neuralnet.sofm;


public class KohonenUpdateAction implements org.apache.commons.math3.ml.neuralnet.UpdateAction {
	private final org.apache.commons.math3.ml.distance.DistanceMeasure distance;

	private final org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunction learningFactor;

	private final org.apache.commons.math3.ml.neuralnet.sofm.NeighbourhoodSizeFunction neighbourhoodSize;

	private final java.util.concurrent.atomic.AtomicLong numberOfCalls = new java.util.concurrent.atomic.AtomicLong((-1));

	public KohonenUpdateAction(org.apache.commons.math3.ml.distance.DistanceMeasure distance ,org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunction learningFactor ,org.apache.commons.math3.ml.neuralnet.sofm.NeighbourhoodSizeFunction neighbourhoodSize) {
		this.distance = distance;
		this.learningFactor = learningFactor;
		this.neighbourhoodSize = neighbourhoodSize;
	}

	public void update(org.apache.commons.math3.ml.neuralnet.Network net, double[] features) {
		final long numCalls = numberOfCalls.incrementAndGet();
		final double currentLearning = learningFactor.value(numCalls);
		final org.apache.commons.math3.ml.neuralnet.Neuron best = findAndUpdateBestNeuron(net, features, currentLearning);
		final int currentNeighbourhood = neighbourhoodSize.value(numCalls);
		final org.apache.commons.math3.analysis.function.Gaussian neighbourhoodDecay = new org.apache.commons.math3.analysis.function.Gaussian(currentLearning , 0 , (1.0 / currentNeighbourhood));
		if (currentNeighbourhood > 0) {
			java.util.Collection<org.apache.commons.math3.ml.neuralnet.Neuron> neighbours = new java.util.HashSet<org.apache.commons.math3.ml.neuralnet.Neuron>();
			neighbours.add(best);
			final java.util.HashSet<org.apache.commons.math3.ml.neuralnet.Neuron> exclude = new java.util.HashSet<org.apache.commons.math3.ml.neuralnet.Neuron>();
			exclude.add(best);
			int radius = 1;
			do {
				neighbours = net.getNeighbours(neighbours, exclude);
				for (org.apache.commons.math3.ml.neuralnet.Neuron n : neighbours) {
					updateNeighbouringNeuron(n, features, neighbourhoodDecay.value(radius));
				}
				exclude.addAll(neighbours);
				++radius;
			} while (radius <= currentNeighbourhood );
		} 
	}

	public long getNumberOfCalls() {
		return numberOfCalls.get();
	}

	private void updateNeighbouringNeuron(org.apache.commons.math3.ml.neuralnet.Neuron n, double[] features, double learningRate) {
		while (true) {
			final double[] expect = n.getFeatures();
			final double[] update = computeFeatures(expect, features, learningRate);
			if (n.compareAndSetFeatures(expect, update)) {
				break;
			} 
		}
	}

	private org.apache.commons.math3.ml.neuralnet.Neuron findAndUpdateBestNeuron(org.apache.commons.math3.ml.neuralnet.Network net, double[] features, double learningRate) {
		while (true) {
			final org.apache.commons.math3.ml.neuralnet.Neuron best = org.apache.commons.math3.ml.neuralnet.MapUtils.findBest(features, net, distance);
			final double[] expect = best.getFeatures();
			final double[] update = computeFeatures(expect, features, learningRate);
			if (best.compareAndSetFeatures(expect, update)) {
				return best;
			} 
		}
	}

	private double[] computeFeatures(double[] current, double[] sample, double learningRate) {
		final org.apache.commons.math3.linear.ArrayRealVector c = new org.apache.commons.math3.linear.ArrayRealVector(current , false);
		final org.apache.commons.math3.linear.ArrayRealVector s = new org.apache.commons.math3.linear.ArrayRealVector(sample , false);
		return s.subtract(c).mapMultiplyToSelf(learningRate).add(c).toArray();
	}
}

