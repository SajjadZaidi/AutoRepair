package org.apache.commons.math3.geometry.partitioning;


public class BoundaryProjection<S extends org.apache.commons.math3.geometry.Space> {
	private final org.apache.commons.math3.geometry.Point<S> original;

	private final org.apache.commons.math3.geometry.Point<S> projected;

	private final double offset;

	public BoundaryProjection(final org.apache.commons.math3.geometry.Point<S> original ,final org.apache.commons.math3.geometry.Point<S> projected ,final double offset) {
		this.original = original;
		this.projected = projected;
		this.offset = offset;
	}

	public org.apache.commons.math3.geometry.Point<S> getOriginal() {
		return original;
	}

	public org.apache.commons.math3.geometry.Point<S> getProjected() {
		return projected;
	}

	public double getOffset() {
		return offset;
	}
}

