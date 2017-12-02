package org.apache.commons.math3.fitting;


public class WeightedObservedPoints implements java.io.Serializable {
	private static final long serialVersionUID = 20130813L;

	private final java.util.List<org.apache.commons.math3.fitting.WeightedObservedPoint> observations = new java.util.ArrayList<org.apache.commons.math3.fitting.WeightedObservedPoint>();

	public void add(double x, double y) {
		add(1.0, x, y);
	}

	public void add(double weight, double x, double y) {
		observations.add(new org.apache.commons.math3.fitting.WeightedObservedPoint(weight , x , y));
	}

	public void add(org.apache.commons.math3.fitting.WeightedObservedPoint observed) {
		observations.add(observed);
	}

	public java.util.List<org.apache.commons.math3.fitting.WeightedObservedPoint> toList() {
		return new java.util.ArrayList<org.apache.commons.math3.fitting.WeightedObservedPoint>(observations);
	}

	public void clear() {
		observations.clear();
	}
}

