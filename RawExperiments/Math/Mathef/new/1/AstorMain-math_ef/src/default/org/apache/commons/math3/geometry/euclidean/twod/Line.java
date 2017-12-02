package org.apache.commons.math3.geometry.euclidean.twod;


public class Line implements org.apache.commons.math3.geometry.partitioning.Embedding<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> , org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> {
	private static final double DEFAULT_TOLERANCE = 1.0E-10;

	private double angle;

	private double cos;

	private double sin;

	private double originOffset;

	private final double tolerance;

	private org.apache.commons.math3.geometry.euclidean.twod.Line reverse;

	public Line(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p1 ,final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p2 ,final double tolerance) {
		reset(p1, p2);
		this.tolerance = tolerance;
	}

	public Line(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p ,final double angle ,final double tolerance) {
		reset(p, angle);
		this.tolerance = tolerance;
	}

	private Line(final double angle ,final double cos ,final double sin ,final double originOffset ,final double tolerance) {
		org.apache.commons.math3.geometry.euclidean.twod.Line.this.angle = angle;
		org.apache.commons.math3.geometry.euclidean.twod.Line.this.cos = cos;
		org.apache.commons.math3.geometry.euclidean.twod.Line.this.sin = sin;
		org.apache.commons.math3.geometry.euclidean.twod.Line.this.originOffset = originOffset;
		this.tolerance = tolerance;
		org.apache.commons.math3.geometry.euclidean.twod.Line.this.reverse = null;
	}

	@java.lang.Deprecated
	public Line(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p1 ,final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p2) {
		this(p1, p2, org.apache.commons.math3.geometry.euclidean.twod.Line.DEFAULT_TOLERANCE);
	}

	@java.lang.Deprecated
	public Line(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p ,final double angle) {
		this(p, angle, org.apache.commons.math3.geometry.euclidean.twod.Line.DEFAULT_TOLERANCE);
	}

	public Line(final org.apache.commons.math3.geometry.euclidean.twod.Line line) {
		angle = org.apache.commons.math3.util.MathUtils.normalizeAngle(line.angle, org.apache.commons.math3.util.FastMath.PI);
		cos = line.cos;
		sin = line.sin;
		originOffset = line.originOffset;
		tolerance = line.tolerance;
		reverse = null;
	}

	public org.apache.commons.math3.geometry.euclidean.twod.Line copySelf() {
		return new org.apache.commons.math3.geometry.euclidean.twod.Line(org.apache.commons.math3.geometry.euclidean.twod.Line.this);
	}

	public void reset(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p1, final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p2) {
		unlinkReverse();
		final double dx = (p2.getX()) - (p1.getX());
		final double dy = (p2.getY()) - (p1.getY());
		final double d = org.apache.commons.math3.util.FastMath.hypot(dx, dy);
		if (d == 0.0) {
			angle = 0.0;
			cos = 1.0;
			sin = 0.0;
			originOffset = p1.getY();
		} else {
			angle = (org.apache.commons.math3.util.FastMath.PI) + (org.apache.commons.math3.util.FastMath.atan2((-dy), (-dx)));
			cos = dx / d;
			sin = dy / d;
			originOffset = (org.apache.commons.math3.util.MathArrays.linearCombination(p2.getX(), p1.getY(), (-(p1.getX())), p2.getY())) / d;
		}
	}

	public void reset(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p, final double alpha) {
		unlinkReverse();
		org.apache.commons.math3.geometry.euclidean.twod.Line.this.angle = org.apache.commons.math3.util.MathUtils.normalizeAngle(alpha, org.apache.commons.math3.util.FastMath.PI);
		cos = org.apache.commons.math3.util.FastMath.cos(org.apache.commons.math3.geometry.euclidean.twod.Line.this.angle);
		sin = org.apache.commons.math3.util.FastMath.sin(org.apache.commons.math3.geometry.euclidean.twod.Line.this.angle);
		originOffset = org.apache.commons.math3.util.MathArrays.linearCombination(cos, p.getY(), (-(sin)), p.getX());
	}

	public void revertSelf() {
		unlinkReverse();
		if ((angle) < (org.apache.commons.math3.util.FastMath.PI)) {
			angle += org.apache.commons.math3.util.FastMath.PI;
		} else {
			angle -= org.apache.commons.math3.util.FastMath.PI;
		}
		cos = -(cos);
		sin = -(sin);
		originOffset = -(originOffset);
	}

	private void unlinkReverse() {
		if ((reverse) != null) {
			reverse.reverse = null;
		} 
		reverse = null;
	}

	public org.apache.commons.math3.geometry.euclidean.twod.Line getReverse() {
		if ((reverse) == null) {
			reverse = new org.apache.commons.math3.geometry.euclidean.twod.Line(((angle) < (org.apache.commons.math3.util.FastMath.PI) ? (angle) + (org.apache.commons.math3.util.FastMath.PI) : (angle) - (org.apache.commons.math3.util.FastMath.PI)) , (-(cos)) , (-(sin)) , (-(originOffset)) , tolerance);
			reverse.reverse = org.apache.commons.math3.geometry.euclidean.twod.Line.this;
		} 
		return reverse;
	}

	public org.apache.commons.math3.geometry.euclidean.oned.Vector1D toSubSpace(org.apache.commons.math3.geometry.Vector<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> vector) {
		return toSubSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(vector)));
	}

	public org.apache.commons.math3.geometry.euclidean.twod.Vector2D toSpace(org.apache.commons.math3.geometry.Vector<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> vector) {
		return toSpace(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D>)(vector)));
	}

	public org.apache.commons.math3.geometry.euclidean.oned.Vector1D toSubSpace(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> point) {
		org.apache.commons.math3.geometry.euclidean.twod.Vector2D p2 = ((org.apache.commons.math3.geometry.euclidean.twod.Vector2D)(point));
		return new org.apache.commons.math3.geometry.euclidean.oned.Vector1D(org.apache.commons.math3.util.MathArrays.linearCombination(cos, p2.getX(), sin, p2.getY()));
	}

	public org.apache.commons.math3.geometry.euclidean.twod.Vector2D toSpace(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> point) {
		final double abscissa = ((org.apache.commons.math3.geometry.euclidean.oned.Vector1D)(point)).getX();
		return new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(org.apache.commons.math3.util.MathArrays.linearCombination(abscissa, cos, (-(originOffset)), sin) , org.apache.commons.math3.util.MathArrays.linearCombination(abscissa, sin, originOffset, cos));
	}

	public org.apache.commons.math3.geometry.euclidean.twod.Vector2D intersection(final org.apache.commons.math3.geometry.euclidean.twod.Line other) {
		final double d = org.apache.commons.math3.util.MathArrays.linearCombination(sin, other.cos, (-(other.sin)), cos);
		if ((org.apache.commons.math3.util.FastMath.abs(d)) < (tolerance)) {
			return null;
		} 
		return new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(((org.apache.commons.math3.util.MathArrays.linearCombination(cos, other.originOffset, (-(other.cos)), originOffset)) / d) , ((org.apache.commons.math3.util.MathArrays.linearCombination(sin, other.originOffset, (-(other.sin)), originOffset)) / d));
	}

	public org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> project(org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> point) {
		return toSpace(toSubSpace(point));
	}

	public double getTolerance() {
		return tolerance;
	}

	public org.apache.commons.math3.geometry.euclidean.twod.SubLine wholeHyperplane() {
		return new org.apache.commons.math3.geometry.euclidean.twod.SubLine(org.apache.commons.math3.geometry.euclidean.twod.Line.this , new org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet(tolerance));
	}

	public org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet wholeSpace() {
		return new org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet(tolerance);
	}

	public double getOffset(final org.apache.commons.math3.geometry.euclidean.twod.Line line) {
		return (originOffset) + ((org.apache.commons.math3.util.MathArrays.linearCombination(cos, line.cos, sin, line.sin)) > 0 ? -(line.originOffset) : line.originOffset);
	}

	public double getOffset(org.apache.commons.math3.geometry.Vector<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> vector) {
		return getOffset(((org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D>)(vector)));
	}

	public double getOffset(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> point) {
		org.apache.commons.math3.geometry.euclidean.twod.Vector2D p2 = ((org.apache.commons.math3.geometry.euclidean.twod.Vector2D)(point));
		return org.apache.commons.math3.util.MathArrays.linearCombination(sin, p2.getX(), (-(cos)), p2.getY(), 1.0, originOffset);
	}

	public boolean sameOrientationAs(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> other) {
		final org.apache.commons.math3.geometry.euclidean.twod.Line otherL = ((org.apache.commons.math3.geometry.euclidean.twod.Line)(other));
		return (org.apache.commons.math3.util.MathArrays.linearCombination(sin, otherL.sin, cos, otherL.cos)) >= 0.0;
	}

	public org.apache.commons.math3.geometry.euclidean.twod.Vector2D getPointAt(final org.apache.commons.math3.geometry.euclidean.oned.Vector1D abscissa, final double offset) {
		final double x = abscissa.getX();
		final double dOffset = offset - (originOffset);
		return new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(org.apache.commons.math3.util.MathArrays.linearCombination(x, cos, dOffset, sin) , org.apache.commons.math3.util.MathArrays.linearCombination(x, sin, (-dOffset), cos));
	}

	public boolean contains(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p) {
		return (org.apache.commons.math3.util.FastMath.abs(getOffset(p))) < (tolerance);
	}

	public double distance(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p) {
		return org.apache.commons.math3.util.FastMath.abs(getOffset(p));
	}

	public boolean isParallelTo(final org.apache.commons.math3.geometry.euclidean.twod.Line line) {
		return (org.apache.commons.math3.util.FastMath.abs(org.apache.commons.math3.util.MathArrays.linearCombination(sin, line.cos, (-(cos)), line.sin))) < (tolerance);
	}

	public void translateToPoint(final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p) {
		originOffset = org.apache.commons.math3.util.MathArrays.linearCombination(cos, p.getY(), (-(sin)), p.getX());
	}

	public double getAngle() {
		return org.apache.commons.math3.util.MathUtils.normalizeAngle(angle, org.apache.commons.math3.util.FastMath.PI);
	}

	public void setAngle(final double angle) {
		unlinkReverse();
		org.apache.commons.math3.geometry.euclidean.twod.Line.this.angle = org.apache.commons.math3.util.MathUtils.normalizeAngle(angle, org.apache.commons.math3.util.FastMath.PI);
		cos = org.apache.commons.math3.util.FastMath.cos(org.apache.commons.math3.geometry.euclidean.twod.Line.this.angle);
		sin = org.apache.commons.math3.util.FastMath.sin(org.apache.commons.math3.geometry.euclidean.twod.Line.this.angle);
	}

	public double getOriginOffset() {
		return originOffset;
	}

	public void setOriginOffset(final double offset) {
		unlinkReverse();
		originOffset = offset;
	}

	public static org.apache.commons.math3.geometry.partitioning.Transform<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> getTransform(final java.awt.geom.AffineTransform transform) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
		return new org.apache.commons.math3.geometry.euclidean.twod.Line.LineTransform(transform);
	}

	private static class LineTransform implements org.apache.commons.math3.geometry.partitioning.Transform<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> {
		private double cXX;

		private double cXY;

		private double cX1;

		private double cYX;

		private double cYY;

		private double cY1;

		private double c1Y;

		private double c1X;

		private double c11;

		public LineTransform(final java.awt.geom.AffineTransform transform) throws org.apache.commons.math3.exception.MathIllegalArgumentException {
			final double[] m = new double[6];
			transform.getMatrix(m);
			cXX = m[0];
			cXY = m[2];
			cX1 = m[4];
			cYX = m[1];
			cYY = m[3];
			cY1 = m[5];
			c1Y = org.apache.commons.math3.util.MathArrays.linearCombination(cXY, cY1, (-(cYY)), cX1);
			c1X = org.apache.commons.math3.util.MathArrays.linearCombination(cXX, cY1, (-(cYX)), cX1);
			c11 = org.apache.commons.math3.util.MathArrays.linearCombination(cXX, cYY, (-(cYX)), cXY);
			if ((org.apache.commons.math3.util.FastMath.abs(c11)) < 1.0E-20) {
				throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.NON_INVERTIBLE_TRANSFORM);
			} 
		}

		public org.apache.commons.math3.geometry.euclidean.twod.Vector2D apply(final org.apache.commons.math3.geometry.Point<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> point) {
			final org.apache.commons.math3.geometry.euclidean.twod.Vector2D p2D = ((org.apache.commons.math3.geometry.euclidean.twod.Vector2D)(point));
			final double x = p2D.getX();
			final double y = p2D.getY();
			return new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(org.apache.commons.math3.util.MathArrays.linearCombination(cXX, x, cXY, y, cX1, 1) , org.apache.commons.math3.util.MathArrays.linearCombination(cYX, x, cYY, y, cY1, 1));
		}

		public org.apache.commons.math3.geometry.euclidean.twod.Line apply(final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> hyperplane) {
			final org.apache.commons.math3.geometry.euclidean.twod.Line line = ((org.apache.commons.math3.geometry.euclidean.twod.Line)(hyperplane));
			final double rOffset = org.apache.commons.math3.util.MathArrays.linearCombination(c1X, line.cos, c1Y, line.sin, c11, line.originOffset);
			final double rCos = org.apache.commons.math3.util.MathArrays.linearCombination(cXX, line.cos, cXY, line.sin);
			final double rSin = org.apache.commons.math3.util.MathArrays.linearCombination(cYX, line.cos, cYY, line.sin);
			final double inv = 1.0 / (org.apache.commons.math3.util.FastMath.sqrt(((rSin * rSin) + (rCos * rCos))));
			return new org.apache.commons.math3.geometry.euclidean.twod.Line(((org.apache.commons.math3.util.FastMath.PI) + (org.apache.commons.math3.util.FastMath.atan2((-rSin), (-rCos)))) , (inv * rCos) , (inv * rSin) , (inv * rOffset) , line.tolerance);
		}

		public org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> apply(final org.apache.commons.math3.geometry.partitioning.SubHyperplane<org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D> sub, final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> original, final org.apache.commons.math3.geometry.partitioning.Hyperplane<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D> transformed) {
			final org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint op = ((org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint)(sub.getHyperplane()));
			final org.apache.commons.math3.geometry.euclidean.twod.Line originalLine = ((org.apache.commons.math3.geometry.euclidean.twod.Line)(original));
			final org.apache.commons.math3.geometry.euclidean.twod.Line transformedLine = ((org.apache.commons.math3.geometry.euclidean.twod.Line)(transformed));
			final org.apache.commons.math3.geometry.euclidean.oned.Vector1D newLoc = transformedLine.toSubSpace(apply(originalLine.toSpace(op.getLocation())));
			return new org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint(newLoc , op.isDirect() , originalLine.tolerance).wholeHyperplane();
		}
	}
}

