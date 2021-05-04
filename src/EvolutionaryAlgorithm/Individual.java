package EvolutionaryAlgorithm;

/**
 * @author brb19
 * EvolutionaryAlgorithm.Individual - models an individual for evolutionary algorithm
 */
public class Individual implements Comparable<Individual>{
    private int fitness = 0;
    private char[] dna;

    public Individual(){
        dna = new char[0];

    }

    /**
     * makes a new individual with the value "x" in binary
     * @param x integer value of the dna
     */
    public Individual(int x){
        dna = String.format("%8s", Integer.toBinaryString(x)).toCharArray();
        this.dna = String.valueOf(dna).replace(' ', '0').toCharArray();
    }

    /**
     * makes a new individual with the String "s"
     * @param s String representation of the dna
     */
    public Individual(String s){
        this.dna = s.toCharArray();
    }

    /**
     * makes a new individual with the array c
     * @param c char array of dna
     */
    public Individual(char[] c){
        this.dna = c;
    }


    /**
     * compares this individual an another by their fitness values
     * @param individual the individual being compared to
     * @return positive int: the given individual is greater than this individual
     *         negative int: this individual is greater than the given individual
     *         0: they are both the same fitness value
     */
    @Override
    public int compareTo(Individual individual) {
        return individual.getFitness() - this.getFitness() ;
    }

    @Override
    public String toString() {
        return String.valueOf(dna);
    }

    /**
     *
     * @return this individuals fitness
     */
    public int getFitness(){
        return this.fitness;
    }

    /**
     * ssets this individuals fitness
     * @param f fitness
     */
    public void setFitness(int f){
        this.fitness = f;
    }

    /**
     *
     * @return this individuals dna
     */
    public char[] getDna(){
        return this.dna;
    }

    /**
     * changes this individuals dna
     * @param c new dna
     */
    public void changeDna(char[] c){
        dna = c;
    }


}
