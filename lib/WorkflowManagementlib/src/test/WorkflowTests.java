import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class WorkflowTests {
    @Test
    public void T01_SetterAndGetter(){
        Hierarchy hierarchy = new Hierarchy();
        assertNull("Head should be null", hierarchy.getHead());
        Position head = new Position();
        hierarchy.setHead(head);
        assertSame("Head not correct", head, hierarchy.getHead());
    }
}
