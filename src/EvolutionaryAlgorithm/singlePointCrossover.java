package EvolutionaryAlgorithm;


/**
 * @author brb19
 * singlePointCrossover - implements single point crossover for evolutionary algorithms
 */

import java.util.Random;

public class singlePointCrossover implements Crossover {

    Random r = new Random();

    public singlePointCrossover(){

    }

    /**
     * crossover with 2 parents to create an offspring
     * @param p1 parent 1
     * @param p2 parent 2
     * @return the offspring result from the crossover of p1 and p2
     */
    public char[] crossover(Individual p1, Individual p2){
        int index = r.nextInt(p1.toString().length());
        char[] offspring;
        String half1 = p1.toString().substring(0,index);
        String half2 = p2.toString().substring(index);
        offspring = half1.concat(half2).toCharArray();
        return offspring;
    }

    public String toString(){
        return CrossoverType.SINGLE_POINT_CROSSOVER.toString();
    }

    /**
     *
     * @return the type of crossover
     */
    public CrossoverType getType(){
        return CrossoverType.SINGLE_POINT_CROSSOVER;
    }
}
