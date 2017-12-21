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

        b.setOnAction(actionEvent -> {
            p.addPos();
        });


        Scene q = new Scene(rootPane, 400, 300);
        stage.setScene(q);
        stage.show();

        /*

        Line curve = createStartingCurve();

        final Node loginPanel = makeDraggable(curve.startXProperty(), curve.startYProperty(), createLoginPanel());
        final Node confirmationPanel = makeDraggable(curve.endXProperty(), curve.endYProperty(), createLoginPanel());

        final Pane panelsPane = new Pane();
        panelsPane.getChildren().addAll(loginPanel,
                confirmationPanel);


        panelsPane.getChildren().addAll(curve);

        final BorderPane sceneRoot = new BorderPane();

        BorderPane.setAlignment(panelsPane, Pos.TOP_LEFT);
        sceneRoot.setCenter(panelsPane);

        final CheckBox dragModeCheckbox = new CheckBox("Drag mode");
        BorderPane.setMargin(dragModeCheckbox, new Insets(6));
        sceneRoot.setBottom(dragModeCheckbox);


        final Scene scene = new Scene(sceneRoot, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Draggable Panels Example");
        stage.show();*/
    }
}
