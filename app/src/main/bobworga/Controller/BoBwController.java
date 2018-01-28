package bobworga.Controller;

import at.htl.florianschwarcz.organisationalstructurelib.*;
import bobworga.utilclass.PersonClickEvent;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class BoBwController implements Initializable{
    @FXML
    private BorderPane mainWindowPane;
    @FXML
    private TabPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //hrList.setOnPersonDoubleClicked(personClickEvent -> tabPane.getSelectionModel().select(1));
        //humanResourcesTab.setHrListReference(hrList);
    }


}
