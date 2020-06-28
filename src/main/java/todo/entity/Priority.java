package todo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

public class Priority extends Entity {
    private int priorityValue=0;
    private String priorityDescription;

    public int getPriorityValue() {
        return priorityValue;
    }
    public void setPriorityValue(int priorityValue) {
        this.priorityValue = priorityValue;
    }
    public String getPriorityDescription() {
        return priorityDescription;
    }
    public void setPriorityDescription(String priorityDescription) {
        this.priorityDescription = priorityDescription;
    }


    @Override
    public String getCreateStatement() {        
        return "INSERT INTO priority(value,description) VALUES ("+this.getPriorityValue()+",\""+this.getPriorityDescription()+"\");";
    }

    @Override
    public String getUpdateStatement() {
        return "UPDATE priority SET value="+this.getPriorityValue()+",description=\""+this.getPriorityDescription()+"\" WHERE id="+this.getId()+";";
    }

    @Override
    public String getDeleteStatement() {
        return "DELETE FROM priority WHERE id="+this.getId()+";";
    }

    @Override
    public String getReadStatement() {
        return "SELECT * from priority;";
    }

    @Override
    public void setEnity(ResultSet rs) throws SQLException {
        this.setId(rs.getInt("id"));
        this.priorityValue = rs.getInt("value");
        this.priorityDescription = rs.getString("description");
    }

    @Override
    public String toJSON() {
        return "{\"id\":"+this.getId()+",\r\n\"value\":"+this.getPriorityValue()+",\r\n\"description\":\""+this.getPriorityDescription()+"\"}";
    }

    @Override
    public void parseJSON(String json) {
        JSONObject obj = new JSONObject(json);
        this.priorityValue=obj.getInt("value");
        System.out.println("Set Priority to "+this.priorityValue);
        this.priorityDescription=obj.getString("description");
        System.out.println("Set Priority Description to "+this.priorityDescription);
    }
}