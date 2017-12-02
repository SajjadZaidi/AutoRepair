package org.apache.commons.math3.ml.neuralnet;


public class Network implements java.io.Serializable , java.lang.Iterable<org.apache.commons.math3.ml.neuralnet.Neuron> {
	private static final long serialVersionUID = 20130207L;

	private final java.util.concurrent.ConcurrentHashMap<java.lang.Long, org.apache.commons.math3.ml.neuralnet.Neuron> neuronMap = new java.util.concurrent.ConcurrentHashMap<java.lang.Long, org.apache.commons.math3.ml.neuralnet.Neuron>();

	private final java.util.concurrent.atomic.AtomicLong nextId;

	private final int featureSize;

	private final java.util.concurrent.ConcurrentHashMap<java.lang.Long, java.util.Set<java.lang.Long>> linkMap = new java.util.concurrent.ConcurrentHashMap<java.lang.Long, java.util.Set<java.lang.Long>>();

	public static class NeuronIdentifierComparator implements java.io.Serializable , java.util.Comparator<org.apache.commons.math3.ml.neuralnet.Neuron> {
		private static final long serialVersionUID = 20130207L;

		public int compare(org.apache.commons.math3.ml.neuralnet.Neuron a, org.apache.commons.math3.ml.neuralnet.Neuron b) {
			final long aId = a.getIdentifier();
			final long bId = b.getIdentifier();
			return aId < bId ? -1 : aId > bId ? 1 : 0;
		}
	}

	Network(long nextId ,int featureSize ,org.apache.commons.math3.ml.neuralnet.Neuron[] neuronList ,long[][] neighbourIdList) {
		final int numNeurons = neuronList.length;
		if (numNeurons != (neighbourIdList.length)) {
			throw new org.apache.commons.math3.exception.MathIllegalStateException();
		} 
		for (int i = 0 ; i < numNeurons ; i++) {
			final org.apache.commons.math3.ml.neuralnet.Neuron n = neuronList[i];
			final long id = n.getIdentifier();
			if (id >= nextId) {
				throw new org.apache.commons.math3.exception.MathIllegalStateException();
			} 
			neuronMap.put(id, n);
			linkMap.put(id, new java.util.HashSet<java.lang.Long>());
		}
		for (int i = 0 ; i < numNeurons ; i++) {
			final long aId = neuronList[i].getIdentifier();
			final java.util.Set<java.lang.Long> aLinks = linkMap.get(aId);
			for (java.lang.Long bId : neighbourIdList[i]) {
				if ((neuronMap.get(bId)) == null) {
					throw new org.apache.commons.math3.exception.MathIllegalStateException();
				} 
				addLinkToLinkSet(aLinks, bId);
			}
		}
		this.nextId = new java.util.concurrent.atomic.AtomicLong(nextId);
		this.featureSize = featureSize;
	}

	public Network(long initialIdentifier ,int featureSize) {
		nextId = new java.util.concurrent.atomic.AtomicLong(initialIdentifier);
		this.featureSize = featureSize;
	}

	public java.util.Iterator<org.apache.commons.math3.ml.neuralnet.Neuron> iterator() {
		return neuronMap.values().iterator();
	}

	public java.util.Collection<org.apache.commons.math3.ml.neuralnet.Neuron> getNeurons(java.util.Comparator<org.apache.commons.math3.ml.neuralnet.Neuron> comparator) {
		final java.util.List<org.apache.commons.math3.ml.neuralnet.Neuron> neurons = new java.util.ArrayList<org.apache.commons.math3.ml.neuralnet.Neuron>();
		neurons.addAll(neuronMap.values());
		java.util.Collections.sort(neurons, comparator);
		return neurons;
	}

	public long createNeuron(double[] features) {
		if ((features.length) != (featureSize)) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(features.length , featureSize);
		} 
		final long id = createNextId();
		neuronMap.put(id, new org.apache.commons.math3.ml.neuralnet.Neuron(id , features));
		linkMap.put(id, new java.util.HashSet<java.lang.Long>());
		return id;
	}

	public void deleteNeuron(org.apache.commons.math3.ml.neuralnet.Neuron neuron) {
		final java.util.Collection<org.apache.commons.math3.ml.neuralnet.Neuron> neighbours = getNeighbours(neuron);
		for (org.apache.commons.math3.ml.neuralnet.Neuron n : neighbours) {
			deleteLink(n, neuron);
		}
		neuronMap.remove(neuron.getIdentifier());
	}

	public int getFeaturesSize() {
		return featureSize;
	}

	public void addLink(org.apache.commons.math3.ml.neuralnet.Neuron a, org.apache.commons.math3.ml.neuralnet.Neuron b) {
		final long aId = a.getIdentifier();
		final long bId = b.getIdentifier();
		if (a != (getNeuron(aId))) {
			throw new java.util.NoSuchElementException(java.lang.Long.toString(aId));
		} 
		if (b != (getNeuron(bId))) {
			throw new java.util.NoSuchElementException(java.lang.Long.toString(bId));
		} 
		addLinkToLinkSet(linkMap.get(aId), bId);
	}

	private void addLinkToLinkSet(java.util.Set<java.lang.Long> linkSet, long id) {
		linkSet.add(id);
	}

	public void deleteLink(org.apache.commons.math3.ml.neuralnet.Neuron a, org.apache.commons.math3.ml.neuralnet.Neuron b) {
		final long aId = a.getIdentifier();
		final long bId = b.getIdentifier();
		if (a != (getNeuron(aId))) {
			throw new java.util.NoSuchElementException(java.lang.Long.toString(aId));
		} 
		if (b != (getNeuron(bId))) {
			throw new java.util.NoSuchElementException(java.lang.Long.toString(bId));
		} 
		deleteLinkFromLinkSet(linkMap.get(aId), bId);
	}

	private void deleteLinkFromLinkSet(java.util.Set<java.lang.Long> linkSet, long id) {
		linkSet.remove(id);
	}

	public org.apache.commons.math3.ml.neuralnet.Neuron getNeuron(long id) {
		final org.apache.commons.math3.ml.neuralnet.Neuron n = neuronMap.get(id);
		if (n == null) {
			throw new java.util.NoSuchElementException(java.lang.Long.toString(id));
		} 
		return n;
	}

	public java.util.Collection<org.apache.commons.math3.ml.neuralnet.Neuron> getNeighbours(java.lang.Iterable<org.apache.commons.math3.ml.neuralnet.Neuron> neurons) {
		return getNeighbours(neurons, null);
	}

	public java.util.Collection<org.apache.commons.math3.ml.neuralnet.Neuron> getNeighbours(java.lang.Iterable<org.apache.commons.math3.ml.neuralnet.Neuron> neurons, java.lang.Iterable<org.apache.commons.math3.ml.neuralnet.Neuron> exclude) {
		final java.util.Set<java.lang.Long> idList = new java.util.HashSet<java.lang.Long>();
		for (org.apache.commons.math3.ml.neuralnet.Neuron n : neurons) {
			idList.addAll(linkMap.get(n.getIdentifier()));
		}
		if (exclude != null) {
			for (org.apache.commons.math3.ml.neuralnet.Neuron n : exclude) {
				idList.remove(n.getIdentifier());
			}
		} 
		final java.util.List<org.apache.commons.math3.ml.neuralnet.Neuron> neuronList = new java.util.ArrayList<org.apache.commons.math3.ml.neuralnet.Neuron>();
		for (java.lang.Long id : idList) {
			neuronList.add(getNeuron(id));
		}
		return neuronList;
	}

	public java.util.Collection<org.apache.commons.math3.ml.neuralnet.Neuron> getNeighbours(org.apache.commons.math3.ml.neuralnet.Neuron neuron) {
		return getNeighbours(neuron, null);
	}

	public java.util.Collection<org.apache.commons.math3.ml.neuralnet.Neuron> getNeighbours(org.apache.commons.math3.ml.neuralnet.Neuron neuron, java.lang.Iterable<org.apache.commons.math3.ml.neuralnet.Neuron> exclude) {
		final java.util.Set<java.lang.Long> idList = linkMap.get(neuron.getIdentifier());
		if (exclude != null) {
			for (org.apache.commons.math3.ml.neuralnet.Neuron n : exclude) {
				idList.remove(n.getIdentifier());
			}
		} 
		final java.util.List<org.apache.commons.math3.ml.neuralnet.Neuron> neuronList = new java.util.ArrayList<org.apache.commons.math3.ml.neuralnet.Neuron>();
		for (java.lang.Long id : idList) {
			neuronList.add(getNeuron(id));
		}
		return neuronList;
	}

	private java.lang.Long createNextId() {
		return nextId.getAndIncrement();
	}

	private void readObject(java.io.ObjectInputStream in) {
		throw new java.lang.IllegalStateException();
	}

	private java.lang.Object writeReplace() {
		final org.apache.commons.math3.ml.neuralnet.Neuron[] neuronList = neuronMap.values().toArray(new org.apache.commons.math3.ml.neuralnet.Neuron[0]);
		final long[][] neighbourIdList = new long[neuronList.length][];
		for (int i = 0 ; i < (neuronList.length) ; i++) {
			final java.util.Collection<org.apache.commons.math3.ml.neuralnet.Neuron> neighbours = getNeighbours(neuronList[i]);
			final long[] neighboursId = new long[neighbours.size()];
			int count = 0;
			for (org.apache.commons.math3.ml.neuralnet.Neuron n : neighbours) {
				neighboursId[count] = n.getIdentifier();
				++count;
			}
			neighbourIdList[i] = neighboursId;
		}
		return new org.apache.commons.math3.ml.neuralnet.Network.SerializationProxy(nextId.get() , featureSize , neuronList , neighbourIdList);
	}

	private static class SerializationProxy implements java.io.Serializable {
		private static final long serialVersionUID = 20130207L;

		private final long nextId;

		private final int featureSize;

		private final org.apache.commons.math3.ml.neuralnet.Neuron[] neuronList;

		private final long[][] neighbourIdList;

		SerializationProxy(long nextId ,int featureSize ,org.apache.commons.math3.ml.neuralnet.Neuron[] neuronList ,long[][] neighbourIdList) {
			this.nextId = nextId;
			this.featureSize = featureSize;
			this.neuronList = neuronList;
			this.neighbourIdList = neighbourIdList;
		}

		private java.lang.Object readResolve() {
			return new org.apache.commons.math3.ml.neuralnet.Network(nextId , featureSize , neuronList , neighbourIdList);
		}
	}
}

