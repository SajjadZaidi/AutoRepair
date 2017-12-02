package org.apache.commons.math3.ml.neuralnet;


public class Neuron implements java.io.Serializable {
	private static final long serialVersionUID = 20130207L;

	private final long identifier;

	private final int size;

	private final java.util.concurrent.atomic.AtomicReference<double[]> features;

	Neuron(long identifier ,double[] features) {
		this.identifier = identifier;
		this.size = features.length;
		this.features = new java.util.concurrent.atomic.AtomicReference<double[]>(features.clone());
	}

	public long getIdentifier() {
		return identifier;
	}

	public int getSize() {
		return size;
	}

	public double[] getFeatures() {
		return features.get().clone();
	}

	public boolean compareAndSetFeatures(double[] expect, double[] update) {
		if ((update.length) != (size)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(update.length , size);
		} 
		final double[] current = features.get();
		if (!(containSameValues(current, expect))) {
			return false;
		} 
		if (features.compareAndSet(current, update.clone())) {
			return true;
		} else {
			return false;
		}
	}

	private boolean containSameValues(double[] current, double[] expect) {
		if ((expect.length) != (size)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(expect.length , size);
		} 
		for (int i = 0 ; i < (size) ; i++) {
			if (!(org.apache.commons.math3.util.Precision.equals(current[i], expect[i]))) {
				return false;
			} 
		}
		return true;
	}

	private void readObject(java.io.ObjectInputStream in) {
		throw new java.lang.IllegalStateException();
	}

	private java.lang.Object writeReplace() {
		return new org.apache.commons.math3.ml.neuralnet.Neuron.SerializationProxy(identifier , features.get());
	}

	private static class SerializationProxy implements java.io.Serializable {
		private static final long serialVersionUID = 20130207L;

		private final double[] features;

		private final long identifier;

		SerializationProxy(long identifier ,double[] features) {
			this.identifier = identifier;
			this.features = features;
		}

		private java.lang.Object readResolve() {
			return new org.apache.commons.math3.ml.neuralnet.Neuron(identifier , features);
		}
	}
}

