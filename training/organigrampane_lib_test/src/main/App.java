/*
 * Handling mouse events with event filters
 */

import at.htl.erikmayrhofer.organigrampane.OrganigramChildController;
import at.htl.erikmayrhofer.organigrampane.OrganigramLineController;
import at.htl.erikmayrhofer.organigrampane.OrganigramPane;
import at.htl.erikmayrhofer.organigrampane.PosPane;
import at.htl.florianschwarcz.organisationalstructurelib.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Date;

public final class App extends Application {

    @Override
    public void start(final Stage stage) {
        //OrganigramPane p = new OrganigramPane(new OrganigramChildController());
        OrganigramPane p = new OrganigramPane(new OrganigramLineController());

        Button b = new Button("Add");

        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(p);
        rootPane.setBottom(b);

        Hierarchy h = new Hierarchy();

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

        h.setHead(ceo);
        p.getController().setHierarchy(h);
        p.getController().setRootPosition(teamProg);

        Scene q = new Scene(rootPane, 400, 300);
        stage.setScene(q);
        stage.show();
    }

    private Person createPerson(String firstname, String lastname){
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
