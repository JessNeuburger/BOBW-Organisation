package at.htl.florianschwarcz.organisationalstructurelibtests;

import static org.junit.Assert.*;

import at.htl.florianschwarcz.organisationalstructurelib.Hierarchy;
import at.htl.florianschwarcz.organisationalstructurelib.Person;
import at.htl.florianschwarcz.organisationalstructurelib.Position;
import at.htl.florianschwarcz.organisationalstructurelib.Profile;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class HierarchyTests {
    @Test
    public void T01_SetHeadAndGetHead(){
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
        head.addSubortinate(headSub);
        head.addSubortinate(headSub2);
        List<Position> expected = new LinkedList<>();
        expected.add(head);
        expected.add(headSub);
        expected.add(headSub2);
        assertEquals("List should be like expected", expected, hierarchy.positionList());
        Position headSubSub = new Position();
        headSub.addSubortinate(headSubSub);
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
        Person headPerson = new Person("Test1","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        head.setPerson(headPerson);
        Position headSub = new Position();
        Person headSubPerson = new Person("Test2","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        headSub.setPerson(headSubPerson);
        Position headSub2 = new Position();
        Person headSub2Person = new Person("Test3","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        headSub2.setPerson(headSub2Person);
        hierarchy.setHead(head);
        head.addSubortinate(headSub);
        head.addSubortinate(headSub2);
        List<Person> expected = new LinkedList<>();
        expected.add(headPerson);
        expected.add(headSubPerson);
        expected.add(headSub2Person);
        assertEquals("List should be like expected", expected, hierarchy.personList());
        Position headSubSub = new Position();
        Person headSubSubPerson = new Person("Test4","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", new Profile());
        headSubSub.setPerson(headSubSubPerson);
        headSub.addSubortinate(headSubSub);
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
        Profile headSubPersonProfile = new Profile();
        headSubPersonProfile.addAttribute("Sozial", 5);
        headSubPersonProfile.addAttribute("Handwerk", 5);
        headSubPersonProfile.addAttribute("Programmieren", 10);
        headSubPersonProfile.addAttribute("Datenbanken", 10);
        Person headSubPerson = new Person("Test1","Test", new Date(), "Test", "Test", 1, "Test", "Test", "Test", "Test", headSubPersonProfile);
        headSub.setPerson(headPerson);
        head.addSubortinate(headSub);
        assertEquals("Head's sub should be best person", headSubPerson, hierarchy.getBestPerson(profile));

    }
}
