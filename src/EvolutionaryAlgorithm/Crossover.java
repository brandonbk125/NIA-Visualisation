package EvolutionaryAlgorithm;

/**
 * @author brb19
 * This interface is for crossover operators in evolutionary algorithms
 */
public interface Crossover {
    char[] crossover(Individual p1, Individual p2);
    CrossoverType getType();
}
