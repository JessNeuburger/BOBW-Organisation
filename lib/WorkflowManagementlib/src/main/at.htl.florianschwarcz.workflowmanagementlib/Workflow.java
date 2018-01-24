package at.htl.florianschwarcz.workflowmanagementlib;

public class Workflow {
    private Start start;

    public Workflow(){
    }
    public Workflow(Start start) {
        this.start = start;
    }

    public Start getStart() {
        return start;
    }
    public void setStart(Start start) {
        this.start = start;
    }

    public boolean isComplete(){
        Token token = new Token(start);
        while(token.getState().size() > 0){
            for(int i = token.getState().size() - 1; i >= 0; i--){
                if(!token.proceed((Element)token.getState().toArray()[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
