package at.htl.florianschwarcz.organisationalstructurelibtests;

import static org.junit.Assert.*;

import at.htl.florianschwarcz.organisationalstructurelib.Position;
import at.htl.florianschwarcz.organisationalstructurelib.Staff;
import org.junit.Test;

public class StaffTests
{
    @Test
    public void T01_addSubordinateAndStaff(){
        Staff staff = new Staff();
        Position sub = new Position();
        staff.addSubordinate(sub);
        assertNull("Subordinates list should be null", staff.getSubordinates());
        Staff subStaff = new Staff();
        staff.addStaff(staff);
        assertNull("Staff list should be null", staff.getStaff());
    }
}
