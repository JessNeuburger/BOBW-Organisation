package at.htl.erikmayrhofer.organisationalstructurecomponents.job;

import at.htl.florianschwarcz.organisationalstructurelib.Job;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class JobInfo extends HBox{

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
    }
}
