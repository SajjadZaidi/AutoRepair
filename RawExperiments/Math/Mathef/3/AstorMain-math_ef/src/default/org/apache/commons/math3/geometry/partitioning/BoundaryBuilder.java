package org.apache.commons.math3.geometry.partitioning;


class BoundaryBuilder<S extends org.apache.commons.math3.geometry.Space> implements org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor<S> {
	public org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order visitOrder(org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
		return org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.PLUS_MINUS_SUB;
	}

	public void visitInternalNode(org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
		org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> plusOutside = null;
		org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> plusInside = null;
		org.apache.commons.math3.geometry.partitioning.NodesSet<S> splitters = null;
		final org.apache.commons.math3.geometry.partitioning.Characterization<S> plusChar = new org.apache.commons.math3.geometry.partitioning.Characterization<S>(node.getPlus() , node.getCut().copySelf());
		if (plusChar.touchOutside()) {
			final org.apache.commons.math3.geometry.partitioning.Characterization<S> minusChar = new org.apache.commons.math3.geometry.partitioning.Characterization<S>(node.getMinus() , plusChar.outsideTouching());
			if (minusChar.touchInside()) {
				plusOutside = minusChar.insideTouching();
				splitters = new org.apache.commons.math3.geometry.partitioning.NodesSet<S>();
				splitters.addAll(minusChar.getInsideSplitters());
				splitters.addAll(plusChar.getOutsideSplitters());
			} 
		} 
		if (plusChar.touchInside()) {
			final org.apache.commons.math3.geometry.partitioning.Characterization<S> minusChar = new org.apache.commons.math3.geometry.partitioning.Characterization<S>(node.getMinus() , plusChar.insideTouching());
			if (minusChar.touchOutside()) {
				plusInside = minusChar.outsideTouching();
				if (splitters == null) {
					splitters = new org.apache.commons.math3.geometry.partitioning.NodesSet<S>();
				} 
				splitters.addAll(minusChar.getOutsideSplitters());
				splitters.addAll(plusChar.getInsideSplitters());
			} 
		} 
		if (splitters != null) {
			for (org.apache.commons.math3.geometry.partitioning.BSPTree<S> up = node.getParent() ; up != null ; up = up.getParent()) {
				splitters.add(up);
			}
		} 
		node.setAttribute(new org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S>(plusOutside , plusInside , splitters));
	}

	public void visitLeafNode(org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
	}
}

