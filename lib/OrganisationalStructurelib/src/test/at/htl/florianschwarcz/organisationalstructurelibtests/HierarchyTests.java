package at.htl.florianschwarcz.organisationalstructurelibtests;

import static org.junit.Assert.*;

import at.htl.florianschwarcz.organisationalstructurelib.*;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class HierarchyTests {
    @Test
    public void T01_SetterAndGetter(){
        Hierarchy hierarchy = new Hierarchy();
        assertNull("Head should be null", hierarchy.getHead());
        Position head = new Position();
        hierarchy.setHead(head);
        assertSame("Head not correct", head, hierarchy.getHead());
    }

    @Test
    public void T02_PositionList_SimpleTest(){
        Hierarchy hierarchy = new Hierarchy();
        List<Position> expected = new LinkedList<>();
        assertEquals("List should be empty", expected, hierarchy.positionList());
        Position head = new Position();
        hierarchy.setHead(head);
        expected.add(head);
        assertEquals("List should only contain head", expected, hierarchy.positionList());
    }

    @Test
    public void T03_PositionList_Complex(){
        Hierarchy hierarchy = new Hierarchy();
        Position head = new Position();
        Position headSub = new Position();
        Position headSub2 = new Position();
        hierarchy.setHead(head);
        head.addSubordinate(headSub);
        head.addSubordinate(headSub2);
        List<Position> expected = new LinkedList<>();
        expected.add(head);
        expected.add(headSub);
        expected.add(headSub2);
        assertEquals("List should be like expected", expected, hierarchy.positionList());
        Position headSubSub = new Position();
        headSub.addSubordinate(headSubSub);
        expected.add(2, headSubSub);
        assertEquals("Expanded List should be like expected", expected, hierarchy.positionList());
    }

    @Test
    public void T04_PersonList_Simple(){
        Hierarchy hierarchy = new Hierarchy();
        Position head = new Position();
        hierarchy.setHead(head);
        List<Person> expected = new LinkedList<>();
        assertEquals("List should be empty", expected, hierarchy.personList());
        Person headPerson = new Person("Test","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        head.setPerson(headPerson);
        expected.add(headPerson);
        assertEquals("List should only contain head's person", expected, hierarchy.personList());
    }

    @Test
    public void T05_PersonList_Complex(){
        Hierarchy hierarchy = new Hierarchy();
        Position head = new Position();
        Person headPerson = new Person("Head","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        head.setPerson(headPerson);
        Position headSub = new Position();
        Person headSubPerson = new Person("HeadSub","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        headSub.setPerson(headSubPerson);
        Position headSub2 = new Position();
        Person headSub2Person = new Person("HeadSub2","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        headSub2.setPerson(headSub2Person);
        hierarchy.setHead(head);
        head.addSubordinate(headSub);
        head.addSubordinate(headSub2);
        List<Person> expected = new LinkedList<>();
        expected.add(headPerson);
        expected.add(headSubPerson);
        expected.add(headSub2Person);
        assertEquals("List should be like expected", expected, hierarchy.personList());
        Position headSubSub = new Position();
        Person headSubSubPerson = new Person("HeadSubSub","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        headSubSub.setPerson(headSubSubPerson);
        headSub.addSubordinate(headSubSub);
        expected.add(2, headSubSubPerson);
        assertEquals("Expanded List should be like expected", expected, hierarchy.personList());
    }

    @Test
    public void T06_GetBestPerson(){
        Hierarchy hierarchy = new Hierarchy();
        Profile profile = new Profile();
        profile.addAttribute("Sozial", 7);
        profile.addAttribute("Handwerk", 5);
        profile.addAttribute("Programmieren", 8);
        profile.addAttribute("Datenbanken", 8);
        assertNull("Best Person should be null", hierarchy.getBestPerson(profile));
        Position head = new Position();
        Profile headPersonProfile = new Profile();
        hierarchy.setHead(head);
        headPersonProfile.addAttribute("Sozial", 6);
        headPersonProfile.addAttribute("Handwerk", 3);
        headPersonProfile.addAttribute("Programmieren", 8);
        headPersonProfile.addAttribute("Datenbanken", 7);
        Person headPerson = new Person("Test1","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", headPersonProfile);
        head.setPerson(headPerson);
        assertEquals("Head should be best person", headPerson, hierarchy.getBestPerson(profile));
        Position headSub = new Position();
        Profile headSubJobProfile = new Profile();
        headSubJobProfile.addAttribute("Sozial", 5);
        headSubJobProfile.addAttribute("Handwerk", 5);
        headSubJobProfile.addAttribute("Programmieren", 10);
        headSubJobProfile.addAttribute("Datenbanken", 10);
        Person headSubPerson = new Person("Test2","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", headSubJobProfile);
        headSub.setPerson(headSubPerson);
        head.addSubordinate(headSub);
        assertEquals("Head's sub should be best person", headSubPerson, hierarchy.getBestPerson(profile));
    }

    @Test
    public void T07_GetBestJob() {
        Hierarchy hierarchy = new Hierarchy();
        Profile profile = new Profile();
        profile.addAttribute("Sozial", 7);
        profile.addAttribute("Handwerk", 5);
        profile.addAttribute("Programmieren", 8);
        profile.addAttribute("Datenbanken", 8);
        assertNull("Best Job should be null", hierarchy.getBestJob(profile));
        Position head = new Position();
        Profile headJobProfile = new Profile();
        hierarchy.setHead(head);
        headJobProfile.addAttribute("Sozial", 5);
        headJobProfile.addAttribute("Handwerk", 5);
        headJobProfile.addAttribute("Programmieren", 10);
        headJobProfile.addAttribute("Datenbanken", 10);
        Job headJob = new Job("Test1", headJobProfile);
        head.setJob(headJob);
        assertEquals("Head should be best job", headJob, hierarchy.getBestJob(profile));
        Position headSub = new Position();
        Profile headSubJobProfile = new Profile();
        headSubJobProfile.addAttribute("Sozial", 6);
        headSubJobProfile.addAttribute("Handwerk", 3);
        headSubJobProfile.addAttribute("Programmieren", 8);
        headSubJobProfile.addAttribute("Datenbanken", 7);
        Job headSubJob = new Job("Test2", headSubJobProfile);
        headSub.setJob(headSubJob);
        head.addSubordinate(headSub);
        assertEquals("Head's sub should be best job", headSubJob, hierarchy.getBestJob(profile));
    }

    @Test
    public void T08_GetBestJob() {
        Hierarchy hierarchy = new Hierarchy();
        Profile profile = new Profile();
        profile.addAttribute("Sozial", 7);
        profile.addAttribute("Handwerk", 5);
        profile.addAttribute("Datenbanken", 8);
        profile.addAttribute("Buchhaltung", 10);
        assertNull("Best Job should be null", hierarchy.getBestJob(profile));
        Position head = new Position();
        Profile headJobProfile = new Profile();
        hierarchy.setHead(head);
        headJobProfile.addAttribute("Sozial", 5);
        headJobProfile.addAttribute("Handwerk", 5);
        headJobProfile.addAttribute("Programmieren", 10);
        headJobProfile.addAttribute("Datenbanken", 10);
        Job headJob = new Job("Test1", headJobProfile);
        head.setJob(headJob);
        assertEquals("Head should be best job", headJob, hierarchy.getBestJob(profile));
        Position headSub = new Position();
        Profile headSubJobProfile = new Profile();
        headSubJobProfile.addAttribute("Sozial", 6);
        headSubJobProfile.addAttribute("Handwerk", 3);
        headSubJobProfile.addAttribute("Datenbanken", 7);
        headSubJobProfile.addAttribute("Buchhaltung", 8);
        Job headSubJob = new Job("Test2", headSubJobProfile);
        headSub.setJob(headSubJob);
        head.addSubordinate(headSub);
        assertEquals("Head's sub should be best job", headSubJob, hierarchy.getBestJob(profile));
    }

    @Test
    public void T09_GetTable_WithoutFreePositions(){
        Hierarchy hierarchy = new Hierarchy();
        assertEquals("Hierarchy should be empty", "Hierarchie leer\n", hierarchy.getTable());
        Position head = new Position();
        Person headPerson = new Person("Head","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        head.setPerson(headPerson);
        hierarchy.setHead(head);
        String expected = "1 Head\n";
        assertEquals("Table should contain head only", expected, hierarchy.getTable());
        Position headSub = new Position();
        Person headSubPerson = new Person("HeadSub","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        headSub.setPerson(headSubPerson);
        head.addSubordinate(headSub);
        expected += "2 HeadSub\n";
        Position headSub2 = new Position();
        Person headSub2Person = new Person("HeadSub2","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        headSub2.setPerson(headSub2Person);
        head.addSubordinate(headSub2);
        expected += "2 HeadSub2\n";
        assertEquals("Table should be like expected", expected,hierarchy.getTable());
        Position headSubSub = new Position();
        Person headSubSubPerson = new Person("HeadSubSub","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        headSubSub.setPerson(headSubSubPerson);
        headSub.addSubordinate(headSubSub);
        expected = "1 Head\n2 HeadSub\n3 HeadSubSub\n2 HeadSub2\n";
        assertEquals("Expanded table should be like expected", expected, hierarchy.getTable());
    }

    @Test
    public void T10_GetTable_WithFreePositions(){
        Position head = new Position();
        Hierarchy hierarchy = new Hierarchy(head);
        Position headSub = new Position();
        head.addSubordinate(headSub);
        Position headSub2 = new Position();
        head.addSubordinate(headSub2);
        Position headSubSub = new Position();
        headSub.addSubordinate(headSubSub);
        String expected = "1 Frei\n2 Frei\n3 Frei\n2 Frei\n";
        assertEquals("Table should be like expected", expected, hierarchy.getTable());
    }
}
