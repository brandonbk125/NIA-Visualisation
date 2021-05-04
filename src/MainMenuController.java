import EvolutionaryAlgorithm.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML private Spinner<Integer> bitStrLengthSpinner = new Spinner<>();
    @FXML private Spinner<Integer> popSpinner = new Spinner<>();
    @FXML private Spinner<Integer> offspringSpinner = new Spinner<>();
    @FXML private Spinner<Integer> tournamentSpinner = new Spinner<>();
    @FXML private ChoiceBox<MutationType> choiceMutation = new ChoiceBox<>();
    @FXML private ChoiceBox<SelectionType> choiceSelection = new ChoiceBox<>();
    @FXML private ChoiceBox<ReplacementType> choiceReplacement = new ChoiceBox<>();
    @FXML private ChoiceBox<CrossoverType> choiceCrossover = new ChoiceBox<>();
    @FXML private ChoiceBox<FitnessEvaluationType> choiceFitnessFunction = new ChoiceBox<>();

    private Alert alertOffspring2Small = new Alert(Alert.AlertType.ERROR);
    private Alert alertSelectOptions = new Alert(Alert.AlertType.ERROR);
    private Alert alertTournamentTooBig = new Alert(Alert.AlertType.ERROR);
    SpinnerValueFactory<Integer> valueFactory;




    @FXML
    public void initialize(URL location, ResourceBundle resources){
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 10000, 8);
        bitStrLengthSpinner.setValueFactory(valueFactory);
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5,10000, 5);
        popSpinner.setValueFactory(valueFactory);
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5,10000, 5);
        offspringSpinner.setValueFactory(valueFactory);
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10000, 2);
        tournamentSpinner.setValueFactory(valueFactory);

        choiceSelection.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldValue, newValue) -> {
            if(observableValue.getValue().intValue() == 1){
                tournamentSelected();
            }else{
                notTournamentSelected();
            }
        });


        alertSelectOptions.setContentText("Set all parameters before attempting to start the algorithm");
        alertOffspring2Small.setContentText("Comma Replacement requires offspring \nto be greater than or equal to population");
        alertTournamentTooBig.setContentText("Tournament size cannot be bigger than population size");

        loadData();
    }

    private void loadData(){
        choiceMutation.setItems(FXCollections.observableArrayList(MutationType.values()));
        choiceSelection.setItems(FXCollections.observableArrayList(SelectionType.values()));
        choiceReplacement.setItems(FXCollections.observableArrayList(ReplacementType.values()));
        choiceCrossover.setItems(FXCollections.observableArrayList(CrossoverType.values()));
        choiceFitnessFunction.setItems(FXCollections.observableArrayList(FitnessEvaluationType.values()));
    }

    @FXML
    private void startAlgorithm() throws IOException {
        if(choiceMutation.getValue() == null || choiceSelection.getValue() == null || choiceReplacement.getValue() == null || choiceCrossover.getValue() == null || choiceFitnessFunction.getValue() == null){
            alertSelectOptions.showAndWait();
        }else if(choiceReplacement.getValue() == ReplacementType.COMMA && offspringSpinner.getValue() < popSpinner.getValue()) {
            alertOffspring2Small.showAndWait();
        }else if(choiceSelection.getValue() == SelectionType.TOURNAMENT && tournamentSpinner.getValue() > popSpinner.getValue()){
            alertTournamentTooBig.showAndWait();
        }else {
            EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(bitStrLengthSpinner.getValue());
            ea.setMutationOperator(choiceMutation.getValue().getMutation());

            if(choiceSelection.getValue() == SelectionType.TOURNAMENT){
                ea.setTournamentSelection(new TournamentSelection(), tournamentSpinner.getValue());
            }else{
                ea.setSelectionType(choiceSelection.getValue().getSelection());
            }

            ea.setReplacementType(choiceReplacement.getValue().getReplacement());
            ea.setCrossoverType(choiceCrossover.getValue().getCrossover());
            ea.setFitnessEvaluation(choiceFitnessFunction.getValue().getFitnessEvaluation());
            ea.setPopSize(popSpinner.getValue());
            ea.setOffspringPopSize(offspringSpinner.getValue());
            GUI.showEAVisual(ea);
        }
    }

    private void tournamentSelected(){
        tournamentSpinner.setDisable(false);

    }

    private void notTournamentSelected(){
        tournamentSpinner.setDisable(true);
    }



}
