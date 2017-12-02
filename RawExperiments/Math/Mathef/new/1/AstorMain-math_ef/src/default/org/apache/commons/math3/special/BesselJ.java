package org.apache.commons.math3.special;


public class BesselJ implements org.apache.commons.math3.analysis.UnivariateFunction {
	private static final double PI2 = 0.6366197723675814;

	private static final double TOWPI1 = 6.28125;

	private static final double TWOPI2 = 0.001935307179586477;

	private static final double TWOPI = (org.apache.commons.math3.special.BesselJ.TOWPI1) + (org.apache.commons.math3.special.BesselJ.TWOPI2);

	private static final double ENTEN = 1.0E308;

	private static final double ENSIG = 1.0E16;

	private static final double RTNSIG = 1.0E-4;

	private static final double ENMTEN = 8.9E-308;

	private static final double X_MIN = 0.0;

	private static final double X_MAX = 10000.0;

	private static final double[] FACT = new double[]{ 1.0 , 1.0 , 2.0 , 6.0 , 24.0 , 120.0 , 720.0 , 5040.0 , 40320.0 , 362880.0 , 3628800.0 , 3.99168E7 , 4.790016E8 , 6.2270208E9 , 8.71782912E10 , 1.307674368E12 , 2.0922789888E13 , 3.55687428096E14 , 6.402373705728E15 , 1.21645100408832E17 , 2.43290200817664E18 , 5.109094217170944E19 , 1.1240007277776077E21 , 2.585201673888498E22 , 6.204484017332394E23 };

	private final double order;

	public BesselJ(double order) {
		this.order = order;
	}

	public double value(double x) throws org.apache.commons.math3.exception.ConvergenceException, org.apache.commons.math3.exception.MathIllegalArgumentException {
		return org.apache.commons.math3.special.BesselJ.value(order, x);
	}

	public static double value(double order, double x) throws org.apache.commons.math3.exception.ConvergenceException, org.apache.commons.math3.exception.MathIllegalArgumentException {
		final int n = ((int)(order));
		final double alpha = order - n;
		final int nb = n + 1;
		final org.apache.commons.math3.special.BesselJ.BesselJResult res = org.apache.commons.math3.special.BesselJ.rjBesl(x, alpha, nb);
		if ((res.nVals) >= nb) {
			return res.vals[n];
		} else if ((res.nVals) < 0) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.BESSEL_FUNCTION_BAD_ARGUMENT , order , x);
		} else if ((org.apache.commons.math3.util.FastMath.abs(res.vals[((res.nVals) - 1)])) < 1.0E-100) {
			return res.vals[n];
		} 
		throw new org.apache.commons.math3.exception.ConvergenceException(org.apache.commons.math3.exception.util.LocalizedFormats.BESSEL_FUNCTION_FAILED_CONVERGENCE , order , x);
	}

	public static class BesselJResult {
		private final double[] vals;

		private final int nVals;

		public BesselJResult(double[] b ,int n) {
			vals = org.apache.commons.math3.util.MathArrays.copyOf(b, b.length);
			nVals = n;
		}

		public double[] getVals() {
			return org.apache.commons.math3.util.MathArrays.copyOf(vals, vals.length);
		}

		public int getnVals() {
			return nVals;
		}
	}

	public static org.apache.commons.math3.special.BesselJ.BesselJResult rjBesl(double x, double alpha, int nb) {
		final double[] b = new double[nb];
		int ncalc = 0;
		double alpem = 0;
		double alp2em = 0;
		final int magx = ((int)(x));
		if (((((nb > 0) && (x >= (org.apache.commons.math3.special.BesselJ.X_MIN))) && (x <= (org.apache.commons.math3.special.BesselJ.X_MAX))) && (alpha >= 0)) && (alpha < 1)) {
			ncalc = nb;
			for (int i = 0 ; i < nb ; ++i) {
				b[i] = 0;
			}
			double tempa;
			double tempb;
			double tempc;
			double tover;
			if (x < (org.apache.commons.math3.special.BesselJ.RTNSIG)) {
				tempa = 1;
				alpem = 1 + alpha;
				double halfx = 0;
				if (x > (org.apache.commons.math3.special.BesselJ.ENMTEN)) {
					halfx = 0.5 * x;
				} 
				if (alpha != 0) {
					tempa = (org.apache.commons.math3.util.FastMath.pow(halfx, alpha)) / (alpha * (org.apache.commons.math3.special.Gamma.gamma(alpha)));
				} 
				tempb = 0;
				if ((x + 1) > 1) {
					tempb = (-halfx) * halfx;
				} 
				b[0] = tempa + ((tempa * tempb) / alpem);
				if ((x != 0) && ((b[0]) == 0)) {
					ncalc = 0;
				} 
				if (nb != 1) {
					if (x <= 0) {
						for (int n = 1 ; n < nb ; ++n) {
							b[n] = 0;
						}
					} else {
						tempc = halfx;
						tover = tempb != 0 ? (org.apache.commons.math3.special.BesselJ.ENMTEN) / tempb : (2 * (org.apache.commons.math3.special.BesselJ.ENMTEN)) / x;
						for (int n = 1 ; n < nb ; ++n) {
							tempa /= alpem;
							alpem += 1;
							tempa *= tempc;
							if (tempa <= (tover * alpem)) {
								tempa = 0;
							} 
							b[n] = tempa + ((tempa * tempb) / alpem);
							if (((b[n]) == 0) && (ncalc > n)) {
								ncalc = n;
							} 
						}
					}
				} 
			} else if ((x > 25.0) && (nb <= (magx + 1))) {
				final double xc = org.apache.commons.math3.util.FastMath.sqrt(((org.apache.commons.math3.special.BesselJ.PI2) / x));
				final double mul = 0.125 / x;
				final double xin = mul * mul;
				int m = 0;
				if (x >= 130.0) {
					m = 4;
				} else if (x >= 35.0) {
					m = 8;
				} else {
					m = 11;
				}
				final double xm = 4.0 * m;
				double t = ((double)((int)((x / (org.apache.commons.math3.special.BesselJ.TWOPI)) + 0.5)));
				final double z = ((x - (t * (org.apache.commons.math3.special.BesselJ.TOWPI1))) - (t * (org.apache.commons.math3.special.BesselJ.TWOPI2))) - ((alpha + 0.5) / (org.apache.commons.math3.special.BesselJ.PI2));
				double vsin = org.apache.commons.math3.util.FastMath.sin(z);
				double vcos = org.apache.commons.math3.util.FastMath.cos(z);
				double gnu = 2 * alpha;
				double capq;
				double capp;
				double s;
				double t1;
				double xk;
				for (int i = 1 ; i <= 2 ; i++) {
					s = ((((xm - 1) - gnu) * ((xm - 1) + gnu)) * xin) * 0.5;
					t = (gnu - (xm - 3.0)) * (gnu + (xm - 3.0));
					capp = (s * t) / (org.apache.commons.math3.special.BesselJ.FACT[(2 * m)]);
					t1 = (gnu - (xm + 1)) * (gnu + (xm + 1));
					capq = (s * t1) / (org.apache.commons.math3.special.BesselJ.FACT[((2 * m) + 1)]);
					xk = xm;
					int k = 2 * m;
					t1 = t;
					for (int j = 2 ; j <= m ; j++) {
						xk -= 4.0;
						s = ((xk - 1) - gnu) * ((xk - 1) + gnu);
						t = (gnu - (xk - 3.0)) * (gnu + (xk - 3.0));
						capp = (((capp + (1 / (org.apache.commons.math3.special.BesselJ.FACT[(k - 2)]))) * s) * t) * xin;
						capq = (((capq + (1 / (org.apache.commons.math3.special.BesselJ.FACT[(k - 1)]))) * s) * t1) * xin;
						k -= 2;
						t1 = t;
					}
					capp += 1;
					capq = ((capq + 1) * ((gnu * gnu) - 1)) * (0.125 / x);
					b[(i - 1)] = xc * ((capp * vcos) - (capq * vsin));
					if (nb == 1) {
						return new org.apache.commons.math3.special.BesselJ.BesselJResult(org.apache.commons.math3.util.MathArrays.copyOf(b, b.length) , ncalc);
					} 
					t = vsin;
					vsin = -vcos;
					vcos = t;
					gnu += 2.0;
				}
				if (nb > 2) {
					gnu = (2 * alpha) + 2.0;
					for (int j = 2 ; j < nb ; ++j) {
						b[j] = ((gnu * (b[(j - 1)])) / x) - (b[(j - 2)]);
						gnu += 2.0;
					}
				} 
			} else {
				final int nbmx = nb - magx;
				int n = magx + 1;
				int nstart = 0;
				int nend = 0;
				double en = 2 * (n + alpha);
				double plast = 1;
				double p = en / x;
				double pold;
				double test = 2 * (org.apache.commons.math3.special.BesselJ.ENSIG);
				boolean readyToInitialize = false;
				if (nbmx >= 3) {
					tover = (org.apache.commons.math3.special.BesselJ.ENTEN) / (org.apache.commons.math3.special.BesselJ.ENSIG);
					nstart = magx + 2;
					nend = nb - 1;
					en = 2 * ((nstart - 1) + alpha);
					double psave;
					double psavel;
					for (int k = nstart ; k <= nend ; k++) {
						n = k;
						en += 2.0;
						pold = plast;
						plast = p;
						p = ((en * plast) / x) - pold;
						if (p > tover) {
							tover = org.apache.commons.math3.special.BesselJ.ENTEN;
							p /= tover;
							plast /= tover;
							psave = p;
							psavel = plast;
							nstart = n + 1;
							do {
								n += 1;
								en += 2.0;
								pold = plast;
								plast = p;
								p = ((en * plast) / x) - pold;
							} while (p <= 1 );
							tempb = en / x;
							test = (pold * plast) * (0.5 - (0.5 / (tempb * tempb)));
							test /= org.apache.commons.math3.special.BesselJ.ENSIG;
							p = plast * tover;
							n -= 1;
							en -= 2.0;
							nend = org.apache.commons.math3.util.FastMath.min(nb, n);
							for (int l = nstart ; l <= nend ; l++) {
								pold = psavel;
								psavel = psave;
								psave = ((en * psavel) / x) - pold;
								if ((psave * psavel) > test) {
									ncalc = l - 1;
									readyToInitialize = true;
									break;
								} 
							}
							ncalc = nend;
							readyToInitialize = true;
							break;
						} 
					}
					if (!readyToInitialize) {
						n = nend;
						en = 2 * (n + alpha);
						test = org.apache.commons.math3.util.FastMath.max(test, ((org.apache.commons.math3.util.FastMath.sqrt((plast * (org.apache.commons.math3.special.BesselJ.ENSIG)))) * (org.apache.commons.math3.util.FastMath.sqrt((2 * p)))));
					} 
				} 
				if (!readyToInitialize) {
					do {
						n += 1;
						en += 2.0;
						pold = plast;
						plast = p;
						p = ((en * plast) / x) - pold;
					} while (p < test );
				} 
				n += 1;
				en += 2.0;
				tempb = 0;
				tempa = 1 / p;
				int m = (2 * n) - (4 * (n / 2));
				double sum = 0;
				double em = ((double)(n / 2));
				alpem = (em - 1) + alpha;
				alp2em = (2 * em) + alpha;
				if (m != 0) {
					sum = ((tempa * alpem) * alp2em) / em;
				} 
				nend = n - nb;
				boolean readyToNormalize = false;
				boolean calculatedB0 = false;
				for (int l = 1 ; l <= nend ; l++) {
					n -= 1;
					en -= 2.0;
					tempc = tempb;
					tempb = tempa;
					tempa = ((en * tempb) / x) - tempc;
					m = 2 - m;
					if (m != 0) {
						em -= 1;
						alp2em = (2 * em) + alpha;
						if (n == 1) {
							break;
						} 
						alpem = (em - 1) + alpha;
						if (alpem == 0) {
							alpem = 1;
						} 
						sum = ((sum + (tempa * alp2em)) * alpem) / em;
					} 
				}
				b[(n - 1)] = tempa;
				if (nend >= 0) {
					if (nb <= 1) {
						alp2em = alpha;
						if ((alpha + 1) == 1) {
							alp2em = 1;
						} 
						sum += (b[0]) * alp2em;
						readyToNormalize = true;
					} else {
						n -= 1;
						en -= 2.0;
						b[(n - 1)] = ((en * tempa) / x) - tempb;
						if (n == 1) {
							calculatedB0 = true;
						} else {
							m = 2 - m;
							if (m != 0) {
								em -= 1;
								alp2em = (2 * em) + alpha;
								alpem = (em - 1) + alpha;
								if (alpem == 0) {
									alpem = 1;
								} 
								sum = ((sum + ((b[(n - 1)]) * alp2em)) * alpem) / em;
							} 
						}
					}
				} 
				if ((!readyToNormalize) && (!calculatedB0)) {
					nend = n - 2;
					if (nend != 0) {
						for (int l = 1 ; l <= nend ; l++) {
							n -= 1;
							en -= 2.0;
							b[(n - 1)] = ((en * (b[n])) / x) - (b[(n + 1)]);
							m = 2 - m;
							if (m != 0) {
								em -= 1;
								alp2em = (2 * em) + alpha;
								alpem = (em - 1) + alpha;
								if (alpem == 0) {
									alpem = 1;
								} 
								sum = ((sum + ((b[(n - 1)]) * alp2em)) * alpem) / em;
							} 
						}
					} 
				} 
				if (!readyToNormalize) {
					if (!calculatedB0) {
						b[0] = (((2.0 * (alpha + 1)) * (b[1])) / x) - (b[2]);
					} 
					em -= 1;
					alp2em = (2 * em) + alpha;
					if (alp2em == 0) {
						alp2em = 1;
					} 
					sum += (b[0]) * alp2em;
				} 
				if ((org.apache.commons.math3.util.FastMath.abs(alpha)) > 1.0E-16) {
					sum *= (org.apache.commons.math3.special.Gamma.gamma(alpha)) * (org.apache.commons.math3.util.FastMath.pow((x * 0.5), (-alpha)));
				} 
				tempa = org.apache.commons.math3.special.BesselJ.ENMTEN;
				if (sum > 1) {
					tempa *= sum;
				} 
				for (n = 0 ; n < nb ; n++) {
					if ((org.apache.commons.math3.util.FastMath.abs(b[n])) < tempa) {
						b[n] = 0;
					} 
					b[n] /= sum;
				}
			}
		} else {
			if ((b.length) > 0) {
				b[0] = 0;
			} 
			ncalc = (org.apache.commons.math3.util.FastMath.min(nb, 0)) - 1;
		}
		return new org.apache.commons.math3.special.BesselJ.BesselJResult(org.apache.commons.math3.util.MathArrays.copyOf(b, b.length) , ncalc);
	}
}

