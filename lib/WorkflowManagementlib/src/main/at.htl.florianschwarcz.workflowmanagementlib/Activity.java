package at.htl.florianschwarcz.workflowmanagementlib;

public class Activity extends Element{
    Element next;
    String description;

    public Activity() {
        description = "";
    }

    public Element getNext() {
        return next;
    }

    public void setNext(Element next) {
        this.next = next;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
