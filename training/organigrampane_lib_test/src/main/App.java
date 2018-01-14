/*
 * Handling mouse events with event filters
 */

import at.htl.erikmayrhofer.organigrampane.OrganigramPane;
import at.htl.erikmayrhofer.organigrampane.PosPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public final class App extends Application {

    @Override
    public void start(final Stage stage) {


        OrganigramPane p = new OrganigramPane();

        Button b = new Button("Add");


        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(p);
        rootPane.setBottom(b);

        b.setOnAction(actionEvent -> {
            Button rootButt = new Button("Root");
            PosPane rootPos = new PosPane(p,rootButt, "Root");
            rootButt.setOnAction(actionEvent1 -> {
                addChild(rootPos);
            });
            p.setRoot(rootPos);
        });

        Scene q = new Scene(rootPane, 400, 300);
        stage.setScene(q);
        stage.show();
    }

    private int tmpIndex = 0;

    public void addChild(PosPane parentPos){
        Button newButt = new Button("Child "+tmpIndex);
        PosPane newPos = new PosPane(parentPos,newButt,"Child "+tmpIndex);
        tmpIndex++;
        newButt.setOnAction(actionEvent1 -> {
            addChild(newPos);
        });
        parentPos.addPos(newPos);
    }
}
