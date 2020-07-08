package todo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import todo.MissingParamaterException;

/**
 * Entity class for the Priority Entity
 */
public class Priority extends Entity {
    private int priorityValue=0;
    private String priorityDescription;

    /**
     * Returns the Value (rating) of the priority
     * @return the value
     */
    public int getPriorityValue() {
        return priorityValue;
    }
    /**
     * Sets the Priority (rating) of the priority
     * @param priorityValue the value
     */
    public void setPriorityValue(int priorityValue) {
        this.priorityValue = priorityValue;
    }

    /**
     * get the description of the priority
     * @return the description
     */
    public String getPriorityDescription() {
        return priorityDescription;
    }
    /**
     * set the desciption of the Priority
     * @param priorityDescription the priority
     */
    public void setPriorityDescription(String priorityDescription) {
        this.priorityDescription = priorityDescription;
    }


    /**
     * Return the INSERT statement for Priority
     */
    @Override
    public String getCreateStatement() {        
        return "INSERT INTO priority(value,description) VALUES ("+this.getPriorityValue()+",\""+this.getPriorityDescription()+"\");";
    }

    /**
     * Returns the UPDATE statement for the Priority
     */
    @Override
    public String getUpdateStatement() {
        return "UPDATE priority SET value="+this.getPriorityValue()+",description=\""+this.getPriorityDescription()+"\" WHERE id="+this.getId()+";";
    }

    /**
     * Returns the DELETE statement for the Priority
     */
    @Override
    public String getDeleteStatement() {
        return "DELETE FROM priority WHERE id="+this.getId()+";";
    }

    /**
     * Returns the SELECT Statement of the Priority
     */
    @Override
    public String getReadStatement() {
        return "SELECT * from priority;";
    }

    /**
     * Build the Priority from a ResultSet from the Database
     */
    @Override
    public void setEnity(ResultSet rs) throws SQLException {
        this.setId(rs.getInt("id"));
        this.priorityValue = rs.getInt("value");
        this.priorityDescription = rs.getString("description");
    }

    /**
     * Converts the Priority to a JSON String
     */
    @Override
    public String toJSON() {
        return "{\"id\":"+this.getId()+",\r\n\"value\":"+this.getPriorityValue()+",\r\n\"description\":\""+this.getPriorityDescription()+"\"}";
    }


    /**
     * Converts a JSON String to the Priority Object
     * 
     * @throws MissingParamaterException
     */
    @Override
    public Priority parseJSON(String json) throws MissingParamaterException {
        Priority p = new Priority();
        JSONObject obj = new JSONObject(json);
        if (obj.has("value")) {
            this.priorityValue=obj.getInt("value");
            p.setPriorityValue(this.priorityValue);
            System.out.println("Set Priority to "+this.priorityValue);
        }
        else {
            throw new MissingParamaterException("parameter value is missing");
        }
        if (obj.has("description")) {
            this.priorityDescription=obj.getString("description");
            p.setPriorityDescription(this.priorityDescription);
            System.out.println("Set Priority Description to "+this.priorityDescription);
        }
        if (obj.has("id")) {
            if (!obj.isNull("id")) {                
                this.setId(obj.getInt("id"));
                p.setId(this.getId());
            }

        }
        return p;
    }

    @Override
    public String toString() {        
        return "("+this.priorityValue+") "+this.priorityDescription;
    }
}