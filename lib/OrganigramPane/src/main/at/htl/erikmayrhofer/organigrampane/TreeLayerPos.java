package at.htl.erikmayrhofer.organigrampane;

public class TreeLayerPos {
    private int x;
    private int y;
    private PosPane pos;
    private TreeLayerGroup children;
    private TreeLayer layer;

    public TreeLayerPos(PosPane pos, TreeLayer layer) {
        this.pos = pos;
        this.layer = layer;
    }

    //<editor-fold desc="Getters and Setters">
    //<editor-fold desc="getSetChildren">
    public TreeLayerGroup getChildren() {
        return children;
    }

    public void setChildren(TreeLayerGroup children) {
        this.children = children;
    }
    //</editor-fold>

    public PosPane getPos() {
        return pos;
    }

    //<editor-fold desc="getSetX">
    public int getStartX() {
        return x;
    }

    public void setStartX(int x) {
        this.x = x;
    }

    public int getEndX(){
        return getStartX()+getWidth();
    }
    //</editor-fold>

    public void setMiddle(int middle){
        setStartX(middle-getWidth()/2);
    }
    //<editor-fold desc="getSetY">

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    //</editor-fold>

    public int getWidth() {
        return PosPane.POS_PANE_WIDTH+2*PosPane.POS_PANE_MARGIN_X;
    }

    public int getMiddle() {
        return getStartX()+getWidth()/2;
    }
    //</editor-fold>

    public void bubbleDown() {
        //System.out.println("Bubbling: "+pos.getName());
        setY(layer.getStartY());

        if(children != null && children.size() > 0) {
            setStartX(layer.getEndX());
            getChildren().propagateMiddle(getMiddle());
            setMiddle(getChildren().getMiddle());
        }else{
            //System.out.println("    (Has no children, so add to end of layer)");
            setStartX(layer.getEndX());
        }
    }

    public void propagateOffset(int offset) {
        setStartX(getStartX()+offset);
        if(getChildren() != null)
            getChildren().propagateOffset(offset);
    }
}
