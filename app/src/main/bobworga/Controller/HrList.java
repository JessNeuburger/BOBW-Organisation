package bobworga.Controller;

import at.htl.florianschwarcz.organisationalstructurelib.Person;
import bobworga.utilclass.PersonClickEvent;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HrList extends VBox{
    @FXML
    private ListView<Person> hrListView;



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


        hrListView.setCellFactory(new javafx.util.Callback<ListView<Person>, ListCell<Person>>() {
            @Override
            public ListCell<Person> call(ListView<Person> personListView) {
                return new ListCell<Person>(){
                    @Override
                    protected void updateItem(Person item, boolean empty) {
                        super.updateItem(item, empty);
                        if(!empty)
                            setText(item.getFirstName() + " " +item.getLastName());
                    }
                };
            }
        });
        hrListView.setItems(FXCollections.observableList(BoBwController.getHierarchyInstance().personList()));

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
}
