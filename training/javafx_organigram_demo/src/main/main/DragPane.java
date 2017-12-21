package main;

import javafx.scene.Cursor;
import javafx.scene.layout.Pane;

public class DragPane extends Pane {

    public DragPane() {
        enableDrag();
        this.setStyle("-fx-background-color: blue;");
    }

    private void enableDrag() {
        final Delta dragDelta = new Delta();
        setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = getTranslateX() - mouseEvent.getScreenX();
            dragDelta.y = getTranslateY() - mouseEvent.getScreenY();
            getScene().setCursor(Cursor.MOVE);
        });
        setOnMouseReleased(mouseEvent -> getScene().setCursor(Cursor.HAND));
        setOnMouseDragged(mouseEvent -> {
            double newX = mouseEvent.getScreenX() + dragDelta.x;
            if (newX > 0 && newX < getScene().getWidth()) {
                setTranslateX(newX);
            }
            double newY = mouseEvent.getScreenY() + dragDelta.y;
            if (newY > 0 && newY < getScene().getHeight()) {
                setTranslateY(newY);
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
