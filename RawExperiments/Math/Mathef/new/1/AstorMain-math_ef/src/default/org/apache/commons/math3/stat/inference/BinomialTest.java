package org.apache.commons.math3.stat.inference;


public class BinomialTest {
	public boolean binomialTest(int numberOfTrials, int numberOfSuccesses, double probability, org.apache.commons.math3.stat.inference.AlternativeHypothesis alternativeHypothesis, double alpha) {
		double pValue = binomialTest(numberOfTrials, numberOfSuccesses, probability, alternativeHypothesis);
		return pValue < alpha;
	}

	public double binomialTest(int numberOfTrials, int numberOfSuccesses, double probability, org.apache.commons.math3.stat.inference.AlternativeHypothesis alternativeHypothesis) {
		if (numberOfTrials < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(numberOfTrials);
		} 
		if (numberOfSuccesses < 0) {
			throw new org.apache.commons.math3.exception.NotPositiveException(numberOfSuccesses);
		} 
		if ((probability < 0) || (probability > 1)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(probability , 0 , 1);
		} 
		if (numberOfTrials < numberOfSuccesses) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER , numberOfTrials , numberOfSuccesses);
		} 
		if (alternativeHypothesis == null) {
			throw new org.apache.commons.math3.exception.NullArgumentException();
		} 
		final org.apache.commons.math3.distribution.BinomialDistribution distribution = new org.apache.commons.math3.distribution.BinomialDistribution(null , numberOfTrials , probability);
		switch (alternativeHypothesis) {
			case GREATER_THAN :
				return 1 - (distribution.cumulativeProbability((numberOfSuccesses - 1)));
			case LESS_THAN :
				return distribution.cumulativeProbability(numberOfSuccesses);
			case TWO_SIDED :
				int criticalValueLow = 0;
				int criticalValueHigh = numberOfTrials;
				double pTotal = 0;
				while (true) {
					double pLow = distribution.probability(criticalValueLow);
					double pHigh = distribution.probability(criticalValueHigh);
					if (pLow == pHigh) {
						pTotal += 2 * pLow;
						criticalValueLow++;
						criticalValueHigh--;
					} else if (pLow < pHigh) {
						pTotal += pLow;
						criticalValueLow++;
					} else {
						pTotal += pHigh;
						criticalValueHigh--;
					}
					if ((criticalValueLow > numberOfSuccesses) || (criticalValueHigh < numberOfSuccesses)) {
						break;
					} 
				}
				return pTotal;
			default :
				throw new org.apache.commons.math3.exception.MathInternalError(org.apache.commons.math3.exception.util.LocalizedFormats.OUT_OF_RANGE_SIMPLE , alternativeHypothesis , org.apache.commons.math3.stat.inference.AlternativeHypothesis.TWO_SIDED , org.apache.commons.math3.stat.inference.AlternativeHypothesis.LESS_THAN);
		}
	}
}

