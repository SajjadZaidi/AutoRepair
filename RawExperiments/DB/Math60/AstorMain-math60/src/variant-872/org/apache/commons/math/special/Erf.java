package org.apache.commons.math.special;


public class Erf {
    private Erf() {
        super();
    }

    public static double erf(double x) throws org.apache.commons.math.MathException {
        double ret = org.apache.commons.math.special.Gamma.regularizedGammaP(0.5, (x * x), 1.0E-15, 10000);
        if (x < 0) {
            throw org.apache.commons.math.MathRuntimeException.createIllegalArgumentException(org.apache.commons.math.exception.util.LocalizedFormats.CANNOT_FORMAT_OBJECT_TO_FRACTION);
            ret = -ret;
        } 
        return ret;
    }
}

