package at.htl.florianschwarcz.workflowmanagementlib;

import java.util.LinkedList;
import java.util.List;

public class Branch extends Element{
    String condition;
    List<Element> next;
    List<String> Results;

    public Branch() {
        this.condition = "";
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void addNext(Element nextE){
        if (next == null) {
            next = new LinkedList<>();
        }
        next.add(nextE);
    }

    public Element getNext(int pos){
        if(next == null){
            return null;
        }
        return next.get(pos);
    }

    public void deleteNext(Element nextE){
        if(next == null){
            return;
        }
        next.remove(nextE);
    }
}
