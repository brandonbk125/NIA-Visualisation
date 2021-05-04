package EvolutionaryAlgorithm;

import java.util.Random;

/**
 * @author brb19
 * RandomSelection - implements random selection for evolutionary algorithm
 */
public class RandomSelection implements Selection {
    Random r;

    public RandomSelection(){
        r = new Random();
    }

    /**
     * selects an individual at random from a given population
     * @param pop population to select from
     * @return the chosen individual
     */
    public Individual select(Population pop){
        int selectionIndex = r.nextInt(pop.getSize());
        return pop.getIndividuals()[selectionIndex];
    }

    public String toString(){
        return SelectionType.RANDOM.toString();
    }

    /**
     *
     * @return the selection type
     */
    public SelectionType getType(){
        return SelectionType.RANDOM;
    }

}
