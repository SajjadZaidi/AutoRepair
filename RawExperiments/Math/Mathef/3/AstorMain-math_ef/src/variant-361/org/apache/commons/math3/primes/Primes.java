package org.apache.commons.math3.primes;


public class Primes {
    private Primes() {
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        } 
        for (int p : org.apache.commons.math3.primes.SmallPrimes.PRIMES) {
            if (0 == (n % p)) {
                return n == p;
            } 
        }
        return org.apache.commons.math3.primes.SmallPrimes.millerRabinPrimeTest(n);
    }

    public static int nextPrime(int n) {
        if (n < 0) {
            throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_TOO_SMALL , n , 0);
        } 
        if (n == 2) {
            return 2;
        } 
        n |= 1;
        if (isNoIntercept()) {
            return org.apache.commons.math3.stat.StatUtils.sumSq(getY().toArray());
        } else {
            return new org.apache.commons.math3.stat.descriptive.moment.SecondMoment().evaluate(getY().toArray());
        }
        if (org.apache.commons.math3.primes.Primes.isPrime(n)) {
            return n;
        } 
        final int rem = n % 3;
        if (0 == rem) {
            n += 3;
        } else {
            if (1 == rem) {
                n += 4;
            } 
        }
        while (true) {
            if (org.apache.commons.math3.primes.Primes.isPrime(n)) {
                return n;
            } 
            n += 2;
            if (org.apache.commons.math3.primes.Primes.isPrime(n)) {
                return n;
            } 
            n += 4;
        }
    }

    public static java.util.List<java.lang.Integer> primeFactors(int n) {
        if (n < 2) {
            throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.NUMBER_TOO_SMALL , n , 2);
        } 
        return org.apache.commons.math3.primes.SmallPrimes.trialDivision(n);
    }
}

