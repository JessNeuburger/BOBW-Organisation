package at.htl.florianschwarcz.organisationalstructuretests;

import static org.junit.Assert.*;

import at.htl.florianschwarcz.organisationalstructurelib.Hierarchy;
import org.junit.Test;

public class HierarchyTests {
    @Test
    public void T01_SimpleHierarchyCreation_ShouldHaveNoHead(){
        Hierarchy hierarchy = new Hierarchy();
        assertNull(hierarchy.getHead());
    }
}
