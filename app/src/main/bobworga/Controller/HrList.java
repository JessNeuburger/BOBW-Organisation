package bobworga.Controller;

import at.htl.florianschwarcz.organisationalstructurelib.Person;
import bobworga.model.Repository;
import bobworga.utilclass.PersonClickEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HrList extends VBox{
    @FXML
    private ListView<Person> hrListView;
    @FXML
    private Button addHrButton;

    private EventHandler<ActionEvent> onFinishedHandler;


    public HrList(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HrList.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("BLUP");
            e.printStackTrace();
        }
        System.out.println(hrListView);

        addHrButton.setOnAction(actionEvent->{
            addEmptyHrElement();
            if(onFinishedHandler!=null){
                onFinishedHandler.handle(actionEvent);
            }
        });
        hrListView.setCellFactory(new javafx.util.Callback<ListView<Person>, ListCell<Person>>() {
            @Override
            public ListCell<Person> call(ListView<Person> personListView) {
                return new ListCell<Person>(){
                    @Override
                    protected void updateItem(Person item, boolean empty) {
                        super.updateItem(item, empty);
                        if(!empty)
                            textProperty().bind(item.firstNameProperty().concat(" ").concat(item.lastNameProperty()));
                    }
                };
            }
        });
        hrListView.setItems(Repository.getInstance().getPersons());

    }

    public ListView<Person> getHrListView() {
        return hrListView;
    }

    public void setOnPersonDoubleClicked(EventHandler<PersonClickEvent> eventhandler){
        hrListView.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2){
                eventhandler.handle(new PersonClickEvent(mouseEvent, hrListView.getSelectionModel().getSelectedItem()));
            }
        });
    }

    private void addEmptyHrElement(){
        Repository.getInstance().getPersons().add(new Person("new","Person",null,"","","","","","",""));
    }
}
