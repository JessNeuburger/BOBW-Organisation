package bobworga.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class HumanResources extends BorderPane{

    private HrList hrListReference;

    public HumanResources(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tabs/HumanResources.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        hrListReference.getHrListView().getSelectionModel().selectedItemProperty();
        //TODO Here
    }


    public void setHrListReference(HrList hrListReference) {
        this.hrListReference = hrListReference;
    }
}
