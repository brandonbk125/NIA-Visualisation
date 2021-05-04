package EvolutionaryAlgorithm;
import java.util.Arrays;
import java.util.Random;

/**
 * @author brb19
 * TournamentSelection - implements tournament selection for evolutionary algorithms
 */
public class TournamentSelection implements Selection {
    private int tournamentSize;
    private Random r = new Random();

    /**
     * creates a new instance of tournament selection
     */
    public TournamentSelection(){

    }

    /**
     * selects an individual from the population via tournament selection
     * @param pop the current population
     * @return the selected individual
     */
    public Individual select(Population pop) {
        Individual[] tournament = getTournament(pop);
        Individual winner = tournament[0];
        for (int i = 1; i < tournamentSize; i++){
            if(winner.getFitness() < tournament[i].getFitness()){
                winner = tournament[i];
            }
        }

        return winner;
    }

    private Individual[] getTournament(Population pop){
        Individual[] tournament = new Individual[tournamentSize];
        int[] indexArray = new int[tournamentSize];
        Arrays.fill(indexArray, -1);
        int index;
        for (int i = 0; i < tournamentSize; i++){
            do{
                index = r.nextInt(pop.getSize());
            }while(contains(indexArray, index));


            tournament[i] = pop.getIndividuals()[index];
            indexArray[i] = index;
        }
        return tournament;
    }

    /**
     * sets the size of the tournament
     * @param tournamentSize the size of the tournament
     */
    public void setTournamentSize(int tournamentSize){
        this.tournamentSize = tournamentSize;
    }

    private static boolean contains(int[] arr, int index){
        for(int i: arr){
            if(i == index){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return SelectionType.TOURNAMENT.toString();
    }

    /**
     *
     * @return the type of selection
     */
    public SelectionType getType(){
        return SelectionType.TOURNAMENT;
    }
}
