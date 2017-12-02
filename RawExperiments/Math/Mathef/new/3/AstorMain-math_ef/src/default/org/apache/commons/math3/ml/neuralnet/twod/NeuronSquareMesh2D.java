package org.apache.commons.math3.ml.neuralnet.twod;


public class NeuronSquareMesh2D implements java.io.Serializable {
	private final org.apache.commons.math3.ml.neuralnet.Network network;

	private final int numberOfRows;

	private final int numberOfColumns;

	private final boolean wrapRows;

	private final boolean wrapColumns;

	private final org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood neighbourhood;

	private final long[][] identifiers;

	NeuronSquareMesh2D(boolean wrapRowDim ,boolean wrapColDim ,org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood neighbourhoodType ,double[][][] featuresList) {
		numberOfRows = featuresList.length;
		numberOfColumns = featuresList[0].length;
		if ((numberOfRows) < 2) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(numberOfRows , 2 , true);
		} 
		if ((numberOfColumns) < 2) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(numberOfColumns , 2 , true);
		} 
		wrapRows = wrapRowDim;
		wrapColumns = wrapColDim;
		neighbourhood = neighbourhoodType;
		final int fLen = featuresList[0][0].length;
		network = new org.apache.commons.math3.ml.neuralnet.Network(0 , fLen);
		identifiers = new long[numberOfRows][numberOfColumns];
		for (int i = 0 ; i < (numberOfRows) ; i++) {
			for (int j = 0 ; j < (numberOfColumns) ; j++) {
				identifiers[i][j] = network.createNeuron(featuresList[i][j]);
			}
		}
		createLinks();
	}

	public NeuronSquareMesh2D(int numRows ,boolean wrapRowDim ,int numCols ,boolean wrapColDim ,org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood neighbourhoodType ,org.apache.commons.math3.ml.neuralnet.FeatureInitializer[] featureInit) {
		if (numRows < 2) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(numRows , 2 , true);
		} 
		if (numCols < 2) {
			throw new org.apache.commons.math3.exception.NumberIsTooSmallException(numCols , 2 , true);
		} 
		numberOfRows = numRows;
		wrapRows = wrapRowDim;
		numberOfColumns = numCols;
		wrapColumns = wrapColDim;
		neighbourhood = neighbourhoodType;
		identifiers = new long[numberOfRows][numberOfColumns];
		final int fLen = featureInit.length;
		network = new org.apache.commons.math3.ml.neuralnet.Network(0 , fLen);
		for (int i = 0 ; i < numRows ; i++) {
			for (int j = 0 ; j < numCols ; j++) {
				final double[] features = new double[fLen];
				for (int fIndex = 0 ; fIndex < fLen ; fIndex++) {
					features[fIndex] = featureInit[fIndex].value();
				}
				identifiers[i][j] = network.createNeuron(features);
			}
		}
		createLinks();
	}

	public org.apache.commons.math3.ml.neuralnet.Network getNetwork() {
		return network;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	public org.apache.commons.math3.ml.neuralnet.Neuron getNeuron(int i, int j) {
		if ((i < 0) || (i >= (numberOfRows))) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(i , 0 , ((numberOfRows) - 1));
		} 
		if ((j < 0) || (j >= (numberOfColumns))) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(j , 0 , ((numberOfColumns) - 1));
		} 
		return network.getNeuron(identifiers[i][j]);
	}

	private void createLinks() {
		final java.util.List<java.lang.Long> linkEnd = new java.util.ArrayList<java.lang.Long>();
		final int iLast = (numberOfRows) - 1;
		final int jLast = (numberOfColumns) - 1;
		for (int i = 0 ; i < (numberOfRows) ; i++) {
			for (int j = 0 ; j < (numberOfColumns) ; j++) {
				linkEnd.clear();
				switch (neighbourhood) {
					case MOORE :
						if (i > 0) {
							if (j > 0) {
								linkEnd.add(identifiers[(i - 1)][(j - 1)]);
							} 
							if (j < jLast) {
								linkEnd.add(identifiers[(i - 1)][(j + 1)]);
							} 
						} 
						if (i < iLast) {
							if (j > 0) {
								linkEnd.add(identifiers[(i + 1)][(j - 1)]);
							} 
							if (j < jLast) {
								linkEnd.add(identifiers[(i + 1)][(j + 1)]);
							} 
						} 
						if (wrapRows) {
							if (i == 0) {
								if (j > 0) {
									linkEnd.add(identifiers[iLast][(j - 1)]);
								} 
								if (j < jLast) {
									linkEnd.add(identifiers[iLast][(j + 1)]);
								} 
							} else if (i == iLast) {
								if (j > 0) {
									linkEnd.add(identifiers[0][(j - 1)]);
								} 
								if (j < jLast) {
									linkEnd.add(identifiers[0][(j + 1)]);
								} 
							} 
						} 
						if (wrapColumns) {
							if (j == 0) {
								if (i > 0) {
									linkEnd.add(identifiers[(i - 1)][jLast]);
								} 
								if (i < iLast) {
									linkEnd.add(identifiers[(i + 1)][jLast]);
								} 
							} else if (j == jLast) {
								if (i > 0) {
									linkEnd.add(identifiers[(i - 1)][0]);
								} 
								if (i < iLast) {
									linkEnd.add(identifiers[(i + 1)][0]);
								} 
							} 
						} 
						if ((wrapRows) && (wrapColumns)) {
							if ((i == 0) && (j == 0)) {
								linkEnd.add(identifiers[iLast][jLast]);
							} else if ((i == 0) && (j == jLast)) {
								linkEnd.add(identifiers[iLast][0]);
							} else if ((i == iLast) && (j == 0)) {
								linkEnd.add(identifiers[0][jLast]);
							} else if ((i == iLast) && (j == jLast)) {
								linkEnd.add(identifiers[0][0]);
							} 
						} 
					case VON_NEUMANN :
						if (i > 0) {
							linkEnd.add(identifiers[(i - 1)][j]);
						} 
						if (i < iLast) {
							linkEnd.add(identifiers[(i + 1)][j]);
						} 
						if (wrapRows) {
							if (i == 0) {
								linkEnd.add(identifiers[iLast][j]);
							} else if (i == iLast) {
								linkEnd.add(identifiers[0][j]);
							} 
						} 
						if (j > 0) {
							linkEnd.add(identifiers[i][(j - 1)]);
						} 
						if (j < jLast) {
							linkEnd.add(identifiers[i][(j + 1)]);
						} 
						if (wrapColumns) {
							if (j == 0) {
								linkEnd.add(identifiers[i][jLast]);
							} else if (j == jLast) {
								linkEnd.add(identifiers[i][0]);
							} 
						} 
						break;
					default :
						throw new org.apache.commons.math3.exception.MathInternalError();
				}
				final org.apache.commons.math3.ml.neuralnet.Neuron aNeuron = network.getNeuron(identifiers[i][j]);
				for (long b : linkEnd) {
					final org.apache.commons.math3.ml.neuralnet.Neuron bNeuron = network.getNeuron(b);
					network.addLink(aNeuron, bNeuron);
				}
			}
		}
	}

	private void readObject(java.io.ObjectInputStream in) {
		throw new java.lang.IllegalStateException();
	}

	private java.lang.Object writeReplace() {
		final double[][][] featuresList = new double[numberOfRows][numberOfColumns][];
		for (int i = 0 ; i < (numberOfRows) ; i++) {
			for (int j = 0 ; j < (numberOfColumns) ; j++) {
				featuresList[i][j] = getNeuron(i, j).getFeatures();
			}
		}
		return new org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D.SerializationProxy(wrapRows , wrapColumns , neighbourhood , featuresList);
	}

	private static class SerializationProxy implements java.io.Serializable {
		private static final long serialVersionUID = 20130226L;

		private final boolean wrapRows;

		private final boolean wrapColumns;

		private final org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood neighbourhood;

		private final double[][][] featuresList;

		SerializationProxy(boolean wrapRows ,boolean wrapColumns ,org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood neighbourhood ,double[][][] featuresList) {
			this.wrapRows = wrapRows;
			this.wrapColumns = wrapColumns;
			this.neighbourhood = neighbourhood;
			this.featuresList = featuresList;
		}

		private java.lang.Object readResolve() {
			return new org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D(wrapRows , wrapColumns , neighbourhood , featuresList);
		}
	}
}

