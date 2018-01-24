package at.htl.florianschwarcz.workflowmanagementlib;

public class Activity extends SingleNextElement{
    String description;

    public Activity() {
        description = "";
    }
    public Activity(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
