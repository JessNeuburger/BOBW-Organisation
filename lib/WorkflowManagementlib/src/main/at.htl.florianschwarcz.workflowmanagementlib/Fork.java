package at.htl.florianschwarcz.workflowmanagementlib;

import java.util.LinkedList;
import java.util.List;

public class Fork extends Element{
    private List<Element> next;

    /**
     * Returns the next-element on the given position.
     * @param pos
     * @return
     */
    public Element getNext(int pos) {
        if(next == null){
            return null;
        }
        return next.get(pos);
    }

    /**
     * Adds an element to the next-list.
     * @param nextE
     */
    public void addNext(Element nextE) {
        if(next == null){
            next = new LinkedList<>();
        }
        next.add(nextE);
    }

    /**
     * Delets a given Element from the next-list.
     * @param nextE
     */
    public void deleteNext(Element nextE){
        if(next == null){
            return;
        }
        next.remove(nextE);
    }
}
