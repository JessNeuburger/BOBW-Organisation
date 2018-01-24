package bobworga.model;

import at.htl.florianschwarcz.organisationalstructurelib.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class Repository {
    private static Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private Hierarchy hierarchy;
    private ObservableList<Person> persons;
    private ObservableList<Job> jobs;

    private Repository() {
        hierarchy = new Hierarchy();
        persons = FXCollections.observableArrayList();
        jobs = FXCollections.observableArrayList();
    }

    public void loadData(){
        Person personA = createPerson("Max","Mustermann");
        Person personB = createPerson("Berta","Blaufisch");
        Person personC = createPerson("Carsten","Cloverfield");
        Person personD = createPerson("Daniel","Düsentrieb");
        Person personE = createPerson("Erik","Emmerling");
        Person personF = createPerson("Ferdinand","Faraday");
        Person personG = createPerson("Günther","Grobian");
        Person personH = createPerson("Heinrich","Heinling");

        Job master = createJob("Globalmaster",new Profile());
        Job sProgrammer = createJob("Senior Programmer",new Profile());
        Job jProgrammer = createJob("Junior Programmer",new Profile());
        Job teamLeader = createJob("Teammanager",new Profile());
        Job sales = createJob("Salesassistant",new Profile());


        Position ceo = new Position();
        Position teamSales = new Position();
        Position salesA = new Position();
        Position salesB = new Position();
        Position teamProg = new Position();
        Position seniorProg = new Position();
        Position juniorProgA = new Position();
        Position juniorProgB = new Position();

        ceo.setJob(master);
        teamSales.setJob(teamLeader);
        salesA.setJob(sales);
        salesB.setJob(sales);
        teamProg.setJob(teamLeader);
        seniorProg.setJob(sProgrammer);
        juniorProgA.setJob(jProgrammer);
        juniorProgB.setJob(jProgrammer);

        ceo.setPerson(personA);
        teamSales.setPerson(personB);
        salesA.setPerson(personC);
        salesB.setPerson(personD);
        teamProg.setPerson(personE);
        seniorProg.setPerson(personF);
        juniorProgA.setPerson(personG);
        juniorProgB.setPerson(personH);

        ceo.addSubordinate(teamSales);
        ceo.addSubordinate(teamProg);
        teamSales.addSubordinate(salesA);
        teamSales.addSubordinate(salesB);
        teamProg.addSubordinate(seniorProg);
        seniorProg.addSubordinate(juniorProgA);
        teamProg.addSubordinate(juniorProgB);

        hierarchy.setHead(ceo);
    }

    private Job createJob(String name, Profile profile) {
        Job j = new Job(name, profile);
        jobs.add(j);
        return j;
    }

    private Person createPerson(String firstname, String lastname){
        Person p = new Person(
                lastname,
                firstname,
                new Date(),
                "None",
                "Bla",
                "3",
                "Linz",
                "3020",
                "bla@gm.co",
                "123",
                new Profile());
        persons.add(p);
        return p;
    }

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public void deletePosition(Position position){
        System.out.println("Removing Position");
        Position sup = position.getSuperordinate();
        sup.getSubordinates().remove(position);
        sup.getSubordinates().addAll(position.getSubordinates());
        getHierarchy().invalidate();
    }

    public ObservableList<Person> getPersons() {
        return persons;
    }

    public ObservableList<Person> getUnemployed(){
        return getPersons().filtered(person -> person.getPosition() == null);
    }

    public ObservableList<Job> getJobs() {
        return jobs;
    }

    public List<Position> getPositions(){
        return hierarchy.getAllPositions();
    }

    public void move(Position position, Position newSuperordinate) {
        position.getSuperordinate().getSubordinates().remove(position);
        newSuperordinate.addSubordinate(position);
    }
}
