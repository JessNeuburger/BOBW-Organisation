package at.htl.erikmayrhofer.organisationalstructurecomponents.profile;

import at.htl.florianschwarcz.organisationalstructurelib.Person;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ProfileEditor extends GridPane {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField birthCityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField numberField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField zipCodeField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField socialSecurityNumberField;


    private SimpleObjectProperty<Person> profile;

    public SimpleObjectProperty<Person> profileProperty() {
        return profile;
    }

    public Person getProfile() {
        return profile.get();
    }

    public void setPerson(Person person) {
        this.profile.set(person);
    }


    public ProfileEditor(Person person){
        this();
        setPerson(person);
    }

    public ProfileEditor() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/person/PersonEditor.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        profile = new SimpleObjectProperty<>();
        profile.addListener((observableValue, oldProfile, newProfile) -> {
            if(oldProfile != null) {
                firstNameField.textProperty().unbindBidirectional(oldPerson.firstNameProperty());
                lastNameField.textProperty().unbindBidirectional(oldPerson.lastNameProperty());
                //ToDo DatePicker
            }
            if(newProfile != null) {
                firstNameField.textProperty().bindBidirectional(newPerson.firstNameProperty());
                lastNameField.textProperty().bindBidirectional(newPerson.lastNameProperty());
                //ToDo DatePicker
            }
        });
    }
}
