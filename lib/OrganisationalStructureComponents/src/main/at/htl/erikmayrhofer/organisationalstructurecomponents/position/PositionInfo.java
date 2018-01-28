package at.htl.erikmayrhofer.organisationalstructurecomponents.position;

import at.htl.erikmayrhofer.organisationalstructurecomponents.job.JobInfo;
import at.htl.florianschwarcz.organisationalstructurelib.Person;
import at.htl.florianschwarcz.organisationalstructurelib.Position;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PositionInfo extends VBox {

    private ObjectProperty<Position> position;
    private ObjectProperty<Person> positionPerson;

    @FXML
    private Label personName;
    @FXML
    private JobInfo jobInfo;

    public Position getPosition() {
        return position.get();
    }

    public ObjectProperty<Position> positionProperty() {
        return position;
    }

    public void setPosition(Position position) {
        this.position.set(position);
    }

    public PositionInfo(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/position/PositionInfo.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        position = new SimpleObjectProperty<>();
        positionPerson = new SimpleObjectProperty<>();

        position.addListener((observableValue, position, t1) -> {
            if(t1 != null) {
                positionPerson.bind(t1.personProperty());
                jobInfo.jobProperty().bind(t1.jobProperty());
            }
        });
        positionPerson.addListener((observableValue, person, t1) -> {
            if(t1 != null)
                personName.textProperty().bind(t1.fullNameBinding());
            else {
                personName.textProperty().unbind();
                personName.setText("no Person");
            }
        });
    }

    public PositionInfo(Position pos){
        this();
        setPosition(pos);
    }
}
