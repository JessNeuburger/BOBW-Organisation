package bobworga.Controller;

import at.htl.erikmayrhofer.organisationalstructurecomponents.job.JobEditor;
import at.htl.florianschwarcz.organisationalstructurelib.Job;
import at.htl.florianschwarcz.organisationalstructurelib.Person;
import at.htl.florianschwarcz.organisationalstructurelib.Profile;
import bobworga.model.Repository;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Jobs extends BorderPane{

    @FXML
    private ListView<Job> jobsListView;
    @FXML
    private JobEditor jobEditor;
    @FXML
    private Button addJobButton;

    public Jobs(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tabs/Jobs.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jobsListView.setCellFactory(new javafx.util.Callback<ListView<Job>, ListCell<Job>>() {
            @Override
            public ListCell<Job> call(ListView<Job> personListView) {
                return new ListCell<Job>(){
                    @Override
                    protected void updateItem(Job item, boolean empty) {
                        super.updateItem(item, empty);
                        if(!empty)
                            textProperty().bind(item.nameProperty());
                    }
                };
            }
        });
        jobsListView.setItems(Repository.getInstance().getJobs());

        jobEditor.jobProperty().bind(jobsListView.getSelectionModel().selectedItemProperty());

        addJobButton.setOnAction(actionEvent -> {
            Repository.getInstance().getJobs().add(new Job("newJob", new Profile()));
        });
    }
}
