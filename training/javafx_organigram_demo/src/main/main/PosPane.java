package main;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class PosPane extends HBox {

    public static final int POS_PANE_WIDTH = 120;

    private PosPane parentPos;
    private ArrayList<PosPane> childPos;
    private IntegerProperty childLength;
    private OrganigramPane organigramPane;

    public PosPane(PosPane parentPos, Node child) {
        this(parentPos, parentPos.organigramPane, child);
    }

    public PosPane(PosPane parentPos, OrganigramPane organigramPane, Node child) {

        this.setStyle("-fx-background-color: lightgray;");

        this.getChildren().add(child);
        this.parentPos = parentPos;
        this.childPos = new ArrayList<>();
        this.organigramPane = organigramPane;
        this.setPrefWidth(POS_PANE_WIDTH);
        this.setMaxWidth(POS_PANE_WIDTH);
        this.setMinWidth(POS_PANE_WIDTH);
        this.setPadding(new Insets(10));
        childLength = new SimpleIntegerProperty(0);
    }

    public PosPane getParentPos() {
        return parentPos;
    }

    public void setParentPos(PosPane parentPos) {
        this.parentPos = parentPos;
    }

    public int addChildPos(PosPane child){
        this.childPos.add(child);
        childLength.setValue(this.childPos.size());
        Line l = new Line();
        l.startYProperty().bind(lineLeaveYProperty());
        l.startXProperty().bind(lineLeaveXProperty());
        l.endXProperty().bind(child.lineEntryXProperty());
        l.endYProperty().bind(child.lineEntryYProperty());
        organigramPane.stageLine(l);
        return this.childPos.size()-1;
    }

    public PosPane getChildPos(int index){
        return childPos.get(index);
    }

    public DoubleBinding getChildY(int index) {
        return layoutYProperty().add(heightProperty()).add(20);
    }

    public DoubleBinding centerXProperty(){
        return layoutXProperty().add(widthProperty().divide(2));
    }

    public DoubleBinding lineEntryXProperty(){
        return centerXProperty();
    }
    public ReadOnlyDoubleProperty lineEntryYProperty(){
        return layoutYProperty();
    }
    public DoubleBinding lineLeaveXProperty(){
        return centerXProperty();
    }
    public DoubleBinding lineLeaveYProperty(){
        return layoutYProperty().add(heightProperty());
    }


    public DoubleBinding getChildX(int index) {
        DoubleBinding b = centerXProperty().subtract(
                childLength.multiply(POS_PANE_WIDTH/2)
        ).add(POS_PANE_WIDTH*index);

        b.addListener((observableValue, number, t1) -> {
            if(t1.doubleValue() < 0){
                getOrganigramParent().setOffsetPos(getOrganigramParent().getOffsetPos()-t1.doubleValue());
            }
        });
        return b;
    }

    public OrganigramPane getOrganigramParent(){
        return organigramPane;
    }

    public void addPos(Node child){
        PosPane b = new PosPane(this,organigramPane, child);
        addPos(b);
    }

    public void addPos(PosPane b){
        organigramPane.stagePos(b);
        int index = addChildPos(b);
        b.layoutXProperty().bind(b.getParentPos().getChildX(index));
        b.layoutYProperty().bind(b.getParentPos().getChildY(index));
    }
}
