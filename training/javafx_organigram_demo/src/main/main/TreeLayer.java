package main;

import java.util.ArrayList;

public class TreeLayer extends ArrayList<TreeLayerGroup> {
    private int height;
    private int startY;

    public TreeLayer() {
    }

    public int getStartY() {
        return startY;
    }

    public int getEndY(){
        return getStartY()+getHeight();
    }

    public int getEndX(){
        int endX = 0;
        for(TreeLayerGroup g : this){
            endX = Math.max(endX,g.getEndX());
        }
        System.out.println("TreeLayer calculating endX "+endX);
        return endX;
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

}
