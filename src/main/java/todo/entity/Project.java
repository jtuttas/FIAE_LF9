package todo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

public class Project extends Entity {

    private String projectName="project";

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    @Override
    public String getCreateStatement() {        
        return "INSERT INTO project(name) VALUES (\""+this.getProjectName()+"\");";
    }

    @Override
    public String getUpdateStatement() {
        return "UPDATE project SET name=\""+this.getProjectName()+"\" WHERE projId="+this.getId()+";";
    }

    @Override
    public String getDeleteStatement() {
        return "DELETE FROM project WHERE projId="+this.getId()+";";
    }

    @Override
    public String getReadStatement() {
        return "SELECT * from project;";
    }

    @Override
    public void setEnity(ResultSet rs) throws SQLException {
        this.setId(rs.getInt("projId"));
        this.projectName = rs.getString("name");
    }

    @Override
    public String toJSON() {
        return "{\"id\":"+this.getId()+",\r\n\"name\":\""+this.getProjectName()+"\"}";
    }

    @Override
    public void parseJSON(String json) {
        JSONObject obj = new JSONObject(json);
        this.projectName=obj.getString("name");
        System.out.println("Set Projektname to "+this.projectName);
    }
    
}