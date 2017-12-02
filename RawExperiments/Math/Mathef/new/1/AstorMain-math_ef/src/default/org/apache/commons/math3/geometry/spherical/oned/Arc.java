package org.apache.commons.math3.geometry.spherical.oned;


public class Arc {
	private final double lower;

	private final double upper;

	private final double middle;

	private final double tolerance;

	public Arc(final double lower ,final double upper ,final double tolerance) throws org.apache.commons.math3.exception.NumberIsTooLargeException {
		this.tolerance = tolerance;
		if ((org.apache.commons.math3.util.Precision.equals(lower, upper, 0)) || ((upper - lower) >= (org.apache.commons.math3.util.MathUtils.TWO_PI))) {
			this.lower = 0;
			this.upper = org.apache.commons.math3.util.MathUtils.TWO_PI;
			this.middle = org.apache.commons.math3.util.FastMath.PI;
		} else if (lower <= upper) {
			this.lower = org.apache.commons.math3.util.MathUtils.normalizeAngle(lower, org.apache.commons.math3.util.FastMath.PI);
			this.upper = (org.apache.commons.math3.geometry.spherical.oned.Arc.this.lower) + (upper - lower);
			this.middle = 0.5 * ((org.apache.commons.math3.geometry.spherical.oned.Arc.this.lower) + (org.apache.commons.math3.geometry.spherical.oned.Arc.this.upper));
		} else {
			throw new org.apache.commons.math3.exception.NumberIsTooLargeException(org.apache.commons.math3.exception.util.LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL , lower , upper , true);
		}
	}

	public double getInf() {
		return lower;
	}

	public double getSup() {
		return upper;
	}

	public double getSize() {
		return (upper) - (lower);
	}

	public double getBarycenter() {
		return middle;
	}

	public double getTolerance() {
		return tolerance;
	}

	public org.apache.commons.math3.geometry.partitioning.Region.Location checkPoint(final double point) {
		final double normalizedPoint = org.apache.commons.math3.util.MathUtils.normalizeAngle(point, middle);
		if ((normalizedPoint < ((lower) - (tolerance))) || (normalizedPoint > ((upper) + (tolerance)))) {
			return org.apache.commons.math3.geometry.partitioning.Region.Location.OUTSIDE;
		} else if ((normalizedPoint > ((lower) + (tolerance))) && (normalizedPoint < ((upper) - (tolerance)))) {
			return org.apache.commons.math3.geometry.partitioning.Region.Location.INSIDE;
		} else {
			return (getSize()) >= ((org.apache.commons.math3.util.MathUtils.TWO_PI) - (tolerance)) ? org.apache.commons.math3.geometry.partitioning.Region.Location.INSIDE : org.apache.commons.math3.geometry.partitioning.Region.Location.BOUNDARY;
		}
	}
}

