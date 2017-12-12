/*
 * Handling mouse events with event filters
 */
package main;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public final class App extends Application {

    @Override
    public void start(final Stage stage) {

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
        stage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }

    private Node makeDraggable(DoubleProperty x, DoubleProperty y, final Node node) {
        final DragContext dragContext = new DragContext();

        Anchor a1 = new Anchor(Color.BLUE, x, y);
        Group moveGroup = new Group(node, a1);
        final Group wrapGroup = new Group(moveGroup);



        wrapGroup.addEventFilter(
                MouseEvent.ANY,
                Event::consume);

        wrapGroup.addEventFilter(
                MouseEvent.MOUSE_PRESSED,
                mouseEvent -> {
                    // remember initial mouse cursor coordinates
                    // and node position
                    dragContext.mouseAnchorX = mouseEvent.getX();
                    dragContext.mouseAnchorY = mouseEvent.getY();
                    dragContext.initialTranslateX =
                            moveGroup.getTranslateX();
                    dragContext.initialTranslateY =
                            moveGroup.getTranslateY();

                });

        wrapGroup.addEventFilter(
                MouseEvent.MOUSE_DRAGGED,
                mouseEvent -> {
                    // shift node from its initial position by delta
                    // calculated from mouse cursor movement
                    moveGroup.setTranslateX(
                            dragContext.initialTranslateX
                                    + mouseEvent.getX()
                                    - dragContext.mouseAnchorX);
                    moveGroup.setTranslateY(
                            dragContext.initialTranslateY
                                    + mouseEvent.getY()
                                    - dragContext.mouseAnchorY);

                });

        return wrapGroup;
    }

    private static Node createLoginPanel() {
        final ToggleGroup toggleGroup = new ToggleGroup();

        final TextField textField = new TextField();
        textField.setPrefColumnCount(10);
        textField.setPromptText("Your name");

        final PasswordField passwordField = new PasswordField();
        passwordField.setPrefColumnCount(10);
        passwordField.setPromptText("Your password");

        final HBox panel =
                createHBox(6,
                        createVBox(2, createRadioButton("High", toggleGroup, true),
                                createRadioButton("Medium", toggleGroup,
                                        false),
                                createRadioButton("Low", toggleGroup, false)),
                        createVBox(2, textField, passwordField));
        panel.setAlignment(Pos.BOTTOM_LEFT);
        configureBorder(panel);

        return panel;
    }

    private static void configureBorder(final Region region) {
        region.setStyle("-fx-background-color: white;"
                + "-fx-border-color: black;"
                + "-fx-border-width: 1;"
                + "-fx-border-radius: 6;"
                + "-fx-padding: 6;");
    }

    private static RadioButton createRadioButton(final String text,
                                                 final ToggleGroup toggleGroup,
                                                 final boolean selected) {
        final RadioButton radioButton = new RadioButton(text);
        radioButton.setToggleGroup(toggleGroup);
        radioButton.setSelected(selected);

        return radioButton;
    }

    private static HBox createHBox(final double spacing,
                                   final Node... children) {
        final HBox hbox = new HBox(spacing);
        hbox.getChildren().addAll(children);
        return hbox;
    }

    private static VBox createVBox(final double spacing,
                                   final Node... children) {
        final VBox vbox = new VBox(spacing);
        vbox.getChildren().addAll(children);
        return vbox;
    }



    private static final class DragContext {
        public double mouseAnchorX;
        public double mouseAnchorY;
        public double initialTranslateX;
        public double initialTranslateY;
    }


    private Line createStartingCurve() {
        Line curve = new Line();
        curve.setStroke(Color.FORESTGREEN);
        curve.setStrokeWidth(4);
        curve.setStrokeLineCap(StrokeLineCap.ROUND);
        curve.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
        return curve;
    }


    class Anchor extends Circle {
        Anchor(Color color, DoubleProperty x, DoubleProperty y) {
            super(x.get(), y.get(), 3);
            setFill(color.deriveColor(1, 1, 1, 0.5));
            setStroke(color);
            setStrokeWidth(2);
            setStrokeType(StrokeType.OUTSIDE);


            x.bind(translateXProperty());
            y.bind(translateYProperty());
            //enableDrag();
        }

        // make a node movable by dragging it around with the mouse.
        private void enableDrag() {
            final Delta dragDelta = new Delta();
            setOnMousePressed(mouseEvent -> {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = getCenterX() - mouseEvent.getX();
                dragDelta.y = getCenterY() - mouseEvent.getY();
                getScene().setCursor(Cursor.MOVE);
            });
            setOnMouseReleased(mouseEvent -> getScene().setCursor(Cursor.HAND));
            setOnMouseDragged(mouseEvent -> {
                double newX = mouseEvent.getX() + dragDelta.x;
                if (newX > 0 && newX < getScene().getWidth()) {
                    setCenterX(newX);
                }
                double newY = mouseEvent.getY() + dragDelta.y;
                if (newY > 0 && newY < getScene().getHeight()) {
                    setCenterY(newY);
                }
            });
            setOnMouseEntered(mouseEvent -> {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    getScene().setCursor(Cursor.HAND);
                }
            });
            setOnMouseExited(mouseEvent -> {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    getScene().setCursor(Cursor.DEFAULT);
                }
            });
        }

        // records relative x and y co-ordinates.
        private class Delta { double x, y; }
    }
}
