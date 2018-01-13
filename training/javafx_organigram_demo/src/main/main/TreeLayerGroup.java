package main;

import java.util.ArrayList;
import java.util.List;

public class TreeLayerGroup extends ArrayList<TreeLayerPos> {
    private TreeLayer layer;

    public TreeLayerGroup(TreeLayer layer) {
        this.layer = layer;
    }

    //<editor-fold desc="Getters and Setters">
    public TreeLayer getLayer() {
        return layer;
    }

    //<editor-fold desc="Position getters & setters">
    //TODO Cache this
    public int getStartX() {
        int min = get(0).getStartX();
        for(TreeLayerPos p : this){
            min = Math.min(p.getStartX(), min);
        }
        return min;
    }

    //TODO Cache this
    public int getEndX(){
        int max = 0;
        for(TreeLayerPos p : this){
            max = Math.max(p.getEndX(), max);
        }
        return max;
    }

    public int getMiddle() {
        return (getStartX()+getEndX())/2;
    }
    //</editor-fold>
    //</editor-fold>

    public void addAll(List<PosPane> c){
        for(PosPane p:c){
            add(new TreeLayerPos(p,getLayer()));
        }
    }

    public void propagateMiddle(int minimalMiddle) {
        //System.out.println("- TreeLayerGroup propagating minMiddle of "+minimalMiddle);
        forEach(TreeLayerPos::bubbleDown);
        int offset = minimalMiddle-getMiddle();
        if(offset > 0){
            propagateOffset(offset);
        }
    }

    public void propagateOffset(int offset) {
        forEach((tlp) -> tlp.propagateOffset(offset));
    }
}
