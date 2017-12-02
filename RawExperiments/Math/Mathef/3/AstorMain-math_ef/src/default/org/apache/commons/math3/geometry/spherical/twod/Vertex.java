package org.apache.commons.math3.geometry.spherical.twod;


public class Vertex {
	private final org.apache.commons.math3.geometry.spherical.twod.S2Point location;

	private org.apache.commons.math3.geometry.spherical.twod.Edge incoming;

	private org.apache.commons.math3.geometry.spherical.twod.Edge outgoing;

	private final java.util.List<org.apache.commons.math3.geometry.spherical.twod.Circle> circles;

	Vertex(final org.apache.commons.math3.geometry.spherical.twod.S2Point location) {
		this.location = location;
		org.apache.commons.math3.geometry.spherical.twod.Vertex.this.incoming = null;
		org.apache.commons.math3.geometry.spherical.twod.Vertex.this.outgoing = null;
		this.circles = new java.util.ArrayList<org.apache.commons.math3.geometry.spherical.twod.Circle>();
	}

	public org.apache.commons.math3.geometry.spherical.twod.S2Point getLocation() {
		return location;
	}

	void bindWith(final org.apache.commons.math3.geometry.spherical.twod.Circle circle) {
		circles.add(circle);
	}

	org.apache.commons.math3.geometry.spherical.twod.Circle sharedCircleWith(final org.apache.commons.math3.geometry.spherical.twod.Vertex vertex) {
		for (final org.apache.commons.math3.geometry.spherical.twod.Circle circle1 : circles) {
			for (final org.apache.commons.math3.geometry.spherical.twod.Circle circle2 : vertex.circles) {
				if (circle1 == circle2) {
					return circle1;
				} 
			}
		}
		return null;
	}

	void setIncoming(final org.apache.commons.math3.geometry.spherical.twod.Edge incoming) {
		org.apache.commons.math3.geometry.spherical.twod.Vertex.this.incoming = incoming;
		bindWith(incoming.getCircle());
	}

	public org.apache.commons.math3.geometry.spherical.twod.Edge getIncoming() {
		return incoming;
	}

	void setOutgoing(final org.apache.commons.math3.geometry.spherical.twod.Edge outgoing) {
		org.apache.commons.math3.geometry.spherical.twod.Vertex.this.outgoing = outgoing;
		bindWith(outgoing.getCircle());
	}

	public org.apache.commons.math3.geometry.spherical.twod.Edge getOutgoing() {
		return outgoing;
	}
}

