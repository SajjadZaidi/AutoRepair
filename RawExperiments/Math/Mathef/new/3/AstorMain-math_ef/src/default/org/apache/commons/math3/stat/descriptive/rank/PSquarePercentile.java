package org.apache.commons.math3.stat.descriptive.rank;


public class PSquarePercentile extends org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic implements java.io.Serializable , org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic {
	private static final int PSQUARE_CONSTANT = 5;

	private static final double DEFAULT_QUANTILE_DESIRED = 50.0;

	private static final long serialVersionUID = 2283912083175715479L;

	private static final java.text.DecimalFormat DECIMAL_FORMAT = new java.text.DecimalFormat("00.00");

	private final java.util.List<java.lang.Double> initialFive = new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.FixedCapacityList<java.lang.Double>(org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSQUARE_CONSTANT);

	private final double quantile;

	private transient double lastObservation;

	private org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers markers = null;

	private double pValue = java.lang.Double.NaN;

	private long countOfObservations;

	public PSquarePercentile(final double p) {
		if ((p > 100) || (p < 0)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_RANGE , p , 0 , 100);
		} 
		this.quantile = p / 100.0;
	}

	PSquarePercentile() {
		this(org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.DEFAULT_QUANTILE_DESIRED);
	}

	@java.lang.Override
	public int hashCode() {
		double result = getResult();
		result = java.lang.Double.isNaN(result) ? 37 : result;
		final double markersHash = (markers) == null ? 0 : markers.hashCode();
		final double[] toHash = new double[]{ result , quantile , markersHash , countOfObservations };
		return java.util.Arrays.hashCode(toHash);
	}

	@java.lang.Override
	public boolean equals(java.lang.Object o) {
		boolean result = false;
		if ((org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.this) == o) {
			result = true;
		} else if ((o != null) && (o instanceof org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile)) {
			org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile that = ((org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile)(o));
			boolean isNotNull = ((markers) != null) && ((that.markers) != null);
			boolean isNull = ((markers) == null) && ((that.markers) == null);
			result = isNotNull ? markers.equals(that.markers) : isNull;
			result = result && ((getN()) == (that.getN()));
		} 
		return result;
	}

	@java.lang.Override
	public void increment(final double observation) {
		(countOfObservations)++;
		org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.this.lastObservation = observation;
		if ((markers) == null) {
			if (initialFive.add(observation)) {
				java.util.Collections.sort(initialFive);
				pValue = initialFive.get(((int)((quantile) * ((initialFive.size()) - 1))));
				return ;
			} 
			markers = org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.newMarkers(initialFive, quantile);
		} 
		pValue = markers.processDataPoint(observation);
	}

	@java.lang.Override
	public java.lang.String toString() {
		if ((markers) == null) {
			return java.lang.String.format("obs=%s pValue=%s", org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.DECIMAL_FORMAT.format(lastObservation), org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.DECIMAL_FORMAT.format(pValue));
		} else {
			return java.lang.String.format("obs=%s markers=%s", org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.DECIMAL_FORMAT.format(lastObservation), markers.toString());
		}
	}

	public long getN() {
		return countOfObservations;
	}

	@java.lang.Override
	public org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic copy() {
		org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile copy = new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile((100.0 * (quantile)));
		if ((markers) != null) {
			copy.markers = ((org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers)(markers.clone()));
		} 
		copy.countOfObservations = countOfObservations;
		copy.pValue = pValue;
		copy.initialFive.clear();
		copy.initialFive.addAll(initialFive);
		return copy;
	}

	public double quantile() {
		return quantile;
	}

	@java.lang.Override
	public void clear() {
		markers = null;
		initialFive.clear();
		countOfObservations = 0L;
		pValue = java.lang.Double.NaN;
	}

	@java.lang.Override
	public double getResult() {
		if ((java.lang.Double.compare(quantile, 1.0)) == 0) {
			pValue = maximum();
		} else if ((java.lang.Double.compare(quantile, 0.0)) == 0) {
			pValue = minimum();
		} 
		return pValue;
	}

	private double maximum() {
		double val = java.lang.Double.NaN;
		if ((markers) != null) {
			val = markers.height(org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSQUARE_CONSTANT);
		} else if (!(initialFive.isEmpty())) {
			val = initialFive.get(((initialFive.size()) - 1));
		} 
		return val;
	}

	private double minimum() {
		double val = java.lang.Double.NaN;
		if ((markers) != null) {
			val = markers.height(1);
		} else if (!(initialFive.isEmpty())) {
			val = initialFive.get(0);
		} 
		return val;
	}

	private static class Markers implements java.io.Serializable , org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers {
		private static final long serialVersionUID = 1L;

		private static final int LOW = 2;

		private static final int HIGH = 4;

		private final org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker[] markerArray;

		private transient int k = -1;

		private Markers(final org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker[] theMarkerArray) {
			org.apache.commons.math3.util.MathUtils.checkNotNull(theMarkerArray);
			markerArray = theMarkerArray;
			for (int i = 1 ; i < (org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSQUARE_CONSTANT) ; i++) {
				markerArray[i].previous(markerArray[(i - 1)]).next(markerArray[(i + 1)]).index(i);
			}
			markerArray[0].previous(markerArray[0]).next(markerArray[1]).index(0);
			markerArray[5].previous(markerArray[4]).next(markerArray[5]).index(5);
		}

		private Markers(final java.util.List<java.lang.Double> initialFive ,final double p) {
			this(org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers.createMarkerArray(initialFive, p));
		}

		private static org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker[] createMarkerArray(final java.util.List<java.lang.Double> initialFive, final double p) {
			final int countObserved = initialFive == null ? -1 : initialFive.size();
			if (countObserved < (org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSQUARE_CONSTANT)) {
				throw new org.apache.commons.math3.exception.InsufficientDataException(org.apache.commons.math3.exception.util.LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE , countObserved , org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSQUARE_CONSTANT);
			} 
			java.util.Collections.sort(initialFive);
			return new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker[]{ new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker() , new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker(initialFive.get(0) , 1 , 0 , 1) , new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker(initialFive.get(1) , (1 + (2 * p)) , (p / 2) , 2) , new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker(initialFive.get(2) , (1 + (4 * p)) , p , 3) , new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker(initialFive.get(3) , (3 + (2 * p)) , ((1 + p) / 2) , 4) , new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker(initialFive.get(4) , 5 , 1 , 5) };
		}

		@java.lang.Override
		public int hashCode() {
			return java.util.Arrays.deepHashCode(markerArray);
		}

		@java.lang.Override
		public boolean equals(java.lang.Object o) {
			boolean result = false;
			if ((org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers.this) == o) {
				result = true;
			} else if ((o != null) && (o instanceof org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers)) {
				org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers that = ((org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers)(o));
				result = java.util.Arrays.deepEquals(markerArray, that.markerArray);
			} 
			return result;
		}

		public double processDataPoint(final double inputDataPoint) {
			final int kthCell = findCellAndUpdateMinMax(inputDataPoint);
			incrementPositions(1, (kthCell + 1), 5);
			updateDesiredPositions();
			adjustHeightsOfMarkers();
			return getPercentileValue();
		}

		public double getPercentileValue() {
			return height(3);
		}

		private int findCellAndUpdateMinMax(final double observation) {
			k = -1;
			if (observation < (height(1))) {
				markerArray[1].markerHeight = observation;
				k = 1;
			} else if (observation < (height(2))) {
				k = 1;
			} else if (observation < (height(3))) {
				k = 2;
			} else if (observation < (height(4))) {
				k = 3;
			} else if (observation <= (height(5))) {
				k = 4;
			} else {
				markerArray[5].markerHeight = observation;
				k = 4;
			}
			return k;
		}

		private void adjustHeightsOfMarkers() {
			for (int i = org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers.LOW ; i <= (org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers.HIGH) ; i++) {
				estimate(i);
			}
		}

		public double estimate(final int index) {
			if ((index < (org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers.LOW)) || (index > (org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers.HIGH))) {
				throw new org.apache.commons.math3.exception.OutOfRangeException(index , org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers.LOW , org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers.HIGH);
			} 
			return markerArray[index].estimate();
		}

		private void incrementPositions(final int d, final int startIndex, final int endIndex) {
			for (int i = startIndex ; i <= endIndex ; i++) {
				markerArray[i].incrementPosition(d);
			}
		}

		private void updateDesiredPositions() {
			for (int i = 1 ; i < (markerArray.length) ; i++) {
				markerArray[i].updateDesiredPosition();
			}
		}

		private void readObject(java.io.ObjectInputStream anInputStream) throws java.io.IOException, java.lang.ClassNotFoundException {
			anInputStream.defaultReadObject();
			for (int i = 1 ; i < (org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSQUARE_CONSTANT) ; i++) {
				markerArray[i].previous(markerArray[(i - 1)]).next(markerArray[(i + 1)]).index(i);
			}
			markerArray[0].previous(markerArray[0]).next(markerArray[1]).index(0);
			markerArray[5].previous(markerArray[4]).next(markerArray[5]).index(5);
		}

		public double height(final int markerIndex) {
			if ((markerIndex >= (markerArray.length)) || (markerIndex <= 0)) {
				throw new org.apache.commons.math3.exception.OutOfRangeException(markerIndex , 1 , markerArray.length);
			} 
			return markerArray[markerIndex].markerHeight;
		}

		@java.lang.Override
		public java.lang.Object clone() {
			return new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers(new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker[]{ new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker() , ((org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker)(markerArray[1].clone())) , ((org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker)(markerArray[2].clone())) , ((org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker)(markerArray[3].clone())) , ((org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker)(markerArray[4].clone())) , ((org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker)(markerArray[5].clone())) });
		}

		@java.lang.Override
		public java.lang.String toString() {
			return java.lang.String.format("m1=[%s],m2=[%s],m3=[%s],m4=[%s],m5=[%s]", markerArray[1].toString(), markerArray[2].toString(), markerArray[3].toString(), markerArray[4].toString(), markerArray[5].toString());
		}
	}

	private static class Marker implements java.io.Serializable , java.lang.Cloneable {
		private static final long serialVersionUID = -3575879478288538431L;

		private int index;

		private double intMarkerPosition;

		private double desiredMarkerPosition;

		private double markerHeight;

		private double desiredMarkerIncrement;

		private transient org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker next;

		private transient org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker previous;

		private final org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator nonLinear = new org.apache.commons.math3.analysis.interpolation.NevilleInterpolator();

		private transient org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator linear = new org.apache.commons.math3.analysis.interpolation.LinearInterpolator();

		private Marker() {
			org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this.next = org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this.previous = org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this;
		}

		private Marker(double heightOfMarker ,double makerPositionDesired ,double markerPositionIncrement ,double markerPositionNumber) {
			this();
			org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this.markerHeight = heightOfMarker;
			org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this.desiredMarkerPosition = makerPositionDesired;
			org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this.desiredMarkerIncrement = markerPositionIncrement;
			org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this.intMarkerPosition = markerPositionNumber;
		}

		private org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker previous(final org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker previousMarker) {
			org.apache.commons.math3.util.MathUtils.checkNotNull(previousMarker);
			org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this.previous = previousMarker;
			return org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this;
		}

		private org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker next(final org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker nextMarker) {
			org.apache.commons.math3.util.MathUtils.checkNotNull(nextMarker);
			org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this.next = nextMarker;
			return org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this;
		}

		private org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker index(final int indexOfMarker) {
			org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this.index = indexOfMarker;
			return org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this;
		}

		private void updateDesiredPosition() {
			desiredMarkerPosition += desiredMarkerIncrement;
		}

		private void incrementPosition(final int d) {
			intMarkerPosition += d;
		}

		private double difference() {
			return (desiredMarkerPosition) - (intMarkerPosition);
		}

		private double estimate() {
			final double di = difference();
			final boolean isNextHigher = ((next.intMarkerPosition) - (intMarkerPosition)) > 1;
			final boolean isPreviousLower = ((previous.intMarkerPosition) - (intMarkerPosition)) < (-1);
			if (((di >= 1) && isNextHigher) || ((di <= (-1)) && isPreviousLower)) {
				final int d = di >= 0 ? 1 : -1;
				final double[] xval = new double[]{ previous.intMarkerPosition , intMarkerPosition , next.intMarkerPosition };
				final double[] yval = new double[]{ previous.markerHeight , markerHeight , next.markerHeight };
				final double xD = (intMarkerPosition) + d;
				org.apache.commons.math3.analysis.UnivariateFunction univariateFunction = nonLinear.interpolate(xval, yval);
				markerHeight = univariateFunction.value(xD);
				if (isEstimateBad(yval, markerHeight)) {
					int delta = (xD - (xval[1])) > 0 ? 1 : -1;
					final double[] xBad = new double[]{ xval[1] , xval[(1 + delta)] };
					final double[] yBad = new double[]{ yval[1] , yval[(1 + delta)] };
					org.apache.commons.math3.util.MathArrays.sortInPlace(xBad, yBad);
					univariateFunction = linear.interpolate(xBad, yBad);
					markerHeight = univariateFunction.value(xD);
				} 
				incrementPosition(d);
			} 
			return markerHeight;
		}

		private boolean isEstimateBad(final double[] y, final double yD) {
			return (yD <= (y[0])) || (yD >= (y[2]));
		}

		@java.lang.Override
		public boolean equals(java.lang.Object o) {
			boolean result = false;
			if ((org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this) == o) {
				result = true;
			} else if ((o != null) && (o instanceof org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker)) {
				org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker that = ((org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker)(o));
				result = (java.lang.Double.compare(markerHeight, that.markerHeight)) == 0;
				result = result && ((java.lang.Double.compare(intMarkerPosition, that.intMarkerPosition)) == 0);
				result = result && ((java.lang.Double.compare(desiredMarkerPosition, that.desiredMarkerPosition)) == 0);
				result = result && ((java.lang.Double.compare(desiredMarkerIncrement, that.desiredMarkerIncrement)) == 0);
				result = result && ((next.index) == (that.next.index));
				result = result && ((previous.index) == (that.previous.index));
			} 
			return result;
		}

		@java.lang.Override
		public int hashCode() {
			return java.util.Arrays.hashCode(new double[]{ markerHeight , intMarkerPosition , desiredMarkerIncrement , desiredMarkerPosition , previous.index , next.index });
		}

		private void readObject(java.io.ObjectInputStream anInstream) throws java.io.IOException, java.lang.ClassNotFoundException {
			anInstream.defaultReadObject();
			previous = next = org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker.this;
			linear = new org.apache.commons.math3.analysis.interpolation.LinearInterpolator();
		}

		@java.lang.Override
		public java.lang.Object clone() {
			return new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Marker(markerHeight , desiredMarkerPosition , desiredMarkerIncrement , intMarkerPosition);
		}

		@java.lang.Override
		public java.lang.String toString() {
			return java.lang.String.format("index=%.0f,n=%.0f,np=%.2f,q=%.2f,dn=%.2f,prev=%d,next=%d", ((double)(index)), org.apache.commons.math3.util.Precision.round(intMarkerPosition, 0), org.apache.commons.math3.util.Precision.round(desiredMarkerPosition, 2), org.apache.commons.math3.util.Precision.round(markerHeight, 2), org.apache.commons.math3.util.Precision.round(desiredMarkerIncrement, 2), previous.index, next.index);
		}
	}

	private static class FixedCapacityList<E> extends java.util.ArrayList<E> implements java.io.Serializable {
		private static final long serialVersionUID = 2283952083075725479L;

		private final int capacity;

		public FixedCapacityList(final int fixedCapacity) {
			super(fixedCapacity);
			this.capacity = fixedCapacity;
		}

		@java.lang.Override
		public boolean add(final E e) {
			return (size()) < (capacity) ? super.add(e) : false;
		}

		@java.lang.Override
		public boolean addAll(java.util.Collection<? extends E> collection) {
			boolean isCollectionLess = (collection != null) && (((collection.size()) + (size())) <= (capacity));
			return isCollectionLess ? super.addAll(collection) : false;
		}
	}

	public static org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers newMarkers(final java.util.List<java.lang.Double> initialFive, final double p) {
		return new org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.Markers(initialFive , p);
	}

	protected interface PSquareMarkers extends java.lang.Cloneable {
		double getPercentileValue();

		java.lang.Object clone();

		double height(final int markerIndex);

		double processDataPoint(final double inputDataPoint);

		double estimate(final int index);
	}
}

