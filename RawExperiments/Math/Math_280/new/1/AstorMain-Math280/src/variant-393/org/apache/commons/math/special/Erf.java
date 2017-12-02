package org.apache.commons.math.special;


public class Erf {
    private Erf() {
        super();
    }

    public static double erf(double x) throws org.apache.commons.math.MathException {
        double ret = org.apache.commons.math.special.Gamma.regularizedGammaP(0.5, (x * x), 1.0E-15, 10000);
        if (x >= (org.apache.commons.math.special.Gamma.C_LIMIT)) {
            double inv = 1 / (x * x);
            return ((java.lang.Math.log(x)) - (0.5 / x)) - (inv * ((1.0 / 12) + (inv * ((1.0 / 120) - (inv / 252)))));
        } 
        return ret;
    }
}

