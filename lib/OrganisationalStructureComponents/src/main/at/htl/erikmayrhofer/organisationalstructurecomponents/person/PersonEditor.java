package at.htl.erikmayrhofer.organisationalstructurecomponents.person;

import at.htl.florianschwarcz.organisationalstructurelib.Job;
import at.htl.florianschwarcz.organisationalstructurelib.Person;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class PersonEditor extends GridPane {

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


    private ObjectProperty<Person> person;

    public ObjectProperty<Person> personProperty() {
        return person;
    }

    public Person getPerson() {
        return person.get();
    }

    public void setPerson(Person person) {
        this.person.set(person);
    }


    public PersonEditor(Person person){
        this();
        setPerson(person);
    }

    public PersonEditor() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/person/PersonEditor.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        person = new SimpleObjectProperty<>();
        person.addListener((observableValue, oldPerson, newPerson) -> {
            if(oldPerson != null) {
                firstNameField.textProperty().unbindBidirectional(oldPerson.firstNameProperty());
                lastNameField.textProperty().unbindBidirectional(oldPerson.lastNameProperty());
                //ToDo DatePicker
                birthCityField.textProperty().unbindBidirectional(oldPerson.birthCityProperty());
                streetField.textProperty().unbindBidirectional(oldPerson.streetProperty());
                numberField.textProperty().unbindBidirectional(oldPerson.numberProperty());
                cityField.textProperty().unbindBidirectional(oldPerson.cityProperty());
                zipCodeField.textProperty().unbindBidirectional(oldPerson.zipCodeProperty());
                emailField.textProperty().unbindBidirectional(oldPerson.emailProperty());
                socialSecurityNumberField.textProperty().unbindBidirectional(oldPerson.socialSecurityNumberProperty());
            }
            if(newPerson != null) {
                firstNameField.textProperty().bindBidirectional(newPerson.firstNameProperty());
                lastNameField.textProperty().bindBidirectional(newPerson.lastNameProperty());
                //ToDo DatePicker
                birthCityField.textProperty().bindBidirectional(newPerson.birthCityProperty());
                streetField.textProperty().bindBidirectional(newPerson.streetProperty());
                numberField.textProperty().bindBidirectional(newPerson.numberProperty());
                cityField.textProperty().bindBidirectional(newPerson.cityProperty());
                zipCodeField.textProperty().bindBidirectional(newPerson.zipCodeProperty());
                emailField.textProperty().bindBidirectional(newPerson.emailProperty());
                socialSecurityNumberField.textProperty().bindBidirectional(newPerson.socialSecurityNumberProperty());
            }
        });
    }
}
