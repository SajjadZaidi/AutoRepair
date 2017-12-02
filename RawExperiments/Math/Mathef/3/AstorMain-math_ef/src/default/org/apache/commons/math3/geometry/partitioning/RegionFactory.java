package org.apache.commons.math3.geometry.partitioning;


public class RegionFactory<S extends org.apache.commons.math3.geometry.Space> {
	private final org.apache.commons.math3.geometry.partitioning.RegionFactory<S>.NodesCleaner nodeCleaner;

	public RegionFactory() {
		nodeCleaner = new NodesCleaner();
	}

	public org.apache.commons.math3.geometry.partitioning.Region<S> buildConvex(final org.apache.commons.math3.geometry.partitioning.Hyperplane<S>... hyperplanes) {
		if ((hyperplanes == null) || ((hyperplanes.length) == 0)) {
			return null;
		} 
		final org.apache.commons.math3.geometry.partitioning.Region<S> region = hyperplanes[0].wholeSpace();
		org.apache.commons.math3.geometry.partitioning.BSPTree<S> node = region.getTree(false);
		node.setAttribute(java.lang.Boolean.TRUE);
		for (final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyperplane : hyperplanes) {
			if (node.insertCut(hyperplane)) {
				node.setAttribute(null);
				node.getPlus().setAttribute(java.lang.Boolean.FALSE);
				node = node.getMinus();
				node.setAttribute(java.lang.Boolean.TRUE);
			} 
		}
		return region;
	}

	public org.apache.commons.math3.geometry.partitioning.Region<S> union(final org.apache.commons.math3.geometry.partitioning.Region<S> region1, final org.apache.commons.math3.geometry.partitioning.Region<S> region2) {
		final org.apache.commons.math3.geometry.partitioning.BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new UnionMerger());
		tree.visit(nodeCleaner);
		return region1.buildNew(tree);
	}

	public org.apache.commons.math3.geometry.partitioning.Region<S> intersection(final org.apache.commons.math3.geometry.partitioning.Region<S> region1, final org.apache.commons.math3.geometry.partitioning.Region<S> region2) {
		final org.apache.commons.math3.geometry.partitioning.BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new IntersectionMerger());
		tree.visit(nodeCleaner);
		return region1.buildNew(tree);
	}

	public org.apache.commons.math3.geometry.partitioning.Region<S> xor(final org.apache.commons.math3.geometry.partitioning.Region<S> region1, final org.apache.commons.math3.geometry.partitioning.Region<S> region2) {
		final org.apache.commons.math3.geometry.partitioning.BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new XorMerger());
		tree.visit(nodeCleaner);
		return region1.buildNew(tree);
	}

	public org.apache.commons.math3.geometry.partitioning.Region<S> difference(final org.apache.commons.math3.geometry.partitioning.Region<S> region1, final org.apache.commons.math3.geometry.partitioning.Region<S> region2) {
		final org.apache.commons.math3.geometry.partitioning.BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new DifferenceMerger(region1 , region2));
		tree.visit(nodeCleaner);
		return region1.buildNew(tree);
	}

	public org.apache.commons.math3.geometry.partitioning.Region<S> getComplement(final org.apache.commons.math3.geometry.partitioning.Region<S> region) {
		return region.buildNew(recurseComplement(region.getTree(false)));
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<S> recurseComplement(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
		final java.util.Map<org.apache.commons.math3.geometry.partitioning.BSPTree<S>, org.apache.commons.math3.geometry.partitioning.BSPTree<S>> map = new java.util.HashMap<org.apache.commons.math3.geometry.partitioning.BSPTree<S>, org.apache.commons.math3.geometry.partitioning.BSPTree<S>>();
		final org.apache.commons.math3.geometry.partitioning.BSPTree<S> transformedTree = recurseComplement(node, map);
		for (final java.util.Map.Entry<org.apache.commons.math3.geometry.partitioning.BSPTree<S>, org.apache.commons.math3.geometry.partitioning.BSPTree<S>> entry : map.entrySet()) {
			if ((entry.getKey().getCut()) != null) {
				@java.lang.SuppressWarnings(value = "unchecked")
				org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S> original = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S>)(entry.getKey().getAttribute()));
				if (original != null) {
					@java.lang.SuppressWarnings(value = "unchecked")
					org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S> transformed = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S>)(entry.getValue().getAttribute()));
					for (final org.apache.commons.math3.geometry.partitioning.BSPTree<S> splitter : original.getSplitters()) {
						transformed.getSplitters().add(map.get(splitter));
					}
				} 
			} 
		}
		return transformedTree;
	}

	private org.apache.commons.math3.geometry.partitioning.BSPTree<S> recurseComplement(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node, final java.util.Map<org.apache.commons.math3.geometry.partitioning.BSPTree<S>, org.apache.commons.math3.geometry.partitioning.BSPTree<S>> map) {
		final org.apache.commons.math3.geometry.partitioning.BSPTree<S> transformedNode;
		if ((node.getCut()) == null) {
			transformedNode = new org.apache.commons.math3.geometry.partitioning.BSPTree<S>((((java.lang.Boolean)(node.getAttribute())) ? java.lang.Boolean.FALSE : java.lang.Boolean.TRUE));
		} else {
			@java.lang.SuppressWarnings(value = "unchecked")
			org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S> attribute = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S>)(node.getAttribute()));
			if (attribute != null) {
				final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> plusOutside = (attribute.getPlusInside()) == null ? null : attribute.getPlusInside().copySelf();
				final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> plusInside = (attribute.getPlusOutside()) == null ? null : attribute.getPlusOutside().copySelf();
				attribute = new org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<S>(plusOutside , plusInside , new org.apache.commons.math3.geometry.partitioning.NodesSet<S>());
			} 
			transformedNode = new org.apache.commons.math3.geometry.partitioning.BSPTree<S>(node.getCut().copySelf() , recurseComplement(node.getPlus(), map) , recurseComplement(node.getMinus(), map) , attribute);
		}
		map.put(node, transformedNode);
		return transformedNode;
	}

	private class UnionMerger implements org.apache.commons.math3.geometry.partitioning.BSPTree.LeafMerger<S> {
		public org.apache.commons.math3.geometry.partitioning.BSPTree<S> merge(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> leaf, final org.apache.commons.math3.geometry.partitioning.BSPTree<S> tree, final org.apache.commons.math3.geometry.partitioning.BSPTree<S> parentTree, final boolean isPlusChild, final boolean leafFromInstance) {
			if (((java.lang.Boolean)(leaf.getAttribute()))) {
				leaf.insertInTree(parentTree, isPlusChild, new VanishingToLeaf(true));
				return leaf;
			} 
			tree.insertInTree(parentTree, isPlusChild, new VanishingToLeaf(false));
			return tree;
		}
	}

	private class IntersectionMerger implements org.apache.commons.math3.geometry.partitioning.BSPTree.LeafMerger<S> {
		public org.apache.commons.math3.geometry.partitioning.BSPTree<S> merge(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> leaf, final org.apache.commons.math3.geometry.partitioning.BSPTree<S> tree, final org.apache.commons.math3.geometry.partitioning.BSPTree<S> parentTree, final boolean isPlusChild, final boolean leafFromInstance) {
			if (((java.lang.Boolean)(leaf.getAttribute()))) {
				tree.insertInTree(parentTree, isPlusChild, new VanishingToLeaf(true));
				return tree;
			} 
			leaf.insertInTree(parentTree, isPlusChild, new VanishingToLeaf(false));
			return leaf;
		}
	}

	private class XorMerger implements org.apache.commons.math3.geometry.partitioning.BSPTree.LeafMerger<S> {
		public org.apache.commons.math3.geometry.partitioning.BSPTree<S> merge(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> leaf, final org.apache.commons.math3.geometry.partitioning.BSPTree<S> tree, final org.apache.commons.math3.geometry.partitioning.BSPTree<S> parentTree, final boolean isPlusChild, final boolean leafFromInstance) {
			org.apache.commons.math3.geometry.partitioning.BSPTree<S> t = tree;
			if (((java.lang.Boolean)(leaf.getAttribute()))) {
				t = recurseComplement(t);
			} 
			t.insertInTree(parentTree, isPlusChild, new VanishingToLeaf(true));
			return t;
		}
	}

	private class DifferenceMerger implements org.apache.commons.math3.geometry.partitioning.BSPTree.LeafMerger<S> , org.apache.commons.math3.geometry.partitioning.BSPTree.VanishingCutHandler<S> {
		private final org.apache.commons.math3.geometry.partitioning.Region<S> region1;

		private final org.apache.commons.math3.geometry.partitioning.Region<S> region2;

		public DifferenceMerger(final org.apache.commons.math3.geometry.partitioning.Region<S> region1 ,final org.apache.commons.math3.geometry.partitioning.Region<S> region2) {
			this.region1 = region1.copySelf();
			this.region2 = region2.copySelf();
		}

		public org.apache.commons.math3.geometry.partitioning.BSPTree<S> merge(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> leaf, final org.apache.commons.math3.geometry.partitioning.BSPTree<S> tree, final org.apache.commons.math3.geometry.partitioning.BSPTree<S> parentTree, final boolean isPlusChild, final boolean leafFromInstance) {
			if (((java.lang.Boolean)(leaf.getAttribute()))) {
				final org.apache.commons.math3.geometry.partitioning.BSPTree<S> argTree = recurseComplement((leafFromInstance ? tree : leaf));
				argTree.insertInTree(parentTree, isPlusChild, org.apache.commons.math3.geometry.partitioning.RegionFactory.DifferenceMerger.this);
				return argTree;
			} 
			final org.apache.commons.math3.geometry.partitioning.BSPTree<S> instanceTree = leafFromInstance ? leaf : tree;
			instanceTree.insertInTree(parentTree, isPlusChild, org.apache.commons.math3.geometry.partitioning.RegionFactory.DifferenceMerger.this);
			return instanceTree;
		}

		public org.apache.commons.math3.geometry.partitioning.BSPTree<S> fixNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
			final org.apache.commons.math3.geometry.partitioning.BSPTree<S> cell = node.pruneAroundConvexCell(java.lang.Boolean.TRUE, java.lang.Boolean.FALSE, null);
			final org.apache.commons.math3.geometry.partitioning.Region<S> r = region1.buildNew(cell);
			final org.apache.commons.math3.geometry.Point<S> p = r.getBarycenter();
			return new org.apache.commons.math3.geometry.partitioning.BSPTree<S>((((region1.checkPoint(p)) == (org.apache.commons.math3.geometry.partitioning.Region.Location.INSIDE)) && ((region2.checkPoint(p)) == (org.apache.commons.math3.geometry.partitioning.Region.Location.OUTSIDE))));
		}
	}

	private class NodesCleaner implements org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor<S> {
		public org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order visitOrder(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
			return org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.PLUS_SUB_MINUS;
		}

		public void visitInternalNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
			node.setAttribute(null);
		}

		public void visitLeafNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
		}
	}

	private class VanishingToLeaf implements org.apache.commons.math3.geometry.partitioning.BSPTree.VanishingCutHandler<S> {
		private final boolean inside;

		public VanishingToLeaf(final boolean inside) {
			this.inside = inside;
		}

		public org.apache.commons.math3.geometry.partitioning.BSPTree<S> fixNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
			if (node.getPlus().getAttribute().equals(node.getMinus().getAttribute())) {
				return new org.apache.commons.math3.geometry.partitioning.BSPTree<S>(node.getPlus().getAttribute());
			} else {
				return new org.apache.commons.math3.geometry.partitioning.BSPTree<S>(inside);
			}
		}
	}
}

