package bobworga.Controller;

import at.htl.florianschwarcz.organisationalstructurelib.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class BoBwController implements Initializable{
    @FXML
    private BorderPane mainWindowPane;
    @FXML
    private HrList hrList;

    private static Hierarchy hierarchyInstance;

    public static Hierarchy getHierarchyInstance() {
        if(hierarchyInstance==null){
            hierarchyInstance = new Hierarchy();

            Person personA = createPerson("Max","Mustermann");
            Person personB = createPerson("Berta","Blaufisch");
            Person personC = createPerson("Carsten","Cloverfield");
            Person personD = createPerson("Daniel","Düsentrieb");
            Person personE = createPerson("Erik","Emmerling");
            Person personF = createPerson("Ferdinand","Faraday");
            Person personG = createPerson("Günther","Grobian");
            Person personH = createPerson("Heinrich","Heinling");

            Job master = new Job("Globalmaster",new Profile());
            Job sProgrammer = new Job("Senior Programmer",new Profile());
            Job jProgrammer = new Job("Junior Programmer",new Profile());
            Job teamLeader = new Job("Teammanager",new Profile());
            Job sales = new Job("Salesassistant",new Profile());

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

            hierarchyInstance.setHead(ceo);
        }
        return hierarchyInstance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

     }

    private static Person createPerson(String firstname, String lastname){
        return new Person(
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
    }
}
