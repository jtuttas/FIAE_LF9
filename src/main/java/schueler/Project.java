package schueler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project
 */
public class Project extends Entity {

    public Project(String n) {
        super(n);
    }

    @Override
    public String getCreateStatement() {
        return "insert into project(name) values (" + getName() + ")";
    }

    @Override
    public String getUpdateStatement() {
        return "update project set name=" + getName() + " where id=" + id;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from project where id=" + id;
    }

    @Override
    public String getReadStatement() {
        return "select * from project";
    }

    public void setEntity(ResultSet rs) {
        try {
            this.setId(rs.getInt(1));
            this.setName(rs.getString(2));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}