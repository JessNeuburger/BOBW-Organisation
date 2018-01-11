package main;

import java.util.ArrayList;

public class TreeLayer extends ArrayList<TreeLayerGroup> {
    private int height;
    private int startY;
    private int width;
    private TreeLayer parentLayer;

    public int getStartY() {
        return startY;
    }

    public int getEndY(){
        return getStartY()+getHeight();
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public TreeLayer getParentLayer() {
        return parentLayer;
    }

    public TreeLayer(TreeLayer parentLayer) {
        this.parentLayer = parentLayer;
    }
}
