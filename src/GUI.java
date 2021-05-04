import EvolutionaryAlgorithm.EvolutionaryAlgorithm;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {
    private static Stage primaryStage;
    private static BorderPane mainLayout;

    public void start(Stage primaryStage) throws Exception{
        GUI.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image("assets/logo.png"));
        GUI.primaryStage.setTitle("Evolutionary Algorithm");
        showEAMenu();

    }

    public static void showEAMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("MainMenu.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showEAVisual(EvolutionaryAlgorithm ea) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("EAVisual.fxml"));
        mainLayout = loader.load();
        EAVisualController controller = loader.getController();
        controller.initData(ea);
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

    }



}
