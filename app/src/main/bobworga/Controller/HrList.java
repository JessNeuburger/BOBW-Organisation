package bobworga.Controller;

import at.htl.florianschwarcz.organisationalstructurelib.Person;
import javafx.collections.FXCollections;
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
        //hrListView.setCellFactory(p -> (return new ListCell<Person>();        ));
        hrListView.setItems(FXCollections.observableList(BoBwController.getHierarchyInstance().personList()));
    }


}
