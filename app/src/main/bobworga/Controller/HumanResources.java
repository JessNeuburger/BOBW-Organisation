package bobworga.Controller;

import at.htl.erikmayrhofer.organigrampane.BasicOrganigramNodeFactory;
import at.htl.erikmayrhofer.organigrampane.OrganigramControllerAdapter;
import at.htl.erikmayrhofer.organigrampane.OrganigramLineController;
import at.htl.erikmayrhofer.organigrampane.OrganigramPane;
import at.htl.florianschwarcz.organisationalstructurelib.Person;
import at.htl.florianschwarcz.organisationalstructurelib.Profile;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Date;

public class HumanResources extends BorderPane{

    private HrList hrListReference;

    private SimpleObjectProperty<Person> selectedPerson;

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
    @FXML
    private OrganigramPane organigramPane;

    public HumanResources(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tabs/HumanResources.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        selectedPerson = new SimpleObjectProperty<>();

        organigramPane.setController(new OrganigramLineController(new BasicOrganigramNodeFactory()));
        selectedPerson.addListener((observableValue, person, t1) -> {
            if(t1.getPosition()!=null)organigramPane.getController().setRootPosition(t1.getPosition());
        });
    }


    public void setHrListReference(HrList hrListReference) {
        selectedPerson.unbind();
        this.hrListReference = hrListReference;
        selectedPerson.bind(this.hrListReference.getHrListView().getSelectionModel().selectedItemProperty());
        selectedPerson.addListener((observableValue, oldPerson, newPerson) -> {
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
