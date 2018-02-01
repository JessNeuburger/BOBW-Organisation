package at.htl.maximilianwahl.databasehandler;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import at.htl.florianschwarcz.organisationalstructurelib.*;
import org.apache.derby.jdbc.EmbeddedDriver;

public class derbyDBConnectionHandler {

    private static String dbURL = "jdbc:derby:myDB;create=true;user=me;password=mine";
    private static Connection conn = null;
    private static Statement stmt = null;
    private Hierarchy hierarchy = new Hierarchy();
    private ArrayList<Person> persons;
    private ArrayList<Job> jobs;
    private ArrayList<Position> positions;

    public ArrayList<Person> getPersons() {
        return persons;
    }
    public ArrayList<Job> getJobs() {
        return jobs;
    }
    public ArrayList<Position> getPositions() {
        return positions;
    }
    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public derbyDBConnectionHandler() {
        try {
            init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void init() throws SQLException {
        openConnection();
        ArrayList<String> fields = new ArrayList<>();
        fields.addAll(Arrays.asList("PersonId INTEGER","lastName Varchar(20)","firstName Varchar(20)","birthDate date","birthCity varchar(20)","street varchar(20)","number varchar(20)","city varchar(20)","zipCode char(4)","email varchar(20)","ssc char(10)","PRIMARY KEY (PersonId)"));
        runDDLStatement(StatementBuilder.createCreateStatement("Person",(ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("JobID INTEGER","name varchar(20)","PRIMARY KEY (JobID)"));
        runDDLStatement(StatementBuilder.createCreateStatement("Job", (ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("PositionId INTEGER","PersonId INTEGER references Person","JobId INTEGER references Job","PRIMARY KEY (PositionID)"));
        runDDLStatement(StatementBuilder.createCreateStatement("Position", (ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("AttributeId INTEGER","name varchar(20)","PRIMARY KEY (ProfileId)"));
        runDDLStatement(StatementBuilder.createCreateStatement("Attribute", (ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("AttributeId INTEGER references attribute","JobId INTEGER references Job","PersonID INTEGER references Person","value INTEGER"));
        runDDLStatement(StatementBuilder.createCreateStatement("AttributeValue", (ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("master INTEGER references position","slave INTEGER references position","type char(10)"));
        runDDLStatement(StatementBuilder.createCreateStatement("Relation", (ArrayList<String>) fields));
        //retrieve all data
        //Persons
        persons = getPersonsDB();
        for(Person p:persons){
            Profile prof = new Profile();
            int id = p.getDBKey();
            HashMap<String,Integer> attributes = getPersonValues(id);
            for (String s: attributes.keySet() ) {
                prof.addAttribute(s,attributes.get(s));
            }
            p.setProfile(prof);
        }
        //Jobs
        jobs = getJobsDB();
        //Positions
        positions = getPosDB(jobs,persons);
        for (Position pos:positions) {
            pos.addSubordinates(getSubordinates(pos,positions));
            pos.addStaff(getStaff(pos,positions));
        }
        hierarchy.setHead(positions.stream().filter((p)->p.getSuperordinate() == null).findFirst().get());
    }
    private List<Staff> getStaff(Position pos, ArrayList<Position> positions) throws SQLException {
        int id = pos.hashCode();
        ArrayList<Staff> res = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("Select slave from Relation where master ="+id+"AND type = 'staff'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(rs.next()){
            try {
                int slave =rs.getInt("slave");
                res.add((Staff)(positions.stream().filter((p)->p.hashCode() ==slave ).findFirst().get()));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return res;
    }
    private List<Position> getSubordinates(Position pos,ArrayList<Position> positions) throws SQLException {
        int id = pos.hashCode();
        ArrayList<Position> res = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("Select slave from Relation where master ="+id+"AND type = 'subordinate'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(rs.next()){
            try {
                int slave =rs.getInt("slave");
                res.add(positions.stream().filter((p)->p.hashCode() ==slave ).findFirst().get());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return res;
    }
    private ArrayList<Position> getPosDB(ArrayList<Job> jobs,ArrayList<Person> persons) throws SQLException {
        ArrayList<Position> res = new ArrayList<>();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("Select * from Position");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(rs.next()){
            try {
                int jobId = rs.getInt("jobId");
                int personId= rs.getInt("PersonId");
                Position pos =new Position();
                pos.setJob(jobs.stream().filter((job) -> jobId ==job.hashCode() ).findFirst().get());
                pos.setPerson(persons.stream().filter((person)->personId==person.getDBKey()).findFirst().get());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return res;
    }
    private ArrayList<Integer> getPosIds() throws SQLException {
        ArrayList<Integer> ids = new ArrayList<>();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("Select PositionId from Position");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(rs.next()){
            try {
                ids.add(rs.getInt(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return ids;
    }
    private ArrayList<Job> getJobsDB() throws SQLException {
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
    private ArrayList<Person> getPersonsDB() throws SQLException {
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
    private int getKeyFromAttribute(String key) {
        Integer[] keys = new Integer[1];
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select AttributeId from Attribute where name ="+"'"+key+"'");

            while(results.next())
            {
                int id = results.getInt(1);
                keys[0] = id;
                //should only be one
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return keys[0].intValue();
    }

    private void openConnection(){
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
    private void commit() throws SQLException {
        conn.commit();
    }

    //data saving
    private void savePerson(Person p){
       java.sql.Date date = new java.sql.Date(p.getBirthDate().getTime());
        try {
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO Person values " +
                    "("+p.getDBKey()+",'"
                    +p.getLastName()+"','"
                    +p.getFirstName()+"','"
                    +date.toString()+"','"
                    +p.getBirthCity()+"','"
                    +p.getStreet()+"','"
                    +p.getNumber()+"','"
                    +p.getCity()+"','"
                    +p.getZipCode()+"','"
                    +p.getEmail()+"','"
                    +p.getSocialSecurityNumber()+"')");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //TODO save his profile
        Profile prof = p.getProfile();
        for (Map.Entry<String, Integer> entry : prof.getAttributes().entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            int attribute = getKeyFromAttribute(key);
            try {
                stmt = conn.createStatement();
                stmt.execute("INSERT INTO AttributeValue values " +
                        "("+attribute+","
                        +"null"+","
                        +p.getDBKey()+","
                        +value+")");
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void saveRelation(Position p){
        for (Staff s:p.getStaff()) {
            try {
                stmt = conn.createStatement();
                stmt.execute("INSERT INTO Relation values " +
                        "("+p.hashCode()+","
                        +s.hashCode()+",'staff')");
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (Position pos:p.getSubordinates()) {
            try {
                stmt = conn.createStatement();
                stmt.execute("INSERT INTO Relation values " +
                        "("+p.hashCode()+","
                        +pos.hashCode()+",'subordinate')");
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void saveJob(Job j){
        try {
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO Job values " +
                    "("+j.hashCode()+",'"
                    +j.getName()+"')");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //TODO SAVE HIS PROFILE
        Profile prof = j.getProfile();
        for (Map.Entry<String, Integer> entry : prof.getAttributes().entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            int attribute = getKeyFromAttribute(key);
            try {
                stmt = conn.createStatement();
                stmt.execute("INSERT INTO AttributeValue values " +
                        "("+attribute+","
                        +j.hashCode()+","
                        +"null"+","
                        +value+")");
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void savePosition(Position p){
        try {
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO Position values " +
                    "("+p.hashCode()+","
                    +p.getPerson().getDBKey()+","+
                    +p.getJob().hashCode()+")");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void newAttribute(String name){
        try {
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO Attribute values " +
                    "("+name.hashCode()+",'"
                    +name+"')");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void save(List<Position> pos,List<Person> per,List<Job> jobs){
        truncate();
        for (Person p:per){
            savePerson(p);
        }
        for (Job j:jobs) {
            saveJob(j);
        }
        for (Position p:pos) {
            savePosition(p);
        }
        for (Position p:pos) {
            saveRelation(p);
        }
    }
    private void truncate() {
        truncateTable("Person");
        truncateTable("Job");
        truncateTable("Position");
        truncateTable("AttributeValue");
        truncateTable("Relation");
    }
    private void truncateTable(String table) {
        try {
            stmt = conn.createStatement();
            stmt.execute("TRUNCATE TABLE "+table);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

