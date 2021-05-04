package EvolutionaryAlgorithm;

/**
 * @author brb19
 * this interface is for replacement operators used in evolutionary algorithms
 */
public interface Replacement {
    Individual[] replace(Population pop, Individual[] offspring);
    ReplacementType getType();
}
