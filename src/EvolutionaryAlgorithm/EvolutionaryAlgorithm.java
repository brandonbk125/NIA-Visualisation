package EvolutionaryAlgorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author brb19
 * EvolutionaryAlgoritm - models an evolutionary algorithm
 */
public class EvolutionaryAlgorithm {
    private String GOAL;
    private Mutation mutationOperator;
    private Crossover crossoverType;
    private Selection selectionType;
    private Replacement replacementType;
    private FitnessEvaluation fitnessEvaluation;

    private int tournamentSize;
    private int bitStrLength;
    private int popSize;
    private int offspringPopSize;

    private int generationNum = 0;

    private double avgFitness;
    private double stdDeviation;
    private double minFitness;
    private double maxFitness;

    private Population pop;
    private Scanner sc = new Scanner(System.in);


    /**
     * creates an empty evolutionary algorithm
     */
    public EvolutionaryAlgorithm(){

    }


    /**
     * creates an evolutionary algorithm with a set goal
     * @param goal the goal of the algorithm
     */
    public EvolutionaryAlgorithm(String goal){
        this.GOAL = goal;
    }

    /**
     * creates an evolutionary algorithm with a set goal length
     * @param bitStrLength length of goal bit string
     */
    public EvolutionaryAlgorithm(int bitStrLength){
        this.bitStrLength = bitStrLength;
        StringBuilder str = new StringBuilder();
        str.append("1".repeat(Math.max(0, bitStrLength)));
        GOAL = str.toString();

    }

    /**
     * initialise the evolutionary algorithm via user inputs at the terminal
     */
    public void init(){
        System.out.println("Evolutionary Algorithm");
        System.out.println("----------------------------");
        getMutationOperator();
        getSelectionOperator();
        getReplacementType();
        getCrossoverOperator();
        getFitnessOperator();

    }

    /**
     *
     * @param mutation sets the mutation operator of this evolutionary algorithm
     */
    public void setMutationOperator(Mutation mutation){
        this.mutationOperator = mutation;
    }

    /**
     *
     * @param selection sets the selection operator of this evolutionary algorithm
     */
    public void setSelectionType(Selection selection){
        this.selectionType = selection;
    }

    /**
     * if the selection type is tournament selection it requires an extra parameter
     * @param selectionType the tournament selection
     * @param size the size of the tournaments
     */
    public void setTournamentSelection(TournamentSelection selectionType, int size){
        this.selectionType = selectionType;
        this.tournamentSize = size;
        selectionType.setTournamentSize(tournamentSize);
    }

    /**
     *
     * @param replacement sets the replacement operator for this evolutionary algorithm
     */
    public void setReplacementType(Replacement replacement){
        this.replacementType = replacement;
    }

    /**
     *
     * @param crossover sets the crossover operator for this evolutionary algorithm
     */
    public void setCrossoverType(Crossover crossover){
        this.crossoverType = crossover;
    }

    /**
     *
     * @param fitnessEval sets the fitness evaluation for this evolutionary algorithm
     */
    public void setFitnessEvaluation(FitnessEvaluation fitnessEval){
        this.fitnessEvaluation = fitnessEval;
    }


    /**
     * initialises the population for this evolutionary algorithm based on the values for lambda and bitStrLength
     */
    public void initPopulation(){
        pop = new Population(popSize);
        pop.initPopulation(bitStrLength);
    }

    /**
     * this method will perform a number of runs of the algorithm
     * @param numIterations the number of iterations that the algorithm will have
     */
    public void run(int numIterations){
        //initialisation of population
        initPopulation();
        while(generationNum < numIterations){
            iteration();
        }
    }

    private void calcAvgFitness(Population pop){
        avgFitness = 0;
        for (int k = 0; k < pop.getSize(); k++) {
            System.out.print(pop.getIndividuals()[k]);
            System.out.println(" :: "+ pop.getIndividuals()[k].getFitness());
            avgFitness += pop.getIndividuals()[k].getFitness();
        }
        avgFitness /= pop.getSize();

    }

    private void calcStdDeviation(Population pop){
        double sum = 0.0;
        stdDeviation = 0.0;
        for(int i = 0; i < pop.getSize(); i++){
            sum += pop.getIndividuals()[i].getFitness();
        }
        double mean = sum/pop.getSize();
        for(int i = 0; i < pop.getSize(); i++){
            stdDeviation += Math.pow(pop.getIndividuals()[i].getFitness() - mean, 2);
        }
        stdDeviation = Math.sqrt(stdDeviation/pop.getSize());
    }

    private void calcMinFitness(Population pop){
        minFitness = pop.getIndividuals()[pop.getSize()-1].getFitness();
    }

    private void calcMaxFitness(Population pop){
        maxFitness = pop.getIndividuals()[0].getFitness();;
    }

    /**
     * runs one iteration of the evolutionary algorithm
     */
    public void iteration(){
        pop.evaluatePopulationFitness(GOAL, fitnessEvaluation);
        pop.sortIndivduals();

        System.out.print("Generation number: ");
        System.out.println(generationNum);
        //System.out.println("   DNA   :: FITNESS");
        calcAvgFitness(pop);
        calcStdDeviation(pop);
        calcMinFitness(pop);
        calcMaxFitness(pop);
        System.out.format("AVG FITNESS: %.2f\n", avgFitness);
        System.out.format("STD DEVIATION: %.2f\n", stdDeviation);
        System.out.println("----------------------------");

        Individual[] offspringArray = new Individual[offspringPopSize];
        for (int i = 0; i < offspringPopSize; i++ ){
            //selection for reproduction
            Individual parent1 = selectionType.select(pop);
            Individual parent2;
            do{
                parent2 = selectionType.select(pop);
            }while(parent1 == parent2);

            //crossover
            Individual offspring = new Individual(crossoverType.crossover(parent1, parent2));

            //mutation
            mutationOperator.mutation(offspring);

            offspring.setFitness(fitnessEvaluation.evaluate(GOAL, offspring));
            offspringArray[i] = offspring;
        }

        //replacement
        Arrays.sort(offspringArray);

            /*
            System.out.println("OFFSPRING ARRAY");
            for (int k = 0; k < pop.getSize(); k++) {
                System.out.print(offspringArray[k]);
                System.out.println(" :: "+ offspringArray[k].getFitness());
            }
            System.out.println("----------------------------");

             */
        pop.setPopulation(replacementType.replace(pop,offspringArray));
        generationNum++;
    }

    /**
     *
     * @return the average fitness of the current population
     */
    public double getCurrentAvgFitness(){
        return avgFitness;
    }

    /**
     *
     * @return the standard deviation of the current population
     */
    public double getCurrentStdDeviation(){
        return stdDeviation;
    }

    /**
     *
     * @return the min fitness of the current population
     */
    public double getMinFitness(){
        return minFitness;
    }

    /**
     *
     * @return the max fitness of the current population
     */
    public double getMaxFitness(){
        return maxFitness;
    }


    /**
     *
     * @return the generation number of the current population, which shows how many runs the algorithm has had
     */
    public int getCurrentGenerationNum(){
        return generationNum;
    }

    /**
     *
     * @return the current population
     */
    public Population getPopulation(){
        return pop;
    }


    private void getReplacementType(){
        int input;
        do{
            System.out.println("Replacement Type?");
            System.out.println("----------------------------");
            System.out.println("(1) - Comma replacement");
            System.out.println("(0) - Exit");
            System.out.println("----------------------------");
            System.out.print("Enter choice: ");

            input = sc.nextInt();
            switch (input){
                case 1:
                    this.replacementType = new PlusReplacement();
                    break;
                default:
                    break;
            }
        }while(input < 0);
    }

    private void getSelectionOperator(){
        int input;
        do{
            System.out.println("Selection Type?");
            System.out.println("----------------------------");
            System.out.println("(1) - Random");
            System.out.println("(0) - Exit");
            System.out.println("----------------------------");
            System.out.print("Enter choice: ");

            input = sc.nextInt();
            switch (input){
                case 1:
                    this.selectionType = new RandomSelection();
                    break;
                default:
                    break;
            }
        }while(input < 0);

    }

    private void getCrossoverOperator(){
        int input;
        do{
            System.out.println("Crossover Type?");
            System.out.println("----------------------------");
            System.out.println("(1) - Single Point Crossover");
            System.out.println("(0) - Exit");
            System.out.println("----------------------------");
            System.out.print("Enter choice: ");

            input = sc.nextInt();
            switch (input){
                case 1:
                    this.crossoverType = new singlePointCrossover();
                    break;
                default:
                    break;
            }
        }while(input < 0);

    }

    private void getFitnessOperator(){
        int input;
        do{
            System.out.println("Fitness Evaluation Type?");
            System.out.println("----------------------------");
            System.out.println("(1) - Generalised Onemax");
            System.out.println("(0) - Exit");
            System.out.println("----------------------------");
            System.out.print("Enter choice: ");

            input = sc.nextInt();
            switch (input){
                case 1:
                    this.fitnessEvaluation = new OneMax();
                    break;
                default:
                    break;
            }
        }while(input < 0);

    }

    private void getMutationOperator(){
        int input;
        do{
            System.out.println("Mutation Type?");
            System.out.println("----------------------------");
            System.out.println("(1) - Standard Bit Mutation");
            System.out.println("(2) - 1 Bit Mutation");
            System.out.println("(0) - Exit");
            System.out.println("----------------------------");
            System.out.print("Enter choice: ");

            input = sc.nextInt();
            switch (input){
                case 1:
                    this.mutationOperator = new StandardBitMutation();
                    break;
                case 2:
                    this.mutationOperator = new bBitMutation(1);
                    break;
                default:
                    break;
            }
        }while(input < 0);
    }

    /**
     *
     * @return the mutation operator
     */
    public Mutation getMutation(){
        return mutationOperator;
    }

    /**
     *
     * @return the crossover operator
     */
    public Crossover getCrossover(){
        return crossoverType;
    }

    /**
     *
     * @return the selection operator
     */
    public Selection getSelection(){
        return selectionType;
    }

    /**
     *
     * @return the fitness evaluation operator
     */
    public FitnessEvaluation getFitnessEvaluation(){
        return fitnessEvaluation;
    }

    /**
     *
     * @return the replacement operator
     */
    public Replacement getReplacement(){
        return replacementType;
    }

    /**
     *
     * @return the current goal of the evolutionary algorithm
     */
    public String getGOAL(){
        return GOAL;
    }

    /**
     *
     * @return the length of the bit strings used in this evolutionary algorithm
     */
    public int getBitStrLength(){
        return bitStrLength;
    }

    /**
     *
     * @param popSize the size of the population
     */
    public void setPopSize(int popSize){
        this.popSize = popSize;
    }

    /**
     *
     * @return the value of the population size
     */
    public int getPopSize(){
        return popSize;
    }

    /**
     *
     * @param offspringPopSize the size of the offspring population
     */
    public void setOffspringPopSize(int offspringPopSize){
        this.offspringPopSize = offspringPopSize;
    }

    /**
     *
     * @return the value of the offspring population size
     */
    public int getOffspringPopSize(){
        return offspringPopSize;
    }

    /**
     * if there is a tournament selection in the algorithm the size of the tournament is important to know
     * @return the size of the tournament
     */
    public int getTournamentSize(){
        return tournamentSize;
    }



}
