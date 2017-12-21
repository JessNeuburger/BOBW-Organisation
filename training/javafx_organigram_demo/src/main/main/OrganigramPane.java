package main;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;


public class OrganigramPane extends Pane {

    private PosPane head;

    private DragPane dragPane;

    public OrganigramPane() {
        dragPane = new DragPane();
        dragPane.setPrefSize(4000,4000);
        this.getChildren().add(dragPane);
    }

    public PosPane getHead() {
        return head;
    }

    public void setHead(PosPane head) {
        this.head = head;
    }

    public void addPos(){
        PosPane b = new PosPane(head);
        dragPane.getChildren().add(b);
        if(this.head != null){
            b.setParentPos(head);
            int index = head.addChildPos(b);
            b.layoutXProperty().bind(b.getParentPos().getChildX(index));
            b.layoutYProperty().bind(b.getParentPos().getChildY(index));
        }else{
            this.head = b;
        }
    }

}

