package EvolutionaryAlgorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * @author brb19
 * EvolutionaryAlgorithm.Population class - models a population of individuals
 *
 */
public class Population {

    private Random r = new Random();
    private Individual[] individuals;
    private int popSize;

    /**
     * makes a new population with n individuals
     * @param n Size of the population
     */
    public Population(int n){
        this.popSize = n;
        individuals = new Individual[popSize];

    }

    /**
     * makes a new population with a given array
     * @param array array of individuals
     */
    public Population(Individual[] array){
        this.individuals = array;
    }

    /**
     * initialises the populations dna
     */
    public void initPopulation(int n){
        char[] dna;

        for(int i = 0; i < individuals.length; i++){

            dna = new char[n];
            for(int j = 0; j < n; j++){
                dna[j] = (char) (49 - (r.nextInt(256)%2));
            }
            individuals[i] = new Individual(dna);
        }
    }


    /**
     * sets the population to a new set of individuals
     * @param individualsArray array of individuals
     */
    public void setPopulation(Individual[] individualsArray){
        this.individuals = individualsArray;
    }

    /**
     * removes an individual from the population of a given index
     * @param index index of the individual to remove
     */
    public void removeIndividual(int index){
        individuals[index] = null;
        System.arraycopy(individuals, index + 1, individuals, index, individuals.length - 1 - index);
        popSize--;
    }


    /**
     * evaluates the entire population towards a given goal
     * @param goal
     */
    public void evaluatePopulationFitness(String goal, FitnessEvaluation fitnessEvaluation){
        for(int i = 0; i < popSize; i++){
            individuals[i].setFitness(fitnessEvaluation.evaluate(goal, individuals[i]));
        }
    }


    /**
     * sorts the population by their fitness value
     */
    public void sortIndivduals(){
        Arrays.sort(individuals);
    }


    /**
     *
     * @return the individuals in this population as an array
     */
    public Individual[] getIndividuals(){
        return individuals;
    }

    /**
     *
     * @return the number of individuals in this population
     */
    public int getSize(){
        return popSize;
    }


}
