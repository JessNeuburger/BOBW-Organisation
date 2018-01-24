package bobworga.Controller;

import at.htl.erikmayrhofer.organigrampane.*;
import at.htl.erikmayrhofer.organisationalstructurecomponents.job.JobInfo;
import at.htl.florianschwarcz.organisationalstructurelib.Position;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class Positions extends BorderPane{

    @FXML
    private OrganigramPane organigramPane;

    public Positions(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tabs/Positions.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        organigramPane.setController(new OrganigramChildController(position -> {
            HBox box = new HBox();
            box.getChildren().add(new Label("Job"));
            box.getChildren().add(new JobInfo(position.getJob()));
            return box;
        }));
        organigramPane.getController().setHierarchy(BoBwController.getHierarchyInstance());
    }
}
