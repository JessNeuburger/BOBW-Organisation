package at.htl.erikmayrhofer.organisationalstructurecomponents.job;

import at.htl.florianschwarcz.organisationalstructurelib.Job;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class JobInfo extends VBox{

    @FXML
    private Label nameLabel;

    private SimpleObjectProperty<Job> job;

    public SimpleObjectProperty<Job> jobProperty() {
        return job;
    }

    public Job getJob() {
        return job.get();
    }

    public void setJob(Job job) {
        this.job.set(job);
    }


    public JobInfo(Job job){
        this();
        setJob(job);
    }

    public JobInfo(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/job/JobInfo.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        job = new SimpleObjectProperty<>();
        job.addListener((observableValue, job, t1) -> {
            nameLabel.textProperty().unbind();
            if(t1 != null){
                nameLabel.textProperty().bind(t1.nameProperty());
            }
        });

    }
}
