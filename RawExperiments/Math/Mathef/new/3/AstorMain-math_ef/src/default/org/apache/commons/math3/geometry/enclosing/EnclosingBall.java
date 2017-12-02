package org.apache.commons.math3.geometry.enclosing;


public class EnclosingBall<S extends org.apache.commons.math3.geometry.Space, P extends org.apache.commons.math3.geometry.Point<S>> implements java.io.Serializable {
	private static final long serialVersionUID = 20140126L;

	private final P center;

	private final double radius;

	private final P[] support;

	public EnclosingBall(final P center ,final double radius ,final P... support) {
		this.center = center;
		this.radius = radius;
		this.support = support.clone();
	}

	public P getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	public P[] getSupport() {
		return support.clone();
	}

	public int getSupportSize() {
		return support.length;
	}

	public boolean contains(final P point) {
		return (point.distance(center)) <= (radius);
	}

	public boolean contains(final P point, final double margin) {
		return (point.distance(center)) <= ((radius) + margin);
	}
}

