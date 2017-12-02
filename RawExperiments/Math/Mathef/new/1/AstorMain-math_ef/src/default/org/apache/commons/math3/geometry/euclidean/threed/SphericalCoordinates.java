package org.apache.commons.math3.geometry.euclidean.threed;


public class SphericalCoordinates implements java.io.Serializable {
	private static final long serialVersionUID = 20130206L;

	private final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v;

	private final double r;

	private final double theta;

	private final double phi;

	private double[][] jacobian;

	private double[][] rHessian;

	private double[][] thetaHessian;

	private double[][] phiHessian;

	public SphericalCoordinates(final org.apache.commons.math3.geometry.euclidean.threed.Vector3D v) {
		this.v = v;
		this.r = v.getNorm();
		this.theta = v.getAlpha();
		this.phi = org.apache.commons.math3.util.FastMath.acos(((v.getZ()) / (r)));
	}

	public SphericalCoordinates(final double r ,final double theta ,final double phi) {
		final double cosTheta = org.apache.commons.math3.util.FastMath.cos(theta);
		final double sinTheta = org.apache.commons.math3.util.FastMath.sin(theta);
		final double cosPhi = org.apache.commons.math3.util.FastMath.cos(phi);
		final double sinPhi = org.apache.commons.math3.util.FastMath.sin(phi);
		this.r = r;
		this.theta = theta;
		this.phi = phi;
		this.v = new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(((r * cosTheta) * sinPhi) , ((r * sinTheta) * sinPhi) , (r * cosPhi));
	}

	public org.apache.commons.math3.geometry.euclidean.threed.Vector3D getCartesian() {
		return v;
	}

	public double getR() {
		return r;
	}

	public double getTheta() {
		return theta;
	}

	public double getPhi() {
		return phi;
	}

	public double[] toCartesianGradient(final double[] sGradient) {
		computeJacobian();
		return new double[]{ (((sGradient[0]) * (jacobian[0][0])) + ((sGradient[1]) * (jacobian[1][0]))) + ((sGradient[2]) * (jacobian[2][0])) , (((sGradient[0]) * (jacobian[0][1])) + ((sGradient[1]) * (jacobian[1][1]))) + ((sGradient[2]) * (jacobian[2][1])) , ((sGradient[0]) * (jacobian[0][2])) + ((sGradient[2]) * (jacobian[2][2])) };
	}

	public double[][] toCartesianHessian(final double[][] sHessian, final double[] sGradient) {
		computeJacobian();
		computeHessians();
		final double[][] hj = new double[3][3];
		final double[][] cHessian = new double[3][3];
		hj[0][0] = (((sHessian[0][0]) * (jacobian[0][0])) + ((sHessian[1][0]) * (jacobian[1][0]))) + ((sHessian[2][0]) * (jacobian[2][0]));
		hj[0][1] = (((sHessian[0][0]) * (jacobian[0][1])) + ((sHessian[1][0]) * (jacobian[1][1]))) + ((sHessian[2][0]) * (jacobian[2][1]));
		hj[0][2] = ((sHessian[0][0]) * (jacobian[0][2])) + ((sHessian[2][0]) * (jacobian[2][2]));
		hj[1][0] = (((sHessian[1][0]) * (jacobian[0][0])) + ((sHessian[1][1]) * (jacobian[1][0]))) + ((sHessian[2][1]) * (jacobian[2][0]));
		hj[1][1] = (((sHessian[1][0]) * (jacobian[0][1])) + ((sHessian[1][1]) * (jacobian[1][1]))) + ((sHessian[2][1]) * (jacobian[2][1]));
		hj[2][0] = (((sHessian[2][0]) * (jacobian[0][0])) + ((sHessian[2][1]) * (jacobian[1][0]))) + ((sHessian[2][2]) * (jacobian[2][0]));
		hj[2][1] = (((sHessian[2][0]) * (jacobian[0][1])) + ((sHessian[2][1]) * (jacobian[1][1]))) + ((sHessian[2][2]) * (jacobian[2][1]));
		hj[2][2] = ((sHessian[2][0]) * (jacobian[0][2])) + ((sHessian[2][2]) * (jacobian[2][2]));
		cHessian[0][0] = (((jacobian[0][0]) * (hj[0][0])) + ((jacobian[1][0]) * (hj[1][0]))) + ((jacobian[2][0]) * (hj[2][0]));
		cHessian[1][0] = (((jacobian[0][1]) * (hj[0][0])) + ((jacobian[1][1]) * (hj[1][0]))) + ((jacobian[2][1]) * (hj[2][0]));
		cHessian[2][0] = ((jacobian[0][2]) * (hj[0][0])) + ((jacobian[2][2]) * (hj[2][0]));
		cHessian[1][1] = (((jacobian[0][1]) * (hj[0][1])) + ((jacobian[1][1]) * (hj[1][1]))) + ((jacobian[2][1]) * (hj[2][1]));
		cHessian[2][1] = ((jacobian[0][2]) * (hj[0][1])) + ((jacobian[2][2]) * (hj[2][1]));
		cHessian[2][2] = ((jacobian[0][2]) * (hj[0][2])) + ((jacobian[2][2]) * (hj[2][2]));
		cHessian[0][0] += (((sGradient[0]) * (rHessian[0][0])) + ((sGradient[1]) * (thetaHessian[0][0]))) + ((sGradient[2]) * (phiHessian[0][0]));
		cHessian[1][0] += (((sGradient[0]) * (rHessian[1][0])) + ((sGradient[1]) * (thetaHessian[1][0]))) + ((sGradient[2]) * (phiHessian[1][0]));
		cHessian[2][0] += ((sGradient[0]) * (rHessian[2][0])) + ((sGradient[2]) * (phiHessian[2][0]));
		cHessian[1][1] += (((sGradient[0]) * (rHessian[1][1])) + ((sGradient[1]) * (thetaHessian[1][1]))) + ((sGradient[2]) * (phiHessian[1][1]));
		cHessian[2][1] += ((sGradient[0]) * (rHessian[2][1])) + ((sGradient[2]) * (phiHessian[2][1]));
		cHessian[2][2] += ((sGradient[0]) * (rHessian[2][2])) + ((sGradient[2]) * (phiHessian[2][2]));
		cHessian[0][1] = cHessian[1][0];
		cHessian[0][2] = cHessian[2][0];
		cHessian[1][2] = cHessian[2][1];
		return cHessian;
	}

	private void computeJacobian() {
		if ((jacobian) == null) {
			final double x = v.getX();
			final double y = v.getY();
			final double z = v.getZ();
			final double rho2 = (x * x) + (y * y);
			final double rho = org.apache.commons.math3.util.FastMath.sqrt(rho2);
			final double r2 = rho2 + (z * z);
			jacobian = new double[3][3];
			jacobian[0][0] = x / (r);
			jacobian[0][1] = y / (r);
			jacobian[0][2] = z / (r);
			jacobian[1][0] = (-y) / rho2;
			jacobian[1][1] = x / rho2;
			jacobian[2][0] = (x * z) / (rho * r2);
			jacobian[2][1] = (y * z) / (rho * r2);
			jacobian[2][2] = (-rho) / r2;
		} 
	}

	private void computeHessians() {
		if ((rHessian) == null) {
			final double x = v.getX();
			final double y = v.getY();
			final double z = v.getZ();
			final double x2 = x * x;
			final double y2 = y * y;
			final double z2 = z * z;
			final double rho2 = x2 + y2;
			final double rho = org.apache.commons.math3.util.FastMath.sqrt(rho2);
			final double r2 = rho2 + z2;
			final double xOr = x / (r);
			final double yOr = y / (r);
			final double zOr = z / (r);
			final double xOrho2 = x / rho2;
			final double yOrho2 = y / rho2;
			final double xOr3 = xOr / r2;
			final double yOr3 = yOr / r2;
			final double zOr3 = zOr / r2;
			rHessian = new double[3][3];
			rHessian[0][0] = (y * yOr3) + (z * zOr3);
			rHessian[1][0] = (-x) * yOr3;
			rHessian[2][0] = (-z) * xOr3;
			rHessian[1][1] = (x * xOr3) + (z * zOr3);
			rHessian[2][1] = (-y) * zOr3;
			rHessian[2][2] = (x * xOr3) + (y * yOr3);
			rHessian[0][1] = rHessian[1][0];
			rHessian[0][2] = rHessian[2][0];
			rHessian[1][2] = rHessian[2][1];
			thetaHessian = new double[2][2];
			thetaHessian[0][0] = (2 * xOrho2) * yOrho2;
			thetaHessian[1][0] = (yOrho2 * yOrho2) - (xOrho2 * xOrho2);
			thetaHessian[1][1] = ((-2) * xOrho2) * yOrho2;
			thetaHessian[0][1] = thetaHessian[1][0];
			final double rhor2 = rho * r2;
			final double rho2r2 = rho * rhor2;
			final double rhor4 = rhor2 * r2;
			final double rho3r4 = rhor4 * rho2;
			final double r2P2rho2 = (3 * rho2) + z2;
			phiHessian = new double[3][3];
			phiHessian[0][0] = (z * (rho2r2 - (x2 * r2P2rho2))) / rho3r4;
			phiHessian[1][0] = ((((-x) * y) * z) * r2P2rho2) / rho3r4;
			phiHessian[2][0] = (x * (rho2 - z2)) / rhor4;
			phiHessian[1][1] = (z * (rho2r2 - (y2 * r2P2rho2))) / rho3r4;
			phiHessian[2][1] = (y * (rho2 - z2)) / rhor4;
			phiHessian[2][2] = ((2 * rho) * zOr3) / (r);
			phiHessian[0][1] = phiHessian[1][0];
			phiHessian[0][2] = phiHessian[2][0];
			phiHessian[1][2] = phiHessian[2][1];
		} 
	}

	private java.lang.Object writeReplace() {
		return new org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates.DataTransferObject(v.getX() , v.getY() , v.getZ());
	}

	private static class DataTransferObject implements java.io.Serializable {
		private static final long serialVersionUID = 20130206L;

		private final double x;

		private final double y;

		private final double z;

		public DataTransferObject(final double x ,final double y ,final double z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		private java.lang.Object readResolve() {
			return new org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates(new org.apache.commons.math3.geometry.euclidean.threed.Vector3D(x , y , z));
		}
	}
}

