package org.apache.commons.math3.geometry.partitioning;


public abstract class AbstractSubHyperplane<S extends org.apache.commons.math3.geometry.Space, T extends org.apache.commons.math3.geometry.Space> implements org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> {
	private final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyperplane;

	private final org.apache.commons.math3.geometry.partitioning.Region<T> remainingRegion;

	protected AbstractSubHyperplane(final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyperplane ,final org.apache.commons.math3.geometry.partitioning.Region<T> remainingRegion) {
		this.hyperplane = hyperplane;
		this.remainingRegion = remainingRegion;
	}

	protected abstract org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<S, T> buildNew(final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyper, final org.apache.commons.math3.geometry.partitioning.Region<T> remaining);

	public org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<S, T> copySelf() {
		return buildNew(hyperplane.copySelf(), remainingRegion);
	}

	public org.apache.commons.math3.geometry.partitioning.Hyperplane<S> getHyperplane() {
		return hyperplane;
	}

	public org.apache.commons.math3.geometry.partitioning.Region<T> getRemainingRegion() {
		return remainingRegion;
	}

	public double getSize() {
		return remainingRegion.getSize();
	}

	public org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<S, T> reunite(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> other) {
		@java.lang.SuppressWarnings(value = "unchecked")
		org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<S, T> o = ((org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<S, T>)(other));
		return buildNew(hyperplane, new org.apache.commons.math3.geometry.partitioning.RegionFactory<T>().union(remainingRegion, o.remainingRegion));
	}

	public org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<S, T> applyTransform(final org.apache.commons.math3.geometry.partitioning.Transform<S, T> transform) {
		final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> tHyperplane = transform.apply(hyperplane);
		final java.util.Map<org.apache.commons.math3.geometry.partitioning.BSPTree<T>, org.apache.commons.math3.geometry.partitioning.BSPTree<T>> map = new java.util.HashMap<org.apache.commons.math3.geometry.partitioning.BSPTree<T>, org.apache.commons.math3.geometry.partitioning.BSPTree<T>>();
		final org.apache.commons.math3.geometry.partitioning.BSPTree<T> tTree = recurseTransform(remainingRegion.getTree(false), tHyperplane, transform, map);
		for (final java.util.Map.Entry<org.apache.commons.math3.geometry.partitioning.BSPTree<T>, org.apache.commons.math3.geometry.partitioning.BSPTree<T>> entry : map.entrySet()) {
			if ((entry.getKey().getCut()) != null) {
				@java.lang.SuppressWarnings(value = "unchecked")
				org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<T> original = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<T>)(entry.getKey().getAttribute()));
				if (original != null) {
					@java.lang.SuppressWarnings(value = "unchecked")
					org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<T> transformed = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<T>)(entry.getValue().getAttribute()));
					for (final org.apache.commons.math3.geometry.partitioning.BSPTree<T> splitter : original.getSplitters()) {
						transformed.getSplitters().add(map.get(splitter));
					}
				} 
			} 
		}
		return buildNew(tHyperplane, remainingRegion.buildNew(tTree));
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<T> recurseTransform(final org.apache.commons.math3.geometry.partitioning.BSPTree<T> node, final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> transformed, final org.apache.commons.math3.geometry.partitioning.Transform<S, T> transform, final java.util.Map<org.apache.commons.math3.geometry.partitioning.BSPTree<T>, org.apache.commons.math3.geometry.partitioning.BSPTree<T>> map) {
		final org.apache.commons.math3.geometry.partitioning.BSPTree<T> transformedNode;
		if ((node.getCut()) == null) {
			transformedNode = new org.apache.commons.math3.geometry.partitioning.BSPTree<T>(node.getAttribute());
		} else {
			@java.lang.SuppressWarnings(value = "unchecked")
			org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<T> attribute = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<T>)(node.getAttribute()));
			if (attribute != null) {
				final org.apache.commons.math3.geometry.partitioning.SubHyperplane<T> tPO = (attribute.getPlusOutside()) == null ? null : transform.apply(attribute.getPlusOutside(), hyperplane, transformed);
				final org.apache.commons.math3.geometry.partitioning.SubHyperplane<T> tPI = (attribute.getPlusInside()) == null ? null : transform.apply(attribute.getPlusInside(), hyperplane, transformed);
				attribute = new org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<T>(tPO , tPI , new org.apache.commons.math3.geometry.partitioning.NodesSet<T>());
			} 
			transformedNode = new org.apache.commons.math3.geometry.partitioning.BSPTree<T>(transform.apply(node.getCut(), hyperplane, transformed) , recurseTransform(node.getPlus(), transformed, transform, map) , recurseTransform(node.getMinus(), transformed, transform, map) , attribute);
		}
		map.put(node, transformedNode);
		return transformedNode;
	}

	public abstract org.apache.commons.math3.geometry.partitioning.Side side(org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyper);

	public abstract org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<S> split(org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyper);

	public boolean isEmpty() {
		return remainingRegion.isEmpty();
	}
}

