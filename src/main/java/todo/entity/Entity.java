package todo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Entity {
    
    protected int id;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public abstract void setEnity(ResultSet rs) throws SQLException;
    public abstract String getCreateStatement();
    public abstract String getUpdateStatement();
    public abstract String getDeleteStatement();
    public abstract String getReadStatement();
    public abstract String toJSON();
    public abstract void parseJSON(String json);
    
}