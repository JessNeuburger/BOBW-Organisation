package main;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

import java.util.ArrayList;


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

    public void relayout(){
        System.out.println("=-=-=-Relayout-=-=-=");
        ArrayList<TreeLayer> spaces = new ArrayList<>();


        TreeLayer firstLayer = new TreeLayer(null);
        TreeLayerGroup headLayerGroup = new TreeLayerGroup(null, firstLayer);
        TreeLayerPos headPos = new TreeLayerPos(head, firstLayer, headLayerGroup);
        headLayerGroup.add(headPos);
        firstLayer.add(headLayerGroup);
        spaces.add(firstLayer);

        TreeLayer aboveLayer = firstLayer;
        while(true){
            TreeLayer currLayer = new TreeLayer(aboveLayer);

            for(TreeLayerGroup g : spaces.get(spaces.size()-1)){
                for(TreeLayerPos p : g){
                    TreeLayerGroup currGroup = new TreeLayerGroup(p,currLayer);
                    currGroup.addAll(p.getPos().getChildPos());
                    if(currGroup.size() > 0) {
                        p.setChildren(currGroup);
                        currLayer.add(currGroup);
                    }
                }
            }
            if(currLayer.size() == 0)
                break;
            spaces.add(currLayer);
            aboveLayer = currLayer;
        }

        int lastY = 0;
        for(TreeLayer l : spaces){
            double maxHeight = 0;
            for(TreeLayerGroup g : l){
                for(TreeLayerPos p : g){
                    maxHeight = Math.max(maxHeight,PosPane.POS_PANE_HEIGHT+2*PosPane.POS_PANE_MARGIN_Y);
                }
            }
            l.setHeight((int)maxHeight);
            l.setStartY(lastY);
            lastY = l.getEndY();
        }

        headPos.bubbleDown();

        //PosPane set Pos
        for(TreeLayer l : spaces){
            for(TreeLayerGroup g : l){
                for(TreeLayerPos p : g){
                    p.getPos().setLayoutX(p.getStartX());
                    p.getPos().setLayoutY(p.getY());
                }
            }
        }
/*
        //Debug print
        System.out.println("========");
        for(TreeLayer l : spaces){
            System.out.println("Layer: "+l.getStartY()+"-  "+l.getHeight()+"  -"+l.getEndY());
            for(TreeLayerGroup g : l){
                System.out.println("-Group: "+g.getStartX()+"-  "+g.getWidth()+"  -"+g.getEndX());
                for(TreeLayerPos p : g){
                    System.out.println("--PosPane: "+p.getPos().getName());

                }
            }
        }*/
    }

    public void stagePos(PosPane pos){
        dragPane.getChildren().addAll(pos);
    }

    public void setRoot(PosPane rootPane){
        if(this.head != null) throw new IllegalStateException("Root is already set.");
        //rootPane.layoutXProperty().bind(offsetPosProperty());
        this.head = rootPane;
        dragPane.getChildren().add(rootPane);
    }

    public void stageLine(Line l){
        dragPane.getChildren().addAll(l);
    }

    public void setRoot(Node child){
        setRoot(new PosPane(null, this,child,"Root"));
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

