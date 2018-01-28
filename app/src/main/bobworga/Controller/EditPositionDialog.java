package bobworga.Controller;

import at.htl.florianschwarcz.organisationalstructurelib.Job;
import at.htl.florianschwarcz.organisationalstructurelib.Person;
import at.htl.florianschwarcz.organisationalstructurelib.Position;
import bobworga.model.Repository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EditPositionDialog extends BorderPane {

    private Position position;

    @FXML
    private ChoiceBox<Job> jobChoice;
    @FXML
    private ChoiceBox<Person> personChoice;

    @FXML
    private Button saveButton;
    @FXML
    private Button abortButton;
    @FXML
    private ChoiceBox<Position> superordinateChoice;


    private EventHandler<ActionEvent> onFinishedHandler;

    public EditPositionDialog(Position position){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditPositionDialog.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.position = position;

        System.out.println(jobChoice);

        jobChoice.setItems(Repository.getInstance().getJobs());

        List<Person> p = new ArrayList<>(Repository.getInstance().getUnemployed());
        if(position.getPerson()!=null){
            p.add(position.getPerson());
        }
        p.add(0,null);

        personChoice.setItems(FXCollections.observableList(p));

        List<Position> positions = Repository.getInstance().getPositions();
        positions.remove(position);
        positions.sort(Comparator.comparing(o -> o.getPerson().fullNameBinding().getValue()));
        superordinateChoice.setItems(FXCollections.observableList(positions));

        jobChoice.setConverter(new StringConverter<Job>() {
            @Override
            public String toString(Job job) {
                return job.getName();
            }

            @Override
            public Job fromString(String s) {
                throw new RuntimeException("Cannot parse job from String.");
            }
        });
        personChoice.setConverter(new StringConverter<Person>() {
            @Override
            public String toString(Person person) {
                System.out.println(person);
                if(person==null)return "no Person";
                return person.fullNameBinding().getValue();
            }

            @Override
            public Person fromString(String s) {
                throw new RuntimeException("Cannot parse job from String.");
            }
        });
        superordinateChoice.setConverter(new StringConverter<Position>() {
            @Override
            public String toString(Position position) {
                return position.getPerson().fullNameBinding().getValue()+" "+position.getJob().getName();
            }

            @Override
            public Position fromString(String s) {
                throw new RuntimeException("Cannot parse job from String.");
            }
        });

        jobChoice.getSelectionModel().select(position.getJob());
        personChoice.getSelectionModel().select(position.getPerson());
        superordinateChoice.getSelectionModel().select(position.getSuperordinate());

        saveButton.setOnAction(actionEvent -> {
            save();
            if(onFinishedHandler != null)
                onFinishedHandler.handle(actionEvent);
        });
        abortButton.setOnAction(actionEvent -> {
            if(onFinishedHandler !=null)onFinishedHandler.handle(actionEvent);
        });
    }

    private void save() {
        position.setJob(jobChoice.getSelectionModel().getSelectedItem());
        position.setPerson(personChoice.getSelectionModel().getSelectedItem());
        if(superordinateChoice.getSelectionModel().getSelectedItem() != position.getSuperordinate()){
            Repository.getInstance().move(position, superordinateChoice.getSelectionModel().getSelectedItem());
            Repository.getInstance().getHierarchy().invalidate();
        }
    }


    public void setOnFinished(EventHandler<ActionEvent> handler){
        this.onFinishedHandler = handler;
    }
}
