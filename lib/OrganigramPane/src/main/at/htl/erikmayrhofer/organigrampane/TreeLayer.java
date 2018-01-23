package at.htl.erikmayrhofer.organigrampane;

import java.util.ArrayList;

public class TreeLayer extends ArrayList<TreeLayerGroup> {
    private int height;
    private int startY;

    //<editor-fold desc="Getters and Setters">
    //<editor-fold desc="Position Getters & Setters">
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
        return endX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }
    //</editor-fold>

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    //</editor-fold>

}
