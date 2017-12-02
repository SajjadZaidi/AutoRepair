package org.apache.commons.math3.geometry.spherical.twod;


public class Edge {
	private final org.apache.commons.math3.geometry.spherical.twod.Vertex start;

	private org.apache.commons.math3.geometry.spherical.twod.Vertex end;

	private final double length;

	private final org.apache.commons.math3.geometry.spherical.twod.Circle circle;

	Edge(final org.apache.commons.math3.geometry.spherical.twod.Vertex start ,final org.apache.commons.math3.geometry.spherical.twod.Vertex end ,final double length ,final org.apache.commons.math3.geometry.spherical.twod.Circle circle) {
		this.start = start;
		org.apache.commons.math3.geometry.spherical.twod.Edge.this.end = end;
		this.length = length;
		this.circle = circle;
		start.setOutgoing(org.apache.commons.math3.geometry.spherical.twod.Edge.this);
		end.setIncoming(org.apache.commons.math3.geometry.spherical.twod.Edge.this);
	}

	public org.apache.commons.math3.geometry.spherical.twod.Vertex getStart() {
		return start;
	}

	public org.apache.commons.math3.geometry.spherical.twod.Vertex getEnd() {
		return end;
	}

	public double getLength() {
		return length;
	}

	public org.apache.commons.math3.geometry.spherical.twod.Circle getCircle() {
		return circle;
	}

	public org.apache.commons.math3.geometry.euclidean.threed.Vector3D getPointAt(final double alpha) {
		return circle.getPointAt((alpha + (circle.getPhase(start.getLocation().getVector()))));
	}

	void setNextEdge(final org.apache.commons.math3.geometry.spherical.twod.Edge next) {
		end = next.getStart();
		end.setIncoming(org.apache.commons.math3.geometry.spherical.twod.Edge.this);
		end.bindWith(getCircle());
	}

	void split(final org.apache.commons.math3.geometry.spherical.twod.Circle splitCircle, final java.util.List<org.apache.commons.math3.geometry.spherical.twod.Edge> outsideList, final java.util.List<org.apache.commons.math3.geometry.spherical.twod.Edge> insideList) {
		final double edgeStart = circle.getPhase(start.getLocation().getVector());
		final org.apache.commons.math3.geometry.spherical.oned.Arc arc = circle.getInsideArc(splitCircle);
		final double arcRelativeStart = (org.apache.commons.math3.util.MathUtils.normalizeAngle(arc.getInf(), (edgeStart + (org.apache.commons.math3.util.FastMath.PI)))) - edgeStart;
		final double arcRelativeEnd = arcRelativeStart + (arc.getSize());
		final double unwrappedEnd = arcRelativeEnd - (org.apache.commons.math3.util.MathUtils.TWO_PI);
		final double tolerance = circle.getTolerance();
		org.apache.commons.math3.geometry.spherical.twod.Vertex previousVertex = start;
		if (unwrappedEnd >= ((length) - tolerance)) {
			insideList.add(org.apache.commons.math3.geometry.spherical.twod.Edge.this);
		} else {
			double alreadyManagedLength = 0;
			if (unwrappedEnd >= 0) {
				previousVertex = addSubEdge(previousVertex, new org.apache.commons.math3.geometry.spherical.twod.Vertex(new org.apache.commons.math3.geometry.spherical.twod.S2Point(circle.getPointAt((edgeStart + unwrappedEnd)))), unwrappedEnd, insideList, splitCircle);
				alreadyManagedLength = unwrappedEnd;
			} 
			if (arcRelativeStart >= ((length) - tolerance)) {
				if (unwrappedEnd >= 0) {
					previousVertex = addSubEdge(previousVertex, end, ((length) - alreadyManagedLength), outsideList, splitCircle);
				} else {
					outsideList.add(org.apache.commons.math3.geometry.spherical.twod.Edge.this);
				}
			} else {
				previousVertex = addSubEdge(previousVertex, new org.apache.commons.math3.geometry.spherical.twod.Vertex(new org.apache.commons.math3.geometry.spherical.twod.S2Point(circle.getPointAt((edgeStart + arcRelativeStart)))), (arcRelativeStart - alreadyManagedLength), outsideList, splitCircle);
				alreadyManagedLength = arcRelativeStart;
				if (arcRelativeEnd >= ((length) - tolerance)) {
					previousVertex = addSubEdge(previousVertex, end, ((length) - alreadyManagedLength), insideList, splitCircle);
				} else {
					previousVertex = addSubEdge(previousVertex, new org.apache.commons.math3.geometry.spherical.twod.Vertex(new org.apache.commons.math3.geometry.spherical.twod.S2Point(circle.getPointAt((edgeStart + arcRelativeStart)))), (arcRelativeStart - alreadyManagedLength), insideList, splitCircle);
					alreadyManagedLength = arcRelativeStart;
					previousVertex = addSubEdge(previousVertex, end, ((length) - alreadyManagedLength), outsideList, splitCircle);
				}
			}
		}
	}

	private org.apache.commons.math3.geometry.spherical.twod.Vertex addSubEdge(final org.apache.commons.math3.geometry.spherical.twod.Vertex subStart, final org.apache.commons.math3.geometry.spherical.twod.Vertex subEnd, final double subLength, final java.util.List<org.apache.commons.math3.geometry.spherical.twod.Edge> list, final org.apache.commons.math3.geometry.spherical.twod.Circle splitCircle) {
		if (subLength <= (circle.getTolerance())) {
			return subStart;
		} 
		subEnd.bindWith(splitCircle);
		final org.apache.commons.math3.geometry.spherical.twod.Edge edge = new org.apache.commons.math3.geometry.spherical.twod.Edge(subStart , subEnd , subLength , circle);
		list.add(edge);
		return subEnd;
	}
}

