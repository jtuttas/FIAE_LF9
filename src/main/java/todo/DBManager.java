package todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manager for the SQLite Database as a Singelton
 */
public class DBManager {

    private Connection c;
    private static DBManager instance;

    /**
     * Connect to the SQLite Database
     * @param s The Filename of the Database
     */
    private DBManager(String s) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+s);
        } catch (ClassNotFoundException e) {
            System.out.println("Treiber nicht gefunden");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the Database Connection
     * @return Connection Objekt for the database
     */
    public Connection getConnection() {
        return c;
    }

    /**
     * Get the Instance of the DBManger
     * @param s Name of the SQLite Database
     * @return Instance Object
     */
    public static DBManager getInstance(String s) {
        if (instance!=null) {
            return instance;
        }
        instance = new DBManager(s);
        return instance;
    }
    
}