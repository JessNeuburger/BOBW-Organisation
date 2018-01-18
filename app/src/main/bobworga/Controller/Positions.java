package bobworga.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Positions extends AnchorPane{
    public Positions(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tabs/Positions.fxml"));
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
