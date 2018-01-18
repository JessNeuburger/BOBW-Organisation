package at.htl.florianschwarcz.organisationalstructurelibtests;

import static org.junit.Assert.*;

import at.htl.florianschwarcz.organisationalstructurelib.*;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PositionTests {
    @Test
    public void T01_SetterAndGetter(){
        Position position = new Position();
        assertNull("Job should be null", position.getJob());
        assertNull( "Person should be null", position.getPerson());
        Job job = new Job();
        position.setJob(job);
        Person person = new Person("Test","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        position.setPerson(person);
        assertSame("Job not correct", job, position.getJob());
        assertSame("Person not correct", person, position.getPerson());
    }

    @Test
    public void T02_GetAllSubordinatePositions_Simple() {
        Position position = new Position();
        List<Position> expected = new LinkedList<>();
        assertEquals("List should be empty", expected, position.getAllSubordinatePositions());
        Position sub = new Position();
        position.addSubordinate(sub);
        expected.add(sub);
        assertEquals("List should only contain head", expected, position.getAllSubordinatePositions());
    }

    @Test
    public void T03_GetAllSubordinatePositions_Complex(){
        Position position = new Position();
        Position sub = new Position();
        Position sub2 = new Position();
        position.addSubordinate(sub);
        position.addSubordinate(sub2);
        List<Position> expected = new LinkedList<>();
        expected.add(sub);
        expected.add(sub2);
        assertEquals("List should be like expected", expected, position.getAllSubordinatePositions());
        Position subSub = new Position();
        position.addSubordinate(subSub);
        expected.add(2, subSub);
        assertEquals("Expanded List should be like expected", expected, position.getAllSubordinatePositions());
    }

    @Test
    public void T04_GetAllSubordinatePersons_Simple() {
        Position position = new Position();
        List<Person> expected = new LinkedList<>();
        assertEquals("List should be empty", expected, position.getAllSubordinatePersons());
        Position sub = new Position();
        Person subPerson = new Person("Test","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        sub.setPerson(subPerson);
        position.addSubordinate(sub);
        expected.add(subPerson);
        assertEquals("List should only contain head", expected, position.getAllSubordinatePersons());
    }

    @Test
    public void T05_GetAllSubordinatePersons_Complex(){
        Position position = new Position();
        Position sub = new Position();
        Person subPerson = new Person("Test1","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        sub.setPerson(subPerson);
        Position sub2 = new Position();
        Person sub2Person = new Person("Test2","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        sub2.setPerson(sub2Person);
        position.addSubordinate(sub);
        position.addSubordinate(sub2);
        List<Person> expected = new LinkedList<>();
        expected.add(subPerson);
        expected.add(sub2Person);
        assertEquals("List should be like expected", expected, position.getAllSubordinatePersons());
        Position subSub = new Position();
        Person subSubPerson = new Person("Test3","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        subSub.setPerson(subSubPerson);
        position.addSubordinate(subSub);
        expected.add(2, subSubPerson);
        assertEquals("Expanded List should be like expected", expected, position.getAllSubordinatePersons());
    }

    @Test
    public void T06_GetStaff(){
        Position position = new Position();
        assertNull("Staff list should be null", position.getStaff());
        Staff staff = new Staff();
        Staff staff2 = new Staff();
        List<Staff> expected = new LinkedList<>();
        position.addStaff(staff);
        expected.add(staff);
        position.addStaff(staff2);
        expected.add(staff2);
        assertEquals("List should be like expected", expected, position.getStaff());
    }
}
