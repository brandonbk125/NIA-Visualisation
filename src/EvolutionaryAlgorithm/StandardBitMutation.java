package EvolutionaryAlgorithm;

import java.util.Random;
import java.lang.Math;

/**
 * @author brb19
 * StandardBitMutation - implements standard bit mutation for evolurionary algorithm
 * The code found in this class was inspired by a C implementation of this operator
 * that was found in the appendix of CSM6520: Evolutionary Computing: Variation Operators
 */



public class StandardBitMutation implements Mutation {
    private int nextPos;
    private double logbasis;

    private Random r = new Random();

    public StandardBitMutation(){

    }

    /**
     *
     * @param n length of individuals data to be mutated
     */
    private void init(int n){
        double pm = 1.0 / n;
        nextPos = -1;
        logbasis = Math.log(1.0 - pm);
        long doubleImprecision;
        if(logbasis == 0){
            System.out.println("Mutation probability too low, "+ pm);
            doubleImprecision = -1;
        }else{
            doubleImprecision = (long)Math.floor(Math.log(Math.pow(2.0, -47.0)) / logbasis);
            if (doubleImprecision <0){
                doubleImprecision = Long.MAX_VALUE;
            }
        }

    }

    /**
     *
     * @param offspring the individual to be mutated
     * @return if the individual was mutated
     */
    public int mutation( Individual offspring){
        init(offspring.toString().length());
        int next,start = 0;
        int mutated = 0;
        char[] offspringdna = offspring.toString().toCharArray();
        int n = offspringdna.length;
        next = getNextPos(n-1);
        if (next != -1){
            mutated = 1;
            while(next != -1){
                offspringdna[start + next] = (char)( (97 - offspringdna[start+next]));
                start += (next +1);
                next = getNextPos(n - start - 1);
            }
            offspring.changeDna(offspringdna);
        }

        return mutated;
    }

    private int getNextPos(int length) {
        if (nextPos >= 0) {
            return savePos(nextPos, length);
        }
        nextPos = (int) Math.floor(Math.log(r.nextDouble()) / logbasis);
        return savePos(nextPos, length);
    }

    private int savePos(int pos, int length){
        if(pos > length){
            nextPos = pos-length-1;
            return -1;
        }else{
            nextPos = -1;
            return pos;
        }
    }

    public String toString(){
        return MutationType.STD_BIT.toString();
    }

    /**
     *
     * @return the type of mutation
     */
    public MutationType getType(){
        return MutationType.STD_BIT;
    }

}
