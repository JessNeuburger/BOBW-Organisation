package main;

public class TreeLayerPos {
    public int x;
    public int y;
    public PosPane pos;
    public TreeLayerGroup children;
    public TreeLayer layer;
    public TreeLayerGroup group;
    public TreeLayerPos prevPos;


    public TreeLayerPos(PosPane pos, TreeLayer layer, TreeLayerGroup group) {
        this.pos = pos;
        this.layer = layer;
        this.group = group;
    }

    public TreeLayerPos getPrevPos() {
        return prevPos;
    }

    public void setPrevPos(TreeLayerPos prevPos) {
        this.prevPos = prevPos;
    }

    public TreeLayerGroup getGroup() {
        return group;
    }

    public void setGroup(TreeLayerGroup group) {
        this.group = group;
    }

    public TreeLayerGroup getChildren() {
        return children;
    }

    public void setChildren(TreeLayerGroup children) {
        this.children = children;
    }

    public TreeLayer getLayer() {
        return layer;
    }

    public void setLayer(TreeLayer layer) {
        this.layer = layer;
    }

    public PosPane getPos() {
        return pos;
    }

    public void setPos(PosPane pos) {
        this.pos = pos;
    }

    public int getStartX() {
        return x;
    }

    public int getEndX(){
        return getStartX()+getWidth();
    }

    public void setStartX(int x) {
        this.x = x;
    }

    public void setMiddle(int middle){
        setStartX(middle-getWidth()/2);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return PosPane.POS_PANE_WIDTH+2*PosPane.POS_PANE_MARGIN_X;
    }

    public void bubbleDown() {
        System.out.println("Bubbling: "+pos.getName());
        setY(layer.getStartY());

        if(children != null && children.size() > 0) {

            //Set middle of children,
            //Propagate this middle through, to test if it fits,
            //Set this middle to middle of children

            setStartX(layer.getEndX());
            getChildren().propagateMiddle(getMiddle());
            setMiddle(getChildren().getMiddle());
            /*
            setMiddle(Math.max(getChildren().getMiddle(), getWidth() / 2));

            int minStart = layer.getEndX();

            if(getStartX() < minStart){
                System.out.println("    (Middle of children would cause overlap, so add to the end of layer)");
                setStartX(minStart);
            }else{
                System.out.println("    ()");
            }

            getChildren().setMiddle(getMiddle());*/
        }else{
            System.out.println("    (Has no children, so add to end of layer)");
            setStartX(layer.getEndX());
        }
    }

    public int getMiddle() {
        return getStartX()+getWidth()/2;
    }

    public void propagateOffset(int offset) {
        setStartX(getStartX()+offset);
        if(getChildren() != null)
            getChildren().propagateOffset(offset);
    }
}
