package EvolutionaryAlgorithm;

/**
 * @author brb19
 * Enum for Fitness Evaluation types
 */
public enum FitnessEvaluationType {
    ONE_MAX("Generalised Onemax", new OneMax());

    private String label;
    private FitnessEvaluation fitnessEvaluation;

    /**
     *
     * @param label String value for the type
     * @param fitnessEvaluation fitness evaluation object
     */
    FitnessEvaluationType(String label, FitnessEvaluation fitnessEvaluation){
        this.label = label;
        this.fitnessEvaluation = fitnessEvaluation;
    }

    /**
     *
     * @return the fitness evaluation object
     */
    public FitnessEvaluation getFitnessEvaluation(){
        return fitnessEvaluation;
    }


    public String toString(){
        return label;
    }
}
