package org.apache.commons.math3.geometry.euclidean.twod;


public class PolygonsSet extends org.apache.commons.math3.geometry.partitioning.AbstractRegion<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> {
	private static final double DEFAULT_TOLERANCE = 1.0E-10;

	private org.apache.commons.math3.geometry.euclidean.twod.Vector2D[][] vertices;

	public PolygonsSet(final double tolerance) {
		super(tolerance);
	}

	public PolygonsSet(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> tree ,final double tolerance) {
		super(tree, tolerance);
	}

	public PolygonsSet(final java.util.Collection<org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>> boundary ,final double tolerance) {
		super(boundary, tolerance);
	}

	public PolygonsSet(final double xMin ,final double xMax ,final double yMin ,final double yMax ,final double tolerance) {
		super(org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.boxBoundary(xMin, xMax, yMin, yMax, tolerance), tolerance);
	}

	public PolygonsSet(final double hyperplaneThickness ,final org.apache.commons.math3.geometry.euclidean.twod.Vector2D... vertices) {
		super(org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.verticesToTree(hyperplaneThickness, vertices), hyperplaneThickness);
	}

	@java.lang.Deprecated
	public PolygonsSet() {
		this(org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.DEFAULT_TOLERANCE);
	}

	@java.lang.Deprecated
	public PolygonsSet(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> tree) {
		this(tree, org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.DEFAULT_TOLERANCE);
	}

	@java.lang.Deprecated
	public PolygonsSet(final java.util.Collection<org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>> boundary) {
		this(boundary, org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.DEFAULT_TOLERANCE);
	}

	@java.lang.Deprecated
	public PolygonsSet(final double xMin ,final double xMax ,final double yMin ,final double yMax) {
		this(xMin, xMax, yMin, yMax, org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.DEFAULT_TOLERANCE);
	}

	private static org.apache.commons.math3.geometry.euclidean.twod.Line[] boxBoundary(final double xMin, final double xMax, final double yMin, final double yMax, final double tolerance) {
		if ((xMin >= (xMax - tolerance)) || (yMin >= (yMax - tolerance))) {
			return null;
		} 
		final org.apache.commons.math3.geometry.euclidean.twod.Vector2D minMin = new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(xMin , yMin);
		final org.apache.commons.math3.geometry.euclidean.twod.Vector2D minMax = new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(xMin , yMax);
		final org.apache.commons.math3.geometry.euclidean.twod.Vector2D maxMin = new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(xMax , yMin);
		final org.apache.commons.math3.geometry.euclidean.twod.Vector2D maxMax = new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(xMax , yMax);
		return new org.apache.commons.math3.geometry.euclidean.twod.Line[]{ new org.apache.commons.math3.geometry.euclidean.twod.Line(minMin , maxMin , tolerance) , new org.apache.commons.math3.geometry.euclidean.twod.Line(maxMin , maxMax , tolerance) , new org.apache.commons.math3.geometry.euclidean.twod.Line(maxMax , minMax , tolerance) , new org.apache.commons.math3.geometry.euclidean.twod.Line(minMax , minMin , tolerance) };
	}

	private static org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> verticesToTree(final double hyperplaneThickness, final org.apache.commons.math3.geometry.euclidean.twod.Vector2D... vertices) {
		final int n = vertices.length;
		if (n == 0) {
			return new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>(java.lang.Boolean.TRUE);
		} 
		final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex[] vArray = new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex[n];
		for (int i = 0 ; i < n ; ++i) {
			vArray[i] = new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex(vertices[i]);
		}
		java.util.List<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge> edges = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge>(n);
		for (int i = 0 ; i < n ; ++i) {
			final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex start = vArray[i];
			final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex end = vArray[((i + 1) % n)];
			org.apache.commons.math3.geometry.euclidean.twod.Line line = start.sharedLineWith(end);
			if (line == null) {
				line = new org.apache.commons.math3.geometry.euclidean.twod.Line(start.getLocation() , end.getLocation() , hyperplaneThickness);
			} 
			edges.add(new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge(start , end , line));
			for (final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex vertex : vArray) {
				if (((vertex != start) && (vertex != end)) && ((org.apache.commons.math3.util.FastMath.abs(line.getOffset(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(vertex.getLocation()))))) <= hyperplaneThickness)) {
					vertex.bindWith(line);
				} 
			}
		}
		final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> tree = new org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>();
		org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.insertEdges(hyperplaneThickness, tree, edges);
		return tree;
	}

	private static void insertEdges(final double hyperplaneThickness, final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> node, final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge> edges) {
		int index = 0;
		org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge inserted = null;
		while ((inserted == null) && (index < (edges.size()))) {
			inserted = edges.get((index++));
			if ((inserted.getNode()) == null) {
				if (node.insertCut(inserted.getLine())) {
					inserted.setNode(node);
				} else {
					inserted = null;
				}
			} else {
				inserted = null;
			}
		}
		if (inserted == null) {
			final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> parent = node.getParent();
			if ((parent == null) || (node == (parent.getMinus()))) {
				node.setAttribute(java.lang.Boolean.TRUE);
			} else {
				node.setAttribute(java.lang.Boolean.FALSE);
			}
			return ;
		} 
		final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge> plusList = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge>();
		final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge> minusList = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge>();
		for (final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge edge : edges) {
			if (edge != inserted) {
				final double startOffset = inserted.getLine().getOffset(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(edge.getStart().getLocation())));
				final double endOffset = inserted.getLine().getOffset(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(edge.getEnd().getLocation())));
				org.apache.commons.math3.geometry.partitioning.Side startSide = (org.apache.commons.math3.util.FastMath.abs(startOffset)) <= hyperplaneThickness ? org.apache.commons.math3.geometry.partitioning.Side.HYPER : startOffset < 0 ? org.apache.commons.math3.geometry.partitioning.Side.MINUS : org.apache.commons.math3.geometry.partitioning.Side.PLUS;
				org.apache.commons.math3.geometry.partitioning.Side endSide = (org.apache.commons.math3.util.FastMath.abs(endOffset)) <= hyperplaneThickness ? org.apache.commons.math3.geometry.partitioning.Side.HYPER : endOffset < 0 ? org.apache.commons.math3.geometry.partitioning.Side.MINUS : org.apache.commons.math3.geometry.partitioning.Side.PLUS;
				switch (startSide) {
					case PLUS :
						if (endSide == (org.apache.commons.math3.geometry.partitioning.Side.MINUS)) {
							final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex splitPoint = edge.split(inserted.getLine());
							minusList.add(splitPoint.getOutgoing());
							plusList.add(splitPoint.getIncoming());
						} else {
							plusList.add(edge);
						}
						break;
					case MINUS :
						if (endSide == (org.apache.commons.math3.geometry.partitioning.Side.PLUS)) {
							final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex splitPoint = edge.split(inserted.getLine());
							minusList.add(splitPoint.getIncoming());
							plusList.add(splitPoint.getOutgoing());
						} else {
							minusList.add(edge);
						}
						break;
					default :
						if (endSide == (org.apache.commons.math3.geometry.partitioning.Side.PLUS)) {
							plusList.add(edge);
						} else if (endSide == (org.apache.commons.math3.geometry.partitioning.Side.MINUS)) {
							minusList.add(edge);
						} 
						break;
				}
			} 
		}
		if (!(plusList.isEmpty())) {
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.insertEdges(hyperplaneThickness, node.getPlus(), plusList);
		} else {
			node.getPlus().setAttribute(java.lang.Boolean.FALSE);
		}
		if (!(minusList.isEmpty())) {
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.insertEdges(hyperplaneThickness, node.getMinus(), minusList);
		} else {
			node.getMinus().setAttribute(java.lang.Boolean.TRUE);
		}
	}

	private static class Vertex {
		private final org.apache.commons.math3.geometry.euclidean.twod.Vector2D location;

		private org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge incoming;

		private org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge outgoing;

		private final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Line> lines;

		public Vertex(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D location) {
			this.location = location;
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex.this.incoming = null;
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex.this.outgoing = null;
			this.lines = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.twod.Line>();
		}

		public org.apache.commons.math3.geometry.euclidean.twod.Vector2D getLocation() {
			return location;
		}

		public void bindWith(final org.apache.commons.math3.geometry.euclidean.twod.Line line) {
			lines.add(line);
		}

		public org.apache.commons.math3.geometry.euclidean.twod.Line sharedLineWith(final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex vertex) {
			for (final org.apache.commons.math3.geometry.euclidean.twod.Line line1 : lines) {
				for (final org.apache.commons.math3.geometry.euclidean.twod.Line line2 : vertex.lines) {
					if (line1 == line2) {
						return line1;
					} 
				}
			}
			return null;
		}

		public void setIncoming(final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge incoming) {
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex.this.incoming = incoming;
			bindWith(incoming.getLine());
		}

		public org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge getIncoming() {
			return incoming;
		}

		public void setOutgoing(final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge outgoing) {
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex.this.outgoing = outgoing;
			bindWith(outgoing.getLine());
		}

		public org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge getOutgoing() {
			return outgoing;
		}
	}

	private static class Edge {
		private final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex start;

		private final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex end;

		private final org.apache.commons.math3.geometry.euclidean.twod.Line line;

		private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> node;

		public Edge(final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex start ,final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex end ,final org.apache.commons.math3.geometry.euclidean.twod.Line line) {
			this.start = start;
			this.end = end;
			this.line = line;
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge.this.node = null;
			start.setOutgoing(org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge.this);
			end.setIncoming(org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge.this);
		}

		public org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex getStart() {
			return start;
		}

		public org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex getEnd() {
			return end;
		}

		public org.apache.commons.math3.geometry.euclidean.twod.Line getLine() {
			return line;
		}

		public void setNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> node) {
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge.this.node = node;
		}

		public org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> getNode() {
			return node;
		}

		public org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex split(final org.apache.commons.math3.geometry.euclidean.twod.Line splitLine) {
			final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex splitVertex = new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Vertex(line.intersection(splitLine));
			splitVertex.bindWith(splitLine);
			final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge startHalf = new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge(start , splitVertex , line);
			final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge endHalf = new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.Edge(splitVertex , end , line);
			startHalf.node = node;
			endHalf.node = node;
			return splitVertex;
		}
	}

	@java.lang.Override
	public org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet buildNew(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> tree) {
		return new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet(tree , getTolerance());
	}

	@java.lang.Override
	protected void computeGeometricalProperties() {
		final org.apache.commons.math3.geometry.euclidean.twod.Vector2D[][] v = getVertices();
		if ((v.length) == 0) {
			final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> tree = getTree(false);
			if (((tree.getCut()) == null) && ((java.lang.Boolean)(tree.getAttribute()))) {
				setSize(java.lang.Double.POSITIVE_INFINITY);
				setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(org.apache.commons.math3.geometry.euclidean.twod.Vector2D.NaN)));
			} else {
				setSize(0);
				setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(0 , 0))));
			}
		} else if ((v[0][0]) == null) {
			setSize(java.lang.Double.POSITIVE_INFINITY);
			setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(org.apache.commons.math3.geometry.euclidean.twod.Vector2D.NaN)));
		} else {
			double sum = 0;
			double sumX = 0;
			double sumY = 0;
			for (org.apache.commons.math3.geometry.euclidean.twod.Vector2D[] loop : v) {
				double x1 = loop[((loop.length) - 1)].getX();
				double y1 = loop[((loop.length) - 1)].getY();
				for (final org.apache.commons.math3.geometry.euclidean.twod.Vector2D point : loop) {
					final double x0 = x1;
					final double y0 = y1;
					x1 = point.getX();
					y1 = point.getY();
					final double factor = (x0 * y1) - (y0 * x1);
					sum += factor;
					sumX += factor * (x0 + x1);
					sumY += factor * (y0 + y1);
				}
			}
			if (sum < 0) {
				setSize(java.lang.Double.POSITIVE_INFINITY);
				setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(org.apache.commons.math3.geometry.euclidean.twod.Vector2D.NaN)));
			} else {
				setSize((sum / 2));
				setBarycenter(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(new org.apache.commons.math3.geometry.euclidean.twod.Vector2D((sumX / (3 * sum)) , (sumY / (3 * sum))))));
			}
		}
	}

	public org.apache.commons.math3.geometry.euclidean.twod.Vector2D[][] getVertices() {
		if ((vertices) == null) {
			if ((getTree(false).getCut()) == null) {
				vertices = new org.apache.commons.math3.geometry.euclidean.twod.Vector2D[0][];
			} else {
				final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.SegmentsBuilder visitor = new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.SegmentsBuilder(getTolerance());
				getTree(true).visit(visitor);
				final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment> segments = visitor.getSegments();
				int pending = segments.size();
				pending -= naturalFollowerConnections(segments);
				if (pending > 0) {
					pending -= splitEdgeConnections(segments);
				} 
				if (pending > 0) {
					pending -= closeVerticesConnections(segments);
				} 
				final java.util.ArrayList<java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Segment>> loops = new java.util.ArrayList<java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Segment>>();
				for (org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment s = getUnprocessed(segments) ; s != null ; s = getUnprocessed(segments)) {
					final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Segment> loop = followLoop(s);
					if (loop != null) {
						if ((loop.get(0).getStart()) == null) {
							loops.add(0, loop);
						} else {
							loops.add(loop);
						}
					} 
				}
				vertices = new org.apache.commons.math3.geometry.euclidean.twod.Vector2D[loops.size()][];
				int i = 0;
				for (final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Segment> loop : loops) {
					if (((loop.size()) < 2) || ((((loop.size()) == 2) && ((loop.get(0).getStart()) == null)) && ((loop.get(1).getEnd()) == null))) {
						final org.apache.commons.math3.geometry.euclidean.twod.Line line = loop.get(0).getLine();
						vertices[(i++)] = new org.apache.commons.math3.geometry.euclidean.twod.Vector2D[]{ null , line.toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(new org.apache.commons.math3.geometry.euclidean.oned.Vector1D((-(java.lang.Float.MAX_VALUE)))))) , line.toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(new org.apache.commons.math3.geometry.euclidean.oned.Vector1D((+(java.lang.Float.MAX_VALUE)))))) };
					} else if ((loop.get(0).getStart()) == null) {
						final org.apache.commons.math3.geometry.euclidean.twod.Vector2D[] array = new org.apache.commons.math3.geometry.euclidean.twod.Vector2D[(loop.size()) + 2];
						int j = 0;
						for (org.apache.commons.math3.geometry.euclidean.twod.Segment segment : loop) {
							if (j == 0) {
								double x = segment.getLine().toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(segment.getEnd()))).getX();
								x -= org.apache.commons.math3.util.FastMath.max(1.0, org.apache.commons.math3.util.FastMath.abs((x / 2)));
								array[(j++)] = null;
								array[(j++)] = segment.getLine().toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(new org.apache.commons.math3.geometry.euclidean.oned.Vector1D(x))));
							} 
							if (j < ((array.length) - 1)) {
								array[(j++)] = segment.getEnd();
							} 
							if (j == ((array.length) - 1)) {
								double x = segment.getLine().toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(segment.getStart()))).getX();
								x += org.apache.commons.math3.util.FastMath.max(1.0, org.apache.commons.math3.util.FastMath.abs((x / 2)));
								array[(j++)] = segment.getLine().toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(new org.apache.commons.math3.geometry.euclidean.oned.Vector1D(x))));
							} 
						}
						vertices[(i++)] = array;
					} else {
						final org.apache.commons.math3.geometry.euclidean.twod.Vector2D[] array = new org.apache.commons.math3.geometry.euclidean.twod.Vector2D[loop.size()];
						int j = 0;
						for (org.apache.commons.math3.geometry.euclidean.twod.Segment segment : loop) {
							array[(j++)] = segment.getStart();
						}
						vertices[(i++)] = array;
					}
				}
			}
		} 
		return vertices.clone();
	}

	private int naturalFollowerConnections(final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment> segments) {
		int connected = 0;
		for (final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment segment : segments) {
			if ((segment.getNext()) == null) {
				final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> node = segment.getNode();
				final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> end = segment.getEndNode();
				for (final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment candidateNext : segments) {
					if ((((candidateNext.getPrevious()) == null) && ((candidateNext.getNode()) == end)) && ((candidateNext.getStartNode()) == node)) {
						segment.setNext(candidateNext);
						candidateNext.setPrevious(segment);
						++connected;
						break;
					} 
				}
			} 
		}
		return connected;
	}

	private int splitEdgeConnections(final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment> segments) {
		int connected = 0;
		for (final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment segment : segments) {
			if ((segment.getNext()) == null) {
				final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> hyperplane = segment.getNode().getCut().getHyperplane();
				final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> end = segment.getEndNode();
				for (final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment candidateNext : segments) {
					if ((((candidateNext.getPrevious()) == null) && ((candidateNext.getNode().getCut().getHyperplane()) == hyperplane)) && ((candidateNext.getStartNode()) == end)) {
						segment.setNext(candidateNext);
						candidateNext.setPrevious(segment);
						++connected;
						break;
					} 
				}
			} 
		}
		return connected;
	}

	private int closeVerticesConnections(final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment> segments) {
		int connected = 0;
		for (final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment segment : segments) {
			if (((segment.getNext()) == null) && ((segment.getEnd()) != null)) {
				final org.apache.commons.math3.geometry.euclidean.twod.Vector2D end = segment.getEnd();
				org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment selectedNext = null;
				double min = java.lang.Double.POSITIVE_INFINITY;
				for (final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment candidateNext : segments) {
					if (((candidateNext.getPrevious()) == null) && ((candidateNext.getStart()) != null)) {
						final double distance = org.apache.commons.math3.geometry.euclidean.twod.Vector2D.distance(end, candidateNext.getStart());
						if (distance < min) {
							selectedNext = candidateNext;
							min = distance;
						} 
					} 
				}
				if (min <= (getTolerance())) {
					segment.setNext(selectedNext);
					selectedNext.setPrevious(segment);
					++connected;
				} 
			} 
		}
		return connected;
	}

	private org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment getUnprocessed(final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment> segments) {
		for (final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment segment : segments) {
			if (!(segment.isProcessed())) {
				return segment;
			} 
		}
		return null;
	}

	private java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Segment> followLoop(final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment defining) {
		final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Segment> loop = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.twod.Segment>();
		loop.add(defining);
		defining.setProcessed(true);
		org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment next = defining.getNext();
		while ((next != defining) && (next != null)) {
			loop.add(next);
			next.setProcessed(true);
			next = next.getNext();
		}
		if (next == null) {
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment previous = defining.getPrevious();
			while (previous != null) {
				loop.add(0, previous);
				previous.setProcessed(true);
				previous = previous.getPrevious();
			}
		} 
		filterSpuriousVertices(loop);
		if (((loop.size()) == 2) && ((loop.get(0).getStart()) != null)) {
			return null;
		} else {
			return loop;
		}
	}

	private void filterSpuriousVertices(final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Segment> loop) {
		for (int i = 0 ; i < (loop.size()) ; ++i) {
			final org.apache.commons.math3.geometry.euclidean.twod.Segment previous = loop.get(i);
			int j = (i + 1) % (loop.size());
			final org.apache.commons.math3.geometry.euclidean.twod.Segment next = loop.get(j);
			if ((next != null) && (org.apache.commons.math3.util.Precision.equals(previous.getLine().getAngle(), next.getLine().getAngle(), org.apache.commons.math3.util.Precision.EPSILON))) {
				loop.set(j, new org.apache.commons.math3.geometry.euclidean.twod.Segment(previous.getStart() , next.getEnd() , previous.getLine()));
				loop.remove((i--));
			} 
		}
	}

	private static class ConnectableSegment extends org.apache.commons.math3.geometry.euclidean.twod.Segment {
		private final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> node;

		private final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> startNode;

		private final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> endNode;

		private org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment previous;

		private org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment next;

		private boolean processed;

		public ConnectableSegment(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D start ,final org.apache.commons.math3.geometry.euclidean.twod.Vector2D end ,final org.apache.commons.math3.geometry.euclidean.twod.Line line ,final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> node ,final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> startNode ,final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> endNode) {
			super(start, end, line);
			this.node = node;
			this.startNode = startNode;
			this.endNode = endNode;
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment.this.previous = null;
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment.this.next = null;
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment.this.processed = false;
		}

		public org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> getNode() {
			return node;
		}

		public org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> getStartNode() {
			return startNode;
		}

		public org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> getEndNode() {
			return endNode;
		}

		public org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment getPrevious() {
			return previous;
		}

		public void setPrevious(final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment previous) {
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment.this.previous = previous;
		}

		public org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment getNext() {
			return next;
		}

		public void setNext(final org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment next) {
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment.this.next = next;
		}

		public void setProcessed(final boolean processed) {
			org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment.this.processed = processed;
		}

		public boolean isProcessed() {
			return processed;
		}
	}

	private static class SegmentsBuilder implements org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> {
		private final double tolerance;

		private final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment> segments;

		public SegmentsBuilder(final double tolerance) {
			this.tolerance = tolerance;
			this.segments = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment>();
		}

		public org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order visitOrder(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> node) {
			return org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order.MINUS_SUB_PLUS;
		}

		public void visitInternalNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> node) {
			@java.lang.SuppressWarnings(value = "unchecked")
			final org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> attribute = ((org.apache.commons.math3.geometry.partitioning.BoundaryAttribute<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(node.getAttribute()));
			final java.lang.Iterable<org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>> splitters = attribute.getSplitters();
			if ((attribute.getPlusOutside()) != null) {
				addContribution(attribute.getPlusOutside(), node, splitters, false);
			} 
			if ((attribute.getPlusInside()) != null) {
				addContribution(attribute.getPlusInside(), node, splitters, true);
			} 
		}

		public void visitLeafNode(final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> node) {
		}

		private void addContribution(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> sub, final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> node, final java.lang.Iterable<org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>> splitters, final boolean reversed) {
			@java.lang.SuppressWarnings(value = "unchecked")
			final org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> absSub = ((org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(sub));
			final org.apache.commons.math3.geometry.euclidean.twod.Line line = ((org.apache.commons.math3.geometry.euclidean.twod.Line)(sub.getHyperplane()));
			final java.util.List<org.apache.commons.math3.geometry.euclidean.oned.Interval> intervals = ((org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet)(absSub.getRemainingRegion())).asList();
			for (final org.apache.commons.math3.geometry.euclidean.oned.Interval i : intervals) {
				final org.apache.commons.math3.geometry.euclidean.twod.Vector2D startV = java.lang.Double.isInfinite(i.getInf()) ? null : ((org.apache.commons.math3.geometry.euclidean.twod.Vector2D)(line.toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(new org.apache.commons.math3.geometry.euclidean.oned.Vector1D(i.getInf()))))));
				final org.apache.commons.math3.geometry.euclidean.twod.Vector2D endV = java.lang.Double.isInfinite(i.getSup()) ? null : ((org.apache.commons.math3.geometry.euclidean.twod.Vector2D)(line.toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(new org.apache.commons.math3.geometry.euclidean.oned.Vector1D(i.getSup()))))));
				final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> startN = selectClosest(startV, splitters);
				final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> endN = selectClosest(endV, splitters);
				if (reversed) {
					segments.add(new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment(endV , startV , line.getReverse() , node , endN , startN));
				} else {
					segments.add(new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment(startV , endV , line , node , startN , endN));
				}
			}
		}

		private org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> selectClosest(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D point, final java.lang.Iterable<org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>> candidates) {
			org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> selected = null;
			double min = java.lang.Double.POSITIVE_INFINITY;
			for (final org.apache.commons.math3.geometry.partitioning.BSPTree<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> node : candidates) {
				final double distance = org.apache.commons.math3.util.FastMath.abs(node.getCut().getHyperplane().getOffset(point));
				if (distance < min) {
					selected = node;
					min = distance;
				} 
			}
			return min <= (tolerance) ? selected : null;
		}

		public java.util.List<org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet.ConnectableSegment> getSegments() {
			return segments;
		}
	}
}

