package at.htl.florianschwarcz.workflowmanagementlib;

public class Workflow {
    private Start start;

    public Start getStart() {
        return start;
    }
    public void setStart(Start start) {
        this.start = start;
    }

    public boolean isComplete(){
        Token token = new Token(start);
        while(!token.getState().contains(null)){
            token.getState().forEach(s -> token.proceed(s));
            if(token.getState().size() == 0){
                return true;
            }
        }
        return false;
    }
}
