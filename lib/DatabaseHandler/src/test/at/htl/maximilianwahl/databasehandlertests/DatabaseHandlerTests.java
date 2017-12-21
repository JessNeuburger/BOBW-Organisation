package at.htl.maximilianwahl.databasehandlertests;



import at.htl.maximilianwahl.databasehandler.derbyDBConnectionHandler;
import org.junit.Assert.*;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;


public class DatabaseHandlerTests {


    @Test
    public void TestInitShouldReturnNoError() throws SQLException {
        derbyDBConnectionHandler handler = new derbyDBConnectionHandler();
        handler.openConnection();
        try{
            handler.init();
        } catch (SQLException e){
            e.printStackTrace();
        }

        assertTrue(true);
    }
}