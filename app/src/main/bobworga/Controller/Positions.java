package bobworga.Controller;

import at.htl.erikmayrhofer.organigrampane.*;
import at.htl.erikmayrhofer.organisationalstructurecomponents.job.JobInfo;
import at.htl.florianschwarcz.organisationalstructurelib.Position;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
            VBox box = new VBox();
            box.getChildren().add(new Label("Job"));
            box.getChildren().add(new JobInfo(position.getJob()));

            Button button = new Button("Add Child");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println("Adding new Child to position: "+position);
                }
            });
            box.getChildren().add(button);
            return box;
        }));
        organigramPane.getController().setHierarchy(BoBwController.getHierarchyInstance());
    }
}
