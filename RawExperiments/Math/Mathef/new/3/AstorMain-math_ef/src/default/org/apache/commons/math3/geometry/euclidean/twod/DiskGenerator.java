package org.apache.commons.math3.geometry.euclidean.twod;


public class DiskGenerator implements org.apache.commons.math3.geometry.enclosing.SupportBallGenerator<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.twod.Vector2D> {
	public org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.twod.Vector2D> ballOnSupport(final java.util.List<org.apache.commons.math3.geometry.euclidean.twod.Vector2D> support) {
		if ((support.size()) < 1) {
			return new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.twod.Vector2D>(org.apache.commons.math3.geometry.euclidean.twod.Vector2D.ZERO , java.lang.Double.NEGATIVE_INFINITY);
		} else {
			final org.apache.commons.math3.geometry.euclidean.twod.Vector2D vA = support.get(0);
			if ((support.size()) < 2) {
				return new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.twod.Vector2D>(vA , 0 , vA);
			} else {
				final org.apache.commons.math3.geometry.euclidean.twod.Vector2D vB = support.get(1);
				if ((support.size()) < 3) {
					return new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.twod.Vector2D>(new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(0.5 , vA , 0.5 , vB) , (0.5 * (vA.distance(vB))) , vA , vB);
				} else {
					final org.apache.commons.math3.geometry.euclidean.twod.Vector2D vC = support.get(2);
					final org.apache.commons.math3.fraction.BigFraction[] c2 = new org.apache.commons.math3.fraction.BigFraction[]{ new org.apache.commons.math3.fraction.BigFraction(vA.getX()) , new org.apache.commons.math3.fraction.BigFraction(vB.getX()) , new org.apache.commons.math3.fraction.BigFraction(vC.getX()) };
					final org.apache.commons.math3.fraction.BigFraction[] c3 = new org.apache.commons.math3.fraction.BigFraction[]{ new org.apache.commons.math3.fraction.BigFraction(vA.getY()) , new org.apache.commons.math3.fraction.BigFraction(vB.getY()) , new org.apache.commons.math3.fraction.BigFraction(vC.getY()) };
					final org.apache.commons.math3.fraction.BigFraction[] c1 = new org.apache.commons.math3.fraction.BigFraction[]{ c2[0].multiply(c2[0]).add(c3[0].multiply(c3[0])) , c2[1].multiply(c2[1]).add(c3[1].multiply(c3[1])) , c2[2].multiply(c2[2]).add(c3[2].multiply(c3[2])) };
					final org.apache.commons.math3.fraction.BigFraction twoM11 = minor(c2, c3).multiply(2);
					final org.apache.commons.math3.fraction.BigFraction m12 = minor(c1, c3);
					final org.apache.commons.math3.fraction.BigFraction m13 = minor(c1, c2);
					final org.apache.commons.math3.fraction.BigFraction centerX = m12.divide(twoM11);
					final org.apache.commons.math3.fraction.BigFraction centerY = m13.divide(twoM11).negate();
					final org.apache.commons.math3.fraction.BigFraction dx = c2[0].subtract(centerX);
					final org.apache.commons.math3.fraction.BigFraction dy = c3[0].subtract(centerY);
					final org.apache.commons.math3.fraction.BigFraction r2 = dx.multiply(dx).add(dy.multiply(dy));
					return new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.twod.Vector2D>(new org.apache.commons.math3.geometry.euclidean.twod.Vector2D(centerX.doubleValue() , centerY.doubleValue()) , org.apache.commons.math3.util.FastMath.sqrt(r2.doubleValue()) , vA , vB , vC);
				}
			}
		}
	}

	private org.apache.commons.math3.fraction.BigFraction minor(final org.apache.commons.math3.fraction.BigFraction[] c1, final org.apache.commons.math3.fraction.BigFraction[] c2) {
		return c2[0].multiply(c1[2].subtract(c1[1])).add(c2[1].multiply(c1[0].subtract(c1[2]))).add(c2[2].multiply(c1[1].subtract(c1[0])));
	}
}

