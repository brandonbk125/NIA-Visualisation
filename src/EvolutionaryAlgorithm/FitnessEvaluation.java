package EvolutionaryAlgorithm;

/**
 * @author brb19
 * this interface is for fitness evaluation functions in evoltionary algorithms
 */
public interface FitnessEvaluation {
    int evaluate(String goal, Individual individual);
    FitnessEvaluationType getType();
}
