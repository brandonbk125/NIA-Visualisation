package EvolutionaryAlgorithm;

/**
 * @author brb19
 * CommaReplacement implements a (lambda, mu) replacement operator for evolutionary algorithm
 */
public class CommaReplacement implements Replacement{

    /**
     * creates a new instance of comma replacement
     */
    public CommaReplacement(){

    }

    /**
     * performs the comma replacement
     * @param pop the current population
     * @param offspring the new individuals to make the new population with
     * @return the new population
     */
    public Individual[] replace(Population pop, Individual[] offspring){
        Individual[] nextGen = new Individual[pop.getSize()];

        for(int i = 0; i < pop.getSize(); i++){
            nextGen[i] = offspring[i];
        }

        return nextGen;
    }


    public String toString(){
        return ReplacementType.COMMA.toString();
    }

    /**
     *
     * @return the type of replacement
     */
    public ReplacementType getType(){
        return ReplacementType.COMMA;
    }

}
