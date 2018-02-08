package at.htl.erikmayrhofer.organisationalstructurecomponents.job;

import at.htl.florianschwarcz.organisationalstructurelib.Job;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class JobEditor extends GridPane {

    @FXML
    private TextField nameField;

    private SimpleObjectProperty<Job> job;

    public ObjectProperty<Job> jobProperty() {
        return job;
    }

    public Job getJob() {
        return job.get();
    }

    public void setJob(Job job) {
        this.job.set(job);
    }


    public JobEditor(Job job){
        this();
        setJob(job);
    }

    public JobEditor() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/job/JobEditor.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        job = new SimpleObjectProperty<>();
        job.addListener((observableValue, job, t1) -> {
            if(job != null)
                nameField.textProperty().unbindBidirectional(job.nameProperty());
            if(t1 != null){
                nameField.textProperty().bindBidirectional(t1.nameProperty());
            }
        });
    }
}
