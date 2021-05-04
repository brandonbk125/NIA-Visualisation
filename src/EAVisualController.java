import EvolutionaryAlgorithm.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EAVisualController implements Initializable {

    private EvolutionaryAlgorithm evolutionaryAlgorithm;
    @FXML private LineChart<String, Double> EAGraph;
    @FXML private TextFlow txtFlowEAInfo;
    @FXML private CheckBox ckbAvgFitness;
    @FXML private CheckBox ckbStdDev = new CheckBox();
    @FXML private CheckBox ckbMaxFit = new CheckBox();
    @FXML private CheckBox ckbMinFit = new CheckBox();
    private XYChart.Series<String, Double> averageFitness = new XYChart.Series<>();
    private XYChart.Series<String, Double> stdDeviation = new XYChart.Series<>();
    private XYChart.Series<String, Double> maxFitness = new XYChart.Series<>();
    private XYChart.Series<String, Double> minFitness = new XYChart.Series<>();


    public void initData(EvolutionaryAlgorithm ea){
        Text eaInfo = new Text("Mutation Type: " + ea.getMutation().toString() +
                "\n\n Selection Type: " + ea.getSelection().toString() +
                "\n\n Replacement Type: " + ea.getReplacement().toString() +
                "\n\n Crossover Type: " + ea.getCrossover().toString() +
                "\n\n Fitness Evaluation: " + ea.getFitnessEvaluation().toString() +
                "\n\n Bit String Length: " + ea.getBitStrLength() +
                "\n\n Population Size: " + ea.getPopSize() +
                "\n\n Offspring Size: " + ea.getOffspringPopSize());
        if((ea.getSelection().getType().equals(SelectionType.TOURNAMENT))){
            eaInfo.setText(eaInfo.getText().concat("\n\n Tournament Size: " + ea.getTournamentSize()));
        }

        evolutionaryAlgorithm = new EvolutionaryAlgorithm(ea.getBitStrLength());
        evolutionaryAlgorithm.setMutationOperator(ea.getMutation());
        evolutionaryAlgorithm.setFitnessEvaluation(ea.getFitnessEvaluation());
        evolutionaryAlgorithm.setCrossoverType(ea.getCrossover());
        evolutionaryAlgorithm.setReplacementType(ea.getReplacement());
        evolutionaryAlgorithm.setSelectionType(ea.getSelection());
        evolutionaryAlgorithm.setPopSize(ea.getPopSize());
        evolutionaryAlgorithm.setOffspringPopSize(ea.getOffspringPopSize());
        evolutionaryAlgorithm.initPopulation();

        txtFlowEAInfo.getChildren().add(eaInfo);


    }

    @FXML
    public void next(){
        evolutionaryAlgorithm.iteration();
        String gen = String.valueOf(evolutionaryAlgorithm.getCurrentGenerationNum());
        averageFitness.getData().add(new XYChart.Data<>(gen, evolutionaryAlgorithm.getCurrentAvgFitness()));
        stdDeviation.getData().add(new XYChart.Data<>(gen, evolutionaryAlgorithm.getCurrentStdDeviation()));
        maxFitness.getData().add(new XYChart.Data<>(gen, evolutionaryAlgorithm.getMaxFitness()));
        minFitness.getData().add(new XYChart.Data<>(gen, evolutionaryAlgorithm.getMinFitness()));
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources){
        averageFitness.setName("Average fitness");
        stdDeviation.setName("Std deviation");
        maxFitness.setName("Max Fitness");
        minFitness.setName("Min Fitness");
        EAGraph.getXAxis().setLabel("Generation Number");
        EAGraph.getYAxis().setLabel("Fitness");

        addCheckBoxListenerForGraph(ckbAvgFitness, averageFitness);
        addCheckBoxListenerForGraph(ckbStdDev, stdDeviation);
        addCheckBoxListenerForGraph(ckbMaxFit, maxFitness);
        addCheckBoxListenerForGraph(ckbMinFit, minFitness);



    }

    @FXML
    public void backButton() throws IOException {
        GUI.showEAMenu();
    }

    private void addCheckBoxListenerForGraph(CheckBox ckb, XYChart.Series<String, Double> series){
        ckb.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(observableValue.getValue()){
                EAGraph.getData().add(series);
            }else{
                EAGraph.getData().remove(series);
            }
        });


    }

}
