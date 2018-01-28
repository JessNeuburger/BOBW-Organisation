package bobworga.Controller;

import at.htl.erikmayrhofer.organigrampane.BasicOrganigramNodeFactory;
import at.htl.erikmayrhofer.organigrampane.OrganigramControllerAdapter;
import at.htl.erikmayrhofer.organigrampane.OrganigramLineController;
import at.htl.erikmayrhofer.organigrampane.OrganigramPane;
import at.htl.erikmayrhofer.organisationalstructurecomponents.person.PersonEditor;
import at.htl.florianschwarcz.organisationalstructurelib.Job;
import at.htl.florianschwarcz.organisationalstructurelib.Person;
import at.htl.florianschwarcz.organisationalstructurelib.Profile;
import bobworga.model.Repository;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Date;

public class HumanResources extends BorderPane{

    private SimpleObjectProperty<Person> selectedPerson;

    @FXML
    private ListView<Person> personListView;
    @FXML
    private PersonEditor personEditor;
    @FXML
    private Button addPersonButton;

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

        personListView.setCellFactory(new javafx.util.Callback<ListView<Person>, ListCell<Person>>() {
            @Override
            public ListCell<Person> call(ListView<Person> personListView) {
                return new ListCell<Person>(){
                    @Override
                    protected void updateItem(Person item, boolean empty) {
                        super.updateItem(item, empty);
                        if(!empty)
                            textProperty().bind(item.fullNameBinding());
                    }
                };
            }
        });
        personListView.setItems(Repository.getInstance().getPersons());

        personEditor.personProperty().bind(personListView.getSelectionModel().selectedItemProperty());

        addPersonButton.setOnAction(actionEvent -> {
            Repository.getInstance().getPersons().add(new Person("Person", "new", new Date(),
                    "","","","","","",""));
        });
    }
}
