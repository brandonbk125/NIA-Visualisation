package EvolutionaryAlgorithm;

import java.util.Random;

/**
 * @author brb19
 * bBitMutation - implements b bit mutation for evolutionary algorithm
 */
public class bBitMutation implements Mutation {
    Random r = new Random();
    int b;

    /**
     * Creates a new instance of bBitMutation
     * @param numBitsToMutate the number of bits to mutate
     */
    public bBitMutation(int numBitsToMutate){
        this.b = numBitsToMutate;
    }

    /**
     * mutates the given individual
     * @param offspring the individual to be mutated
     * @return if the individual was mutated
     */
    public int mutation(Individual offspring){
        for (int i = 0; i < b; i++) {
            int j = r.nextInt(offspring.getDna().length);
            offspring.getDna()[j] = (char) (97 - offspring.getDna()[j]);
        }
        return 1;
    }

    public String toString(){
        return MutationType.bBIT_MUTATION.toString();
    }

    /**
     *
     * @return the type of mutation
     */
    public MutationType getType(){
        return MutationType.bBIT_MUTATION;
    }
}
