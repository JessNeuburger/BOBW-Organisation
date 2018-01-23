package at.htl.florianschwarcz.workflowmanagementlib;

public abstract class SingleNextElement extends Element{
    protected Element next;

    public Element getNext(){
        return next;
    }

    public void setNext(Element next){
        this.next = next;
    }
}
