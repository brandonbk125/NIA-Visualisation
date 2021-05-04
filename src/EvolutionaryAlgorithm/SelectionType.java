package EvolutionaryAlgorithm;

/**
 * @author brb19
 */
public enum SelectionType {
    RANDOM("Random", new RandomSelection()), TOURNAMENT("Tournament", new TournamentSelection());

    private String label;
    private Selection selection;

    /**
     *
     * @param label the string value for the selection type
     * @param selection the object for the selection type
     */
    SelectionType (String label, Selection selection){
        this.label = label;
        this.selection= selection;
    }

    /**
     *
     * @return the object for the selection type
     */
    public Selection getSelection(){
        return selection;
    }

    public String toString(){
        return label;
    }
}
