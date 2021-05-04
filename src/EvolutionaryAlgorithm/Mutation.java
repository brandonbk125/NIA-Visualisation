package EvolutionaryAlgorithm;

/**
 * @author brb19
 * this interface is for mutation operators used in evolutionary algorithms
 */
public interface Mutation {
    int mutation(Individual offspring);
    MutationType getType();
}
