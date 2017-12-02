package org.apache.commons.math3.geometry.partitioning;


public class NodesSet<S extends org.apache.commons.math3.geometry.Space> implements java.lang.Iterable<org.apache.commons.math3.geometry.partitioning.BSPTree<S>> {
	private java.util.List<org.apache.commons.math3.geometry.partitioning.BSPTree<S>> list;

	public NodesSet() {
		list = new java.util.ArrayList<org.apache.commons.math3.geometry.partitioning.BSPTree<S>>();
	}

	public void add(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node) {
		for (final org.apache.commons.math3.geometry.partitioning.BSPTree<S> existing : list) {
			if (node == existing) {
				return ;
			} 
		}
		list.add(node);
	}

	public void addAll(final java.lang.Iterable<org.apache.commons.math3.geometry.partitioning.BSPTree<S>> iterator) {
		for (final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node : iterator) {
			add(node);
		}
	}

	public java.util.Iterator<org.apache.commons.math3.geometry.partitioning.BSPTree<S>> iterator() {
		return list.iterator();
	}
}

