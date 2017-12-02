package org.apache.commons.math3.geometry.euclidean.threed;


public class SubLine {
	private static final double DEFAULT_TOLERANCE = 1.0E-10;

	private final org.apache.commons.math3.geometry.euclidean.threed.Line line;

	private final org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet remainingRegion;

	public SubLine(final org.apache.commons.math3.geometry.euclidean.threed.Line line ,final org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet remainingRegion) {
		this.line = line;
		this.remainingRegion = remainingRegion;
	}

	public SubLine(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D start ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D end ,final double tolerance) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		this(new org.apache.commons.math3.geometry.euclidean.threed.Line(start , end , tolerance), org.apache.commons.math3.geometry.euclidean.threed.SubLine.buildIntervalSet(start, end, tolerance));
	}

	public SubLine(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D start ,final org.apache.commons.math3.geometry.euclidean.threed.Vector3D end) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		this(start, end, org.apache.commons.math3.geometry.euclidean.threed.SubLine.DEFAULT_TOLERANCE);
	}

	public SubLine(final org.apache.commons.math3.geometry.euclidean.threed.Segment segment) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		this(segment.getLine(), org.apache.commons.math3.geometry.euclidean.threed.SubLine.buildIntervalSet(segment.getStart(), segment.getEnd(), segment.getLine().getTolerance()));
	}

	public java.util.List<org.apache.commons.math3.geometry.euclidean.threed.Segment> getSegments() {
		final java.util.List<org.apache.commons.math3.geometry.euclidean.oned.Interval> list = remainingRegion.asList();
		final java.util.List<org.apache.commons.math3.geometry.euclidean.threed.Segment> segments = new java.util.ArrayList<org.apache.commons.math3.geometry.euclidean.threed.Segment>(list.size());
		for (final org.apache.commons.math3.geometry.euclidean.oned.Interval interval : list) {
			final org.apache.commons.math3.geometry.euclidean.threed.Vector3D start = line.toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(new org.apache.commons.math3.geometry.euclidean.oned.Vector1D(interval.getInf()))));
			final org.apache.commons.math3.geometry.euclidean.threed.Vector3D end = line.toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(new org.apache.commons.math3.geometry.euclidean.oned.Vector1D(interval.getSup()))));
			segments.add(new org.apache.commons.math3.geometry.euclidean.threed.Segment(start , end , line));
		}
		return segments;
	}

	public org.apache.commons.math3.geometry.euclidean.threed.Vector3D intersection(final org.apache.commons.math3.geometry.euclidean.threed.SubLine subLine, final boolean includeEndPoints) {
		org.apache.commons.math3.geometry.euclidean.threed.Vector3D v1D = line.intersection(subLine.line);
		if (v1D == null) {
			return null;
		} 
		org.apache.commons.math3.geometry.partitioning.Region.Location loc1 = remainingRegion.checkPoint(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(line.toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(v1D))))));
		org.apache.commons.math3.geometry.partitioning.Region.Location loc2 = subLine.remainingRegion.checkPoint(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(subLine.line.toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(v1D))))));
		if (includeEndPoints) {
			return (loc1 != (org.apache.commons.math3.geometry.partitioning.Region.Location.OUTSIDE)) && (loc2 != (org.apache.commons.math3.geometry.partitioning.Region.Location.OUTSIDE)) ? v1D : null;
		} else {
			return (loc1 == (org.apache.commons.math3.geometry.partitioning.Region.Location.INSIDE)) && (loc2 == (org.apache.commons.math3.geometry.partitioning.Region.Location.INSIDE)) ? v1D : null;
		}
	}

	private static org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet buildIntervalSet(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D start, final org.apache.commons.math3.geometry.euclidean.threed.Vector3D end, final double tolerance) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		final org.apache.commons.math3.geometry.euclidean.threed.Line line = new org.apache.commons.math3.geometry.euclidean.threed.Line(start , end , tolerance);
		return new org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet(line.toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(start))).getX() , line.toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D>)(end))).getX() , tolerance);
	}
}

