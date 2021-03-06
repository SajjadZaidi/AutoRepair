package org.apache.commons.math3.genetics;


public class UniformCrossover<T> implements org.apache.commons.math3.genetics.CrossoverPolicy {
	private final double ratio;

	public UniformCrossover(final double ratio) throws org.apache.commons.math3.exception.OutOfRangeException {
		if ((ratio < 0.0) || (ratio > 1.0)) {
			throw new org.apache.commons.math3.exception.OutOfRangeException(org.apache.commons.math3.exception.util.LocalizedFormats.CROSSOVER_RATE , ratio , 0.0 , 1.0);
		} 
		this.ratio = ratio;
	}

	public double getRatio() {
		return ratio;
	}

	@java.lang.SuppressWarnings(value = "unchecked")
	public org.apache.commons.math3.genetics.ChromosomePair crossover(final org.apache.commons.math3.genetics.Chromosome first, final org.apache.commons.math3.genetics.Chromosome second) throws org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MathIllegalArgumentException {
		if (!((first instanceof org.apache.commons.math3.genetics.AbstractListChromosome<?>) && (second instanceof org.apache.commons.math3.genetics.AbstractListChromosome<?>))) {
			throw new org.apache.commons.math3.exception.MathIllegalArgumentException(org.apache.commons.math3.exception.util.LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME);
		} 
		return mate(((org.apache.commons.math3.genetics.AbstractListChromosome<T>)(first)), ((org.apache.commons.math3.genetics.AbstractListChromosome<T>)(second)));
	}

	private org.apache.commons.math3.genetics.ChromosomePair mate(final org.apache.commons.math3.genetics.AbstractListChromosome<T> first, final org.apache.commons.math3.genetics.AbstractListChromosome<T> second) throws org.apache.commons.math3.exception.DimensionMismatchException {
		final int length = first.getLength();
		if (length != (second.getLength())) {
			throw new org.apache.commons.math3.exception.DimensionMismatchException(second.getLength() , length);
		} 
		final java.util.List<T> parent1Rep = first.getRepresentation();
		final java.util.List<T> parent2Rep = second.getRepresentation();
		final java.util.List<T> child1Rep = new java.util.ArrayList<T>(length);
		final java.util.List<T> child2Rep = new java.util.ArrayList<T>(length);
		final org.apache.commons.math3.random.RandomGenerator random = org.apache.commons.math3.genetics.GeneticAlgorithm.getRandomGenerator();
		for (int index = 0 ; index < length ; index++) {
			if ((random.nextDouble()) < (ratio)) {
				child1Rep.add(parent2Rep.get(index));
				child2Rep.add(parent1Rep.get(index));
			} else {
				child1Rep.add(parent1Rep.get(index));
				child2Rep.add(parent2Rep.get(index));
			}
		}
		return new org.apache.commons.math3.genetics.ChromosomePair(first.newFixedLengthChromosome(child1Rep) , second.newFixedLengthChromosome(child2Rep));
	}
}

