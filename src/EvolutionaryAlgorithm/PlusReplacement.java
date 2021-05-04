package EvolutionaryAlgorithm;

/**
 * @author brb19
 * PlusReplacement - implements (mu + lambda) selection for replacement for evolutionary algorithm
 */

public class PlusReplacement implements Replacement {
    public PlusReplacement(){

    }

    /**
     * replaces the current population using the plus replacement method
     * @param pop current population
     * @param offspring array of offspring to be in the next generation
     * @return array of individuals that will be the next generation
     */
    public Individual[] replace(Population pop, Individual[] offspring){
        Individual[] nextGen = new Individual[pop.getSize()];
        int i = 0;
        int j = 0;


        //if mu > lambda
        if(pop.getSize() > offspring.length){
            for (int n = 0; n < offspring.length; n++){
                //System.out.println("comparing: "+pop.getIndividuals()[i].getFitness()+" and "+offspring[j].getFitness());
                if (pop.getIndividuals()[i].compareTo(offspring[j]) < 0) {
                    //System.out.println("choosing parent: "+pop.getIndividuals()[i].getFitness());
                    nextGen[n] = pop.getIndividuals()[i];
                    i++;
                } else {
                    //System.out.println("choosing offspring: "+offspring[j].getFitness());
                    nextGen[n] = offspring[j];
                    j++;
                }
            }
            for(int n = offspring.length; n < pop.getSize(); n++){
                nextGen[n] = pop.getIndividuals()[i];
                i++;
            }
        }
        //else mu = lambda or mu < lambda
        else {
            for (int n = 0; n < pop.getSize(); n++) {
                //System.out.println("comparing: "+pop.getIndividuals()[i].getFitness()+" and "+offspring[j].getFitness());
                if (pop.getIndividuals()[i].compareTo(offspring[j]) < 0) {
                    //System.out.println("choosing parent: "+pop.getIndividuals()[i].getFitness());
                    nextGen[n] = pop.getIndividuals()[i];
                    i++;
                } else {
                    //System.out.println("choosing offspring: "+offspring[j].getFitness());
                    nextGen[n] = offspring[j];
                    j++;
                }

            }
        }


        return nextGen;
    }

    public String toString(){
        return ReplacementType.PLUS.toString();
    }

    /**
     *
     * @return the type of replacement
     */
    public ReplacementType getType(){
        return ReplacementType.PLUS;
    }

}
