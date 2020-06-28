package todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private String dbname;
    private Connection c;
    private static DBManager instance;

    private DBManager(String s) {
        this.dbname = s;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+s);
        } catch (ClassNotFoundException e) {
            System.out.println("Treiber nicht gefunden");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return c;
    }

    public static DBManager getInstance(String s) {
        if (instance!=null) {
            return instance;
        }
        instance = new DBManager(s);
        return instance;
    }
    
}