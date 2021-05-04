package EvolutionaryAlgorithm;

/**
 * @author brb19
 * this interface is for selecion operators in evolutionary algorithms
 */
public interface Selection {
    Individual select(Population pop);
    SelectionType getType();
}
