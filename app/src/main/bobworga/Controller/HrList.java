package bobworga.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HrList extends VBox{
    public HrList(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HrList.fxml"));
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
