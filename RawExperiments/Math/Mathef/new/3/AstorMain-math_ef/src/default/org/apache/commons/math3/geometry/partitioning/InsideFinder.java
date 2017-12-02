package org.apache.commons.math3.geometry.partitioning;


class InsideFinder<S extends org.apache.commons.math3.geometry.Space> {
	private final org.apache.commons.math3.geometry.partitioning.Region<S> region;

	private boolean plusFound;

	private boolean minusFound;

	public InsideFinder(final org.apache.commons.math3.geometry.partitioning.Region<S> region) {
		this.region = region;
		plusFound = false;
		minusFound = false;
	}

	public void recurseSides(final org.apache.commons.math3.geometry.partitioning.BSPTree<S> node, final org.apache.commons.math3.geometry.partitioning.SubHyperplane<S> sub) {
		if ((node.getCut()) == null) {
			if (((java.lang.Boolean)(node.getAttribute()))) {
				plusFound = true;
				minusFound = true;
			} 
			return ;
		} 
		final org.apache.commons.math3.geometry.partitioning.Hyperplane<S> hyperplane = node.getCut().getHyperplane();
		switch (sub.side(hyperplane)) {
			case PLUS :
				if ((node.getCut().side(sub.getHyperplane())) == (org.apache.commons.math3.geometry.partitioning.Side.PLUS)) {
					if (!(region.isEmpty(node.getMinus()))) {
						plusFound = true;
					} 
				} else {
					if (!(region.isEmpty(node.getMinus()))) {
						minusFound = true;
					} 
				}
				if (!((plusFound) && (minusFound))) {
					recurseSides(node.getPlus(), sub);
				} 
				break;
			case MINUS :
				if ((node.getCut().side(sub.getHyperplane())) == (org.apache.commons.math3.geometry.partitioning.Side.PLUS)) {
					if (!(region.isEmpty(node.getPlus()))) {
						plusFound = true;
					} 
				} else {
					if (!(region.isEmpty(node.getPlus()))) {
						minusFound = true;
					} 
				}
				if (!((plusFound) && (minusFound))) {
					recurseSides(node.getMinus(), sub);
				} 
				break;
			case BOTH :
				final org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane<S> split = sub.split(hyperplane);
				recurseSides(node.getPlus(), split.getPlus());
				if (!((plusFound) && (minusFound))) {
					recurseSides(node.getMinus(), split.getMinus());
				} 
				break;
			default :
				if (node.getCut().getHyperplane().sameOrientationAs(sub.getHyperplane())) {
					if (((node.getPlus().getCut()) != null) || ((java.lang.Boolean)(node.getPlus().getAttribute()))) {
						plusFound = true;
					} 
					if (((node.getMinus().getCut()) != null) || ((java.lang.Boolean)(node.getMinus().getAttribute()))) {
						minusFound = true;
					} 
				} else {
					if (((node.getPlus().getCut()) != null) || ((java.lang.Boolean)(node.getPlus().getAttribute()))) {
						minusFound = true;
					} 
					if (((node.getMinus().getCut()) != null) || ((java.lang.Boolean)(node.getMinus().getAttribute()))) {
						plusFound = true;
					} 
				}
		}
	}

	public boolean plusFound() {
		return plusFound;
	}

	public boolean minusFound() {
		return minusFound;
	}
}

