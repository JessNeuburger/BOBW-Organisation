package at.htl.florianschwarcz.workflowmanagementlib;

import java.util.LinkedList;
import java.util.List;

public abstract class MultipleNextElement extends Element{
    protected List<Element> next;

    public MultipleNextElement(){
        next = new LinkedList<>();
    }
    public MultipleNextElement(List<Element> next) {
        this.next = next;
    }

    /**
     * Returns the whole list of next-elements
     * @return
     */
    public List<Element> getNext(){
        return next;
    }

    /**
     * Returns the next-element on the given position
     * @param pos
     * @return
     */
    public Element getNext(int pos){
        return next.get(pos);
    }

    /**
     * Adds a next-element to the list.
     * @param next
     */
    public void addNext(Element next){
        this.next.add(next);
    }

    /**
     * Deletes the given element from the list
     * @param next
     */
    public void deleteNext(Element next){
        this.next.remove(next);
    }

    /**
     * Deletes the element on the given position.
     * @param pos
     */
    public void deleteNext(int pos){
        this.next.remove(pos);
    }
}
