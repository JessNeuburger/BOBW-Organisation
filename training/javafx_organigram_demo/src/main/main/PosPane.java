package main;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PosPane extends Pane {

    public static final int POS_PANE_WIDTH = 50;

    private PosPane parentPos;
    private ArrayList<PosPane> childPos;
    private IntegerProperty childLength;

    public PosPane(PosPane parentPos) {
        this.getChildren().addAll(new Button("Test"));
        this.setStyle("-fx-background-color: black;");
        this.parentPos = parentPos;
        this.childPos = new ArrayList<>();
        this.setPrefWidth(POS_PANE_WIDTH);
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
        return this.childPos.size()-1;
    }

    public ReadOnlyDoubleProperty getChildWidth(int index){
        return childPos.get(index).widthProperty();
    }

    public DoubleBinding getPreChildWidth(int index){
        DoubleBinding prop = childPos.get(0).widthProperty().add(0);

        for(int i = 1; i < index; i++){
            prop = prop.add(childPos.get(i).widthProperty());
        }

        return prop;
    }

    public DoubleBinding getChildY(int index) {
        return layoutYProperty().add(heightProperty()).add(20);
    }

    public DoubleBinding centerXProperty(){
        return layoutXProperty().add(widthProperty().divide(2));
    }

    public DoubleBinding getChildX(int index) {
        //DoubleBinding b = layoutXProperty().add(widthProperty().multiply(index));

        //CenterX - CompleteWidth/2
        //DoubleBinding b = layoutXProperty().add(childLength.multiply(widthProperty()).divide(2)).add(getChildWidth().multiply(index));
        DoubleBinding b = centerXProperty().subtract(
                childLength.multiply(POS_PANE_WIDTH/2)
        ).add(POS_PANE_WIDTH*index);
        System.out.println("getChildX("+index+") "+b.get());
        return b;
    }
}
