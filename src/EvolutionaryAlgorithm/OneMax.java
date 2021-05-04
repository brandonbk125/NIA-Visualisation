package EvolutionaryAlgorithm;

/**
 * @author brb19
 * Implements hamming distance as a fitness evaluation function. Also known as 'Generalise Onemax'.
 */
public class OneMax implements FitnessEvaluation{

    /**
     * creates a new instance of Hamming Distance
     */
    public OneMax(){

    }

    /**
     * This method evaluates the fitness of an individual by calculating the hamming distance
     * @param goal the goal of the evolutionary algorithm
     * @param individual the individual being evaluated
     * @return The fitness of the indivdual
     */
    public int evaluate(String goal, Individual individual) {
        int fitness = 0;
        for(int i = 0; i < goal.length(); i++){
            if(individual.getDna()[i] == goal.charAt(i)){
                fitness++;
            }
        }
        return fitness;
    }

    public String toString(){
        return FitnessEvaluationType.ONE_MAX.toString();
    }

    /**
     *
     * @return the type of fitness evaluation
     */
    public FitnessEvaluationType getType(){
        return FitnessEvaluationType.ONE_MAX;
    }
}
