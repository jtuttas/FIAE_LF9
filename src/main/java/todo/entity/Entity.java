package todo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Entity class for the Database
 */
public abstract class Entity {
    
    /**
     * id of the primary Key
     */
    protected int id;

    /**
     * Sets the primary KEY (id)
     * @param id the primary key
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the primary key
     * @return the primary key
     */
    public int getId() {
        return id;
    }

    /**
     * Converts the SQLResult Set to the given Entity
     * @param rs Database ResultSet
     * @throws SQLException something goes wrong
     */
    public abstract void setEnity(ResultSet rs) throws SQLException;

    /**
     * SQL Create Statement for the given Entity
     * @return sql statement INSERT INTO ...
     */
    public abstract String getCreateStatement();
    /**
     * SQL Update Statement for the given Entity
     * @return sql Statement UPDATE ....
     */
    public abstract String getUpdateStatement();

    /**
     * SQL delete Statement for the given Entity
     * @return sql statement DELETE FROM ....
     */
    public abstract String getDeleteStatement();

    /**
     * SQL read Statement for the given Entity
     * @return sql Statement SELECT * FROM ...
     */
    public abstract String getReadStatement();

    /**
     * Converts the given Entity to JSON formated String
     * @return the JSON formated String
     */
    public abstract String toJSON();

    /**
     * Converts a JSON formated String into the given Objekt
     * @param json the JSON String
     */
    public abstract void parseJSON(String json);
    
}