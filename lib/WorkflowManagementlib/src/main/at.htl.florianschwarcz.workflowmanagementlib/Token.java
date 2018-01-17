package at.htl.florianschwarcz.workflowmanagementlib;

import java.util.HashSet;

public class Token {
    private HashSet<Element> state;

    public Token(Start start) {
        state.add(start);
    }

    public HashSet<Element> getState() {
        return state;
    }

    public void proceed(Element state){

    }
}
