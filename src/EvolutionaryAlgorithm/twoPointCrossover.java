package EvolutionaryAlgorithm;

import java.util.Random;

/**
 * @author brb19
 * twoPointCrossover - implements a two point crossover for evolutionary algorithms
 */

public class twoPointCrossover implements Crossover {

    Random r = new Random();

    /**
     * creates a new instance of twoPointCrossover
     */
    public twoPointCrossover(){

    }

    /**
     * performs two point crossover given two individuals
     * @param p1 first individual
     * @param p2 second individual
     * @return result of the crossover
     */
    public char[] crossover(Individual p1, Individual p2){
        int index1 = r.nextInt(p1.toString().length());
        int index2 = r.nextInt(p1.toString().length() - index1) + index1;
        char[] offspring;
        String part1 = p1.toString().substring(0,index1);
        String part2 = p2.toString().substring(index1, index2);
        String part3 = p1.toString().substring(index2);
        String result = part1 + part2 + part3;
        offspring = result.toCharArray();
        return offspring;
    }

    public String toString(){
        return CrossoverType.TWO_POINT_CROSSOVER.toString();
    }

    /**
     *
     * @return the crossover type
     */
    public CrossoverType getType(){
        return CrossoverType.TWO_POINT_CROSSOVER;
    }

}
