package main;

import com.sun.media.jfxmedia.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("AddressApp");
        initRootLayout(primaryStage);
        showPersonOverview();
    }

    private void initRootLayout(Stage primary){
        try {

            System.out.println(Thread.currentThread().getContextClassLoader().getResource("view/RootLayout.fxml"));
            System.out.println(App.class.getResource("/view/RootLayout.fxml"));
            System.out.println("Location");
            rootLayout = FXMLLoader.load(App.class.getResource("/view/RootLayout.fxml"));
            System.out.println("Loaded");

            Scene scene = new Scene(rootLayout);
            primary.setScene(scene);
            primary.show();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void showPersonOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/view/PersonView.fxml"));
            AnchorPane personView = loader.load();

            rootLayout.setCenter(personView);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
