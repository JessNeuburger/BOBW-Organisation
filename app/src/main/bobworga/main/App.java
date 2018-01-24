package bobworga.main;

import bobworga.Controller.BoBwController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args){

        launch(args);
        System.out.println("Hello World");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = (BorderPane) FXMLLoader.load(getClass().getResource("/Window.fxml"));
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}