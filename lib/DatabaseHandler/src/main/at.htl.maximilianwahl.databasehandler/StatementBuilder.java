package at.htl.maximilianwahl.databasehandler;


import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author mexxw
 */
public class StatementBuilder {

    public static String createCreateStatement(String tableName,ArrayList<String> fields){
        String parameters = fields.stream().collect(Collectors.joining(","));
        String result = "CREATE TABLE " +tableName +" ("+parameters+")";
        return result;
    }
    public static String createInsertStatement(String tableName,ArrayList<String> values){
        String parameters = values.stream().collect(Collectors.joining(","));
        String result = "INSERT INTO "+tableName +" ("+parameters+")";
        return result;
    }
    public static String createSelectStatement(String tableName,String condition){
        return "SELECT * FROM "+tableName +" WHERE "+condition+"";
    }
    public static String createUpdateStatement(String tableName,String newValue,String condition){
        return "UPDATE "+tableName+" set "+newValue +" where "+condition+"";
    }
    public static String createDeleteStatement(String tableName,String condition){
        return "DELETE from "+tableName +" where "+condition+"";
    }
}