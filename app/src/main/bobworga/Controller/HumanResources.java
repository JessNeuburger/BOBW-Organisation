package bobworga.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class HumanResources extends BorderPane{
    public HumanResources(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tabs/HumanResources.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("BLUP");
            e.printStackTrace();
        }
    }
}
