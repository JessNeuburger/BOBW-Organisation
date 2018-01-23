package bobworga.Controller;

import at.htl.florianschwarcz.organisationalstructurelib.Hierarchy;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BoBwController implements Initializable{
    @FXML
    private BorderPane mainWindowPane;
    @FXML
    private HrList hrList;

    private static Hierarchy hierarchyInstance;

    public static Hierarchy getHierarchyInstance() {
        if(hierarchyInstance==null){
            hierarchyInstance = new Hierarchy();
        }
        return hierarchyInstance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

     }
}
