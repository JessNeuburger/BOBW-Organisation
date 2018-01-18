package at.htl.maximilianwahl.databasehandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import at.htl.florianschwarcz.organisationalstructurelib.*;
import org.apache.derby.jdbc.EmbeddedDriver;

public class derbyDBConnectionHandler {

    private static String dbURL = "jdbc:derby:myDB;create=true;user=me;password=mine";
    private static Connection conn = null;
    private static Statement stmt = null;
    private Hierarchy hierarchy = new Hierarchy();

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public void init() throws SQLException {
        ArrayList<String> fields = new ArrayList<>();
        fields.addAll(Arrays.asList("PersonId INTEGER","lastName Varchar(20)","firstName Varchar(20)","birthDate date","birthCity varchar(20)","street varchar(20)","number INTEGER","city varchar(20)","zipCode char(4)","email varchar(20)","ssc char(10)","PRIMARY KEY (PersonId)"));
        runDDLStatement(StatementBuilder.createCreateStatement("Person",(ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("JobID INTEGER","name varchar(20)","PRIMARY KEY (JobID)"));
        runDDLStatement(StatementBuilder.createCreateStatement("Job", (ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("PositionId INTEGER","PersonId INTEGER references Person","JobId INTEGER references Job","PRIMARY KEY (PositionID)"));
        runDDLStatement(StatementBuilder.createCreateStatement("Position", (ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("ProfileId INTEGER","name varchar(20)","PRIMARY KEY (ProfileId)"));
        runDDLStatement(StatementBuilder.createCreateStatement("Attribute", (ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("ProfileId INTEGER references attribute","JobId INTEGER references Job","PersonID INTEGER references Person","value INTEGER"));
        runDDLStatement(StatementBuilder.createCreateStatement("AttributeValue", (ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("master INTEGER references position","slave INTEGER references position","type char(10)"));
        runDDLStatement(StatementBuilder.createCreateStatement("Relation", (ArrayList<String>) fields));
        //retrieve all data
        //Persons
        ArrayList<Person> persons = getPersons();
        for(Person p:persons){
            Profile prof = new Profile();
            int id = p.getDBKey();
            HashMap<String,Integer> attributes = getPersonValues(id);
            for (String s: attributes.keySet() ) {
                prof.addAttribute(s,attributes.get(s));
            }
            p.setProfile(prof);
        }
        //Joberinos
        ArrayList<Job> jobs = getJobs();
        //
    }

    private ArrayList<Job> getJobs() throws SQLException {
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Job> res = new ArrayList<>();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("Select * from Job");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(rs.next()){
            ids.add(rs.getInt("JobId"));
            names.add(rs.getString("name"));
        }
        int i = 0;
        for (Integer id:ids){
            Profile prof = new Profile();
            HashMap<String,Integer> attributes = getJobValues(id);
            for (String s: attributes.keySet() ) {
                prof.addAttribute(s,attributes.get(s));
            }
            res.add(new Job(names.get(i++),prof));
        }
        return res;
    }

    private HashMap<String,Integer> getJobValues(Integer id) throws SQLException {
        HashMap<String,Integer> result= new HashMap<>();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("Select a.name,av.value from Attribute a,AttributeValue av WHERE av.JobId = a.JobId AND JobId = "+String.valueOf(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(rs.next()){
            result.put(rs.getString(1),rs.getInt(2));
        }
        return result;
    }

    private HashMap<String,Integer> getPersonValues(int id) throws SQLException {
        HashMap<String,Integer> result= new HashMap<>();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("Select a.name,av.value from Attribute a,AttributeValue av WHERE av.PersonID = a.PersonID AND PersonId = "+String.valueOf(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(rs.next()){
            result.put(rs.getString(1),rs.getInt(2));
        }
        return result;
    }

    private ArrayList<Person> getPersons() throws SQLException {
        ArrayList<Person> result = new ArrayList<>();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("Select * from Person");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(rs.next()){
            String lastName = rs.getString("lastName");
            String firstName = rs.getString("firstName");
            String street = rs.getString("street");
            String city = rs.getString("city");
            String birthCity = rs.getString("birthCity");
            String zipCode = rs.getString("zipCode");
            String email = rs.getString("email");
            String ssc = rs.getString("ssc");
            Date birth = rs.getDate("birthDate");
            String number = rs.getString("number");
            result.add(new Person(lastName,firstName,birth,birthCity,street,number,city,zipCode,email,ssc));
        }

        return result;
    }

    public void openConnection(){
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection(dbURL);
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
    private void runDDLStatement(String statement) throws SQLException {
        try
        {
            stmt = conn.createStatement();
            stmt.execute(statement);
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            System.out.println("AlreadyExists");
            sqlExcept.printStackTrace();
        }
    }


    public void closeConnection(){
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }
        }
        catch (SQLException sqlExcept)
        {

        }

    }
}

