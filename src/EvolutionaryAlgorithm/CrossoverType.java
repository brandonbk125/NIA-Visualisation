package EvolutionaryAlgorithm;

/**
 * @author brb19
 * Enum for crossover types
 */
public enum CrossoverType {
    SINGLE_POINT_CROSSOVER("Single point crossover", new singlePointCrossover()),
    TWO_POINT_CROSSOVER("Two point crossover", new twoPointCrossover());

    private String label;
    private Crossover crossover;

    /**
     *
     * @param label the string value for the crossover operator
     * @param crossover the object for that crossover operator
     */
    CrossoverType (String label, Crossover crossover){
        this.label = label;
        this.crossover = crossover;
    }

    /**
     *
     * @return the crossover object
     */
    public Crossover getCrossover(){
        return crossover;
    }

    public String toString(){
        return label;
    }
}
