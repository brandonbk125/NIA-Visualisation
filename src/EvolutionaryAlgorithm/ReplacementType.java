package EvolutionaryAlgorithm;

/**
 * @author brb19
 * Enum for replacement types
 */
public enum ReplacementType {
    PLUS("Plus", new PlusReplacement()), COMMA("Comma", new CommaReplacement());

    private String label;
    private Replacement replacement;

    /**
     *
     * @param label the string value for the replacement type
     * @param replacement the object for the replacement type
     */
    ReplacementType (String label, Replacement replacement){
        this.label = label;
        this.replacement = replacement;
    }

    /**
     *
     * @return the object for the replacement
     */
    public Replacement getReplacement(){
        return this.replacement;
    }

    public String toString(){
        return label;
    }
}
