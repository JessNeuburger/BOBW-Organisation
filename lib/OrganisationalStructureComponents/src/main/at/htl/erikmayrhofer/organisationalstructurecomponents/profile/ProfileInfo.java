package at.htl.erikmayrhofer.organisationalstructurecomponents.profile;

import at.htl.florianschwarcz.organisationalstructurelib.Profile;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ProfileInfo extends HBox{

    public ProfileInfo(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile/ProfileInfo.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
