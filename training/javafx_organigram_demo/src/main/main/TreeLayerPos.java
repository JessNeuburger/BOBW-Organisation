package main;

public class TreeLayerPos {
    public int x;
    public int y;
    public PosPane pos;

    public TreeLayerPos(PosPane pos) {
        this.pos = pos;
    }

    public PosPane getPos() {
        return pos;
    }

    public void setPos(PosPane pos) {
        this.pos = pos;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setMiddle(int middle){
        setX(middle-getWidth()/2);
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
}
