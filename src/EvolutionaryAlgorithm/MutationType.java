package EvolutionaryAlgorithm;

/**
 * @author brb19
 * Enum for mutation types
 */
public enum MutationType {
    STD_BIT("Standard Bit Mutation (1/n)", new StandardBitMutation()),
    bBIT_MUTATION("1 Bit Mutation", new bBitMutation(1));

    private String label;
    private Mutation mutation;

    /**
     *
     * @param label String value of the mutation type
     * @param mutation the mutation object
     */
    MutationType (String label, Mutation mutation){
        this.label = label;
        this.mutation = mutation;
    }

    /**
     *
     * @return the mutation object
     */
    public Mutation getMutation(){
        return mutation;
    }

    public String toString(){
        return label;
    }
}
