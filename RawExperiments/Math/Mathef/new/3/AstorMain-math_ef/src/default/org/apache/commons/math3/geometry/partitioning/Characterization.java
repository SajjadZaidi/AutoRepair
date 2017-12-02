package org.apache.commons.math3.geometry.partitioning;


class Characterization<S extends org.apache.commons.math3.geometry.Space> {
	private org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> outsideTouching;

	private org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> insideTouching;

	private final org.apache.commons.math3.geometry.partitioning.NodesSet<S> outsideSplitters;

	private final org.apache.commons.math3.geometry.partitioning.NodesSet<S> insideSplitters;

	public Characterization(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node ,final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> sub) {
		outsideTouching = null;
		insideTouching = null;
		outsideSplitters = new org.apache.commons.math3.geometry.partitioning.NodesSet<S>();
		insideSplitters = new org.apache.commons.math3.geometry.partitioning.NodesSet<S>();
		characterize(node, sub, new java.util.ArrayList<org.apache.commons.math3.geometry.partitioning.BSPTree<S>>());
	}

	private void characterize(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node, final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> sub, final java.util.List<org.apache.commons.math3.geometry.partitioning.BSPTree<S>> splitters) {
		if ((node.getCut()) == null) {
			final boolean inside = ((java.lang.Boolean)(node.getAttribute()));
			if (inside) {
				addInsideTouching(sub, splitters);
			} else {
				addOutsideTouching(sub, splitters);
			}
		} else {
			final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyperplane = node.getCut().getHyperplane();
			switch (sub.side(hyperplane)) {
				case PLUS :
					characterize(node.getPlus(), sub, splitters);
					break;
				case MINUS :
					characterize(node.getMinus(), sub, splitters);
					break;
				case BOTH :
					final org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<S> split = sub.split(hyperplane);
					splitters.add(node);
					characterize(node.getPlus(), split.getPlus(), splitters);
					characterize(node.getMinus(), split.getMinus(), splitters);
					splitters.remove(((splitters.size()) - 1));
					break;
				default :
					throw new org.apache.commons.math3.exception.MathInternalError();
			}
		}
	}

	private void addOutsideTouching(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> sub, final java.util.List<org.apache.commons.math3.geometry.partitioning.BSPTree<S>> splitters) {
		if ((outsideTouching) == null) {
			outsideTouching = sub;
		} else {
			outsideTouching = outsideTouching.reunite(sub);
		}
		outsideSplitters.addAll(splitters);
	}

	private void addInsideTouching(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> sub, final java.util.List<org.apache.commons.math3.geometry.partitioning.BSPTree<S>> splitters) {
		if ((insideTouching) == null) {
			insideTouching = sub;
		} else {
			insideTouching = insideTouching.reunite(sub);
		}
		insideSplitters.addAll(splitters);
	}

	public boolean touchOutside() {
		return ((outsideTouching) != null) && (!(outsideTouching.isEmpty()));
	}

	public org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> outsideTouching() {
		return outsideTouching;
	}

	public org.apache.commons.math3.geometry.partitioning.NodesSet<S> getOutsideSplitters() {
		return outsideSplitters;
	}

	public boolean touchInside() {
		return ((insideTouching) != null) && (!(insideTouching.isEmpty()));
	}

	public org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> insideTouching() {
		return insideTouching;
	}

	public org.apache.commons.math3.geometry.partitioning.NodesSet<S> getInsideSplitters() {
		return insideSplitters;
	}
}

