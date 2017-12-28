/*
 * Handling mouse events with event filters
 */
package main;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public final class App extends Application {

    @Override
    public void start(final Stage stage) {


        OrganigramPane p = new OrganigramPane();

        Button b = new Button("Add");


        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(p);
        rootPane.setBottom(b);

        b.setOnAction(actionEvent -> p.addPos());

        Scene q = new Scene(rootPane, 400, 300);
        stage.setScene(q);
        stage.show();
    }
}
