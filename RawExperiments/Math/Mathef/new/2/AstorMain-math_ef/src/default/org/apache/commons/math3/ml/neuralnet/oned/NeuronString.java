package org.apache.commons.math3.ml.neuralnet.oned;


public class NeuronString implements java.io.Serializable {
	private final org.apache.commons.math3.ml.neuralnet.Network network;

	private final int size;

	private final boolean wrap;

	private final long[] identifiers;

	NeuronString(boolean wrap ,double[][] featuresList) {
		size = featuresList.length;
		if ((size) < 2) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(size , 2 , true);
		} 
		this.wrap = wrap;
		final int fLen = featuresList[0].length;
		network = new org.apache.commons.math3.ml.neuralnet.Network(0 , fLen);
		identifiers = new long[size];
		for (int i = 0 ; i < (size) ; i++) {
			identifiers[i] = network.createNeuron(featuresList[i]);
		}
		createLinks();
	}

	public NeuronString(int num ,boolean wrap ,org.apache.commons.math3.ml.neuralnet.FeatureInitializer[] featureInit) {
		if (num < 2) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(num , 2 , true);
		} 
		size = num;
		this.wrap = wrap;
		identifiers = new long[num];
		final int fLen = featureInit.length;
		network = new org.apache.commons.math3.ml.neuralnet.Network(0 , fLen);
		for (int i = 0 ; i < num ; i++) {
			final double[] features = new double[fLen];
			for (int fIndex = 0 ; fIndex < fLen ; fIndex++) {
				features[fIndex] = featureInit[fIndex].value();
			}
			identifiers[i] = network.createNeuron(features);
		}
		createLinks();
	}

	public org.apache.commons.math3.ml.neuralnet.Network getNetwork() {
		return network;
	}

	public int getSize() {
		return size;
	}

	public double[] getFeatures(int i) {
		if ((i < 0) || (i >= (size))) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(i , 0 , ((size) - 1));
		} 
		return network.getNeuron(identifiers[i]).getFeatures();
	}

	private void createLinks() {
		for (int i = 0 ; i < ((size) - 1) ; i++) {
			network.addLink(network.getNeuron(i), network.getNeuron((i + 1)));
		}
		for (int i = (size) - 1 ; i > 0 ; i--) {
			network.addLink(network.getNeuron(i), network.getNeuron((i - 1)));
		}
		if (wrap) {
			network.addLink(network.getNeuron(0), network.getNeuron(((size) - 1)));
			network.addLink(network.getNeuron(((size) - 1)), network.getNeuron(0));
		} 
	}

	private void readObject(java.io.ObjectInputStream in) {
		throw new java.lang.IllegalStateException();
	}

	private java.lang.Object writeReplace() {
		final double[][] featuresList = new double[size][];
		for (int i = 0 ; i < (size) ; i++) {
			featuresList[i] = getFeatures(i);
		}
		return new org.apache.commons.math3.ml.neuralnet.oned.NeuronString.SerializationProxy(wrap , featuresList);
	}

	private static class SerializationProxy implements java.io.Serializable {
		private static final long serialVersionUID = 20130226L;

		private final boolean wrap;

		private final double[][] featuresList;

		SerializationProxy(boolean wrap ,double[][] featuresList) {
			this.wrap = wrap;
			this.featuresList = featuresList;
		}

		private java.lang.Object readResolve() {
			return new org.apache.commons.math3.ml.neuralnet.oned.NeuronString(wrap , featuresList);
		}
	}
}

