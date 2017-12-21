package at.htl.maximilianwahl.databasehandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.derby.jdbc.EmbeddedDriver;

public class derbyDBConnectionHandler {

    private static String dbURL = "jdbc:derby:myDB;create=true;user=me;password=mine";
    private static Connection conn = null;
    private static Statement stmt = null;

    public void init() throws SQLException {
        ArrayList<String> fields = new ArrayList<>();
        fields.addAll(Arrays.asList("PersonId Int primary key","lastName Varchar(20)","firstName Varchar(20)","birthDate date","street varchar(20)","number int","city varchar(20)","zipCode char(4)","email varchar(20)","ssc char(10)"));
        runDDLStatement(StatementBuilder.createCreateStatement("Person",(ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("JobID int primary key","name varchar(20)"));
        runDDLStatement(StatementBuilder.createCreateStatement("Job", (ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("PositionId int primary key","PersonId int references Person","JobId int references Job","Subordinates int references Position","staffs int references Position"));
        runDDLStatement(StatementBuilder.createCreateStatement("Position", (ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("ProfileId int primary key","name varchar(20)"));
        runDDLStatement(StatementBuilder.createCreateStatement("Profile", (ArrayList<String>) fields));
        fields.clear();

        fields.addAll(Arrays.asList("ProfileId int references profile","JobId int references Job","PersonID references Person","value int"));
        runDDLStatement(StatementBuilder.createCreateStatement("Attribute", (ArrayList<String>) fields));
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
    public void runDDLStatement(String statement) throws SQLException {
        try
        {
            stmt = conn.createStatement();
            stmt.execute(statement);
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }


    public <T> ArrayList<T> runDMLStatement(String statement, Class<T> castTo){
        ArrayList<T> result = new ArrayList<T>();

        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(statement);
            ResultSetMetaData rsmd = results.getMetaData();


            while(results.next())
            {
                ///AUF FLO WARTEN
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return result;
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

