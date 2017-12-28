package main;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class OrganigramPane extends ScrollPane {

    private PosPane head;

    private Pane dragPane;

    private DoubleProperty centerPos;

    public OrganigramPane() {
        StackPane centerPane = new StackPane();
        centerPane.setPrefSize(StackPane.USE_COMPUTED_SIZE,StackPane.USE_COMPUTED_SIZE);
        centerPane.setCenterShape(true);
        centerPane.setStyle("-fx-background-color: blue;");

        dragPane = new Pane();
        dragPane.setPrefSize(Pane.USE_COMPUTED_SIZE,Pane.USE_COMPUTED_SIZE);

        centerPane.getChildren().add(dragPane);
        setContent(centerPane);
        setPannable(true);

        centerPos = new SimpleDoubleProperty(0);
    }

    public PosPane getHead() {
        return head;
    }

    public void setHead(PosPane head) {
        this.head = head;
    }

    public void addPos(){
        PosPane b = new PosPane(head,this);
        dragPane.getChildren().add(b);
        if(this.head != null){
            b.setParentPos(head);
            int index = head.addChildPos(b);
            b.layoutXProperty().bind(b.getParentPos().getChildX(index));
            b.layoutYProperty().bind(b.getParentPos().getChildY(index));
            if(index > 5){
                head = head.getChildPos(0);
            }
        }else{
            b.layoutXProperty().bind(offsetPosProperty());
            this.head = b;
        }
    }

    public double getOffsetPos() {
        return centerPos.get();
    }

    public DoubleProperty offsetPosProperty() {
        return centerPos;
    }

    public void setOffsetPos(double centerPos) {
        this.centerPos.set(centerPos);
    }
}

