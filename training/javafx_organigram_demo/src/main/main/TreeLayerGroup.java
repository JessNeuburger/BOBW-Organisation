package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TreeLayerGroup extends ArrayList<TreeLayerPos> {
    private TreeLayerPos parent;

    private int width;
    private int startX;

    public int getWidth() {
        return width;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getEndX(){
        return startX+width;
    }

    public TreeLayerGroup(TreeLayerPos parent) {
        this.parent = parent;
    }

    public TreeLayerPos getParent() {
        return parent;
    }

    public void setParent(TreeLayerPos parent) {
        this.parent = parent;
    }

    public TreeLayerGroup(Collection<? extends TreeLayerPos> c, TreeLayerPos parent) {
        super(c);
        this.parent = parent;
    }

    public void addAll(List<PosPane> c){
        for(PosPane p:c){
            add(new TreeLayerPos(p));
        }
    }
}
