package at.htl.erikmayrhofer.organigrampane;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class PosPane extends HBox {

    public static final int POS_PANE_WIDTH = 220;
    public static final int POS_PANE_HEIGHT = 120;
    public static final int POS_PANE_MARGIN_X = 10;
    public static final int POS_PANE_MARGIN_Y = 50;

    private ArrayList<PosPane> childPos;
    private IntegerProperty childLength;
    private OrganigramPane organigramPane;
    private String name;

    public PosPane(PosPane parentPos, Node child, String name) {
        this(parentPos.organigramPane, child, name);
    }

    public PosPane(OrganigramPane organigramPane, Node child, String name) {

        //TODO This does not belong here
        this.setStyle("-fx-background-color: lightgray;");

        this.getChildren().add(child);
        this.childPos = new ArrayList<>();
        this.organigramPane = organigramPane;

        this.setPrefWidth(POS_PANE_WIDTH);
        this.setMaxWidth(POS_PANE_WIDTH);
        this.setMinWidth(POS_PANE_WIDTH);
        this.setPrefHeight(POS_PANE_HEIGHT);
        this.setMaxHeight(POS_PANE_HEIGHT);
        this.setMinHeight(POS_PANE_HEIGHT);

        this.setPadding(new Insets(10));
        childLength = new SimpleIntegerProperty(0);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<PosPane> getChildPos() {
        return childPos;
    }

    private void addChildPos(PosPane child){
        this.childPos.add(child);
        childLength.setValue(this.childPos.size());
        Line l = new Line();
        l.startYProperty().bind(lineLeaveYProperty());
        l.startXProperty().bind(lineLeaveXProperty());
        l.endXProperty().bind(child.lineEntryXProperty());
        l.endYProperty().bind(child.lineEntryYProperty());
        organigramPane.stageLine(l);
    }

    //<editor-fold desc="Line-Properties">
    private DoubleBinding centerXProperty(){
        return layoutXProperty().add(widthProperty().divide(2));
    }
    private DoubleBinding lineEntryXProperty(){
        return centerXProperty();
    }
    private ReadOnlyDoubleProperty lineEntryYProperty(){
        return layoutYProperty();
    }
    private DoubleBinding lineLeaveXProperty(){
        return centerXProperty();
    }
    private DoubleBinding lineLeaveYProperty(){
            return layoutYProperty().add(heightProperty());
    }
    //</editor-fold>

    public void addPos(PosPane b){
        organigramPane.stagePos(b);
        addChildPos(b);

        //TODO this does not belong here
        organigramPane.relayout();
    }
}
