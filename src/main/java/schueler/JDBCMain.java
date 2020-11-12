package schueler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteConfig;



public class JDBCMain {
    
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();  
            config.enforceForeignKeys(true);  
            Connection c = DriverManager.getConnection("jdbc:sqlite:todo_klein.db",config.toProperties());
            Statement st=c.createStatement();
            
Priority p = new Priority(5,"mittel wichtig");
// Anlegen eines Datensatzes
st.execute(p.getCreateStatement());
ResultSet rs = st.executeQuery("select * from priority order by id DESC limit 1");
while (rs.next()) {
    p.setId(rs.getInt(1));
}
// Ändern eines Datensatzes
p.setValue(10);
st.execute(p.getUpdateStatement());
// Löschen des Datensatzes
st.execute(p.getDeleteStatement());

            
        } catch (ClassNotFoundException e) {
            System.out.println("Treiber nicht gefunden");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
