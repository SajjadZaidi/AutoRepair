package org.apache.commons.math3.exception;


public class NoBracketingException extends org.apache.commons.math3.exception.MathIllegalArgumentException {
	private static final long serialVersionUID = -3629324471511904459L;

	private final double lo;

	private final double hi;

	private final double fLo;

	private final double fHi;

	public NoBracketingException(double lo ,double hi ,double fLo ,double fHi) {
		this(org.apache.commons.math3.exception.util.LocalizedFormats.SAME_SIGN_AT_ENDPOINTS, lo, hi, fLo, fHi);
	}

	public NoBracketingException(org.apache.commons.math3.exception.util.Localizable specific ,double lo ,double hi ,double fLo ,double fHi ,java.lang.Object... args) {
		super(specific, java.lang.Double.valueOf(lo), java.lang.Double.valueOf(hi), java.lang.Double.valueOf(fLo), java.lang.Double.valueOf(fHi), args);
		this.lo = lo;
		this.hi = hi;
		this.fLo = fLo;
		this.fHi = fHi;
	}

	public double getLo() {
		return lo;
	}

	public double getHi() {
		return hi;
	}

	public double getFLo() {
		return fLo;
	}

	public double getFHi() {
		return fHi;
	}
}

