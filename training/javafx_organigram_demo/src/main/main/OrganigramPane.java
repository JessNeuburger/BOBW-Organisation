package main;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;


public class OrganigramPane extends ScrollPane {

    private PosPane head;

    //TODO can this be omitted
    private Pane dragPane;

    private DoubleProperty centerPos;

    public OrganigramPane() {
        StackPane centerPane = new StackPane();
        centerPane.setPrefSize(StackPane.USE_COMPUTED_SIZE,StackPane.USE_COMPUTED_SIZE);
        centerPane.setCenterShape(true);

        dragPane = new Pane();
        dragPane.setPrefSize(Pane.USE_COMPUTED_SIZE,Pane.USE_COMPUTED_SIZE);

        centerPane.getChildren().add(dragPane);
        setContent(centerPane);
        setPannable(true);

        centerPos = new SimpleDoubleProperty(0);
    }

    public PosPane getRootPosPane() {
        return head;
    }

    @Deprecated
    public void addPos(Node child){
        PosPane b = new PosPane(head,this, child);
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

    public void stagePos(PosPane pos){
        dragPane.getChildren().addAll(pos);
    }

    public void setRoot(PosPane rootPane){
        if(this.head != null) throw new IllegalStateException("Root is already set.");
        rootPane.layoutXProperty().bind(offsetPosProperty());
        this.head = rootPane;
        dragPane.getChildren().add(rootPane);
    }

    public void stageLine(Line l){
        dragPane.getChildren().addAll(l);
    }

    public void setRoot(Node child){
        setRoot(new PosPane(null, this,child));
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

