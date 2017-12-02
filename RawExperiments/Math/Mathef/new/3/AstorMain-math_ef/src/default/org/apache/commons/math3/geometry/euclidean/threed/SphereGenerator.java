package org.apache.commons.math3.geometry.euclidean.threed;


public class SphereGenerator implements org.apache.commons.math3.geometry.enclosing.SupportBallGenerator<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.threed.Vector3D> {
	public org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.threed.Vector3D> ballOnSupport(final java.util.List<org.apache.commons.math3.geometry.euclidean.threed.Vector3D> support) {
		if ((support.size()) < 1) {
			return new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.threed.Vector3D>(org.apache.commons.math3.geometry.euclidean.threed.Vector3D.ZERO , java.lang.Double.NEGATIVE_INFINITY);
		} else {
			final org.apache.commons.math3.geometry.euclidean.threed.Vector3D vA = support.get(0);
			if ((support.size()) < 2) {
				return new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.threed.Vector3D>(vA , 0 , vA);
			} else {
				final org.apache.commons.math3.geometry.euclidean.threed.Vector3D vB = support.get(1);
				if ((support.size()) < 3) {
					return new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.threed.Vector3D>(new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(0.5 , vA , 0.5 , vB) , (0.5 * (vA.distance(vB))) , vA , vB);
				} else {
					final org.apache.commons.math3.geometry.euclidean.threed.Vector3D vC = support.get(2);
					if ((support.size()) < 4) {
						final org.apache.commons.math3.geometry.euclidean.threed.Plane p = new org.apache.commons.math3.geometry.euclidean.threed.Plane(vA , vB , vC , (1.0E-10 * (((vA.getNorm1()) + (vB.getNorm1())) + (vC.getNorm1()))));
						final org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D, org.apache.commons.math3.geometry.euclidean.twod.Vector2D> disk = new org.apache.commons.math3.geometry.euclidean.twod.DiskGenerator().ballOnSupport(java.util.Arrays.asList(p.toSubSpace(vA), p.toSubSpace(vB), p.toSubSpace(vC)));
						return new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.threed.Vector3D>(p.toSpace(disk.getCenter()) , disk.getRadius() , vA , vB , vC);
					} else {
						final org.apache.commons.math3.geometry.euclidean.threed.Vector3D vD = support.get(3);
						final org.apache.commons.math3.fraction.BigFraction[] c2 = new org.apache.commons.math3.fraction.BigFraction[]{ new org.apache.commons.math3.fraction.BigFraction(vA.getX()) , new org.apache.commons.math3.fraction.BigFraction(vB.getX()) , new org.apache.commons.math3.fraction.BigFraction(vC.getX()) , new org.apache.commons.math3.fraction.BigFraction(vD.getX()) };
						final org.apache.commons.math3.fraction.BigFraction[] c3 = new org.apache.commons.math3.fraction.BigFraction[]{ new org.apache.commons.math3.fraction.BigFraction(vA.getY()) , new org.apache.commons.math3.fraction.BigFraction(vB.getY()) , new org.apache.commons.math3.fraction.BigFraction(vC.getY()) , new org.apache.commons.math3.fraction.BigFraction(vD.getY()) };
						final org.apache.commons.math3.fraction.BigFraction[] c4 = new org.apache.commons.math3.fraction.BigFraction[]{ new org.apache.commons.math3.fraction.BigFraction(vA.getZ()) , new org.apache.commons.math3.fraction.BigFraction(vB.getZ()) , new org.apache.commons.math3.fraction.BigFraction(vC.getZ()) , new org.apache.commons.math3.fraction.BigFraction(vD.getZ()) };
						final org.apache.commons.math3.fraction.BigFraction[] c1 = new org.apache.commons.math3.fraction.BigFraction[]{ c2[0].multiply(c2[0]).add(c3[0].multiply(c3[0])).add(c4[0].multiply(c4[0])) , c2[1].multiply(c2[1]).add(c3[1].multiply(c3[1])).add(c4[1].multiply(c4[1])) , c2[2].multiply(c2[2]).add(c3[2].multiply(c3[2])).add(c4[2].multiply(c4[2])) , c2[3].multiply(c2[3]).add(c3[3].multiply(c3[3])).add(c4[3].multiply(c4[3])) };
						final org.apache.commons.math3.fraction.BigFraction twoM11 = minor(c2, c3, c4).multiply(2);
						final org.apache.commons.math3.fraction.BigFraction m12 = minor(c1, c3, c4);
						final org.apache.commons.math3.fraction.BigFraction m13 = minor(c1, c2, c4);
						final org.apache.commons.math3.fraction.BigFraction m14 = minor(c1, c2, c3);
						final org.apache.commons.math3.fraction.BigFraction centerX = m12.divide(twoM11);
						final org.apache.commons.math3.fraction.BigFraction centerY = m13.divide(twoM11).negate();
						final org.apache.commons.math3.fraction.BigFraction centerZ = m14.divide(twoM11);
						final org.apache.commons.math3.fraction.BigFraction dx = c2[0].subtract(centerX);
						final org.apache.commons.math3.fraction.BigFraction dy = c3[0].subtract(centerY);
						final org.apache.commons.math3.fraction.BigFraction dz = c4[0].subtract(centerZ);
						final org.apache.commons.math3.fraction.BigFraction r2 = dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz));
						return new org.apache.commons.math3.geometry.enclosing.EnclosingBall<org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.threed.Vector3D>(new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(centerX.doubleValue() , centerY.doubleValue() , centerZ.doubleValue()) , org.apache.commons.math3.util.FastMath.sqrt(r2.doubleValue()) , vA , vB , vC , vD);
					}
				}
			}
		}
	}

	private org.apache.commons.math3.fraction.BigFraction minor(final org.apache.commons.math3.fraction.BigFraction[] c1, final org.apache.commons.math3.fraction.BigFraction[] c2, final org.apache.commons.math3.fraction.BigFraction[] c3) {
		return c2[0].multiply(c3[1]).multiply(c1[2].subtract(c1[3])).add(c2[0].multiply(c3[2]).multiply(c1[3].subtract(c1[1]))).add(c2[0].multiply(c3[3]).multiply(c1[1].subtract(c1[2]))).add(c2[1].multiply(c3[0]).multiply(c1[3].subtract(c1[2]))).add(c2[1].multiply(c3[2]).multiply(c1[0].subtract(c1[3]))).add(c2[1].multiply(c3[3]).multiply(c1[2].subtract(c1[0]))).add(c2[2].multiply(c3[0]).multiply(c1[1].subtract(c1[3]))).add(c2[2].multiply(c3[1]).multiply(c1[3].subtract(c1[0]))).add(c2[2].multiply(c3[3]).multiply(c1[0].subtract(c1[1]))).add(c2[3].multiply(c3[0]).multiply(c1[2].subtract(c1[1]))).add(c2[3].multiply(c3[1]).multiply(c1[0].subtract(c1[2]))).add(c2[3].multiply(c3[2]).multiply(c1[1].subtract(c1[0])));
	}
}

