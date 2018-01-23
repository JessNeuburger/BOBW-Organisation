import at.htl.florianschwarcz.workflowmanagementlib.*;
import org.junit.Test;

import javax.activity.ActivityRequiredException;

import static org.junit.Assert.*;

public class WorkflowTests {
    @Test
    public void T01_SetterAndGetter(){
        Workflow workflow = new Workflow();
        assertNull("Start should be null", workflow.getStart());
        Start start = new Start();
        workflow.setStart(start);
        assertSame("Start not correct", start, workflow.getStart());
    }

    @Test
    public void T02_IsComplete_Simple(){
        Start start = new Start();
        Workflow workflow = new Workflow(start);
        Activity activity = new Activity("Test");
        start.setNext(activity);
        assertFalse("Workflow should be incomplete", workflow.isComplete());
        activity.setNext(new End());
        assertTrue("Workflow should be complete", workflow.isComplete());
    }

    @Test
    public void T03_IsComplete_Complex(){
        Start start = new Start();
        Workflow workflow = new Workflow(start);
        Activity activity1 = new Activity("Test1");
        start.setNext(activity1);
        Fork fork1 = new Fork();
        activity1.setNext(fork1);
        Activity activity2 = new Activity("Test2");
        Activity activity3 = new Activity("Test3");
        fork1.addNext(activity2);
        fork1.addNext(activity3);
        assertFalse("Workflow should be incomplete", workflow.isComplete());
        activity2.setNext(new End());
        assertFalse("Workflow should still be incomplete", workflow.isComplete());
        Fork fork2 = new Fork();
        activity3.setNext(fork2);
        Activity activity4 = new Activity("Test4");
        Activity activity5 = new Activity("Test5");
        fork2.addNext(activity4);
        fork2.addNext(activity5);
        Join join = new Join();
        activity4.setNext(join);
        activity5.setNext(join);
        join.setNext(new End());
        assertTrue("Workflow should be complete", workflow.isComplete());
    }
}
