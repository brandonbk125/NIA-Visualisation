import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        GUI userInterface = new GUI();
        Application.launch(GUI.class, args);

        /*
        EvolutionaryAlgorithm.EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm.EvolutionaryAlgorithm(8);
        ea.init();
        ea.setPopSize(10);
        ea.setOffspringPopSize(10);
        ea.run(5);
        */





















        

        /*
        Random r = new Random();
        EvolutionaryAlgorithm.Population p1 = new EvolutionaryAlgorithm.Population(6);
        p1.initPopulation();
        for (int i = 0; i < p1.getSize(); i++) {
            System.out.println(p1.getIndividuals()[i]);
            p1.getIndividuals()[i].evaluateFitness("00000101");
            System.out.println(p1.getIndividuals()[i].getFitness());
        }
        System.out.println("----------");

        EvolutionaryAlgorithm.Selection rSelect = new EvolutionaryAlgorithm.RandomSelection();
        EvolutionaryAlgorithm.Mutation mutationOperator = new EvolutionaryAlgorithm.StandardBitMutation(p1.getSize());
        System.out.println("Before mutation: ");
        EvolutionaryAlgorithm.Individual person1 = rSelect.select(p1);
        System.out.println(person1);
        EvolutionaryAlgorithm.Individual person2 = rSelect.select(p1);
        System.out.println(person2);

        int mutate = mutationOperator.mutation(person1);
        int mutate2 = mutationOperator.mutation(person2);
        System.out.println("First Mutated: "+mutate);
        System.out.println("Second Mutated: "+ mutate2);
        System.out.println("After mutation: ");
        System.out.println(person1);
        System.out.println(person2);
        System.out.println("----------");


        for (int i = 0; i < p1.getSize(); i++) {
            System.out.println(p1.getIndividuals()[i]);
        }


        /*
        p1.evaluatePopulationFitness("01110111");
        for (int i = 0; i < p1.getSize(); i++) {
            System.out.println(p1.getIndividuals()[i].getFitness());
        }
        System.out.println("\n");
        for (int i = 0; i < p1.getSize(); i++) {
            System.out.println(p1.getIndividuals()[i]);
        }
        System.out.println("\n");

        */

        /*
        EvolutionaryAlgorithm.StandardBitMutation mutation = new EvolutionaryAlgorithm.StandardBitMutation();
        EvolutionaryAlgorithm.Individual person1;
        EvolutionaryAlgorithm.Individual person2;
        String s = "01010101";
        person1 = new EvolutionaryAlgorithm.Individual(s);
        person2 = new EvolutionaryAlgorithm.Individual();
        System.out.println(person1);
        System.out.println(person2);
        for (int i = 0; i < 10; i++){
            int n = mutation.mutation(person1, person2);
            System.out.println(n);
            System.out.println(person1);
            System.out.println(person2);

        }
        */


        /*
        System.out.println("----------------");
        EvolutionaryAlgorithm.Individual person3;
        EvolutionaryAlgorithm.RandomSelection rSelect = new EvolutionaryAlgorithm.RandomSelection();
        for (int i = 0; i < 10; i++) {
            person3 = rSelect.select(p1);
            System.out.println(person3);

        }
        */





    }
}
