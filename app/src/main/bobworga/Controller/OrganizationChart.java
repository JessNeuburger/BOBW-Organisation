package bobworga.Controller;

import at.htl.erikmayrhofer.organigrampane.BasicOrganigramNodeFactory;
import at.htl.erikmayrhofer.organigrampane.OrganigramChildController;
import at.htl.erikmayrhofer.organigrampane.OrganigramPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OrganizationChart extends AnchorPane{

    @FXML
    private OrganigramPane organigramPane;

    public OrganizationChart(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tabs/OrganizationChart.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        organigramPane.setController(new OrganigramChildController(new BasicOrganigramNodeFactory()));
        organigramPane.getController().setHierarchy(BoBwController.getHierarchyInstance());
        
    }
}
