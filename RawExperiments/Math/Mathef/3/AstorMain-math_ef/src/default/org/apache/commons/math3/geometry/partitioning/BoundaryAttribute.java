package org.apache.commons.math3.geometry.partitioning;


public class BoundaryAttribute<S extends org.apache.commons.math3.geometry.Space> {
	private final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> plusOutside;

	private final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> plusInside;

	private final org.apache.commons.math3.geometry.partitioning.NodesSet<S> splitters;

	@java.lang.Deprecated
	public BoundaryAttribute(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> plusOutside ,final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> plusInside) {
		this(plusOutside, plusInside, null);
	}

	BoundaryAttribute(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> plusOutside ,final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> plusInside ,final org.apache.commons.math3.geometry.partitioning.NodesSet<S> splitters) {
		this.plusOutside = plusOutside;
		this.plusInside = plusInside;
		this.splitters = splitters;
	}

	public org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> getPlusOutside() {
		return plusOutside;
	}

	public org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> getPlusInside() {
		return plusInside;
	}

	public org.apache.commons.math3.geometry.partitioning.NodesSet<S> getSplitters() {
		return splitters;
	}
}

