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
        return null;
    }

    @Override
    public String getUpdateStatement() {
        return null;
    }

    @Override
    public String getDeleteStatement() {
        return null;
    }

    @Override
    public String getReadStatement() {
        return null;
    }
}