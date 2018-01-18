package at.htl.florianschwarcz.workflowmanagementlib;

import java.util.HashSet;

public class WorkflowManager {
    private HashSet<Workflow> workflows;
    private Token[] tokens;

    public WorkflowManager(){

    }
    public WorkflowManager(HashSet<Workflow> workflows) {
        this.workflows = workflows;
    }
    public WorkflowManager(Token[] tokens){
        this.tokens = tokens;
    }
    public WorkflowManager(HashSet<Workflow> workflows, Token[] tokens){
        this.workflows = workflows;
        this.tokens = tokens;
    }
}
