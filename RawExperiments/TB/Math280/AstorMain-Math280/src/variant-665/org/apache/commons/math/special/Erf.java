package org.apache.commons.math.special;


public class Erf {
    private Erf() {
        super();
    }

    public static double erf(double x) throws org.apache.commons.math.MathException {
        double ret = org.apache.commons.math.special.Gamma.regularizedGammaP(0.5, (x * x), 1.0E-15, 10000);
        if ((java.lang.Double.isNaN(x)) || (x <= 0.0)) {
            ret = java.lang.Double.NaN;
        } else {
            double g = 607.0 / 128.0;
            double sum = 0.0;
            for (int i = (org.apache.commons.math.special.Gamma.lanczos.length) - 1 ; i > 0 ; --i) {
                sum = sum + ((org.apache.commons.math.special.Gamma.lanczos[i]) / (x + i));
            }
            sum = sum + (org.apache.commons.math.special.Gamma.lanczos[0]);
            double tmp = (x + g) + 0.5;
            ret = ((((x + 0.5) * (java.lang.Math.log(tmp))) - tmp) + (org.apache.commons.math.special.Gamma.HALF_LOG_2_PI)) + (java.lang.Math.log((sum / x)));
        }
        return ret;
    }
}

