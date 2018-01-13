package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TreeLayerGroup extends ArrayList<TreeLayerPos> {
    private TreeLayerPos parent;
    private TreeLayer layer;


    public TreeLayerGroup(TreeLayerPos parent, TreeLayer layer) {
        this.parent = parent;
        this.layer = layer;
    }

    public TreeLayerGroup(Collection<? extends TreeLayerPos> c, TreeLayerPos parent, TreeLayer layer) {
        super(c);
        this.parent = parent;
        this.layer = layer;
    }

    public TreeLayer getLayer() {
        return layer;
    }

    public void setLayer(TreeLayer layer) {
        this.layer = layer;
    }

    public int getWidth() {
        return getEndX()-getStartX();
    }

    //TODO Cache this
    public int getStartX() {
        int min = get(0).getStartX();
        for(TreeLayerPos p : this){
            min = Math.min(p.getStartX(), min);
        }
        return min;
    }

    /*
    public void setStartX(int startX) {
        this.startX = startX;
    }*/

    //TODO Cache this
    public int getEndX(){
        int max = 0;
        for(TreeLayerPos p : this){
            max = Math.max(p.getEndX(), max);
        }
        return max;
    }

    public TreeLayerPos getParent() {
        return parent;
    }

    public void setParent(TreeLayerPos parent) {
        this.parent = parent;
    }



    public void addAll(List<PosPane> c){
        for(PosPane p:c){
            add(new TreeLayerPos(p,layer));
        }
    }

    public int getMiddle() {
        return (getStartX()+getEndX())/2;
    }

    public void propagateMiddle(int minimalMiddle) {
        System.out.println("- TreeLayerGroup propagating minMiddle of "+minimalMiddle);

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
