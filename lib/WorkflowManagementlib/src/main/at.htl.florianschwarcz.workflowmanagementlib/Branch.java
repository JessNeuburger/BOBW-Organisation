package at.htl.florianschwarcz.workflowmanagementlib;

import java.util.LinkedList;
import java.util.List;

public class Branch extends MultipleNextElement{
    String condition;
    List<String> results;

    public Branch() {
        this.condition = "";
    }
    public Branch(String condition){
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Element getNext(String result){
        return next.get(results.indexOf(result));
    }

    public void addNext(Element next, String result){
        this.next.add(next);
        this.results.add(result);
    }
    public boolean addNexts(Element[] next, String[] results){
        if(next.length != results.length){
            return false;
        }
        for(int i = 0; i < next.length; i++){
            this.next.add(next[i]);
            this.results.add(results[i]);
        }
        return true;
    }

    public List<String> getResults(){
        return results;
    }

    public String getResult(int pos){
        return results.get(pos);
    }

    @Override
    public void deleteNext(Element next) {
        results.remove(this.next.indexOf(next));
        this.next.remove(next);
    }
    @Override
    public void deleteNext(int pos) {
        results.remove(pos);
        next.remove(pos);
    }

    @Override
    public void addNext(Element next) {
        return;
    }
}
